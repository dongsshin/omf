/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanSpecSheetGroupVO.java
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
public class ProdPlanSpecSheetGroupVO extends BusinessObjectMasterVO {
    private String        specSheetGroupId                                  ;
    private String        year                                              ;
    private String        divisionCode                                      ;
    private String        productType                                       ;
    private String        guideExcelFileId                                  ;


    public void    setSpecSheetGroupId(String specSheetGroupId){
        this.specSheetGroupId = specSheetGroupId;
    }
    public void    setYear(String year){
        this.year = year;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setProductType(String productType){
        this.productType = productType;
    }
    public void    setGuideExcelFileId(String guideExcelFileId){
        this.guideExcelFileId = guideExcelFileId;
    }
    public String getSpecSheetGroupId(){
        return specSheetGroupId;
    }
    public String getYear(){
        return year;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getProductType(){
        return productType;
    }
    public String getGuideExcelFileId(){
        return guideExcelFileId;
    }
}

