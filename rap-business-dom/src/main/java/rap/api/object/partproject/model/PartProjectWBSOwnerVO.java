/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartProjectWBSOwnerVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartProjectWBSOwnerVO extends BusinessObjectVO {
    private String        partActivityCode                                  ;
    private String        ownerUserId                                       ;
    private String        resultContents                                    ;


    public void    setPartActivityCode(String partActivityCode){
        this.partActivityCode = partActivityCode;
    }
    public void    setOwnerUserId(String ownerUserId){
        this.ownerUserId = ownerUserId;
    }
    public void    setResultContents(String resultContents){
        this.resultContents = resultContents;
    }
    public String getPartActivityCode(){
        return partActivityCode;
    }
    public String getOwnerUserId(){
        return ownerUserId;
    }
    public String getResultContents(){
        return resultContents;
    }
}

