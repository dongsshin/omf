/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanExcelTemplateFileVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pp.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProdPlanExcelTemplateFileVO extends BusinessObjectMasterVO {
    private String        module                                            ;
    private String        divisionCode                                      ;
    private String        productType                                       ;
    private String        fileId                                            ;
    private String        remark                                            ;


    public void    setModule(String module){
        this.module = module;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setProductType(String productType){
        this.productType = productType;
    }
    public void    setFileId(String fileId){
        this.fileId = fileId;
    }
    public void    setRemark(String remark){
        this.remark = remark;
    }
    public String getModule(){
        return module;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getProductType(){
        return productType;
    }
    public String getFileId(){
        return fileId;
    }
    public String getRemark(){
        return remark;
    }
}

