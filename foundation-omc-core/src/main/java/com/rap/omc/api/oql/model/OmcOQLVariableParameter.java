package com.rap.omc.api.oql.model;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;
import com.rap.omc.api.oql.utility.OmcUtility;

public class OmcOQLVariableParameter extends OmcOQLRoot{
    private String  parameterName;
    private String  parameterValue;
    private boolean isFunction;
    private boolean isDateConvert = false;
    
	
    public boolean isDateConvert(){
        return isDateConvert;
    }
    
    public void setDateConvert(boolean isDateConvert){
        this.isDateConvert = isDateConvert;
    }
    public OmcOQLVariableParameter() {
		super();
	}
	public OmcOQLVariableParameter(String parameterName, String parameterValue,
			boolean isFunction) {
		super();
		this.parameterName = parameterName;
		this.parameterValue = parameterValue;
		this.isFunction = isFunction;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	public boolean isFunction() {
		return isFunction;
	}
	public void setFunction(boolean isFunction) {
		this.isFunction = isFunction;
	}
	public boolean isRightArribute() {
	    return(OmcUtility.isArributeFormat(this.parameterValue));
    }
}
