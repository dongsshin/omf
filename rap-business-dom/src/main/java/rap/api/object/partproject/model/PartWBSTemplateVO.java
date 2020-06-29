/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartWBSTemplateVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartWBSTemplateVO extends BusinessObjectVO {
    private String        divisionCode                                      ;
    private String        prodProjectGubun                                  ;
    private String        partActivityNameKor                               ;
    private String        partActivityNameEng                               ;
    private Integer       partActivitySorting                               ;
    private String        partOwnerRole                                     ;
    private String        partActivityType                                  ;
    private String        completeType                                      ;
    private String        documentMandatory                                  = "N";


    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setProdProjectGubun(String prodProjectGubun){
        this.prodProjectGubun = prodProjectGubun;
    }
    public void    setPartActivityNameKor(String partActivityNameKor){
        this.partActivityNameKor = partActivityNameKor;
    }
    public void    setPartActivityNameEng(String partActivityNameEng){
        this.partActivityNameEng = partActivityNameEng;
    }
    public void    setPartActivitySorting(Integer partActivitySorting){
        this.partActivitySorting = partActivitySorting;
    }
    public void    setPartOwnerRole(String partOwnerRole){
        this.partOwnerRole = partOwnerRole;
    }
    public void    setPartActivityType(String partActivityType){
        this.partActivityType = partActivityType;
    }
    public void    setCompleteType(String completeType){
        this.completeType = completeType;
    }
    public void    setDocumentMandatory(String documentMandatory){
        this.documentMandatory = documentMandatory;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getProdProjectGubun(){
        return prodProjectGubun;
    }
    public String getPartActivityNameKor(){
        return partActivityNameKor;
    }
    public String getPartActivityNameEng(){
        return partActivityNameEng;
    }
    public Integer getPartActivitySorting(){
        return partActivitySorting;
    }
    public String getPartOwnerRole(){
        return partOwnerRole;
    }
    public String getPartActivityType(){
        return partActivityType;
    }
    public String getCompleteType(){
        return completeType;
    }
    public String getDocumentMandatory(){
        return documentMandatory;
    }
}

