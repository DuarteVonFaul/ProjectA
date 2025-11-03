package com.sge.boavista.Services.workflow.ConciliacaoWorkFlow;

import com.sge.boavista.Entities.DTO.SGE.ConcProssDTO;
import com.sge.boavista.Entities.Message;
import com.sge.boavista.Repositories.TranstefRepository;

public class SearchTranstefService {

    private TranstefRepository repository;

    public SearchTranstefService(TranstefRepository repository){
        this.repository = repository;
    }

    public Message Execute(ConcProssDTO process){
        return  this.repository.findSales(process);
    }


}
