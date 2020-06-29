/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaTabLayout.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 6.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import java.util.List;


/**
 * <pre>
 * Class : OmcSchemaTabLayout
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaTabLayoutVO extends OmcSchemaSysRootVO{
    private String  usages                             ;
    private String  kindsStr                           ;
    private String  labels                             ;
    private String  labelsKr                             ;
    private String  subObjectList                      ;
    private List<String>  subObjectArrayList           ;
    private String  linkHerf                           ;
    private String  linkAlt                            ;
    private int     heights                            ;
    private long    kinds                              ;
    

    
    
    public String getLabelsKr(){
        return labelsKr;
    }

    
    public void setLabelsKr(String labelsKr){
        this.labelsKr = labelsKr;
    }

    public String getLinkHerf(){
        return linkHerf;
    }
    
    public void setLinkHerf(String linkHerf){
        this.linkHerf = linkHerf;
    }
    public List<String> getSubObjectArrayList(){
        return subObjectArrayList;
    }
    public void setSubObjectArrayList(List<String> subObjectArrayList){
        this.subObjectArrayList = subObjectArrayList;
    }



    public String getLabels(){
        return labels;
    }


    
    public void setLabels(String labels){
        this.labels = labels;
    }


    public long getKinds(){
        return kinds;
    }

    
    public void setKinds(long kinds){
        this.kinds = kinds;
    }

    public String getUsages(){
        return usages;
    }
    
    public String getKindsStr(){
        return kindsStr;
    }
    public String getSubObjectList(){
        return subObjectList;
    }

    public String getLinkAlt(){
        return linkAlt;
    }
    
    public int getHeights(){
        return heights;
    }
    
    public void setUsages(String usages){
        this.usages = usages;
    }
    
    public void setKindsStr(String kindsStr){
        this.kindsStr = kindsStr;
    }
    public void setSubObjectList(String subObjectList){
        this.subObjectList = subObjectList;
    }

    
    public void setLinkAlt(String linkAlt){
        this.linkAlt = linkAlt;
    }
    
    public void setHeights(int heights){
        this.heights = heights;
    }
       
}
