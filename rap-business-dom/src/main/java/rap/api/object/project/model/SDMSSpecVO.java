/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SDMSSpecVO.java
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
public class SDMSSpecVO extends BusinessObjectMasterVO {
    private String        modelSuffix                                       ;
    private Float         specVersion                                       ;
    private String        releaseYyyymmdd                                   ;
    private String        releaseBy                                         ;


    public void    setModelSuffix(String modelSuffix){
        this.modelSuffix = modelSuffix;
    }
    public void    setSpecVersion(Float specVersion){
        this.specVersion = specVersion;
    }
    public void    setReleaseYyyymmdd(String releaseYyyymmdd){
        this.releaseYyyymmdd = releaseYyyymmdd;
    }
    public void    setReleaseBy(String releaseBy){
        this.releaseBy = releaseBy;
    }
    public String getModelSuffix(){
        return modelSuffix;
    }
    public Float getSpecVersion(){
        return specVersion;
    }
    public String getReleaseYyyymmdd(){
        return releaseYyyymmdd;
    }
    public String getReleaseBy(){
        return releaseBy;
    }
}

