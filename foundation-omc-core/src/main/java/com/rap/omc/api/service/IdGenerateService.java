package com.rap.omc.api.service;

public interface IdGenerateService {
    public String[] generateUniqueNameSet(int wantedCount);
    public String generateUniqueName(String idKey);
}
