/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DesignProjectVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.ProjectsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DesignProjectVO extends ProjectsVO {
    private String        designProjectClass                                ;
    private String        requestor                                         ;
    private String        designProjectCategory                             ;
    private String        labCode                                           ;
    private String        designItem                                        ;
    private String        designProjectTypeCode                             ;
    private String        designProjectGradeCode                            ;
    private String        designPrecedeTypeCode                             ;
    private String        upItemCode                                        ;
    private String        designOutsourcingCode                             ;
    private String        itemCode                                          ;
    private String        refProjectActivity                                ;
    private String        drawingTransferFlag                               ;
    private String        commentConcept                                    ;
    private String        commentRendering                                  ;
    private String        commentMockup                                     ;
    private String        commentFollowup                                   ;
    private String        coworkListCode                                    ;
    private String        commonProjectCode                                 ;
    private String        researchDeptCode                                  ;
    private String        majorComment                                      ;


    public void    setDesignProjectClass(String designProjectClass){
        this.designProjectClass = designProjectClass;
    }
    public void    setRequestor(String requestor){
        this.requestor = requestor;
    }
    public void    setDesignProjectCategory(String designProjectCategory){
        this.designProjectCategory = designProjectCategory;
    }
    public void    setLabCode(String labCode){
        this.labCode = labCode;
    }
    public void    setDesignItem(String designItem){
        this.designItem = designItem;
    }
    public void    setDesignProjectTypeCode(String designProjectTypeCode){
        this.designProjectTypeCode = designProjectTypeCode;
    }
    public void    setDesignProjectGradeCode(String designProjectGradeCode){
        this.designProjectGradeCode = designProjectGradeCode;
    }
    public void    setDesignPrecedeTypeCode(String designPrecedeTypeCode){
        this.designPrecedeTypeCode = designPrecedeTypeCode;
    }
    public void    setUpItemCode(String upItemCode){
        this.upItemCode = upItemCode;
    }
    public void    setDesignOutsourcingCode(String designOutsourcingCode){
        this.designOutsourcingCode = designOutsourcingCode;
    }
    public void    setItemCode(String itemCode){
        this.itemCode = itemCode;
    }
    public void    setRefProjectActivity(String refProjectActivity){
        this.refProjectActivity = refProjectActivity;
    }
    public void    setDrawingTransferFlag(String drawingTransferFlag){
        this.drawingTransferFlag = drawingTransferFlag;
    }
    public void    setCommentConcept(String commentConcept){
        this.commentConcept = commentConcept;
    }
    public void    setCommentRendering(String commentRendering){
        this.commentRendering = commentRendering;
    }
    public void    setCommentMockup(String commentMockup){
        this.commentMockup = commentMockup;
    }
    public void    setCommentFollowup(String commentFollowup){
        this.commentFollowup = commentFollowup;
    }
    public void    setCoworkListCode(String coworkListCode){
        this.coworkListCode = coworkListCode;
    }
    public void    setCommonProjectCode(String commonProjectCode){
        this.commonProjectCode = commonProjectCode;
    }
    public void    setResearchDeptCode(String researchDeptCode){
        this.researchDeptCode = researchDeptCode;
    }
    public void    setMajorComment(String majorComment){
        this.majorComment = majorComment;
    }
    public String getDesignProjectClass(){
        return designProjectClass;
    }
    public String getRequestor(){
        return requestor;
    }
    public String getDesignProjectCategory(){
        return designProjectCategory;
    }
    public String getLabCode(){
        return labCode;
    }
    public String getDesignItem(){
        return designItem;
    }
    public String getDesignProjectTypeCode(){
        return designProjectTypeCode;
    }
    public String getDesignProjectGradeCode(){
        return designProjectGradeCode;
    }
    public String getDesignPrecedeTypeCode(){
        return designPrecedeTypeCode;
    }
    public String getUpItemCode(){
        return upItemCode;
    }
    public String getDesignOutsourcingCode(){
        return designOutsourcingCode;
    }
    public String getItemCode(){
        return itemCode;
    }
    public String getRefProjectActivity(){
        return refProjectActivity;
    }
    public String getDrawingTransferFlag(){
        return drawingTransferFlag;
    }
    public String getCommentConcept(){
        return commentConcept;
    }
    public String getCommentRendering(){
        return commentRendering;
    }
    public String getCommentMockup(){
        return commentMockup;
    }
    public String getCommentFollowup(){
        return commentFollowup;
    }
    public String getCoworkListCode(){
        return coworkListCode;
    }
    public String getCommonProjectCode(){
        return commonProjectCode;
    }
    public String getResearchDeptCode(){
        return researchDeptCode;
    }
    public String getMajorComment(){
        return majorComment;
    }
}

