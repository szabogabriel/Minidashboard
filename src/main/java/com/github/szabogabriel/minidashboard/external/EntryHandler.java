package com.github.szabogabriel.minidashboard.external;

import com.github.szabogabriel.minidashboard.data.dto.DataEntryDto;
import com.github.szabogabriel.minidashboard.data.entites.DataEntryEntity;

public interface EntryHandler {

    DataEntryDto handle(DataEntryEntity entity);
    
}
