package com.rap.omc.schema.object.temp.dom;

import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.object.temp.model.SchemaClassRootVO;
import com.rap.omc.schema.object.temp.model.SchemaKeyTableVO;
import com.rap.omc.schema.serviceutil.SchemaServiceUtil;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;


public class SchemaClassRoot{
    SchemaClassRootVO vo;
    public SchemaClassRoot(){
    }
    public SchemaClassRoot(SchemaClassRootVO vo){
        this.vo = vo;
    }
    public SchemaClassRoot(String obid){
        if (NullUtil.isNone(obid)){
            throw new FoundationException("api.object.warn.obid");
        }
        try{
            SchemaKeyTableVO keyTableVO = SchemaServiceUtil.getSchemKeyTable(obid);
            if (NullUtil.isNull(keyTableVO)){
                throw new FoundationException("api.object.warn.obid");
            }
            if (keyTableVO.getKinds() == OmcSystemConstants.SYSKEY_KIND_BOClass)
                this.vo = SchemaServiceUtil.getBusienssObjectClass(keyTableVO.getObid());
            else if(keyTableVO.getKinds() == OmcSystemConstants.SYSKEY_KIND_ROClass){
                this.vo = SchemaServiceUtil.getRelationObjectClass(keyTableVO.getObid());
            }else{
                throw new FoundationException("api.object.error.class");
            }
        }
        catch (ClassCastException e){
            throw new FoundationException("api.object.error.class");
        }
    }
    public void setVo(SchemaClassRootVO vo){
        this.vo = vo;
    }
    public SchemaClassRootVO getVo(){
        return this.vo;
    }
}