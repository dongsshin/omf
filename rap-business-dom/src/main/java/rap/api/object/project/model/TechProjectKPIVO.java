/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : TechProjectKPIVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class TechProjectKPIVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        rivalCompany                                      ;
    private String        kpiItem                                           ;
    private String        currentLevelOwn                                   ;
    private String        currentLevelSignal                                ;
    private String        currentLevelRival                                 ;
    private String        targetLevelOwn                                    ;
    private String        targetLevelSignal                                 ;
    private String        targetLevelRival                                  ;
    private String        longTermLevelOwn                                  ;
    private String        longTermLevelSignal                               ;
    private String        longTermLevelRival                                ;
    private String        longTermLevelMonth                                ;
    private String        diffTechnology                                    ;
    private Integer       sequences                                          = 1;
    private String        resultTermLevelOwn                                ;
    private String        resultTermLevelSignal                             ;
    private String        resultTermLevelRival                              ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setRivalCompany(String rivalCompany){
        this.rivalCompany = rivalCompany;
    }
    public void    setKpiItem(String kpiItem){
        this.kpiItem = kpiItem;
    }
    public void    setCurrentLevelOwn(String currentLevelOwn){
        this.currentLevelOwn = currentLevelOwn;
    }
    public void    setCurrentLevelSignal(String currentLevelSignal){
        this.currentLevelSignal = currentLevelSignal;
    }
    public void    setCurrentLevelRival(String currentLevelRival){
        this.currentLevelRival = currentLevelRival;
    }
    public void    setTargetLevelOwn(String targetLevelOwn){
        this.targetLevelOwn = targetLevelOwn;
    }
    public void    setTargetLevelSignal(String targetLevelSignal){
        this.targetLevelSignal = targetLevelSignal;
    }
    public void    setTargetLevelRival(String targetLevelRival){
        this.targetLevelRival = targetLevelRival;
    }
    public void    setLongTermLevelOwn(String longTermLevelOwn){
        this.longTermLevelOwn = longTermLevelOwn;
    }
    public void    setLongTermLevelSignal(String longTermLevelSignal){
        this.longTermLevelSignal = longTermLevelSignal;
    }
    public void    setLongTermLevelRival(String longTermLevelRival){
        this.longTermLevelRival = longTermLevelRival;
    }
    public void    setLongTermLevelMonth(String longTermLevelMonth){
        this.longTermLevelMonth = longTermLevelMonth;
    }
    public void    setDiffTechnology(String diffTechnology){
        this.diffTechnology = diffTechnology;
    }
    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setResultTermLevelOwn(String resultTermLevelOwn){
        this.resultTermLevelOwn = resultTermLevelOwn;
    }
    public void    setResultTermLevelSignal(String resultTermLevelSignal){
        this.resultTermLevelSignal = resultTermLevelSignal;
    }
    public void    setResultTermLevelRival(String resultTermLevelRival){
        this.resultTermLevelRival = resultTermLevelRival;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getRivalCompany(){
        return rivalCompany;
    }
    public String getKpiItem(){
        return kpiItem;
    }
    public String getCurrentLevelOwn(){
        return currentLevelOwn;
    }
    public String getCurrentLevelSignal(){
        return currentLevelSignal;
    }
    public String getCurrentLevelRival(){
        return currentLevelRival;
    }
    public String getTargetLevelOwn(){
        return targetLevelOwn;
    }
    public String getTargetLevelSignal(){
        return targetLevelSignal;
    }
    public String getTargetLevelRival(){
        return targetLevelRival;
    }
    public String getLongTermLevelOwn(){
        return longTermLevelOwn;
    }
    public String getLongTermLevelSignal(){
        return longTermLevelSignal;
    }
    public String getLongTermLevelRival(){
        return longTermLevelRival;
    }
    public String getLongTermLevelMonth(){
        return longTermLevelMonth;
    }
    public String getDiffTechnology(){
        return diffTechnology;
    }
    public Integer getSequences(){
        return sequences;
    }
    public String getResultTermLevelOwn(){
        return resultTermLevelOwn;
    }
    public String getResultTermLevelSignal(){
        return resultTermLevelSignal;
    }
    public String getResultTermLevelRival(){
        return resultTermLevelRival;
    }
}

