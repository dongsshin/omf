/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : InvestObjectVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class InvestObjectVO extends BusinessObjectMasterVO {
    private String        corporationCode                                   ;
    private String        investObjectCode                                  ;
    private String        investObjectKorName                               ;
    private String        investObjectEngName                               ;
    private Integer       sorting                                           ;
    private String        useYn                                             ;


    public void    setCorporationCode(String corporationCode){
        this.corporationCode = corporationCode;
    }
    public void    setInvestObjectCode(String investObjectCode){
        this.investObjectCode = investObjectCode;
    }
    public void    setInvestObjectKorName(String investObjectKorName){
        this.investObjectKorName = investObjectKorName;
    }
    public void    setInvestObjectEngName(String investObjectEngName){
        this.investObjectEngName = investObjectEngName;
    }
    public void    setSorting(Integer sorting){
        this.sorting = sorting;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public String getCorporationCode(){
        return corporationCode;
    }
    public String getInvestObjectCode(){
        return investObjectCode;
    }
    public String getInvestObjectKorName(){
        return investObjectKorName;
    }
    public String getInvestObjectEngName(){
        return investObjectEngName;
    }
    public Integer getSorting(){
        return sorting;
    }
    public String getUseYn(){
        return useYn;
    }
}

