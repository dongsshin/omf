/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : UserMenuAccessLogVO.java
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
public class UserMenuAccessLogVO extends BusinessObjectMasterVO {
    private String        userId                                            ;
    private String        menuId                                            ;
    private String        menuName                                          ;


    public void    setUserId(String userId){
        this.userId = userId;
    }
    public void    setMenuId(String menuId){
        this.menuId = menuId;
    }
    public void    setMenuName(String menuName){
        this.menuName = menuName;
    }
    public String getUserId(){
        return userId;
    }
    public String getMenuId(){
        return menuId;
    }
    public String getMenuName(){
        return menuName;
    }
}

