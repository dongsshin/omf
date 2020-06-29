/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultFormTipVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ConsultFormTipVO extends BusinessRelationObjectVO {
    private String        appendFileCd                                      ;
    private String        tipAppr                                           ;
    private String        contentsTip                                       ;
    private String        commentsTip                                       ;
    private String        apprTip                                           ;


    public void    setAppendFileCd(String appendFileCd){
        this.appendFileCd = appendFileCd;
    }
    public void    setTipAppr(String tipAppr){
        this.tipAppr = tipAppr;
    }
    public void    setContentsTip(String contentsTip){
        this.contentsTip = contentsTip;
    }
    public void    setCommentsTip(String commentsTip){
        this.commentsTip = commentsTip;
    }
    public void    setApprTip(String apprTip){
        this.apprTip = apprTip;
    }
    public String getAppendFileCd(){
        return appendFileCd;
    }
    public String getTipAppr(){
        return tipAppr;
    }
    public String getContentsTip(){
        return contentsTip;
    }
    public String getCommentsTip(){
        return commentsTip;
    }
    public String getApprTip(){
        return apprTip;
    }
}

