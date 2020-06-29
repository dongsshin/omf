/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OSCSupplierVO.java
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
public class OSCSupplierVO extends BusinessObjectMasterVO {
    private String        agentNm                                           ;
    private String        countryCode                                       ;
    private String        tradeNo                                           ;
    private String        peopleConf                                        ;
    private String        rschLine                                          ;
    private String        oscInstFeature                                    ;
    private String        chgrNm                                            ;
    private String        homepage                                          ;
    private String        email                                             ;
    private String        addr                                              ;
    private String        telNo                                             ;
    private String        faxNo                                             ;
    private String        delYn                                             ;
    private String        evalTotCnts                                       ;
    private String        oscInstType                                       ;
    private String        oscInstStaff                                      ;
    private String        oscInstAward                                      ;
    private String        oscInstConstraint                                 ;
    private BigDecimal    oscInstEvalAvg                                     = new BigDecimal(0);
    private Integer       oscInstAmtAvg                                     ;
    private Integer       contractCount                                     ;


    public void    setAgentNm(String agentNm){
        this.agentNm = agentNm;
    }
    public void    setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }
    public void    setTradeNo(String tradeNo){
        this.tradeNo = tradeNo;
    }
    public void    setPeopleConf(String peopleConf){
        this.peopleConf = peopleConf;
    }
    public void    setRschLine(String rschLine){
        this.rschLine = rschLine;
    }
    public void    setOscInstFeature(String oscInstFeature){
        this.oscInstFeature = oscInstFeature;
    }
    public void    setChgrNm(String chgrNm){
        this.chgrNm = chgrNm;
    }
    public void    setHomepage(String homepage){
        this.homepage = homepage;
    }
    public void    setEmail(String email){
        this.email = email;
    }
    public void    setAddr(String addr){
        this.addr = addr;
    }
    public void    setTelNo(String telNo){
        this.telNo = telNo;
    }
    public void    setFaxNo(String faxNo){
        this.faxNo = faxNo;
    }
    public void    setDelYn(String delYn){
        this.delYn = delYn;
    }
    public void    setEvalTotCnts(String evalTotCnts){
        this.evalTotCnts = evalTotCnts;
    }
    public void    setOscInstType(String oscInstType){
        this.oscInstType = oscInstType;
    }
    public void    setOscInstStaff(String oscInstStaff){
        this.oscInstStaff = oscInstStaff;
    }
    public void    setOscInstAward(String oscInstAward){
        this.oscInstAward = oscInstAward;
    }
    public void    setOscInstConstraint(String oscInstConstraint){
        this.oscInstConstraint = oscInstConstraint;
    }
    public void    setOscInstEvalAvg(BigDecimal oscInstEvalAvg){
        this.oscInstEvalAvg = oscInstEvalAvg;
    }
    public void    setOscInstAmtAvg(Integer oscInstAmtAvg){
        this.oscInstAmtAvg = oscInstAmtAvg;
    }
    public void    setContractCount(Integer contractCount){
        this.contractCount = contractCount;
    }
    public String getAgentNm(){
        return agentNm;
    }
    public String getCountryCode(){
        return countryCode;
    }
    public String getTradeNo(){
        return tradeNo;
    }
    public String getPeopleConf(){
        return peopleConf;
    }
    public String getRschLine(){
        return rschLine;
    }
    public String getOscInstFeature(){
        return oscInstFeature;
    }
    public String getChgrNm(){
        return chgrNm;
    }
    public String getHomepage(){
        return homepage;
    }
    public String getEmail(){
        return email;
    }
    public String getAddr(){
        return addr;
    }
    public String getTelNo(){
        return telNo;
    }
    public String getFaxNo(){
        return faxNo;
    }
    public String getDelYn(){
        return delYn;
    }
    public String getEvalTotCnts(){
        return evalTotCnts;
    }
    public String getOscInstType(){
        return oscInstType;
    }
    public String getOscInstStaff(){
        return oscInstStaff;
    }
    public String getOscInstAward(){
        return oscInstAward;
    }
    public String getOscInstConstraint(){
        return oscInstConstraint;
    }
    public BigDecimal getOscInstEvalAvg(){
        return oscInstEvalAvg;
    }
    public Integer getOscInstAmtAvg(){
        return oscInstAmtAvg;
    }
    public Integer getContractCount(){
        return contractCount;
    }
}

