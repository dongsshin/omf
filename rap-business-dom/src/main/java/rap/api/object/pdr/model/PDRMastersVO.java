/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PDRMastersVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PDRMastersVO extends BusinessObjectVO {
    private Integer       spechistno                                        ;


    public void    setSpechistno(Integer spechistno){
        this.spechistno = spechistno;
    }
    public Integer getSpechistno(){
        return spechistno;
    }
}

