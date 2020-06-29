/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DocumentDistributionVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DocumentDistributionVO extends BusinessObjectMasterVO {
    private String        documentObid                                      ;
    private String        documentNo                                        ;
    private String        fileDetail                                        ;
    private Date          sendDateToCpc                                     ;
    private Date          receiptDateInCpc                                  ;
    private Date          receiveDeadLine                                   ;
    private String        cpcApprovalStatus                                 ;
    private String        cpcComment                                        ;


    public void    setDocumentObid(String documentObid){
        this.documentObid = documentObid;
    }
    public void    setDocumentNo(String documentNo){
        this.documentNo = documentNo;
    }
    public void    setFileDetail(String fileDetail){
        this.fileDetail = fileDetail;
    }
    public void    setSendDateToCpc(Date sendDateToCpc){
        this.sendDateToCpc = sendDateToCpc;
    }
    public void    setSendDateToCpc(String    sendDateToCpc){
        this.sendDateToCpc = this.omcConvertStr2Date(sendDateToCpc);
    }
    public void    setReceiptDateInCpc(Date receiptDateInCpc){
        this.receiptDateInCpc = receiptDateInCpc;
    }
    public void    setReceiptDateInCpc(String    receiptDateInCpc){
        this.receiptDateInCpc = this.omcConvertStr2Date(receiptDateInCpc);
    }
    public void    setReceiveDeadLine(Date receiveDeadLine){
        this.receiveDeadLine = receiveDeadLine;
    }
    public void    setReceiveDeadLine(String    receiveDeadLine){
        this.receiveDeadLine = this.omcConvertStr2Date(receiveDeadLine);
    }
    public void    setCpcApprovalStatus(String cpcApprovalStatus){
        this.cpcApprovalStatus = cpcApprovalStatus;
    }
    public void    setCpcComment(String cpcComment){
        this.cpcComment = cpcComment;
    }
    public String getDocumentObid(){
        return documentObid;
    }
    public String getDocumentNo(){
        return documentNo;
    }
    public String getFileDetail(){
        return fileDetail;
    }
    public Date getSendDateToCpc(){
        return sendDateToCpc;
    }
    public Date getReceiptDateInCpc(){
        return receiptDateInCpc;
    }
    public Date getReceiveDeadLine(){
        return receiveDeadLine;
    }
    public String getCpcApprovalStatus(){
        return cpcApprovalStatus;
    }
    public String getCpcComment(){
        return cpcComment;
    }
}

