/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : HttpEntityUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 3. 19.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.omc.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


/**
 * <pre>
 * Class : HttpEntityUtil
 * Description : TODO
 * </pre>
 * 
 * @author jongjung.kwon
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class HttpEntityUtil {
    
    private HttpHeaders headers;
    private MultiValueMap<String,Object> formData;
    private HttpEntity entity;
     
    public HttpEntityUtil(){
        
        this.headers= new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
       // headers.set("Accept","application/json");
        headers.set("X-Requested-With", "XMLHttpRequest");
        headers.set("Pdm-Server-Name", "VC-GPDM");
        
        this.formData = new LinkedMultiValueMap<String,Object>();
        
    }
    
    public void setHeaderContentType(MediaType mediaType){
        headers.setContentType(mediaType);        
    }
    
    public void addHeader(String headerName, String headerValue){
        headers.set(headerName, headerValue);        
    }
    
    public void addFormData(String key, Object value){
        this.formData.set(key, value);
        
    }
    
    public void setHttpEntity(){
        this.entity = new HttpEntity(this.formData, this.headers);
    }
    
    public HttpEntity getHttpEntity(){
        return this.entity;
    }
    

}
