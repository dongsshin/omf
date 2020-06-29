/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WorkflowObjectRouteVO.java
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
public class WorkflowObjectRouteVO extends BusinessRelationObjectVO {
    private String        routePurpose                                       = "Standard";
    private String        routeLifeCycle                                    ;
    private String        routeState                                        ;


    public void    setRoutePurpose(String routePurpose){
        this.routePurpose = routePurpose;
    }
    public void    setRouteLifeCycle(String routeLifeCycle){
        this.routeLifeCycle = routeLifeCycle;
    }
    public void    setRouteState(String routeState){
        this.routeState = routeState;
    }
    public String getRoutePurpose(){
        return routePurpose;
    }
    public String getRouteLifeCycle(){
        return routeLifeCycle;
    }
    public String getRouteState(){
        return routeState;
    }
}

