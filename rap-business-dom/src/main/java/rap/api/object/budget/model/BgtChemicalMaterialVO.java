/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BgtChemicalMaterialVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.budget.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BgtChemicalMaterialVO extends BusinessObjectMasterVO {
    private String        chemicalmaterial                                  ;
    private String        sitecode                                          ;


    public void    setChemicalmaterial(String chemicalmaterial){
        this.chemicalmaterial = chemicalmaterial;
    }
    public void    setSitecode(String sitecode){
        this.sitecode = sitecode;
    }
    public String getChemicalmaterial(){
        return chemicalmaterial;
    }
    public String getSitecode(){
        return sitecode;
    }
}

