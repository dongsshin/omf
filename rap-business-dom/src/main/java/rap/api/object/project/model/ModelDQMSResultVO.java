/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ModelDQMSResultVO.java
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
public class ModelDQMSResultVO extends BusinessObjectMasterVO {
    private String        modelName                                         ;
    private String        fromObid                                          ;
    private String        testType                                          ;
    private String        testEvent                                         ;
    private Integer       reqSeq                                            ;
    private String        resultStartYyyymmdd                               ;
    private String        resultEndYyyymmdd                                 ;
    private String        resultUser                                        ;
    private String        attr01                                            ;
    private String        attr02                                            ;
    private String        attr03                                            ;
    private String        attr04                                            ;
    private String        attr05                                            ;
    private String        receivedYyyymmdd                                  ;


    public void    setModelName(String modelName){
        this.modelName = modelName;
    }
    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setTestType(String testType){
        this.testType = testType;
    }
    public void    setTestEvent(String testEvent){
        this.testEvent = testEvent;
    }
    public void    setReqSeq(Integer reqSeq){
        this.reqSeq = reqSeq;
    }
    public void    setResultStartYyyymmdd(String resultStartYyyymmdd){
        this.resultStartYyyymmdd = resultStartYyyymmdd;
    }
    public void    setResultEndYyyymmdd(String resultEndYyyymmdd){
        this.resultEndYyyymmdd = resultEndYyyymmdd;
    }
    public void    setResultUser(String resultUser){
        this.resultUser = resultUser;
    }
    public void    setAttr01(String attr01){
        this.attr01 = attr01;
    }
    public void    setAttr02(String attr02){
        this.attr02 = attr02;
    }
    public void    setAttr03(String attr03){
        this.attr03 = attr03;
    }
    public void    setAttr04(String attr04){
        this.attr04 = attr04;
    }
    public void    setAttr05(String attr05){
        this.attr05 = attr05;
    }
    public void    setReceivedYyyymmdd(String receivedYyyymmdd){
        this.receivedYyyymmdd = receivedYyyymmdd;
    }
    public String getModelName(){
        return modelName;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getTestType(){
        return testType;
    }
    public String getTestEvent(){
        return testEvent;
    }
    public Integer getReqSeq(){
        return reqSeq;
    }
    public String getResultStartYyyymmdd(){
        return resultStartYyyymmdd;
    }
    public String getResultEndYyyymmdd(){
        return resultEndYyyymmdd;
    }
    public String getResultUser(){
        return resultUser;
    }
    public String getAttr01(){
        return attr01;
    }
    public String getAttr02(){
        return attr02;
    }
    public String getAttr03(){
        return attr03;
    }
    public String getAttr04(){
        return attr04;
    }
    public String getAttr05(){
        return attr05;
    }
    public String getReceivedYyyymmdd(){
        return receivedYyyymmdd;
    }
}

