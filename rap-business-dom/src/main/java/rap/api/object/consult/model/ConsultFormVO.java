/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultFormVO.java
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
public class ConsultFormVO extends BusinessObjectMasterVO {
    private String        consAcctCd                                        ;
    private String        consAcctNm                                        ;
    private String        itemInfoYn                                        ;
    private String        esseAppendYn                                      ;
    private String        reportConsYn                                      ;
    private String        calcYn                                            ;
    private String        draftConsYn                                        = "N";
    private String        jspId                                             ;
    private String        useYn                                             ;
    private String        buyYn                                              = "N";
    private String        itemTableNm                                       ;
    private String        composeYn                                         ;
    private String        jspMId                                            ;
    private String        jspRId                                            ;
    private String        newAcctCd                                         ;
    private String        mobileYn                                          ;
    private String        urlLinkYn                                         ;
    private String        apprLineCheckYn                                   ;
    private String        fileYn                                            ;
    private String        gbmsCheckYn                                       ;
    private String        epMobileType                                      ;
    private String        consAcctEngNm                                     ;


    public void    setConsAcctCd(String consAcctCd){
        this.consAcctCd = consAcctCd;
    }
    public void    setConsAcctNm(String consAcctNm){
        this.consAcctNm = consAcctNm;
    }
    public void    setItemInfoYn(String itemInfoYn){
        this.itemInfoYn = itemInfoYn;
    }
    public void    setEsseAppendYn(String esseAppendYn){
        this.esseAppendYn = esseAppendYn;
    }
    public void    setReportConsYn(String reportConsYn){
        this.reportConsYn = reportConsYn;
    }
    public void    setCalcYn(String calcYn){
        this.calcYn = calcYn;
    }
    public void    setDraftConsYn(String draftConsYn){
        this.draftConsYn = draftConsYn;
    }
    public void    setJspId(String jspId){
        this.jspId = jspId;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setBuyYn(String buyYn){
        this.buyYn = buyYn;
    }
    public void    setItemTableNm(String itemTableNm){
        this.itemTableNm = itemTableNm;
    }
    public void    setComposeYn(String composeYn){
        this.composeYn = composeYn;
    }
    public void    setJspMId(String jspMId){
        this.jspMId = jspMId;
    }
    public void    setJspRId(String jspRId){
        this.jspRId = jspRId;
    }
    public void    setNewAcctCd(String newAcctCd){
        this.newAcctCd = newAcctCd;
    }
    public void    setMobileYn(String mobileYn){
        this.mobileYn = mobileYn;
    }
    public void    setUrlLinkYn(String urlLinkYn){
        this.urlLinkYn = urlLinkYn;
    }
    public void    setApprLineCheckYn(String apprLineCheckYn){
        this.apprLineCheckYn = apprLineCheckYn;
    }
    public void    setFileYn(String fileYn){
        this.fileYn = fileYn;
    }
    public void    setGbmsCheckYn(String gbmsCheckYn){
        this.gbmsCheckYn = gbmsCheckYn;
    }
    public void    setEpMobileType(String epMobileType){
        this.epMobileType = epMobileType;
    }
    public void    setConsAcctEngNm(String consAcctEngNm){
        this.consAcctEngNm = consAcctEngNm;
    }
    public String getConsAcctCd(){
        return consAcctCd;
    }
    public String getConsAcctNm(){
        return consAcctNm;
    }
    public String getItemInfoYn(){
        return itemInfoYn;
    }
    public String getEsseAppendYn(){
        return esseAppendYn;
    }
    public String getReportConsYn(){
        return reportConsYn;
    }
    public String getCalcYn(){
        return calcYn;
    }
    public String getDraftConsYn(){
        return draftConsYn;
    }
    public String getJspId(){
        return jspId;
    }
    public String getUseYn(){
        return useYn;
    }
    public String getBuyYn(){
        return buyYn;
    }
    public String getItemTableNm(){
        return itemTableNm;
    }
    public String getComposeYn(){
        return composeYn;
    }
    public String getJspMId(){
        return jspMId;
    }
    public String getJspRId(){
        return jspRId;
    }
    public String getNewAcctCd(){
        return newAcctCd;
    }
    public String getMobileYn(){
        return mobileYn;
    }
    public String getUrlLinkYn(){
        return urlLinkYn;
    }
    public String getApprLineCheckYn(){
        return apprLineCheckYn;
    }
    public String getFileYn(){
        return fileYn;
    }
    public String getGbmsCheckYn(){
        return gbmsCheckYn;
    }
    public String getEpMobileType(){
        return epMobileType;
    }
    public String getConsAcctEngNm(){
        return consAcctEngNm;
    }
}

