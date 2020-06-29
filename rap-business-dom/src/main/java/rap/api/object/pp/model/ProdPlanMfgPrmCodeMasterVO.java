/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanMfgPrmCodeMasterVO.java
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
public class ProdPlanMfgPrmCodeMasterVO extends BusinessObjectMasterVO {
    private String        mfgPrmCode                                        ;
    private String        mfgPrmSheetGroupId                                ;
    private String        siteCode                                          ;
    private String        codeType                                          ;
    private String        startPrmSheetId                                   ;
    private String        useYn                                             ;
    private String        prmSheetId                                        ;
    private String        prmCode                                           ;


    public void    setMfgPrmCode(String mfgPrmCode){
        this.mfgPrmCode = mfgPrmCode;
    }
    public void    setMfgPrmSheetGroupId(String mfgPrmSheetGroupId){
        this.mfgPrmSheetGroupId = mfgPrmSheetGroupId;
    }
    public void    setSiteCode(String siteCode){
        this.siteCode = siteCode;
    }
    public void    setCodeType(String codeType){
        this.codeType = codeType;
    }
    public void    setStartPrmSheetId(String startPrmSheetId){
        this.startPrmSheetId = startPrmSheetId;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setPrmSheetId(String prmSheetId){
        this.prmSheetId = prmSheetId;
    }
    public void    setPrmCode(String prmCode){
        this.prmCode = prmCode;
    }
    public String getMfgPrmCode(){
        return mfgPrmCode;
    }
    public String getMfgPrmSheetGroupId(){
        return mfgPrmSheetGroupId;
    }
    public String getSiteCode(){
        return siteCode;
    }
    public String getCodeType(){
        return codeType;
    }
    public String getStartPrmSheetId(){
        return startPrmSheetId;
    }
    public String getUseYn(){
        return useYn;
    }
    public String getPrmSheetId(){
        return prmSheetId;
    }
    public String getPrmCode(){
        return prmCode;
    }
}

