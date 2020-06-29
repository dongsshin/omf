/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : BusinessStructuredObject.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 20.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.object.dom;

import com.rap.omc.api.object.model.BusinessObjectVO;
import com.rap.omc.api.object.model.BusinessStructuredObjectVO;


/**
 * <pre>
 * Class : BusinessStructuredObject
 * Description : Structured Object에 대한 관리를 위한 Class. 아직 구현되지 않음.
 * </pre>
 * 
 * @author s_dongsshin
 */
public class BusinessStructuredObject extends BusinessObject{
    public BusinessStructuredObject(String obid, boolean withOutData) {
        super(obid,withOutData);
    }
    /**
     * @param vo
     */
    public BusinessStructuredObject(BusinessObjectVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }
    public BusinessStructuredObject(String obid) {
        super(obid);
    }
    @Override
    public BusinessStructuredObjectVO getVo(){
        return (BusinessStructuredObjectVO)super.getVo();
    }
}
