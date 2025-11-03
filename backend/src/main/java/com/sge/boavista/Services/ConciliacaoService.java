package com.sge.boavista.Services;

import com.sge.boavista.Entities.DTO.SGE.ConcProssDTO;
import com.sge.boavista.Entities.DTO.SGE.VendaDTO;
import com.sge.boavista.Entities.Message;
import com.sge.boavista.Repositories.TranstefRepository;
import com.sge.boavista.Services.workflow.ConciliacaoWorkFlow.SearchDateStoryService;
import com.sge.boavista.Services.workflow.ConciliacaoWorkFlow.SearchTranstefService;

import java.util.ArrayList;
import java.util.List;

public class ConciliacaoService {

    private TranstefRepository repository;

    public ConciliacaoService(){
        repository = new TranstefRepository();
    }


    public List<VendaDTO> Conciliacao(String dateCheckpoint, String dateFinal){
        Message reponse = new SearchDateStoryService(repository).Execute(dateCheckpoint,dateFinal);
        SearchTranstefService service = new SearchTranstefService(repository);
        List<VendaDTO> resposta = new ArrayList<> ();
        if(reponse.sucess){
            List<ConcProssDTO> list = (List<ConcProssDTO>) reponse.getMessage();
            list.forEach( process -> {
                Message conciliacao = service.Execute(process);
                resposta.addAll((List<VendaDTO>) conciliacao.getMessage());
            });

            return resposta;

        }
        else{
            return null;
        }
    }




}
