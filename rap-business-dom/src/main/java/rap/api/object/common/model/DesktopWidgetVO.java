/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DesktopWidgetVO.java
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
public class DesktopWidgetVO extends DesktopItemVO {
    private String        widgetName                                        ;
    private String        targetModule                                      ;


    public void    setWidgetName(String widgetName){
        this.widgetName = widgetName;
    }
    public void    setTargetModule(String targetModule){
        this.targetModule = targetModule;
    }
    public String getWidgetName(){
        return widgetName;
    }
    public String getTargetModule(){
        return targetModule;
    }
}

