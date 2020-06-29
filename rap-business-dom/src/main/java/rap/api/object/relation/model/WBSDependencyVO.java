/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WBSDependencyVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class WBSDependencyVO extends BusinessRelationObjectVO {
    private String        isMandantory                                       = "N";
    private String        dependencyType                                     = "FS";


    public void    setIsMandantory(String isMandantory){
        this.isMandantory = isMandantory;
    }
    public void    setDependencyType(String dependencyType){
        this.dependencyType = dependencyType;
    }
    public String getIsMandantory(){
        return isMandantory;
    }
    public String getDependencyType(){
        return dependencyType;
    }
}

