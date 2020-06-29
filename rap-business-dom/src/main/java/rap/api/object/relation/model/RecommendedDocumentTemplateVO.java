/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : RecommendedDocumentTemplateVO.java
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
public class RecommendedDocumentTemplateVO extends BusinessRelationObjectVO {
    private String        isMandantory                                      ;
    private String        skipGradeList                                      = "None";
    private String        phaseName                                         ;


    public void    setIsMandantory(String isMandantory){
        this.isMandantory = isMandantory;
    }
    public void    setSkipGradeList(String skipGradeList){
        this.skipGradeList = skipGradeList;
    }
    public void    setPhaseName(String phaseName){
        this.phaseName = phaseName;
    }
    public String getIsMandantory(){
        return isMandantory;
    }
    public String getSkipGradeList(){
        return skipGradeList;
    }
    public String getPhaseName(){
        return phaseName;
    }
}

