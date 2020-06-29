/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rap.omc.schema.object.temp.model;
/**
 *
 * @author dongsik.shin
 */
public class SchemaKeyTableVO {
    private String obid;
    private int kinds;
    
    public String getObid(){
        return obid;
    }
    
    public int getKinds(){
        return kinds;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
    public void setKinds(Integer kinds){
        this.kinds = kinds;
    }
    
}
