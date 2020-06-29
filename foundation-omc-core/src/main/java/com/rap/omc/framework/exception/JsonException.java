package com.rap.omc.framework.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


public class JsonException extends BaseException {
    
    private static final long serialVersionUID = -6108327756860350046L;
    
    private String code = "fail";
    private String message;
    private String stack;
 
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonException.class);
 
     public JsonException(Exception ex) {
        this.message = ex.getMessage();
        
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        ex.printStackTrace();
        this.stack =  sw.toString();
    }
 
    /**
     * @param fromObject
     */
    public JsonException(String message, String stack) {
        this.message = message;
        this.stack = stack;
    }

    /**
     * @param jsonObject
     */
    public JsonException(JSONObject jsonObject) {
        this.message = jsonObject.getString("message");
        this.stack = jsonObject.getString("stack");
    }

    public ModelAndView asModelAndView() {
    	MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        Map<String, String> model = new HashMap<String, String>();
        model.put("message", this.message);
        model.put("stack", this.stack);
        model.put("code", this.code);
        LOGGER.debug(this.message);
        return new ModelAndView(jsonView, model);
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
    public String getStack(){
        return stack;
    }
    public void setStack(String stack){
        this.stack = stack;
    }

    
    /**
     * 
     * 
     * @return the code
     */
    public String getCode(){
        return code;
    }
    
}