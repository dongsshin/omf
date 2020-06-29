/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DesignRequestsSpeakerInfoVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.requests.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DesignRequestsSpeakerInfoVO extends BusinessObjectMasterVO {
    private String        speakerNo                                         ;
    private String        speakerPartNo                                     ;
    private String        speakerPartType                                   ;
    private String        speakerRefModel                                   ;
    private String        speakerPartW                                      ;
    private String        speakerPartL                                      ;
    private String        speakerPartSize                                   ;
    private String        speakerFrameSize                                  ;


    public void    setSpeakerNo(String speakerNo){
        this.speakerNo = speakerNo;
    }
    public void    setSpeakerPartNo(String speakerPartNo){
        this.speakerPartNo = speakerPartNo;
    }
    public void    setSpeakerPartType(String speakerPartType){
        this.speakerPartType = speakerPartType;
    }
    public void    setSpeakerRefModel(String speakerRefModel){
        this.speakerRefModel = speakerRefModel;
    }
    public void    setSpeakerPartW(String speakerPartW){
        this.speakerPartW = speakerPartW;
    }
    public void    setSpeakerPartL(String speakerPartL){
        this.speakerPartL = speakerPartL;
    }
    public void    setSpeakerPartSize(String speakerPartSize){
        this.speakerPartSize = speakerPartSize;
    }
    public void    setSpeakerFrameSize(String speakerFrameSize){
        this.speakerFrameSize = speakerFrameSize;
    }
    public String getSpeakerNo(){
        return speakerNo;
    }
    public String getSpeakerPartNo(){
        return speakerPartNo;
    }
    public String getSpeakerPartType(){
        return speakerPartType;
    }
    public String getSpeakerRefModel(){
        return speakerRefModel;
    }
    public String getSpeakerPartW(){
        return speakerPartW;
    }
    public String getSpeakerPartL(){
        return speakerPartL;
    }
    public String getSpeakerPartSize(){
        return speakerPartSize;
    }
    public String getSpeakerFrameSize(){
        return speakerFrameSize;
    }
}

