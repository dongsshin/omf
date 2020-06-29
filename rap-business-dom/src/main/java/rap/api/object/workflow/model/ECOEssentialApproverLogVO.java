/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ECOEssentialApproverLogVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.workflow.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ECOEssentialApproverLogVO extends BusinessObjectMasterVO {
    private String        obidCategory                                      ;
    private String        stage1approverlist                                ;
    private String        stage2approverlist                                ;
    private String        stage3approverlist                                ;
    private String        stage4approverlist                                ;
    private String        distributeelist                                   ;
    private String        opType                                            ;
    private String        stage1essentialflag                               ;
    private String        targetObid                                        ;
    private String        stage2essentialflag                               ;
    private String        stage3essentialflag                               ;
    private String        stage4essentialflag                               ;
    private String        distributeeessentialflag                          ;


    public void    setObidCategory(String obidCategory){
        this.obidCategory = obidCategory;
    }
    public void    setStage1approverlist(String stage1approverlist){
        this.stage1approverlist = stage1approverlist;
    }
    public void    setStage2approverlist(String stage2approverlist){
        this.stage2approverlist = stage2approverlist;
    }
    public void    setStage3approverlist(String stage3approverlist){
        this.stage3approverlist = stage3approverlist;
    }
    public void    setStage4approverlist(String stage4approverlist){
        this.stage4approverlist = stage4approverlist;
    }
    public void    setDistributeelist(String distributeelist){
        this.distributeelist = distributeelist;
    }
    public void    setOpType(String opType){
        this.opType = opType;
    }
    public void    setStage1essentialflag(String stage1essentialflag){
        this.stage1essentialflag = stage1essentialflag;
    }
    public void    setTargetObid(String targetObid){
        this.targetObid = targetObid;
    }
    public void    setStage2essentialflag(String stage2essentialflag){
        this.stage2essentialflag = stage2essentialflag;
    }
    public void    setStage3essentialflag(String stage3essentialflag){
        this.stage3essentialflag = stage3essentialflag;
    }
    public void    setStage4essentialflag(String stage4essentialflag){
        this.stage4essentialflag = stage4essentialflag;
    }
    public void    setDistributeeessentialflag(String distributeeessentialflag){
        this.distributeeessentialflag = distributeeessentialflag;
    }
    public String getObidCategory(){
        return obidCategory;
    }
    public String getStage1approverlist(){
        return stage1approverlist;
    }
    public String getStage2approverlist(){
        return stage2approverlist;
    }
    public String getStage3approverlist(){
        return stage3approverlist;
    }
    public String getStage4approverlist(){
        return stage4approverlist;
    }
    public String getDistributeelist(){
        return distributeelist;
    }
    public String getOpType(){
        return opType;
    }
    public String getStage1essentialflag(){
        return stage1essentialflag;
    }
    public String getTargetObid(){
        return targetObid;
    }
    public String getStage2essentialflag(){
        return stage2essentialflag;
    }
    public String getStage3essentialflag(){
        return stage3essentialflag;
    }
    public String getStage4essentialflag(){
        return stage4essentialflag;
    }
    public String getDistributeeessentialflag(){
        return distributeeessentialflag;
    }
}

