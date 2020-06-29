/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PlanProjectApplyingTargetVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PlanProjectApplyingTargetVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        targetObuCode                                     ;
    private String        targetProduct                                     ;
    private String        targetModel                                       ;
    private String        targetArea                                        ;
    private String        targetRelease                                     ;
    private String        targetSalesVolume                                 ;
    private String        targetSalesResult                                 ;
    private String        targetProfit                                      ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setTargetObuCode(String targetObuCode){
        this.targetObuCode = targetObuCode;
    }
    public void    setTargetProduct(String targetProduct){
        this.targetProduct = targetProduct;
    }
    public void    setTargetModel(String targetModel){
        this.targetModel = targetModel;
    }
    public void    setTargetArea(String targetArea){
        this.targetArea = targetArea;
    }
    public void    setTargetRelease(String targetRelease){
        this.targetRelease = targetRelease;
    }
    public void    setTargetSalesVolume(String targetSalesVolume){
        this.targetSalesVolume = targetSalesVolume;
    }
    public void    setTargetSalesResult(String targetSalesResult){
        this.targetSalesResult = targetSalesResult;
    }
    public void    setTargetProfit(String targetProfit){
        this.targetProfit = targetProfit;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getTargetObuCode(){
        return targetObuCode;
    }
    public String getTargetProduct(){
        return targetProduct;
    }
    public String getTargetModel(){
        return targetModel;
    }
    public String getTargetArea(){
        return targetArea;
    }
    public String getTargetRelease(){
        return targetRelease;
    }
    public String getTargetSalesVolume(){
        return targetSalesVolume;
    }
    public String getTargetSalesResult(){
        return targetSalesResult;
    }
    public String getTargetProfit(){
        return targetProfit;
    }
}

