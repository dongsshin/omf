/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectImageNoncdVO.java
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
public class ProjectImageNoncdVO extends BusinessObjectMasterVO {
    private String        commCode                                          ;
    private Integer       uidx                                              ;


    public void    setCommCode(String commCode){
        this.commCode = commCode;
    }
    public void    setUidx(Integer uidx){
        this.uidx = uidx;
    }
    public String getCommCode(){
        return commCode;
    }
    public Integer getUidx(){
        return uidx;
    }
}

