package com.rap.omc.api.oql.model;


public class OmcOQLCondition {
    private String leftValue;
    private String rightValue;
    private String operator;
    
    public OmcOQLCondition(String leftValue, String rightValue, String operator) {
        super();
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.operator = operator;
    }

    public String getLeftValue(){
        return leftValue;
    }
    
    public String getRightValue(){
        return rightValue;
    }

    public String getOperator(){
        return operator;
    }

}
