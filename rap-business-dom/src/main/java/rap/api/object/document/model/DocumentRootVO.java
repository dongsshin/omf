/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DocumentRootVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.document.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DocumentRootVO extends BusinessObjectVO {
    private String        creationPlantUnit                                 ;
    private String        isVersionObject                                    = "false";
    private String        moveFilesToVersion                                 = "false";
    private String        creationOrganization                               = "None";
    private String        organizationClassName                              = "None";


    public void    setCreationPlantUnit(String creationPlantUnit){
        this.creationPlantUnit = creationPlantUnit;
    }
    public void    setIsVersionObject(String isVersionObject){
        this.isVersionObject = isVersionObject;
    }
    public void    setMoveFilesToVersion(String moveFilesToVersion){
        this.moveFilesToVersion = moveFilesToVersion;
    }
    public void    setCreationOrganization(String creationOrganization){
        this.creationOrganization = creationOrganization;
    }
    public void    setOrganizationClassName(String organizationClassName){
        this.organizationClassName = organizationClassName;
    }
    public String getCreationPlantUnit(){
        return creationPlantUnit;
    }
    public String getIsVersionObject(){
        return isVersionObject;
    }
    public String getMoveFilesToVersion(){
        return moveFilesToVersion;
    }
    public String getCreationOrganization(){
        return creationOrganization;
    }
    public String getOrganizationClassName(){
        return organizationClassName;
    }
}

