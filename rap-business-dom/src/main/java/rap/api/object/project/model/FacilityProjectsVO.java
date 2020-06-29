/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : FacilityProjectsVO.java
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
public class FacilityProjectsVO extends ProjectsVO {
    private String        lgPriCode                                         ;
    private String        developAmount                                     ;
    private String        newPlanPredictionYmd                              ;
    private String        involvedOrgCode                                   ;
    private String        pcSwYn                                            ;
    private String        b2bProjectStatus                                  ;


    public void    setLgPriCode(String lgPriCode){
        this.lgPriCode = lgPriCode;
    }
    public void    setDevelopAmount(String developAmount){
        this.developAmount = developAmount;
    }
    public void    setNewPlanPredictionYmd(String newPlanPredictionYmd){
        this.newPlanPredictionYmd = newPlanPredictionYmd;
    }
    public void    setInvolvedOrgCode(String involvedOrgCode){
        this.involvedOrgCode = involvedOrgCode;
    }
    public void    setPcSwYn(String pcSwYn){
        this.pcSwYn = pcSwYn;
    }
    public void    setB2bProjectStatus(String b2bProjectStatus){
        this.b2bProjectStatus = b2bProjectStatus;
    }
    public String getLgPriCode(){
        return lgPriCode;
    }
    public String getDevelopAmount(){
        return developAmount;
    }
    public String getNewPlanPredictionYmd(){
        return newPlanPredictionYmd;
    }
    public String getInvolvedOrgCode(){
        return involvedOrgCode;
    }
    public String getPcSwYn(){
        return pcSwYn;
    }
    public String getB2bProjectStatus(){
        return b2bProjectStatus;
    }
}

