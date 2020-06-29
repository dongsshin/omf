package com.rap.omc.schema.object.temp.dom;

import java.util.List;

import com.rap.omc.schema.object.temp.model.SchemaBusinessObjectVO;
import com.rap.omc.schema.serviceutil.SchemaServiceUtil;

public class SchemaBusinessObject extends SchemaClassRoot{
    SchemaBusinessObjectVO vo;
    public SchemaBusinessObject(){
    }
    public SchemaBusinessObject(SchemaBusinessObjectVO vo){
        super(vo);
    }
    public SchemaBusinessObject(String obid){
        super(obid);
    }
    public List<SchemaBusinessObjectVO> getBusienssObjectClassAll(){
            return(SchemaServiceUtil.getBusienssObjectClassAll());
    }
    public void createBusinessObjectClass(){
        SchemaServiceUtil.createBusinessObjectClass((SchemaBusinessObjectVO)getVo());
    }
    public void inactiveBusinessObjectClass(){
        SchemaServiceUtil.inactiveBusinessObjectClass(getVo().getObid());
    }
}