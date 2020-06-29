/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasTeamRevVO.java
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
public class HasTeamRevVO extends BusinessRelationObjectVO {
    private String        mainFlag                                          ;


    public void    setMainFlag(String mainFlag){
        this.mainFlag = mainFlag;
    }
    public String getMainFlag(){
        return mainFlag;
    }
}

