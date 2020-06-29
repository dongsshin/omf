/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OEMProjectVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.OutSideProjectsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class OEMProjectVO extends OutSideProjectsVO {
    private String        oemName                                           ;


    public void    setOemName(String oemName){
        this.oemName = oemName;
    }
    public String getOemName(){
        return oemName;
    }
}

