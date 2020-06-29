/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : FoundationService.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 15. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.classes.service;

import java.util.List;

import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.foundation.common.model.KeyInfo;
import com.rap.omc.foundation.common.model.search.SearchTargetInfo;
import com.rap.omc.foundation.model.VariableAttribute;


/**
 * <pre>
 * Class : FoundationService
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public interface FoundationService {
    public List<String> getBusinessObjectMasters(String className,String names);
    
    public List<String> getBusinessObjects(String className,String names, String revision);
    
    public KeyInfo getKeyInfo(ObjectRootVO searchInfo);
    
    public KeyInfo getKeyInfo(String obid);

    public <T> List<T> getRelatedObjectList(SearchTargetInfo searchInfo);
    
    public List<String> getBusinessObjectMasterList(String className,List<String> nameList);
    
    public List<String> getBusinessLatestObjectList(String className,List<String> nameList);
    
    public List<BusinessRelationObjectVO> getRelationList(SearchTargetInfo searchInfo);

    public List<VariableAttribute> getDynamicAttributeList(String dynamicAttributeGroup);

}
