/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AssetTypeVO.java
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
public class AssetTypeVO extends BusinessObjectMasterVO {
    private String        corporationCode                                   ;
    private String        assetTypeCode                                     ;
    private String        assetItFlag                                       ;
    private String        assetTypeKorName                                  ;
    private String        assetTypeEngName                                  ;
    private String        upAssetTypeCode                                   ;
    private Integer       sorting                                           ;
    private String        useYn                                             ;


    public void    setCorporationCode(String corporationCode){
        this.corporationCode = corporationCode;
    }
    public void    setAssetTypeCode(String assetTypeCode){
        this.assetTypeCode = assetTypeCode;
    }
    public void    setAssetItFlag(String assetItFlag){
        this.assetItFlag = assetItFlag;
    }
    public void    setAssetTypeKorName(String assetTypeKorName){
        this.assetTypeKorName = assetTypeKorName;
    }
    public void    setAssetTypeEngName(String assetTypeEngName){
        this.assetTypeEngName = assetTypeEngName;
    }
    public void    setUpAssetTypeCode(String upAssetTypeCode){
        this.upAssetTypeCode = upAssetTypeCode;
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
    public String getAssetTypeCode(){
        return assetTypeCode;
    }
    public String getAssetItFlag(){
        return assetItFlag;
    }
    public String getAssetTypeKorName(){
        return assetTypeKorName;
    }
    public String getAssetTypeEngName(){
        return assetTypeEngName;
    }
    public String getUpAssetTypeCode(){
        return upAssetTypeCode;
    }
    public Integer getSorting(){
        return sorting;
    }
    public String getUseYn(){
        return useYn;
    }
}

