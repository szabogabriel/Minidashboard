package com.github.szabogabriel.minidashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.szabogabriel.minidashboard.data.dto.DataEntryDto;
import com.github.szabogabriel.minidashboard.data.entites.DataEntryEntity;
import com.github.szabogabriel.minidashboard.external.EntryHandler;

@Service
public class EntryHandlerService {

    @Autowired
    private ConfigService configService;

    public DataEntryDto handleDataEntry(DataEntryEntity toBeHandled) {
        EntryHandler handler = getEntryHandler(toBeHandled.getDomain().getName(), toBeHandled.getCategory(),
                toBeHandled.getEntry());

        return handler.handle(toBeHandled);
    }

    public EntryHandler getEntryHandler(String domain, String category, String entry) {
        // TODO: create a default and load from config the configured ones.
        return (entity) -> null;
    }

}
