package com.github.szabogabriel.minidashboard.handler;

import com.github.szabogabriel.minidashboard.data.dto.DataEntryDto;
import com.github.szabogabriel.minidashboard.data.entites.DataEntryEntity;

public class DefaultEntryHandler implements EntryHandler {

    @Override
    public DataEntryDto handle(DataEntryEntity entity) {
        return map(entity);
    }
    
}
