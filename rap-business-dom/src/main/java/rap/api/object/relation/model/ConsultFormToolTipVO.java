/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultFormToolTipVO.java
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
public class ConsultFormToolTipVO extends BusinessRelationObjectVO {
    private String        toolTipKor                                        ;
    private String        toolTipEng                                        ;


    public void    setToolTipKor(String toolTipKor){
        this.toolTipKor = toolTipKor;
    }
    public void    setToolTipEng(String toolTipEng){
        this.toolTipEng = toolTipEng;
    }
    public String getToolTipKor(){
        return toolTipKor;
    }
    public String getToolTipEng(){
        return toolTipEng;
    }
}

