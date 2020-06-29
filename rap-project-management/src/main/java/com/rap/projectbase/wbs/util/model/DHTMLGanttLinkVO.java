/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : DHTMLGanttLinkVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 24.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.util.model;

import com.rap.omc.api.util.StrUtil;

/**
 * <pre>
 * Class : DHTMLGanttLinkVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class DHTMLGanttLinkVO extends DTMTLObjectRootVO{
    private String id;
    private String source;
    private String target;
    private String type;
    public DHTMLGanttLinkVO(String id, String source, String target, String type) {
        super();
        this.id = id;
        this.source = source;
        this.target = target;
        if(StrUtil.isEmpty(type)){
            this.type = "0";
        }else{
            if(type.equals("FS")){
                this.type = "0";
            }else if(type.equals("SS")){
                this.type = "1";
            }else if(type.equals("FF")){
                this.type = "2";
            }else if(type.equals("SF")){
                this.type = "3";
            }else{
                this.type = "0";
            }
        }
    }

    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getSource(){
        return source;
    }
    
    public void setSource(String source){
        this.source = source;
    }
    
    public String getTarget(){
        return target;
    }
    
    public void setTarget(String target){
        this.target = target;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
}
