/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultFormReqTypeVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.consult.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ConsultFormReqTypeVO extends BusinessObjectMasterVO {
    private String        consAcctCd                                        ;
    private Integer       fgCd                                              ;
    private String        fgNm                                              ;
    private String        fgEngNm                                           ;
    private String        requestGroup                                      ;
    private Integer       sortOrder                                         ;
    private String        oscRequestType                                    ;
    private String        oscRequestType2                                   ;
    private String        oscRequestType3                                   ;


    public void    setConsAcctCd(String consAcctCd){
        this.consAcctCd = consAcctCd;
    }
    public void    setFgCd(Integer fgCd){
        this.fgCd = fgCd;
    }
    public void    setFgNm(String fgNm){
        this.fgNm = fgNm;
    }
    public void    setFgEngNm(String fgEngNm){
        this.fgEngNm = fgEngNm;
    }
    public void    setRequestGroup(String requestGroup){
        this.requestGroup = requestGroup;
    }
    public void    setSortOrder(Integer sortOrder){
        this.sortOrder = sortOrder;
    }
    public void    setOscRequestType(String oscRequestType){
        this.oscRequestType = oscRequestType;
    }
    public void    setOscRequestType2(String oscRequestType2){
        this.oscRequestType2 = oscRequestType2;
    }
    public void    setOscRequestType3(String oscRequestType3){
        this.oscRequestType3 = oscRequestType3;
    }
    public String getConsAcctCd(){
        return consAcctCd;
    }
    public Integer getFgCd(){
        return fgCd;
    }
    public String getFgNm(){
        return fgNm;
    }
    public String getFgEngNm(){
        return fgEngNm;
    }
    public String getRequestGroup(){
        return requestGroup;
    }
    public Integer getSortOrder(){
        return sortOrder;
    }
    public String getOscRequestType(){
        return oscRequestType;
    }
    public String getOscRequestType2(){
        return oscRequestType2;
    }
    public String getOscRequestType3(){
        return oscRequestType3;
    }
}

