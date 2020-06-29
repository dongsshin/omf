/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DesktopItemVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DesktopItemVO extends BusinessObjectMasterVO {
    private String        attributes                                        ;


    public void    setAttributes(String attributes){
        this.attributes = attributes;
    }
    public String getAttributes(){
        return attributes;
    }
}

