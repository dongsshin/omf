/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DrawingInfoVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DrawingInfoVO extends BusinessObjectMasterVO {
    private String        systemName                                        ;
    private String        partDivisionCode                                  ;
    private String        partNo                                            ;
    private String        drawingNo                                         ;
    private String        drawingRevNo                                      ;
    private String        drawingType                                       ;
    private String        drawingApprovalType                               ;
    private String        changeNo                                          ;
    private String        changeApprovalDate                                ;
    private String        gpdmUrl                                           ;


    public void    setSystemName(String systemName){
        this.systemName = systemName;
    }
    public void    setPartDivisionCode(String partDivisionCode){
        this.partDivisionCode = partDivisionCode;
    }
    public void    setPartNo(String partNo){
        this.partNo = partNo;
    }
    public void    setDrawingNo(String drawingNo){
        this.drawingNo = drawingNo;
    }
    public void    setDrawingRevNo(String drawingRevNo){
        this.drawingRevNo = drawingRevNo;
    }
    public void    setDrawingType(String drawingType){
        this.drawingType = drawingType;
    }
    public void    setDrawingApprovalType(String drawingApprovalType){
        this.drawingApprovalType = drawingApprovalType;
    }
    public void    setChangeNo(String changeNo){
        this.changeNo = changeNo;
    }
    public void    setChangeApprovalDate(String changeApprovalDate){
        this.changeApprovalDate = changeApprovalDate;
    }
    public void    setGpdmUrl(String gpdmUrl){
        this.gpdmUrl = gpdmUrl;
    }
    public String getSystemName(){
        return systemName;
    }
    public String getPartDivisionCode(){
        return partDivisionCode;
    }
    public String getPartNo(){
        return partNo;
    }
    public String getDrawingNo(){
        return drawingNo;
    }
    public String getDrawingRevNo(){
        return drawingRevNo;
    }
    public String getDrawingType(){
        return drawingType;
    }
    public String getDrawingApprovalType(){
        return drawingApprovalType;
    }
    public String getChangeNo(){
        return changeNo;
    }
    public String getChangeApprovalDate(){
        return changeApprovalDate;
    }
    public String getGpdmUrl(){
        return gpdmUrl;
    }
}

