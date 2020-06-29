/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : BusinessObjectMaster.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 6. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.object.dom;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

/**
 * <pre>
 * Class : BusinessObjectMaster
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class BusinessObjectMaster extends BusinessObjectRoot {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessObjectMaster.class);

    @Override
    public String toString(){
        return "BusinessObjectMaster [toString()=" + super.toString() + "]";
    }

    /**
     * @param vo
     */
    public BusinessObjectMaster(BusinessObjectMasterVO vo) {
        super(vo);
    }

    public BusinessObjectMaster(String obid) {
        super(obid);
    }
    public BusinessObjectMaster(String obid, boolean withOutData) {
        super(obid,withOutData);
    }
    @Override
    public BusinessObjectMasterVO getVo(){
        return (BusinessObjectMasterVO)super.getVo();
    }

    @Override
    public void initialize(){
        super.initialize();
        initializeBusinessObjectMaster();
    }

    public void initializeBusinessObjectMaster(){
        LOGGER.debug("initVO() from BusinessObjectMaster");
    }
    /**
     * Business Object Master return함.
     *
     * @param className className Class Name
     * @param names names
     * @return Business Object Master
     */
    @SuppressWarnings("unchecked")
    public static final <T> T  findBusinessObjectMaster(String className, String names){
        List<String> obidList = BaseFoundationUtil.getBusinessObjectMasters(className,names);
        if(NullUtil.isNone(obidList)) return null;
        if(obidList.size() > 1) throw new FoundationException("");
        BusinessObjectMaster dom = new BusinessObjectMaster(obidList.get(0));
        return ((T)dom.getVo());
    }
    /**
     * name List을 가지고 Business Object Master List를 return함.
     *
     * @param className Class Name
     * @param nameList name List
     * @return Business Object Master List
     * @see BusinessObjectMaster#getBusinessObjectMasters(String className,List nameList, boolean notFoundIgnore)
     */
    public static final <T> List<T> getBusinessObjectMasters(String className,List<String> nameList){
        return getBusinessObjectMasters(className,nameList,false);
    }
    /**
     * name List을 가지고 Business Object Master List를 return함.
     *
     * @param className Class Name
     * @param nameList name List
     * @param notFoundIgnore 
     * @return Business Object Master List
     */
    public static final <T> List<T> getBusinessObjectMasters(String className,List<String> nameList, boolean notFoundIgnore){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertList2Str(nameList));
        return ObjectRoot.findObjects(className, "", wherePattern.toString(), parameterPattern.toString());
    }
    /**
     * <pre>
     * name Set을 가지고 Business Object Master를 return함.
     *
      {@code
        Set<String> accountCodeSet = ObjectRoot.getDistinctValueSet(indirectAllocResult, "accountCode", true);
        List<AccountVO> accountVOList = BusinessObjectMaster.getBusinessObjectMasters(ApplicationSchemaConstants.BIZCLASS_ACCOUNT, accountCodeSet);
      }
      </pre>
     * @param className
     * @param nameSet
     * @return Business Object Master List
     */
    public static final <T> List<T> getBusinessObjectMasters(String className,Set<String> nameSet){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(nameSet));
        return ObjectRoot.findObjects(className, "", wherePattern.toString(), parameterPattern.toString());
    }
    @Override
    protected final void CloneCorePre(BusinessObjectRootVO newVO){
        LOGGER.debug(">> 0. BusinessMaster CloneCorePre() -- {}", newVO);
    }
}
