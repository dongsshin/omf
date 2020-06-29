/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SupplierMakerVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class SupplierMakerVO extends BusinessObjectMasterVO {
    private String        supplierCode                                      ;
    private String        supplierNameLocal                                 ;
    private String        supplierNameEng                                   ;
    private String        makerCode                                         ;
    private String        makerNameLocal                                    ;
    private String        makerNameEng                                      ;
    private String        repMakerName                                      ;
    private String        useYn                                             ;
    private String        confrimYn                                         ;


    public void    setSupplierCode(String supplierCode){
        this.supplierCode = supplierCode;
    }
    public void    setSupplierNameLocal(String supplierNameLocal){
        this.supplierNameLocal = supplierNameLocal;
    }
    public void    setSupplierNameEng(String supplierNameEng){
        this.supplierNameEng = supplierNameEng;
    }
    public void    setMakerCode(String makerCode){
        this.makerCode = makerCode;
    }
    public void    setMakerNameLocal(String makerNameLocal){
        this.makerNameLocal = makerNameLocal;
    }
    public void    setMakerNameEng(String makerNameEng){
        this.makerNameEng = makerNameEng;
    }
    public void    setRepMakerName(String repMakerName){
        this.repMakerName = repMakerName;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setConfrimYn(String confrimYn){
        this.confrimYn = confrimYn;
    }
    public String getSupplierCode(){
        return supplierCode;
    }
    public String getSupplierNameLocal(){
        return supplierNameLocal;
    }
    public String getSupplierNameEng(){
        return supplierNameEng;
    }
    public String getMakerCode(){
        return makerCode;
    }
    public String getMakerNameLocal(){
        return makerNameLocal;
    }
    public String getMakerNameEng(){
        return makerNameEng;
    }
    public String getRepMakerName(){
        return repMakerName;
    }
    public String getUseYn(){
        return useYn;
    }
    public String getConfrimYn(){
        return confrimYn;
    }
}

