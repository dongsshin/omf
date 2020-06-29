package com.rap.omc.foundation.common.model;

public class OmcCompareResult {
    private Object classKind;
    private Object value1;
    private Object value2;
    private int compareResult;
    
    
    public Object getClassKind(){
        return classKind;
    }

    
    public void setClassKind(Object classKind){
        this.classKind = classKind;
    }

    public Object getValue1(){
        return value1;
    }
    
    public void setValue1(Object value1){
        this.value1 = value1;
    }
    
    public Object getValue2(){
        return value2;
    }
    
    public void setValue2(Object value2){
        this.value2 = value2;
    }
    
    public int getCompareResult(){
        return compareResult;
    }
    
    public void setCompareResult(int compareResult){
        this.compareResult = compareResult;
    }

    public OmcCompareResult(Object  classKind, Object value1, Object value2,int compareResult) {
        super();
        this.classKind = classKind;
        this.value1 = value1;
        this.value2 = value2;
        this.compareResult = compareResult;
    }
    public OmcCompareResult() {
        super();
    }
}
