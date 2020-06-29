/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ActivityDeliverablesVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ActivityDeliverablesVO extends BusinessRelationObjectVO {
    private Integer       sequences                                          = 0;
    private String        isLatest                                          ;
    private String        newYn                                              = "Y";
    private String        eventCode                                         ;
    private String        eventCodeDisplay                                  ;
    private String        documentType                                      ;
    private String        documentTemplateName                              ;


    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setIsLatest(String isLatest){
        this.isLatest = isLatest;
    }
    public void    setNewYn(String newYn){
        this.newYn = newYn;
    }
    public void    setEventCode(String eventCode){
        this.eventCode = eventCode;
    }
    public void    setEventCodeDisplay(String eventCodeDisplay){
        this.eventCodeDisplay = eventCodeDisplay;
    }
    public void    setDocumentType(String documentType){
        this.documentType = documentType;
    }
    public void    setDocumentTemplateName(String documentTemplateName){
        this.documentTemplateName = documentTemplateName;
    }
    public Integer getSequences(){
        return sequences;
    }
    public String getIsLatest(){
        return isLatest;
    }
    public String getNewYn(){
        return newYn;
    }
    public String getEventCode(){
        return eventCode;
    }
    public String getEventCodeDisplay(){
        return eventCodeDisplay;
    }
    public String getDocumentType(){
        return documentType;
    }
    public String getDocumentTemplateName(){
        return documentTemplateName;
    }
}

