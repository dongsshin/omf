/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DerivationCheckListUserVO.java
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
public class DerivationCheckListUserVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        checkItem                                         ;
    private String        item                                              ;
    private String        userId                                            ;
    private String        userType                                           = "ENG";


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setCheckItem(String checkItem){
        this.checkItem = checkItem;
    }
    public void    setItem(String item){
        this.item = item;
    }
    public void    setUserId(String userId){
        this.userId = userId;
    }
    public void    setUserType(String userType){
        this.userType = userType;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getCheckItem(){
        return checkItem;
    }
    public String getItem(){
        return item;
    }
    public String getUserId(){
        return userId;
    }
    public String getUserType(){
        return userType;
    }
}

