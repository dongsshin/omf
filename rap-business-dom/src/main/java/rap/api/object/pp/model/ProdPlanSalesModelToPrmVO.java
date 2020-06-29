/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanSalesModelToPrmVO.java
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
public class ProdPlanSalesModelToPrmVO extends BusinessObjectMasterVO {
    private String        prmCode                                           ;
    private String        prmSheetGroupId                                   ;
    private String        modelObid                                         ;
    private String        salesModelSuffix                                  ;
    private String        mappingType                                       ;
    private String        fromPrmCode                                       ;
    private String        fromPrmSheetId                                    ;
    private Integer       fromPdrId                                         ;


    public void    setPrmCode(String prmCode){
        this.prmCode = prmCode;
    }
    public void    setPrmSheetGroupId(String prmSheetGroupId){
        this.prmSheetGroupId = prmSheetGroupId;
    }
    public void    setModelObid(String modelObid){
        this.modelObid = modelObid;
    }
    public void    setSalesModelSuffix(String salesModelSuffix){
        this.salesModelSuffix = salesModelSuffix;
    }
    public void    setMappingType(String mappingType){
        this.mappingType = mappingType;
    }
    public void    setFromPrmCode(String fromPrmCode){
        this.fromPrmCode = fromPrmCode;
    }
    public void    setFromPrmSheetId(String fromPrmSheetId){
        this.fromPrmSheetId = fromPrmSheetId;
    }
    public void    setFromPdrId(Integer fromPdrId){
        this.fromPdrId = fromPdrId;
    }
    public String getPrmCode(){
        return prmCode;
    }
    public String getPrmSheetGroupId(){
        return prmSheetGroupId;
    }
    public String getModelObid(){
        return modelObid;
    }
    public String getSalesModelSuffix(){
        return salesModelSuffix;
    }
    public String getMappingType(){
        return mappingType;
    }
    public String getFromPrmCode(){
        return fromPrmCode;
    }
    public String getFromPrmSheetId(){
        return fromPrmSheetId;
    }
    public Integer getFromPdrId(){
        return fromPdrId;
    }
}

