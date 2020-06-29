/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ApprovalLineMasterVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.workflow.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ApprovalLineMasterVO extends BusinessObjectMasterVO {
    private String        requestType                                       ;
    private String        requestSubType                                    ;
    private String        organizationType                                  ;
    private String        amountYn                                          ;


    public void    setRequestType(String requestType){
        this.requestType = requestType;
    }
    public void    setRequestSubType(String requestSubType){
        this.requestSubType = requestSubType;
    }
    public void    setOrganizationType(String organizationType){
        this.organizationType = organizationType;
    }
    public void    setAmountYn(String amountYn){
        this.amountYn = amountYn;
    }
    public String getRequestType(){
        return requestType;
    }
    public String getRequestSubType(){
        return requestSubType;
    }
    public String getOrganizationType(){
        return organizationType;
    }
    public String getAmountYn(){
        return amountYn;
    }
}

