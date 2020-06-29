/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ECMSSummaryScenarioVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ECMSSummaryScenarioVO extends BusinessObjectMasterVO {
    private String        ecmsSenarioCode                                   ;
    private String        ecmsAccountCategory                               ;
    private String        yyyy                                              ;
    private Float         ecmsAmount                                        ;


    public void    setEcmsSenarioCode(String ecmsSenarioCode){
        this.ecmsSenarioCode = ecmsSenarioCode;
    }
    public void    setEcmsAccountCategory(String ecmsAccountCategory){
        this.ecmsAccountCategory = ecmsAccountCategory;
    }
    public void    setYyyy(String yyyy){
        this.yyyy = yyyy;
    }
    public void    setEcmsAmount(Float ecmsAmount){
        this.ecmsAmount = ecmsAmount;
    }
    public String getEcmsSenarioCode(){
        return ecmsSenarioCode;
    }
    public String getEcmsAccountCategory(){
        return ecmsAccountCategory;
    }
    public String getYyyy(){
        return yyyy;
    }
    public Float getEcmsAmount(){
        return ecmsAmount;
    }
}

