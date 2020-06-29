/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : LibrariesVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.classification.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class LibrariesVO extends BusinessObjectMasterVO {
    private String        dynamicAttributeGroup                             ;
    private Integer       subCount                                          ;
    private String        accessRule                                        ;


    public void    setDynamicAttributeGroup(String dynamicAttributeGroup){
        this.dynamicAttributeGroup = dynamicAttributeGroup;
    }
    public void    setSubCount(Integer subCount){
        this.subCount = subCount;
    }
    public void    setAccessRule(String accessRule){
        this.accessRule = accessRule;
    }
    public String getDynamicAttributeGroup(){
        return dynamicAttributeGroup;
    }
    public Integer getSubCount(){
        return subCount;
    }
    public String getAccessRule(){
        return accessRule;
    }
}

