/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AssetMinorVO.java
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
public class AssetMinorVO extends BusinessObjectMasterVO {
    private String        corporationCode                                   ;
    private String        assetMinorCode                                    ;
    private String        assetMajorCode                                    ;
    private String        assetMinorKorName                                 ;
    private String        assetMinorEngName                                 ;
    private String        assetTypeCode                                     ;
    private Integer       sorting                                           ;
    private String        useYn                                             ;


    public void    setCorporationCode(String corporationCode){
        this.corporationCode = corporationCode;
    }
    public void    setAssetMinorCode(String assetMinorCode){
        this.assetMinorCode = assetMinorCode;
    }
    public void    setAssetMajorCode(String assetMajorCode){
        this.assetMajorCode = assetMajorCode;
    }
    public void    setAssetMinorKorName(String assetMinorKorName){
        this.assetMinorKorName = assetMinorKorName;
    }
    public void    setAssetMinorEngName(String assetMinorEngName){
        this.assetMinorEngName = assetMinorEngName;
    }
    public void    setAssetTypeCode(String assetTypeCode){
        this.assetTypeCode = assetTypeCode;
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
    public String getAssetMinorCode(){
        return assetMinorCode;
    }
    public String getAssetMajorCode(){
        return assetMajorCode;
    }
    public String getAssetMinorKorName(){
        return assetMinorKorName;
    }
    public String getAssetMinorEngName(){
        return assetMinorEngName;
    }
    public String getAssetTypeCode(){
        return assetTypeCode;
    }
    public Integer getSorting(){
        return sorting;
    }
    public String getUseYn(){
        return useYn;
    }
}

