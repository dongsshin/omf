/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DerivationProjectCheckItemVO.java
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
public class DerivationProjectCheckItemVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        checkItem                                         ;
    private String        item                                              ;
    private String        confirmYn                                          = "N";
    private String        confirmYyyymmdd                                   ;
    private String        eventCode                                         ;
    private String        confirmUser                                       ;
    private String        qaCheckUser                                       ;
    private String        qaCheckYn                                          = "N";
    private String        qaCheckYyyymmdd                                   ;
    private String        itemComment                                       ;
    private String        suffixCode                                        ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setCheckItem(String checkItem){
        this.checkItem = checkItem;
    }
    public void    setItem(String item){
        this.item = item;
    }
    public void    setConfirmYn(String confirmYn){
        this.confirmYn = confirmYn;
    }
    public void    setConfirmYyyymmdd(String confirmYyyymmdd){
        this.confirmYyyymmdd = confirmYyyymmdd;
    }
    public void    setEventCode(String eventCode){
        this.eventCode = eventCode;
    }
    public void    setConfirmUser(String confirmUser){
        this.confirmUser = confirmUser;
    }
    public void    setQaCheckUser(String qaCheckUser){
        this.qaCheckUser = qaCheckUser;
    }
    public void    setQaCheckYn(String qaCheckYn){
        this.qaCheckYn = qaCheckYn;
    }
    public void    setQaCheckYyyymmdd(String qaCheckYyyymmdd){
        this.qaCheckYyyymmdd = qaCheckYyyymmdd;
    }
    public void    setItemComment(String itemComment){
        this.itemComment = itemComment;
    }
    public void    setSuffixCode(String suffixCode){
        this.suffixCode = suffixCode;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getCheckItem(){
        return checkItem;
    }
    public String getItem(){
        return item;
    }
    public String getConfirmYn(){
        return confirmYn;
    }
    public String getConfirmYyyymmdd(){
        return confirmYyyymmdd;
    }
    public String getEventCode(){
        return eventCode;
    }
    public String getConfirmUser(){
        return confirmUser;
    }
    public String getQaCheckUser(){
        return qaCheckUser;
    }
    public String getQaCheckYn(){
        return qaCheckYn;
    }
    public String getQaCheckYyyymmdd(){
        return qaCheckYyyymmdd;
    }
    public String getItemComment(){
        return itemComment;
    }
    public String getSuffixCode(){
        return suffixCode;
    }
}

