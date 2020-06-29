package com.rap.omc.framework.view;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;
@SuppressWarnings({ "unchecked", "rawtypes" })
public class FoundationMappingJacksonJsonView extends MappingJackson2JsonView {
    
    private boolean extractValueFromSingleKeyModel = false;

    @Override
    protected Object filterModel(Map<String, Object> model) {
        Map<String, Object> result = new HashMap<String, Object>(model.size());
        Set<String> renderedAttributes = (!CollectionUtils.isEmpty(getModelKeys()) ? getModelKeys() : model.keySet());
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            if (!(entry.getValue() instanceof BindingResult) && renderedAttributes.contains(entry.getKey())) {
                if(GlobalConstants.M_DATA.equals(entry.getKey())){               
                    result.put(entry.getKey(), conveterMapToValue(entry.getValue()));                        
                }else{
                    result.put(entry.getKey(), conveterDateToString(entry.getValue()));    
                }            
            }            
        }
        return (this.extractValueFromSingleKeyModel && result.size() == 1 ? result.values().iterator().next() : result);
    }
    /**
     *
     * @param value
     */
    private Object conveterMapToValue(Object mapObject){
        Map<String, Object> subObject = (Map)mapObject;
        HashMap<String, Object> data = new HashMap<String, Object>();
        
        for(Map.Entry<String, Object> entry : subObject.entrySet()){
            if( entry.getValue() instanceof List){
                data.put(entry.getKey(), conveterListToValue(entry.getValue()));
                
            }else if ( entry.getValue() instanceof ObjectRootVO){
                data.put(entry.getKey(), conveterVoToValue(entry.getValue()));
                
            }else if("outData".equals(entry.getKey()) && entry.getValue() instanceof HashMap){
                
                for(Map.Entry<String, Object> subEntry :((Map<String, Object>)entry.getValue()).entrySet()){
                    data.put("outData_"+ subEntry.getKey(), conveterDateToString(subEntry.getValue()));
                }
                
            }else {
                data.put(entry.getKey(), conveterDateToString(entry.getValue()));
            }
            if("outData".equals(entry.getKey()) && entry.getValue() instanceof HashMap){
                data.put(entry.getKey(), conveterDateToString(entry.getValue()));
            }
        }
        return data;
    }
    
    
	private List conveterListToValue(Object listObject){
        
        List<Object> subObject = (ArrayList<Object>)listObject;        
        List<Object> data = new ArrayList<Object>();  
        
        for(int inx=0; inx < subObject.size(); inx++){            
            Object tempObject = subObject.get(inx); 
        
            if( tempObject instanceof List){
                data.add(conveterListToValue(tempObject));                
            }else if (tempObject instanceof ObjectRootVO){
                data.add(conveterVoToValue(tempObject));
            }else if (tempObject instanceof Map) {
                data.add(conveterMapToValue(tempObject));
            }else{
                data.add(conveterDateToString(tempObject));
            }
        }
        return data;
    }
    
    /**
     *
     * @param voObject
     * @return
     */
    private Object conveterVoToValue(Object voObject){
        List<Field> fields = new ArrayList<Field>();
        fields = getAllFields(fields, voObject.getClass());
        HashMap<String, Object> data = new HashMap<String, Object>();

        String fieldName;
        Object fieldValue;
        
        try {
            for(int i=0; i<=fields.size() -1;i++){
                fields.get(i).setAccessible(true);
                fieldName = fields.get(i).getName();
                fieldValue = fields.get(i).get(voObject);
                if(!NullUtil.isNull(fieldValue)){
                    data.put(fieldName, conveterDateToString(fieldValue));
                    if("outData".equals(fieldName)){
                        for(Map.Entry<String, Object> subEntry :((Map<String, Object>)fieldValue).entrySet()){
                            data.put("outData_"+ subEntry.getKey(), conveterDateToString(subEntry.getValue()));
                        }
                    }
                }
            }
      
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return data;
    }
    private  List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }
        return fields;
    }
    private Object conveterDateToString(Object value){
        if( value instanceof Date ){
            SimpleDateFormat transFormat = new SimpleDateFormat(OmcSystemConstants.OMC_JAVA_DATE_FORMAT_DATE);
            return  transFormat.format(value);                                
        }
        return value;
    }
}
