/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : Focus.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 7. 28.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.model;

import java.util.Arrays;
import java.util.List;


/**
 * <pre>
 * Class : Focus
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcForDynamicLoop {
    String uniqueValue;
    List<String> values;

    public String getUniqueValue(){
        return uniqueValue;
    }
    public void setUniqueValue(String uniqueValue){
        this.uniqueValue = uniqueValue;
    }
    public List<String> getValues(){
        return values;
    }
    public void setValues(List<String> values){
        this.values = values;
    }
    public OmcForDynamicLoop() {
        super();
    }
    public OmcForDynamicLoop(String uniqueValue, String... values) {
        this.uniqueValue = uniqueValue;
        this.values = Arrays.asList(values);
    }
    public OmcForDynamicLoop(String uniqueValue, List<String> values) {
        this.uniqueValue = uniqueValue;
        this.values = values;
    }

    
}
