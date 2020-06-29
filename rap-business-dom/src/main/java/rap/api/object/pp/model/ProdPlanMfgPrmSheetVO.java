/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanMfgPrmSheetVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pp.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProdPlanMfgPrmSheetVO extends BusinessObjectVO {
    private String        mfgPrmSheetId                                     ;
    private String        mfgPrmSheetGroupId                                ;
    private String        isLastVersion                                     ;
    private String        isLastReleased                                    ;
    private String        siteCode                                          ;
    private Integer       mainVersion                                       ;
    private Integer       subVersion                                        ;


    public void    setMfgPrmSheetId(String mfgPrmSheetId){
        this.mfgPrmSheetId = mfgPrmSheetId;
    }
    public void    setMfgPrmSheetGroupId(String mfgPrmSheetGroupId){
        this.mfgPrmSheetGroupId = mfgPrmSheetGroupId;
    }
    public void    setIsLastVersion(String isLastVersion){
        this.isLastVersion = isLastVersion;
    }
    public void    setIsLastReleased(String isLastReleased){
        this.isLastReleased = isLastReleased;
    }
    public void    setSiteCode(String siteCode){
        this.siteCode = siteCode;
    }
    public void    setMainVersion(Integer mainVersion){
        this.mainVersion = mainVersion;
    }
    public void    setSubVersion(Integer subVersion){
        this.subVersion = subVersion;
    }
    public String getMfgPrmSheetId(){
        return mfgPrmSheetId;
    }
    public String getMfgPrmSheetGroupId(){
        return mfgPrmSheetGroupId;
    }
    public String getIsLastVersion(){
        return isLastVersion;
    }
    public String getIsLastReleased(){
        return isLastReleased;
    }
    public String getSiteCode(){
        return siteCode;
    }
    public Integer getMainVersion(){
        return mainVersion;
    }
    public Integer getSubVersion(){
        return subVersion;
    }
}

