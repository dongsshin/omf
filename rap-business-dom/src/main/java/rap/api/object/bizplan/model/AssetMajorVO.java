/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AssetMajorVO.java
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
public class AssetMajorVO extends BusinessObjectMasterVO {
    private String        corporationCode                                   ;
    private String        assetMajorCode                                    ;
    private String        assetMajorKorName                                 ;
    private String        assetMajorEngName                                 ;
    private Integer       sorting                                           ;
    private String        useYn                                             ;


    public void    setCorporationCode(String corporationCode){
        this.corporationCode = corporationCode;
    }
    public void    setAssetMajorCode(String assetMajorCode){
        this.assetMajorCode = assetMajorCode;
    }
    public void    setAssetMajorKorName(String assetMajorKorName){
        this.assetMajorKorName = assetMajorKorName;
    }
    public void    setAssetMajorEngName(String assetMajorEngName){
        this.assetMajorEngName = assetMajorEngName;
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
    public String getAssetMajorCode(){
        return assetMajorCode;
    }
    public String getAssetMajorKorName(){
        return assetMajorKorName;
    }
    public String getAssetMajorEngName(){
        return assetMajorEngName;
    }
    public Integer getSorting(){
        return sorting;
    }
    public String getUseYn(){
        return useYn;
    }
}

