/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : GpriNtCodeVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class GpriNtCodeVO extends BusinessObjectMasterVO {
    private String        modelCode                                         ;
    private String        buyerSuffixCode                                   ;
    private String        ntcodeString                                      ;
    private String        feedbackNotificationList                          ;


    public void    setModelCode(String modelCode){
        this.modelCode = modelCode;
    }
    public void    setBuyerSuffixCode(String buyerSuffixCode){
        this.buyerSuffixCode = buyerSuffixCode;
    }
    public void    setNtcodeString(String ntcodeString){
        this.ntcodeString = ntcodeString;
    }
    public void    setFeedbackNotificationList(String feedbackNotificationList){
        this.feedbackNotificationList = feedbackNotificationList;
    }
    public String getModelCode(){
        return modelCode;
    }
    public String getBuyerSuffixCode(){
        return buyerSuffixCode;
    }
    public String getNtcodeString(){
        return ntcodeString;
    }
    public String getFeedbackNotificationList(){
        return feedbackNotificationList;
    }
}

