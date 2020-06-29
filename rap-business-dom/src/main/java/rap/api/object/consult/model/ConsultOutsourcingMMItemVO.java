/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultOutsourcingMMItemVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.consult.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ConsultOutsourcingMMItemVO extends BusinessObjectMasterVO {
    private String        yyyymm                                            ;
    private Float         mm                                                ;


    public void    setYyyymm(String yyyymm){
        this.yyyymm = yyyymm;
    }
    public void    setMm(Float mm){
        this.mm = mm;
    }
    public String getYyyymm(){
        return yyyymm;
    }
    public Float getMm(){
        return mm;
    }
}

