/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CacheInterfaceImpl.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 18. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.classes.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcComUtility;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.foundation.classes.model.AllowedClassInfo;
import com.rap.omc.foundation.classes.model.ClassDbmsTableVO;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.model.ClassNameForDisplayVO;
import com.rap.omc.foundation.classes.model.ColumnInfo;
import com.rap.omc.foundation.classes.service.ClassInfoSubService;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaUtil;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : ClassInfoServiceImpl
 * Description : TODO
 * </pre>
 *
 * @author hyeyoung.park
 */
@Service("classInfoSubService")
public class ClassInfoSubServiceImpl implements ClassInfoSubService {
    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;
    
    @Cacheable(value = "classInfoCache", key = "#className")
    public ClassInfo getClassInfo(String className){
        return getClassInfo("sqlSessionFactory", className);
    }
    @Cacheable(value = "classInfoCache", key = "#className")
    public ClassInfo getClassInfo(String sessionFactoryName, String className){
        StringBuffer sqlStrBuff = new StringBuffer(); 
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter(); 
        sqlStrBuff.append                                 ("select a.pnames               as class_name"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", (select x.pnames from psysbizobjectclassinfo x where x.obid = a.pparent_obid)    as class_name_parent");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pdbms_table          as dbms_table"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pjava_package        as java_package"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", (select x.pjava_package from psysbizobjectclassinfo x where x.obid = a.pparent_obid)    as java_package_parent");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.obid                 as obid"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pflags               as flags"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pparent_obid         as parent_obid"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pdisplayed_name      as displayed_name"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pdisplayed_name_kr   as displayed_name_kr" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pdefault_policy      as default_policy"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pworkflow_url        as workflow_url"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pclass_icon          as class_icon"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pclass_icon_small    as class_icon_small"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.pupper_class_list    as upper_class_list_str"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.plower_class_list    as lower_class_list_str"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.pflags               as class_info_flags"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysbizobjectclassinfo a, psysclassinfo b");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames = #{funVariable_00001}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid = b.pclass_obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        variableParameter.setFunVariable_00001(className);
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Active));
        variableParameter.setSql(sqlStrBuff.toString());
        ClassInfo keyInfo = schemaDao.select("ClassInfo.getDynamicBizClassInfo", variableParameter);
        if (NullUtil.isNull(keyInfo)) {
            variableParameter = new OmcSQLVariableParameter(); 
            sqlStrBuff.setLength(0);
            sqlStrBuff.append                                 ("select a.pnames               as class_name"         );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", (select x.pnames from psysrelobjectclassinfo x where x.obid = a.pparent_obid)    as class_name_parent");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pdbms_table          as dbms_table"         );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pjava_package        as java_package"       );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", (select x.pjava_package from psysrelobjectclassinfo x where x.obid = a.pparent_obid)    as java_package_parent");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.obid                 as obid"               );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pflags               as flags"              );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pparent_obid         as parent_obid"        );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pdisplayed_name      as displayed_name"     );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pdisplayed_name_kr   as displayed_name_kr"  );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pcardinality_from    as cardinality_from"   );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pcardinality_to      as cardinality_to"     );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.prevision_rule_from  as revision_rule_from" );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.prevision_rule_to    as revision_rule_to"   );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.pupper_class_list    as upper_class_list_str"   );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.plower_class_list    as lower_class_list_str"   );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.pflags               as class_info_flags"   );
            sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysrelobjectclassinfo a, psysclassinfo b" );
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames = #{funVariable_00001}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid = b.pclass_obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
            variableParameter.setFunVariable_00001(className);
            variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.RELATION_FLAG_Active));
            variableParameter.setSql(sqlStrBuff.toString());
            keyInfo = schemaDao.select("ClassInfo.getDynamicRelClassInfo", variableParameter);
            
            if(NullUtil.isNull(keyInfo)) {
                OmcComUtility.error("Cannot Find Class(" + className + ").");
            }
                
                
            if (!NullUtil.isNull(keyInfo)){
                variableParameter = new OmcSQLVariableParameter(); 
                sqlStrBuff.setLength(0);
                sqlStrBuff.append                                 ("select a.pflags      as flags"     );
                sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pclass_obid as class_obid");
                sqlStrBuff.append(OmcFoundationConstant.newline).append(", (select concat(x.pclass_name,'.',x.pdbms_table)  from psysclassinfo x where a.pclass_obid        = x.pclass_obid) as class_name");
                sqlStrBuff.append(OmcFoundationConstant.newline).append(", (select concat(y.pclass_name,'.',y.pdbms_table)  from psysclassinfo y where a.prelationship_obid = y.pclass_obid) as relation_name");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysallowedclassforrel a, psysrelobjectclassinfo b, psysclassinfo c");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.prelationship_obid = #{funVariable_00001}");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.prelationship_obid = b.obid");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pclass_obid        = c.pclass_obid");
                sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
                variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.RELATION_FLAG_Active));
                
                variableParameter.setFunVariable_00001(keyInfo.getObid());
                variableParameter.setSql(sqlStrBuff.toString());
                List<AllowedClassInfo> allowedClassInfo  = schemaDao.selectList("ClassInfo.getDynamicAllowedClassInfo", variableParameter);
                keyInfo.setAllowedClassInfo(allowedClassInfo);
                
                
                Set<String> allowedFromClassSet = new HashSet<String>();
                Set<String> allowedToClassSet   = new HashSet<String>();
                if(!NullUtil.isNone(allowedClassInfo)){
                    for(AllowedClassInfo allowedVo : allowedClassInfo){
                        String[] strArray = allowedVo.getClassName().split(Pattern.quote("."));
                        if(Bit.isInclude(allowedVo.getFlags(),OmcSystemConstants.FLAG_ALLOWEDRELATION_FROM)){
                            allowedFromClassSet.add(strArray[0]);
                        }
                        if(Bit.isInclude(allowedVo.getFlags(),OmcSystemConstants.FLAG_ALLOWEDRELATION_TO)){
                            allowedToClassSet.add(strArray[0]);
                        }
                    }
                    //if(allowedFromClassSet.size() < 1 && Bit.isInclude(keyInfo.getFlags(), OmcSystemConstants.RELATION_FLAG_Instantiable)) OmcComUtility.error("Relation Definition Error(" + className + "). From class not defined.");
                    keyInfo.setAllowedFromClassSet(allowedFromClassSet);
                    //if(allowedToClassSet.size() < 1 && Bit.isInclude(keyInfo.getFlags(), OmcSystemConstants.RELATION_FLAG_Instantiable)) OmcComUtility.error("Relation Definition Error(" + className + "). To class not defined.");
                    keyInfo.setAllowedToClassSet(allowedToClassSet);
                }else{
                    //if(Bit.isInclude(keyInfo.getFlags(), OmcSystemConstants.RELATION_FLAG_Instantiable)) OmcComUtility.error("Relation Definition Error(" + className + "). From To Class not defined. ");
                }
            }
        }
        if (!NullUtil.isNull(keyInfo)){
            variableParameter = new OmcSQLVariableParameter(); 
            sqlStrBuff.setLength(0);
            sqlStrBuff.append                                 ("select distinct b.pflags      as flags"     );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.pclass_obid as class_obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", (select concat(x.pclass_name,'.',x.pdbms_table)  from psysclassinfo x where b.pclass_obid        = x.pclass_obid) as class_name");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", (select concat(y.pclass_name,'.',y.pdbms_table)  from psysclassinfo y where b.prelationship_obid = y.pclass_obid) as relation_name");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysallowedclassforrel a, psysallowedclassforrel b");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.prelationship_obid = b.prelationship_obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pclass_obid        = #{funVariable_00001}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and ((");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}",false));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00003}", "#{funVariable_00003}",false));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("or");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("(");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00003}", "#{funVariable_00003}",false));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00002}", "#{funVariable_00002}",false));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("))");
            variableParameter.setFunVariable_00001(keyInfo.getObid());
            variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.FLAG_ALLOWEDRELATION_FROM));
            variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.FLAG_ALLOWEDRELATION_TO));
            
            variableParameter.setSql(sqlStrBuff.toString());
            List<AllowedClassInfo> allowedClassInfo  = schemaDao.selectList("ClassInfo.getDynamicAllowedClassInfo", variableParameter);
            keyInfo.setRelatedClassInfo(allowedClassInfo);
        }
        if (!NullUtil.isNull(keyInfo)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("namesList", keyInfo.getLowerClassList());
            map.put("flags", OmcSystemConstants.CLASSINFO_FLAG_Instantiable);
            List<ClassDbmsTableVO> result = schemaDao.selectList("ClassInfo.getInstantiableClassList", map);
            keyInfo.setInstantiableClassList(result);
        
            map.put("flags", OmcSystemConstants.BUSINESS_FLAG_ComboDisplay);
            List<ClassNameForDisplayVO> result1 = schemaDao.selectList("ClassInfo.getChildClassListForDisplay", map);
            keyInfo.setChildClassListForCombo(result1);
            
            map = new HashMap<String, Object>();
            map.put("flagsClass", OmcSystemConstants.BUSINESS_FLAG_Active);
            map.put("flagsRelation", OmcSystemConstants.SYSLIFEINFO_FLAG_Active);
            map.put("kindsRelation", OmcSystemConstants.SYSLIFEINFO_KIND_Class);
            map.put("flagsLifeCycle", OmcSystemConstants.SYSLCYCLE_FLAG_Active);
            map.put("namesList", keyInfo.getUpperClassList());
            List<String> result2 = schemaDao.selectList("ClassInfo.getLifeCycleListForClass", map);
            keyInfo.setAllowedLifeCycleList(result2);
        }
        
        if (!NullUtil.isNull(keyInfo)) {
            //Column 정보 Setting
            List<ColumnInfo> columnList = schemaDao.selectList("ClassInfo.getColumnList", keyInfo.getClassName());
            keyInfo.setColumnList(columnList);
            //============================Class Menu 정보 Setting===========================================
            List<String> upperClassList = keyInfo.getUpperClassList();
            List<String> strList = null;
            if(!NullUtil.isNone(upperClassList)){
                Map<String,Object> map = new HashMap<String,Object>();
                String[] strArray = new String[upperClassList.size()];
                for(int i = 0; i < upperClassList.size(); i++){
                    strArray[i] = "mnu_" + upperClassList.get(i); 
                }
                map.put("namesList", strArray);
                map.put("flags", OmcSystemConstants.SYSMNU_FLAG_Active);
                map.put("objectKind", OmcSystemConstants.SYSMNU_KIND_ClassMenu);
                strList = schemaDao.selectList("MenuInfo.getMenuNameWithKind", map);
            }
            
            boolean isFound = false;
            if(!NullUtil.isNone(strList)){
                for(int i = 0; i < upperClassList.size(); i++){
                    if(isFound) break;
                    for(int j = 0; j < strList.size(); j++ ){
                        if(("mnu_" + upperClassList.get(i)).equals(strList.get(j))){
                            keyInfo.setClassMenu(strList.get(j));isFound =true; break;
                        }
                    }
                }
            }
           //=================================Structure Menu 정보 Setting=========================================
            strList = null;
            if(!NullUtil.isNone(upperClassList)){
                Map<String,Object> map = new HashMap<String,Object>();
                String[] strArray = new String[upperClassList.size()];
                for(int i = 0; i < upperClassList.size(); i++){
                    strArray[i] = "structure_" + upperClassList.get(i); 
                }
                map.put("namesList", strArray);
                map.put("flags", OmcSystemConstants.SYSMNU_FLAG_Active);
                map.put("objectKind", OmcSystemConstants.SYSMNU_KIND_StructureMenu);
                strList = schemaDao.selectList("MenuInfo.getMenuNameWithKind", map);
            }
            isFound = false;
            if(!NullUtil.isNone(strList)){
                for(int i = 0; i < upperClassList.size(); i++){
                    if(isFound) break;
                    for(int j = 0; j < strList.size(); j++ ){
                        if(("structure_" + upperClassList.get(i)).equals(strList.get(j))){
                            keyInfo.setStructureMenu(strList.get(j));isFound =true; break;
                        }
                    }
                }
            }
            //=================================Class Icon 정보 Setting==========================================
            strList = null;
            List<ClassInfo> classList = null;
            if(!NullUtil.isNone(upperClassList)){
                Map<String,Object> map = new HashMap<String,Object>();
                String[] strArray = new String[upperClassList.size()];
                for(int i = 0; i < upperClassList.size(); i++){
                    strArray[i] = upperClassList.get(i); 
                }
                map.put("namesList", strArray);
                classList = schemaDao.selectList("ClassInfo.getBizClassName", map);
            }
            isFound = false;
            if(!NullUtil.isNone(classList)){
                for(int i = 0; i < upperClassList.size(); i++){
                    if(isFound) break;
                    for(int j = 0; j < classList.size(); j++ ){
                        if((upperClassList.get(i)).equals(classList.get(j).getClassName()) && !StrUtil.isEmpty(classList.get(j).getClassIcon())){
                            keyInfo.setClassIconReal(classList.get(j).getClassIcon());
                            keyInfo.setClassIconSmallReal(classList.get(j).getClassIconSmall());
                            isFound =true; break;
                        }
                    }
                }
            }
        }
        return keyInfo;
    }
}
