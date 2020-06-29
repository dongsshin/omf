/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : PdmXssValidationUtil.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2015. 5. 12.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.omc.framework.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <pre>
 * Class : PdmXssValidationUtil
 * Description : TODO
 * </pre>
 *
 * @author jongjung.kwon
 */
public class PdmXssValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdmXssValidator.class);

    // -------
    private HtmlTagValidator htmlTagValidator;

    public void setHtmlTagValidator(HtmlTagValidator htmlTagValidator){
        this.htmlTagValidator = htmlTagValidator;
    }

    // -------
    private Map<String, Set<String>> skipImagePatternMap = new HashMap<String, Set<String>>();

    public void setSkipImages(Map<String, String> skipImages) {

        for( Map.Entry<String, String> e : skipImages.entrySet() ) {
            Set<String> set = new HashSet<String>();
            for( String ele : e.getValue().split(",") ) {
                set.add( ele.trim() );
            }
            this.skipImagePatternMap.put(e.getKey(), set);
        }
    }

    // -------
    public boolean checkXSSJsonObject(String path, JSONObject obj){

        Iterator itr = obj.keys();
        String keyAttribute = null;
        boolean resultFlag = true;

        while(itr.hasNext()){

            keyAttribute = (String)itr.next();
            Object detailObj = obj.get(keyAttribute);

            if(detailObj instanceof JSONObject){
                resultFlag = checkXSSJsonObject(path, JSONObject.fromObject(detailObj));
            }else if(detailObj instanceof JSONArray){
                resultFlag = checkXSSJsonArray(path, JSONArray.fromObject(detailObj));
            }else{
                if ( this.skipImagePatternMap.containsKey(path) && this.skipImagePatternMap.get(path).contains(keyAttribute)) {
                    LOGGER.debug("XSS checkXSSJsonObject true, path = {}, KEY = {}, value = {}", new String[] { path, keyAttribute, String.valueOf(detailObj)} );
                    resultFlag = htmlTagValidator.validate( String.valueOf(detailObj) );
                } else if (!PdmValidationUtil.checkXSS(String.valueOf(detailObj))) {
                    LOGGER.info("XSS Detect checkXSSJsonObject false, path = {}, KEY = {}, value = {}", new String[] { path, keyAttribute, String.valueOf(detailObj)} );
                    resultFlag= false;
                }
            }

            if(!resultFlag){
                return false;
            }

        }

        return true;
    }

    public boolean checkXSSJsonArray(String path, JSONArray obj){

        boolean resultFlag = true;

        for(int inx=0; inx < obj.size(); inx++){
            Object detailObj = obj.get(inx);

            if(detailObj instanceof JSONObject){
                resultFlag = checkXSSJsonObject(path, JSONObject.fromObject(detailObj));
            }else if(detailObj instanceof JSONArray){
                resultFlag = checkXSSJsonArray(path, JSONArray.fromObject(detailObj));
            }else{
                LOGGER.warn("XSS checkXSSJsonArray :" + String.valueOf(detailObj));
                if (!PdmValidationUtil.checkXSS(String.valueOf(detailObj))) {
                    resultFlag = false;
                }
            }

            if(!resultFlag){
                return false;
            }
        }

        return true;
    }
}
