/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasTeamVO.java
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
public class HasTeamVO extends BusinessRelationObjectVO {
    private String        mainFlag                                          ;
    private String        moveEndMm                                         ;


    public void    setMainFlag(String mainFlag){
        this.mainFlag = mainFlag;
    }
    public void    setMoveEndMm(String moveEndMm){
        this.moveEndMm = moveEndMm;
    }
    public String getMainFlag(){
        return mainFlag;
    }
    public String getMoveEndMm(){
        return moveEndMm;
    }
}

