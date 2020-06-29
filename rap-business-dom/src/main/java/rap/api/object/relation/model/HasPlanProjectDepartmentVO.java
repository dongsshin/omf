/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPlanProjectDepartmentVO.java
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
public class HasPlanProjectDepartmentVO extends BusinessRelationObjectVO {
    private String        mainYn                                            ;
    private String        versionObid                                       ;


    public void    setMainYn(String mainYn){
        this.mainYn = mainYn;
    }
    public void    setVersionObid(String versionObid){
        this.versionObid = versionObid;
    }
    public String getMainYn(){
        return mainYn;
    }
    public String getVersionObid(){
        return versionObid;
    }
}

