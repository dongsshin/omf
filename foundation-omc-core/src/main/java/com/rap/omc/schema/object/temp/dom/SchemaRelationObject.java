package com.rap.omc.schema.object.temp.dom;

import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.object.temp.model.SchemaRelationObjectVO;
import com.rap.omc.schema.serviceutil.SchemaServiceUtil;
import com.rap.omc.util.NullUtil;

public class SchemaRelationObject{
    SchemaRelationObjectVO vo;
    public SchemaRelationObject(){
    }
    public SchemaRelationObject(SchemaRelationObjectVO vo){
        this.vo = vo;
    }
    public SchemaRelationObject(String obidOrName){
        if (NullUtil.isNone(obidOrName)){
            throw new FoundationException("api.object.warn.obid");
        }
        try{
            SchemaRelationObjectVO result = SchemaServiceUtil.getRelationObjectClass(obidOrName);
            if (NullUtil.isNull(result)){
                throw new FoundationException("api.object.warn.obid");
            }
            this.vo = result;
        }
        catch (ClassCastException e){
            throw new FoundationException("api.object.error.class");
        }
    }
    public void setVo(SchemaRelationObjectVO vo){
        this.vo = vo;
    }
    public SchemaRelationObjectVO getVo(){
        return this.vo;
    }
    /*
    public void createBusinessObjectClass(){
        SchemaServiceUtil.createBusinessObjectClass(getVo());
    }
    public void inactiveBusinessObjectClass(){
        SchemaServiceUtil.inactiveBusinessObjectClass(getVo().getObid());
    }
    */
}