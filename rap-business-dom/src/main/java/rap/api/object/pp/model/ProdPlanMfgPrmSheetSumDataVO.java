/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanMfgPrmSheetSumDataVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pp.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProdPlanMfgPrmSheetSumDataVO extends BusinessObjectMasterVO {
    private String        mfgPrmSheetId                                     ;
    private String        mfgPrmCode                                        ;
    private String        countrySeries                                     ;
    private Integer       launchYear                                        ;
    private Integer       launchMonth                                       ;
    private String        launchBefore2year                                 ;
    private String        launchAfter2year                                  ;
    private Integer       endOfProductYear                                  ;
    private Integer       endOfProductMonth                                 ;
    private Integer       prmQty                                            ;


    public void    setMfgPrmSheetId(String mfgPrmSheetId){
        this.mfgPrmSheetId = mfgPrmSheetId;
    }
    public void    setMfgPrmCode(String mfgPrmCode){
        this.mfgPrmCode = mfgPrmCode;
    }
    public void    setCountrySeries(String countrySeries){
        this.countrySeries = countrySeries;
    }
    public void    setLaunchYear(Integer launchYear){
        this.launchYear = launchYear;
    }
    public void    setLaunchMonth(Integer launchMonth){
        this.launchMonth = launchMonth;
    }
    public void    setLaunchBefore2year(String launchBefore2year){
        this.launchBefore2year = launchBefore2year;
    }
    public void    setLaunchAfter2year(String launchAfter2year){
        this.launchAfter2year = launchAfter2year;
    }
    public void    setEndOfProductYear(Integer endOfProductYear){
        this.endOfProductYear = endOfProductYear;
    }
    public void    setEndOfProductMonth(Integer endOfProductMonth){
        this.endOfProductMonth = endOfProductMonth;
    }
    public void    setPrmQty(Integer prmQty){
        this.prmQty = prmQty;
    }
    public String getMfgPrmSheetId(){
        return mfgPrmSheetId;
    }
    public String getMfgPrmCode(){
        return mfgPrmCode;
    }
    public String getCountrySeries(){
        return countrySeries;
    }
    public Integer getLaunchYear(){
        return launchYear;
    }
    public Integer getLaunchMonth(){
        return launchMonth;
    }
    public String getLaunchBefore2year(){
        return launchBefore2year;
    }
    public String getLaunchAfter2year(){
        return launchAfter2year;
    }
    public Integer getEndOfProductYear(){
        return endOfProductYear;
    }
    public Integer getEndOfProductMonth(){
        return endOfProductMonth;
    }
    public Integer getPrmQty(){
        return prmQty;
    }
}

