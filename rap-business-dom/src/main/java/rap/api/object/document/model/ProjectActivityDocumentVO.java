/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectActivityDocumentVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.document.model;


import rap.api.object.document.model.ProjectDocumentsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectActivityDocumentVO extends ProjectDocumentsVO {
    private String        reason                                            ;
    private String        signal                                            ;
    private String        documentNameKor                                   ;
    private String        documentNameEng                                   ;
    private String        forceCompleteYn                                    = "N";
    private String        documentNameChi                                   ;
    private String        attribute01                                       ;


    public void    setReason(String reason){
        this.reason = reason;
    }
    public void    setSignal(String signal){
        this.signal = signal;
    }
    public void    setDocumentNameKor(String documentNameKor){
        this.documentNameKor = documentNameKor;
    }
    public void    setDocumentNameEng(String documentNameEng){
        this.documentNameEng = documentNameEng;
    }
    public void    setForceCompleteYn(String forceCompleteYn){
        this.forceCompleteYn = forceCompleteYn;
    }
    public void    setDocumentNameChi(String documentNameChi){
        this.documentNameChi = documentNameChi;
    }
    public void    setAttribute01(String attribute01){
        this.attribute01 = attribute01;
    }
    public String getReason(){
        return reason;
    }
    public String getSignal(){
        return signal;
    }
    public String getDocumentNameKor(){
        return documentNameKor;
    }
    public String getDocumentNameEng(){
        return documentNameEng;
    }
    public String getForceCompleteYn(){
        return forceCompleteYn;
    }
    public String getDocumentNameChi(){
        return documentNameChi;
    }
    public String getAttribute01(){
        return attribute01;
    }
}

