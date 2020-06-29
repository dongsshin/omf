package com.rap.omc.api.oql.model;

import java.util.HashMap;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;

public class OmcOQLConvertedParmValue extends OmcOQLRoot{
    private String                 strOrigin      ;
    private String                 strConverted   ;
	private String                 dataKey        ;
	private String                 addedChar      ;
	private String                 from           ;
	private String                 to             ;
	private int                    convertedCount ;
	private HashMap<String,String> parm           ;
	private String                 attachedType   ;
	public OmcOQLConvertedParmValue() {
		super();
	}
    /**
     * @param strOrigin
     * @param strConverted
     * @param dataKey
     * @param addedChar
     * @param from
     * @param to
     * @param convertedCount
     * @param parm
     * @param attachedType
     */
    public OmcOQLConvertedParmValue(String strOrigin, String strConverted, String dataKey, String addedChar,
            String from, String to, int convertedCount, HashMap<String, String> parm, String attachedType) {
        super();
        this.strOrigin = strOrigin;
        this.strConverted = strConverted;
        this.dataKey = dataKey;
        this.addedChar = addedChar;
        this.from = from;
        this.to = to;
        this.convertedCount = convertedCount;
        this.parm = parm;
        this.attachedType = attachedType;
    }
    
    public String getStrOrigin(){
        return strOrigin;
    }
    
    public String getStrConverted(){
        return strConverted;
    }
    
    public String getDataKey(){
        return dataKey;
    }
    
    public String getAddedChar(){
        return addedChar;
    }
    
    public String getFrom(){
        return from;
    }
    
    public String getTo(){
        return to;
    }
    
    public int getConvertedCount(){
        return convertedCount;
    }
    
    public HashMap<String, String> getParm(){
        return parm;
    }
    
    public String getAttachedType(){
        return attachedType;
    }
    
    public void setStrOrigin(String strOrigin){
        this.strOrigin = strOrigin;
    }
    
    public void setStrConverted(String strConverted){
        this.strConverted = strConverted;
    }
    
    public void setDataKey(String dataKey){
        this.dataKey = dataKey;
    }
    
    public void setAddedChar(String addedChar){
        this.addedChar = addedChar;
    }
    
    public void setFrom(String from){
        this.from = from;
    }
    
    public void setTo(String to){
        this.to = to;
    }
    
    public void setConvertedCount(int convertedCount){
        this.convertedCount = convertedCount;
    }
    
    public void setParm(HashMap<String, String> parm){
        this.parm = parm;
    }
    
    public void setAttachedType(String attachedType){
        this.attachedType = attachedType;
    }
	
}
