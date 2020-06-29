/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DesignProcessVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.requests.model;


import rap.api.object.requests.model.RequestsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DesignProcessVO extends RequestsVO {
    private String        modelName                                         ;
    private String        projectName                                       ;
    private String        requestEmployeeNo                                 ;
    private String        requestOrgCode                                    ;
    private String        requestDate                                       ;
    private String        useYn                                              = "Y";


    public void    setModelName(String modelName){
        this.modelName = modelName;
    }
    public void    setProjectName(String projectName){
        this.projectName = projectName;
    }
    public void    setRequestEmployeeNo(String requestEmployeeNo){
        this.requestEmployeeNo = requestEmployeeNo;
    }
    public void    setRequestOrgCode(String requestOrgCode){
        this.requestOrgCode = requestOrgCode;
    }
    public void    setRequestDate(String requestDate){
        this.requestDate = requestDate;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public String getModelName(){
        return modelName;
    }
    public String getProjectName(){
        return projectName;
    }
    public String getRequestEmployeeNo(){
        return requestEmployeeNo;
    }
    public String getRequestOrgCode(){
        return requestOrgCode;
    }
    public String getRequestDate(){
        return requestDate;
    }
    public String getUseYn(){
        return useYn;
    }
}

