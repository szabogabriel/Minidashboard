package com.github.szabogabriel.minidashboard.handler;

import com.github.szabogabriel.minidashboard.data.dto.DataEntryDto;
import com.github.szabogabriel.minidashboard.data.entites.DataEntryEntity;

public class DefaultEntryHandler implements EntryHandler {

    @Override
    public DataEntryDto handle(DataEntryEntity entity) {
        DataEntryDto ret = new DataEntryDto();

        ret.setCategory(entity.getCategory());
        ret.setCreateTimestamp(entity.getCreateTimestamp());
        ret.setDomain(entity.getDomain().getName());
        ret.setEntry(entity.getEntry());
        ret.setLastModified(entity.getLastModified());
        ret.setLevel0(entity.getLevel0());
        ret.setLevel1(entity.getLevel1());
        ret.setLevel2(entity.getLevel2());
        ret.setLevel3(entity.getLevel3());
        ret.setLevel4(entity.getLevel4());
        ret.setLevel5(entity.getLevel5());
        ret.setLevel6(entity.getLevel6());
        ret.setLevel7(entity.getLevel7());
        ret.setValidUntil(entity.getValidUntil());
        ret.setVersion(entity.getVersion());

        return ret;
    }
    
}
