/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : BusinessStructuredObjectMaster.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 20.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.object.dom;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import com.rap.omc.api.object.model.BusinessStructuredObjectMasterVO;


/**
 * <pre>
 * Class : BusinessStructuredObjectMaster
 * Description : Structured Object에 대한 관리를 위한 Class. 아직 구현되지 않음.
 * </pre>
 * 
 * @author s_dongsshin
 */
public class BusinessStructuredObjectMaster extends BusinessObjectMaster{
    public BusinessStructuredObjectMaster(String obid, boolean withOutData) {
        super(obid,withOutData);
    }
    /**
     * @param vo
     */
    public BusinessStructuredObjectMaster(BusinessObjectMasterVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }
    public BusinessStructuredObjectMaster(String obid) {
        super(obid);
    }
    @Override
    public BusinessStructuredObjectMasterVO getVo(){
        return (BusinessStructuredObjectMasterVO)super.getVo();
    }
}
