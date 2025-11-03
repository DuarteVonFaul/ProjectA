package com.sge.boavista.Services.workflow.ConciliacaoWorkFlow;

import com.sge.boavista.Entities.Message;
import com.sge.boavista.Repositories.TranstefRepository;

public class SearchDateStoryService {


    private TranstefRepository repository;

    public SearchDateStoryService(TranstefRepository repository){
        this.repository = repository;
    }


    public Message Execute(String dateCheckpoint, String dateFinal){
        return repository.findDatesToConciliar(dateCheckpoint,dateFinal);

    }

}
