/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : BusinessUnitUtil.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2015. 9. 16.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.util;

import java.util.List;

import org.springframework.util.StringUtils;

import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;

import rap.api.object.organization.model.BusinessUnitVO;
import rap.api.object.organization.model.PlantUnitVO;
import rap.application.constants.ApplicationSchemaConstants;



/**
 * <pre>
 * Class : BusinessUnitUtil
 * Description : TODO
 * </pre>
 *
 * @author jongjung.kwon
 */
public class BusinessUnitUtil {

    public static String getBusinessUnit(){
        return ThreadLocalUtil.getString(ThreadLocalUtil.KEY.businessUnit, "");
    }

    /**
     * Site 기준으로 BusinessUnitCode 조회
     * @param site
     * @return
     */
    public static String getBusinessUnit( String site ){
        String businessUnitCode = "";

        if( !StringUtils.isEmpty(site) ){
            PlantUnitVO plantUnitVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_PLANTUNIT, site, false);
            if( plantUnitVO != null ){
                businessUnitCode = plantUnitVO.getBusinessUnitCode();
            }
        }

        return businessUnitCode;
    }

    /**
     * Site 기준으로 BusinessUnitCode 조회
     * @param site
     * @return
     */
    public static String getDivisionUnit( String site ){
        String businessCode = "";
        if( !StringUtils.isEmpty(site) ){
            PlantUnitVO plantUnitVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_PLANTUNIT, site, false);
            if( plantUnitVO != null ){
                businessCode = plantUnitVO.getDivisionCode();
            }
        }

        return businessCode;
    }

    /**
     * 사용자 BusinessUnitVO 목록 조회
     * @param site
     * @return
     */
    public static List<BusinessUnitVO> getBusinessUnitList(){
        return ObjectRoot.searchObjects(
                ApplicationSchemaConstants.BIZCLASS_BUSINESSUNIT,
                getBusinessUnit(),
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE,
                false,
                0
        );
    }

}
