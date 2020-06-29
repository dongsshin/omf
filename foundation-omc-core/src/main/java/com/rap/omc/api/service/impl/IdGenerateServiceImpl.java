package com.rap.omc.api.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.service.IdGenerateService;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.schema.util.OmcUniqueIDGenerator;
import com.rap.omc.util.NullUtil;

@Service("idGenerateService")
public class IdGenerateServiceImpl implements IdGenerateService {

    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generateUniqueName(String idKey){
        if (NullUtil.isNone(idKey)) { return null; }
        if (idKey.equals(GlobalConstants.IDGEN_IDKEY_OBID)) { return OmcUniqueIDGenerator.getObid(); }
        HashMap<String,String> input = new HashMap<String, String>();
        input.put("idKey", idKey);
        String str  = schemaDao.select("Id.getIdGenerator", input);
        return str;
    }
    public String[] generateUniqueNameSet(int wantedCount){
        Set<String> obidSet = new HashSet<String>();
        while(obidSet.size() < wantedCount){
            obidSet.add(OmcUniqueIDGenerator.getObid());
        }
        return StrUtil.convertSet2Array(obidSet);
    }
}

