/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DesktopTileVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.model;


import rap.api.object.common.model.DesktopItemVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DesktopTileVO extends DesktopItemVO {
    private String        tileType                                          ;
    private String        href                                              ;
    private String        iconUrl                                           ;
    private String        iconType                                          ;
    private String        targetObid                                        ;


    public void    setTileType(String tileType){
        this.tileType = tileType;
    }
    public void    setHref(String href){
        this.href = href;
    }
    public void    setIconUrl(String iconUrl){
        this.iconUrl = iconUrl;
    }
    public void    setIconType(String iconType){
        this.iconType = iconType;
    }
    public void    setTargetObid(String targetObid){
        this.targetObid = targetObid;
    }
    public String getTileType(){
        return tileType;
    }
    public String getHref(){
        return href;
    }
    public String getIconUrl(){
        return iconUrl;
    }
    public String getIconType(){
        return iconType;
    }
    public String getTargetObid(){
        return targetObid;
    }
}

