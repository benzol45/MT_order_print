package com.example.mt_orderandprintworkspace.service;

import com.example.mt_orderandprintworkspace.entity.CatalogState;
import com.example.mt_orderandprintworkspace.repository.CatalogStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CatalogStateService {
    private final CatalogStateRepository catalogStateRepository;

    @Autowired
    private CatalogStateService(CatalogStateRepository catalogStateRepository) {
        this.catalogStateRepository = catalogStateRepository;
    }

    public LocalDateTime getCatalogActualizeDate() {
        List<CatalogState> catalogStates = catalogStateRepository.findAll();
        if (catalogStates.size()>1) {
            throw new IllegalStateException("Incorrect state. We have a lot of records of system state");
            //TODO возможно есть мысл получить максимальное, записать его а остальные удалить, мало ли почему они появились
        } else if (catalogStates.size()==0) {
            return LocalDateTime.MIN;
        } else {
            return catalogStates.get(0).getActualizeDateTime();
        }
    }

    public void setCatalogActualizeDateNow() {
        List<CatalogState> catalogStates = catalogStateRepository.findAll();
        if (catalogStates.size()>1) {
            throw new IllegalStateException("Incorrect state. We have a lot of records of system state");
            //TODO возможно есть смысл получить максимальное, записать его а остальные удалить, мало ли почему они появились
        } else if (catalogStates.size()==0) {
            CatalogState catalogState = new CatalogState();
            catalogState.setActualizeDateTime(LocalDateTime.now());
            catalogStateRepository.save(catalogState);
        } else {
            CatalogState catalogState = catalogStates.get(0);
            catalogState.setActualizeDateTime(LocalDateTime.now());
            catalogStateRepository.saveAndFlush(catalogState);
        }
    }
}
