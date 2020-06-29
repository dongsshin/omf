/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : IdGeneratorInfo.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 22. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.model;

/**
 * <pre>
 * Class : IdGeneratorInfo
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class IdGeneratorInfo {

    private long currentNumber;

    private String prefix;

    private String suffix;

    private int length;
    
    private String format;

    /**
     * 
     * 
     * @return the currentNumber
     */
    public long getCurrentNumber(){
        return currentNumber;
    }

    /**
     * 
     * 
     * @param currentNumber the currentNumber to set
     */
    public void setCurrentNumber(long currentNumber){
        this.currentNumber = currentNumber;
    }

    /**
     * 
     * 
     * @return the prefix
     */
    public String getPrefix(){
        return prefix;
    }

    /**
     * 
     * 
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix){
        this.prefix = prefix;
    }

    /**
     * 
     * 
     * @return the suffix
     */
    public String getSuffix(){
        return suffix;
    }

    /**
     * 
     * 
     * @param suffix the suffix to set
     */
    public void setSuffix(String suffix){
        this.suffix = suffix;
    }

    /**
     * 
     * 
     * @return the length
     */
    public int getLength(){
        return length;
    }

    /**
     * 
     * 
     * @param length the length to set
     */
    public void setLength(int length){
        this.length = length;
    }

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
