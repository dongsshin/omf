/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaUtilServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2017. 1. 31.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.foundation.menu.model.CommonMenuSearchVO;
import com.rap.omc.foundation.user.model.CommonUserSearchVO;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.object.dom.OmcSchemaAttribute;
import com.rap.omc.schema.object.dom.OmcSchemaBranch;
import com.rap.omc.schema.object.dom.OmcSchemaFileFormat;
import com.rap.omc.schema.object.dom.OmcSchemaFileLocation;
import com.rap.omc.schema.object.dom.OmcSchemaFileStore;
import com.rap.omc.schema.object.dom.OmcSchemaLayout;
import com.rap.omc.schema.object.dom.OmcSchemaLifeCycle;
import com.rap.omc.schema.object.dom.OmcSchemaMenu;
import com.rap.omc.schema.object.dom.OmcSchemaProperty;
import com.rap.omc.schema.object.dom.OmcSchemaRelation;
import com.rap.omc.schema.object.dom.OmcSchemaSite;
import com.rap.omc.schema.object.dom.OmcSchemaStateTrigger;
import com.rap.omc.schema.object.dom.OmcSchemaStates;
import com.rap.omc.schema.object.dom.OmcSchemaSysBizClass;
import com.rap.omc.schema.object.dom.OmcSchemaSysClassAttrInfo;
import com.rap.omc.schema.object.dom.OmcSchemaSysConstant;
import com.rap.omc.schema.object.dom.OmcSchemaSysRelClass;
import com.rap.omc.schema.object.dom.OmcSchemaTab;
import com.rap.omc.schema.object.dom.OmcSchemaTriggerParameter;
import com.rap.omc.schema.object.dom.OmcSchemaUserCommon;
import com.rap.omc.schema.object.dom.OmcSchemaUserGroup;
import com.rap.omc.schema.object.dom.OmcSchemaUserRole;
import com.rap.omc.schema.object.dom.OmcSchemaUserRoleGroup;
import com.rap.omc.schema.object.dom.OmcSchemaUserUser;
import com.rap.omc.schema.object.model.OmcSchemaAttributeVO;
import com.rap.omc.schema.object.model.OmcSchemaBranchVO;
import com.rap.omc.schema.object.model.OmcSchemaClassAttrRefVO;
import com.rap.omc.schema.object.model.OmcSchemaClassParentChildVO;
import com.rap.omc.schema.object.model.OmcSchemaDynamicAttrGrpVO;
import com.rap.omc.schema.object.model.OmcSchemaFileFormatVO;
import com.rap.omc.schema.object.model.OmcSchemaFileServerVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleBranchVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleStateInfoVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleVO;
import com.rap.omc.schema.object.model.OmcSchemaMenuVO;
import com.rap.omc.schema.object.model.OmcSchemaMethodSetVO;
import com.rap.omc.schema.object.model.OmcSchemaPropertyVO;
import com.rap.omc.schema.object.model.OmcSchemaRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaRoleGroupVO;
import com.rap.omc.schema.object.model.OmcSchemaSeperatedBranchVO;
import com.rap.omc.schema.object.model.OmcSchemaSeperatedClassVO;
import com.rap.omc.schema.object.model.OmcSchemaSeperatedFormatVO;
import com.rap.omc.schema.object.model.OmcSchemaSiteVO;
import com.rap.omc.schema.object.model.OmcSchemaStateTriggerVO;
import com.rap.omc.schema.object.model.OmcSchemaStatesVO;
import com.rap.omc.schema.object.model.OmcSchemaStoreLocationVO;
import com.rap.omc.schema.object.model.OmcSchemaSysAllowedClsForRelVO;
import com.rap.omc.schema.object.model.OmcSchemaSysBizClassVO;
import com.rap.omc.schema.object.model.OmcSchemaSysClassAttrInfoVO;
import com.rap.omc.schema.object.model.OmcSchemaSysClassInfoVO;
import com.rap.omc.schema.object.model.OmcSchemaSysConstantVO;
import com.rap.omc.schema.object.model.OmcSchemaSysKeyVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRelClassVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.object.model.OmcSchemaSysTimeZoneVO;
import com.rap.omc.schema.object.model.OmcSchemaTabLayoutVO;
import com.rap.omc.schema.object.model.OmcSchemaTriggerParameterVO;
import com.rap.omc.schema.object.model.OmcSchemaUserVO;
import com.rap.omc.schema.service.OmcSchemaUtilService;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcDBMSConstants;
import com.rap.omc.schema.util.OmcSchemaConstants;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSchemaUtil;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.schema.util.OmcUniqueIDGenerator;
import com.rap.omc.util.NullUtil;
/**
 * <pre>
 * Class : OmcSchemaUtilServiceImpl
 * Description : TODO
 * </pre>
 *
 * @author s_dongsshin
 */
@Service("omcSchemaUtilService")
@SuppressWarnings("rawtypes")
public class OmcSchemaUtilServiceImpl implements OmcSchemaUtilService {

    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;
        
    public void testPmsConnect(){
        String str = schemaDao.select("SchemaNew.selectDual", null);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemKeyTable(List<OmcSchemaSysKeyVO> parmListVO){
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("list", parmListVO);
        schemaDao.insert("createSystemKeyTableBatch", map);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemKeyTable(OmcSchemaSysKeyVO parmVO){
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    public OmcSchemaSysKeyVO getSystemKeyTableByObid(String obid){
        Map map = new HashMap<String,String>();
        map.put("obid", obid);
        OmcSchemaSysKeyVO result = schemaDao.select("SchemaNew.getSystemKeyTable", map);
        return(result);
    }
    @Override
    public List<OmcSchemaSysKeyVO> getSystemKeyTableByObid(List<String> obid){
        int maxCount = OmcSchemaUtil.getDbmsInMaxCount();
        if(NullUtil.isNone(obid)) return(null);
        int dataCount = obid.size();
        if (dataCount == 0) return(null);
        int runCount = 0;

        Map map = new HashMap<String,Object>();
        List<String> strList = new ArrayList<String>();

        if(dataCount == (dataCount/maxCount)*maxCount){
            runCount = dataCount/maxCount;
        }else{
            runCount = (dataCount/maxCount) + 1;
        }
        int cnt =  (obid.size()/maxCount + 1);
        Object[] objAry = new Object[runCount];

        int seq = 0;
        int idx = 0;

        for(String obj : obid){
            if((seq%maxCount) == 0 && seq != 0){
                objAry[idx] = strList;
                strList = new ArrayList<String>();
                idx++;
            }
            strList.add(obj);
            seq++;
        }
        if(!NullUtil.isNone(strList)) objAry[idx] = strList;
        List<OmcSchemaSysKeyVO> resultAll = new ArrayList<OmcSchemaSysKeyVO>();
        for(int i = 0; i < objAry.length; i++){
            map.put("obidList", objAry[i]);
            List<OmcSchemaSysKeyVO> result = schemaDao.selectList("SchemaNew.getSystemKeyTable", map);
            if(!NullUtil.isNone(result)) resultAll.addAll(result);
        }
        return(resultAll);
    }
    @Override
    public String getObid(int classKinds){
        String[] str = getObidSub(classKinds,1);
        OmcSchemaSysKeyVO vo = new OmcSchemaSysKeyVO();
        vo.setKinds(classKinds);
        vo.setObid(str[0]);
        List<OmcSchemaSysKeyVO> voList = new ArrayList<OmcSchemaSysKeyVO>();
        voList.add(vo);
        OmcSchemaServiceUtils.createSystemKeyTable(voList);
        return(str[0]);
    }
    @Override
    public void initialSchemaSetupMain(String defaultSite){
        initialSchemaSetupDefaultKeyTabe();
        //initialDefaultSiteCreate(defaultSite);
        //Excel Upload에서 생성되어지므로 수행하지 않음
        //initialSchemaSetupSysUsers(defaultSite); 
    }
    private void initialSchemaSetupDefaultKeyTabe(){
        OmcSchemaSysKeyVO parmVO = new OmcSchemaSysKeyVO();
        parmVO.setObid(OmcSchemaConstants.C_SCHEMA_DUMMY_KEY);
        parmVO.setKinds(0);
        createSystemKeyTable(parmVO);
        parmVO = new OmcSchemaSysKeyVO();
        parmVO.setObid(OmcSchemaConstants.C_SCHEMA_00000000000000000000);
        parmVO.setKinds(30);
        createSystemKeyTable(parmVO);
    }
    private void initialDefaultSiteCreate(String defaultSite){
    	OmcSchemaSiteVO omcSchemaSiteVO = new OmcSchemaSiteVO();
    	omcSchemaSiteVO.setNames(defaultSite);
    	omcSchemaSiteVO.setDisplayedNames(defaultSite);
    	OmcSchemaSite omcSchemaSiteDom = new OmcSchemaSite(omcSchemaSiteVO);
    	omcSchemaSiteDom.createObject(new HashMap<String,Object>());
    	
    }
    private void initialSchemaSetupSysUsers(String defaultSite){
        OmcSchemaUserVO userVO = new OmcSchemaUserVO();
        userVO.setNames("XP3866");
        userVO.setDescriptions("LG CNS");
        userVO.setSequences(1);
        userVO.setOwners("XP3866");
        userVO.setKindsStr("User");
        userVO.setSite(defaultSite);
        userVO.setDepartmentCode("LGCNS");
        userVO.setDepartmentDesc("LG CNS");
        userVO.setEmailId("lgcns");
        userVO.setDepartmentDescKor("LG CNS");
        OmcSchemaUserUser userDom = new OmcSchemaUserUser(userVO);
        userDom.createObject(new HashMap<String,Object>());
        
        userVO = new OmcSchemaUserVO();
        userVO.setNames("Interface Agent");
        userVO.setDescriptions("Interface Agent");
        userVO.setSequences(2);
        userVO.setOwners("XP3866");
        userVO.setKindsStr("User");
        userVO.setSite(defaultSite);
        userVO.setDepartmentCode("Interface Agent");
        userVO.setDepartmentDesc("Interface Agent");
        userVO.setEmailId("Interface Agent");
        userVO.setDepartmentDescKor("Interface Agent");
        userDom = new OmcSchemaUserUser(userVO);
        userDom.createObject(new HashMap<String,Object>());
    }
    @Override
    public String[] getObid(int classKinds,int wantedCount){
        String[] str = getObidSub(classKinds,wantedCount);
        List<OmcSchemaSysKeyVO> voList = new ArrayList<OmcSchemaSysKeyVO>();
        for(String s : str){
            voList.add(new OmcSchemaSysKeyVO(s,classKinds));
        }
        OmcSchemaServiceUtils.createSystemKeyTable(voList);
        return(str);
    }
    private String[] getObidSub(int classKinds, int wantedCount){
        List<OmcSchemaSysKeyVO> sysKeyVOList = null;
        List<OmcSchemaSysKeyVO> keyList = new ArrayList<OmcSchemaSysKeyVO>();
        List<String> wntedObidList = new ArrayList<String>();
        int success = 0;
        do{
            List<String> obidList = new ArrayList<String>();
            for(int i = 0; i < wantedCount - success; i++){
                obidList.add(OmcUniqueIDGenerator.getObid());
            }
            sysKeyVOList = OmcSchemaServiceUtils.getSystemKeyTableByObid(obidList);
            if(NullUtil.isNone(sysKeyVOList)) {
                success = wantedCount;
                wntedObidList = obidList;
            }else{
                success = wantedCount - sysKeyVOList.size();
                for(String str: obidList){
                    boolean isOk = true;
                    for(OmcSchemaSysKeyVO vo : sysKeyVOList){
                        if(vo.getObid().equals(str)){
                            isOk = false;
                            break;
                        }
                    }
                    if(isOk) {
                        wntedObidList.add(str);
                        success++;
                    }
                }
            }
            if(success >= wantedCount) break;
        }
        while(true);
        String[] r = new String[wntedObidList.size()];
        int i = 0;
        for(String obid: wntedObidList){
            r[i++] = obid;
        }
        return(r);
    }
    public List<OmcSchemaRelationVO> getSchemaRelationList(OmcSchemaSysRootVO parmVo, long schemaKinds, boolean isActiveOnly){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaRelation.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");

        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pschema_kind", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(schemaKinds));
        if(isActiveOnly){
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
            variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSREL_FLAG_Active));
        }
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pfrom_obid = #{funVariable_00003}");
        variableParameter.setFunVariable_00003(parmVo.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaRelationVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelation", variableParameter);
        return(result);
    }

    /****************************************Property***************************************/
    @Override
    public void setBizClassProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        setCommonPropertySub(vo,OmcSystemConstants.SYSPTY_KIND_Class,propertyName,propertyValue);
    }
    @Override
    public void setRelClassProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        setCommonPropertySub(vo,OmcSystemConstants.SYSPTY_KIND_Class,propertyName,propertyValue);
    }
    @Override
    public void setLifeCycleProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        setCommonPropertySub(vo,OmcSystemConstants.SYSPTY_KIND_LifeCycle,propertyName,propertyValue);
    }
    @Override
    public void setStateProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        setCommonPropertySub(vo,OmcSystemConstants.SYSPTY_KIND_State,propertyName,propertyValue);
    }
    @Override
    public void setConstantsProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        setCommonPropertySub(vo,OmcSystemConstants.SYSPTY_KIND_Constants,propertyName,propertyValue);
    }
    @Override
    public void setUserProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        setCommonPropertySub(vo,OmcSystemConstants.SYSPTY_KIND_User,propertyName,propertyValue);
    }
    @Override
    public void setMenuProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        setCommonPropertySub(vo,OmcSystemConstants.SYSPTY_KIND_Menu,propertyName,propertyValue);
    }
    @Override
    public void setSiteProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        setCommonPropertySub(vo,OmcSystemConstants.SYSPTY_KIND_Site,propertyName,propertyValue);
    }
    @Override
    public void setAttributeProperty(OmcSchemaSysRootVO vo, String propertyName, String propertyValue ){
        setCommonPropertySub(vo,OmcSystemConstants.SYSPTY_KIND_Attribute,propertyName,propertyValue);
    }
    public void setUserProperty(OmcSchemaUserVO vo, String propertyName, String propertyValue ){
        setCommonPropertySub(vo,OmcSystemConstants.SYSPTY_KIND_User,propertyName,propertyValue);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    private void setCommonPropertySub(OmcSchemaSysRootVO sysRootVo, long kinds, String propertyName, String propertyValue ){
       StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaProperty.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1                                 ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.psys_object    = #{funVariable_00001}");
        variableParameter.setFunVariable_00001(sysRootVo.getObid());
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pproperty_name = #{funVariable_00002}");
        variableParameter.setFunVariable_00002(propertyName);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds", "#{funVariable_00003}", "#{funVariable_00003}"));
        variableParameter.setFunVariable_00003(String.valueOf(kinds));

        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaPropertyVO propertyVO = schemaDao.select("SchemaNew.dynamicRetrieveProperty", variableParameter);

        sqlStrBuff.setLength(0);
        if(propertyVO != null){
            String obid = propertyVO.getObid();
            propertyVO = new OmcSchemaPropertyVO();
            propertyVO.setPropertyValue(propertyValue);
            variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append(                                      "update psysproperty a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pproperty_value               = #{funVariable_00002}                   ,");
            variableParameter.setFunVariable_00002(propertyValue);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodifier                     = #{funVariable_00003}                   ,");
            variableParameter.setFunVariable_00003(propertyVO.getModifier());
            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid                        = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(obid);
            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }else if(!StrUtil.isEmpty(propertyValue)){
            propertyVO = new OmcSchemaPropertyVO();
            propertyVO.setSysObject(sysRootVo.getObid());
            propertyVO.setKinds(kinds);
            propertyVO.setPropertyName(propertyName);
            propertyVO.setPropertyValue(propertyValue);
            OmcSchemaProperty propertyDom = new OmcSchemaProperty(propertyVO);
            propertyDom.createObject(null);
        }
    }
    private String getCommonPropertySub(String obid, long kinds, String propertyName){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaProperty.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1                                 ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.psys_object    = #{funVariable_00001}");
        variableParameter.setFunVariable_00001(obid);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pproperty_name = #{funVariable_00002}");
        variableParameter.setFunVariable_00002(propertyName);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds", "#{funVariable_00003}", "#{funVariable_00003}"));
        variableParameter.setFunVariable_00003(String.valueOf(kinds));

        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaPropertyVO propertyVO = schemaDao.select("SchemaNew.dynamicSelect", variableParameter);
        sqlStrBuff.setLength(0);
        if(propertyVO == null) return null;
        return propertyVO.getPropertyValue();
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemProperty(OmcSchemaPropertyVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("              insert into psysproperty(obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psys_object"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pkinds"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pproperty_name"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pproperty_value"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{sysObject}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{kinds}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{propertyName}"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{propertyValue}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }

    /****************************************SystemConstants***************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemConstants(OmcSchemaSysConstantVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append(             "insert into psysconstants(obid "              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pkinds"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pkind_desc"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pconstant_values"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psequences"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",phex_converting_flag");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pchange_comments"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{kinds}"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{kindDesc}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{constantValues}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{sequences}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{hexConvertingFlag}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{changeComments}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemConstants(OmcSchemaSysConstantVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psysconstants a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pconstant_values              = #{constantValues}                  ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pkinds                        = #{kinds}                           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pkind_desc                    = #{kindDesc}                        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.psequences                    = #{sequences}                       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.phex_converting_flag          = #{hexConvertingFlag}               ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodifier                     = #{modifier}                        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid                        = #{obid}                            ");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemConstants(OmcSchemaSysConstantVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psysconstants a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags                        = #{flags}                           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodifier                     = #{modifier}                        ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid                          = #{obid}                            ");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemConstants(OmcSchemaSysConstantVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("delete psysconstants a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid = #{obid}");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", parmVO);
    }
    @Override
    public OmcSchemaSysConstantVO getSystemConstantsWithObid(String obid){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysConstant.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid = #{funVariable_00001}");
        variableParameter.setFunVariable_00010(obid);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaSysConstantVO result = schemaDao.select("SchemaNew.dynamicRetrieveConstants", variableParameter);
        return(result);
    }
    @Override
    public OmcSchemaSysConstantVO getSystemConstantsWithNames(String names){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysConstant.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pnames = #{funVariable_00001}");
        variableParameter.setFunVariable_00010(names);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaSysConstantVO result = schemaDao.select("SchemaNew.dynamicRetrieveConstants", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaSysConstantVO> getInactiveConstantsListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysConstant.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsysconstants x where a.pnames = x.pnames)");
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysConstantVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveConstants", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaSysConstantVO> getAllTemporaryListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysConstant.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysConstantVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveConstants", variableParameter);
        return(result);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryConstants(List<OmcSchemaSysConstantVO> constList){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("delete from zsysconstants");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("list", constList);
        schemaDao.insert("SchemaNew.createTemporaryConstantsBatch", map);
    }
    /*********************************************************************************************/
    /****************************************SystemTimeZone***************************************/
    /*********************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemTimeZone(OmcSchemaSysTimeZoneVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("              insert into psystimezone(obid"                     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"                   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"                   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pkinds"                   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pconstant_values"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psequences"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",phex_converting_flag"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"                );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"                );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{kinds}"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{values}"                );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{sequences}"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")"                         );
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemTimeZone(OmcSchemaSysTimeZoneVO parmVO){
        schemaDao.update("SchemaNew.modifySystemTimeZone", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemTimeZone(OmcSchemaSysTimeZoneVO parmVO){
        schemaDao.update("SchemaNew.modifySystemTimeZone", parmVO);
    }
    @Override
    public void deleteSystemTimeZone(OmcSchemaSysTimeZoneVO parmVO){
        schemaDao.delete("SchemaNew.deleteSystemTimeZone", parmVO);
    }
    @Override
    public OmcSchemaSysTimeZoneVO getSystemTimeZoneWithObid(String obid){
        OmcSchemaSysTimeZoneVO parmVO = new OmcSchemaSysTimeZoneVO();
        parmVO.setObid(obid);
        OmcSchemaSysTimeZoneVO result = schemaDao.select("SchemaNew.getSystemTimeZoneByObid", parmVO);
        return(result);
    }
    @Override
    public OmcSchemaSysTimeZoneVO getSystemTimeZoneWithNames(String names){
        OmcSchemaSysTimeZoneVO parmVO = new OmcSchemaSysTimeZoneVO();
        parmVO.setNames(names);
        OmcSchemaSysTimeZoneVO result = schemaDao.select("SchemaNew.getSystemTimeZoneByNames", parmVO);
        return(result);
    }
    /*********************************************************************************************/
    /****************************************SystemBizClass***************************************/
    /*********************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemBizClass(OmcSchemaSysBizClassVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psysbizobjectclassinfo    (obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdefault_policy");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdbms_table");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdisplayed_name");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdisplayed_name_kr");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pparent_obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pjava_package");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pworkflow_url");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psequences");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pchange_comments");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodule_name");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",premarks");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pclass_icon");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pclass_icon_small");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",powners");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{defaultPolicy}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{dbmsTable}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{displayedName}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{displayedNameKr}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{parentObid}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{javaPackage}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{workflowUrl}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{sequences}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{changeComments}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{moduleName}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{remarks}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{classIcon}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{classIconSmall}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{owners}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemBizClass(OmcSchemaSysBizClassVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psysbizobjectclassinfo a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pdefault_policy               = #{defaultPolicy}                   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdbms_table                   = #{dbmsTable}                       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pflags                        = #{flags}                           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdisplayed_name               = #{displayedName}                   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdisplayed_name_kr            = #{displayedNameKr}                 ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pparent_obid                  = #{parentObid}                      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pjava_package                 = #{javaPackage}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pworkflow_url                 = #{workflowUrl}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.psequences                    = #{sequences}                       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pchange_comments              = #{changeComments}                  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodule_name                  = #{moduleName}                      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.premarks                      = #{remarks}                         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pclass_icon                   = #{classIcon}                       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pclass_icon_small             = #{classIconSmall}                  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.powners                       = #{owners}                          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier                     = #{modifier}                        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid                          = #{obid}                            ");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemBizClass(OmcSchemaSysBizClassVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.BUSINESS_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.BUSINESS_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psysbizobjectclassinfo a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemBizClass(OmcSchemaSysBizClassVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete psysbizobjectclassinfo"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    @Override
    public OmcSchemaSysBizClassVO getSystemBizClassWithObid(String obid){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysBizClass.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(obid);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaSysBizClassVO result = schemaDao.select("SchemaNew.dynamicRetrieveBizClass", variableParameter);
        return(result);
    }
    @Override
    public OmcSchemaSysBizClassVO getSystemBizClassWithNames(boolean isTemporary, String names){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        variableParameter.setFunVariable_00001(names);
        if(isTemporary){
            OmcSchemaSysBizClass.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames = #{funVariable_00001}"          );
        }else{
            OmcSchemaSysBizClass.getCommonSelectSql(sqlStrBuff, variableParameter);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames  = #{funVariable_00001}"        );
        }
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaSysBizClassVO result = schemaDao.select("SchemaNew.dynamicRetrieveBizClass", variableParameter);
        return(result);
    }
    /**********************************************************************************************************/
    @Override
    public List<OmcSchemaSysBizClassVO> getFileBizClassListWithSeperatedList(List<OmcSchemaSeperatedClassVO> seperatedClassList){
        List<OmcSchemaSysBizClassVO> result = new ArrayList<OmcSchemaSysBizClassVO>();
        OmcSchemaSysBizClassVO rsltVO = new OmcSchemaSysBizClassVO();
        for(OmcSchemaSeperatedClassVO vo : seperatedClassList){
            rsltVO = this.getSystemBizClassWithNames(false,vo.getNames());
            if (rsltVO != null){
                result.add(rsltVO);
            }
        }
        return(result);
    }
    @Override
    public List<OmcSchemaSysBizClassVO> getChildSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, OmcSchemaSysBizClassVO parmVO, int wantedLevel){
        return getSystemBizClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_FORWARD,false,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysBizClassVO> getChildSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className, int wantedLevel){
        OmcSchemaSysBizClassVO parmVO = OmcSchemaServiceUtils.getSystemBizClassWithNames(className);
        if(NullUtil.isNull(parmVO)) return rslt;
        parmVO.setUniqueStr(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_UNIQSTR);
        return getSystemBizClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_FORWARD,false,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysBizClassVO> getParentSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, OmcSchemaSysBizClassVO parmVO, int wantedLevel){
        return getSystemBizClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_BACKWARD,false,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysBizClassVO> getParentSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className, int wantedLevel){
        OmcSchemaSysBizClassVO parmVO = OmcSchemaServiceUtils.getSystemBizClassWithNames(className);
        if(NullUtil.isNull(parmVO)) return rslt;
        parmVO.setUniqueStr(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_UNIQSTR);
        return getSystemBizClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_BACKWARD,false,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysBizClassVO> getTemporaryChildSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, OmcSchemaSysBizClassVO parmVO, int wantedLevel){
        return getSystemBizClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_FORWARD,true,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysBizClassVO> getTemporaryChildSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className, int wantedLevel){
        OmcSchemaSysBizClassVO parmVO = OmcSchemaServiceUtils.getTemporarySystemBizClassWithNames(className);
        if(NullUtil.isNull(parmVO)) return rslt;
        parmVO.setUniqueStr(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_UNIQSTR);
        return getSystemBizClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_FORWARD,true,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysBizClassVO> getTemporaryParentSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, OmcSchemaSysBizClassVO parmVO, int wantedLevel){
        return getSystemBizClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_BACKWARD,true,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysBizClassVO> getTemporaryParentSystemBizClassList(boolean firstInclude, List<OmcSchemaSysBizClassVO> rslt, String className, int wantedLevel){
        OmcSchemaSysBizClassVO parmVO = OmcSchemaServiceUtils.getTemporarySystemBizClassWithNames(className);
        if(NullUtil.isNull(parmVO)) return rslt;
        parmVO.setUniqueStr(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_UNIQSTR);
        return getSystemBizClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_BACKWARD,true,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysBizClassVO> getDuplicatedBizClassList(){

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaSysBizClass.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames in (select z.pnames from zsysbizobjectclassinfo z group by z.pnames having count(*) > 1)");
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysBizClassVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveBizClass", variableParameter);
        return(result);

    }
    @Override
    public List<OmcSchemaSysBizClassVO> getNotFoundParntBizClassList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaSysBizClass.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where not exists(select * from zsysbizobjectclassinfo x where x.pnames = a.pnames_parent)");

        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pnames not in(#{funVariable_00001})");
        variableParameter.setFunVariable_00001(OmcSystemConstants.OBJECT_ROOT);

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysBizClassVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveBizClass", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaSysBizClassVO> getAllBiz(boolean activeOnly){
        return(getAllBizSub(false,activeOnly));
    }
    @Override
    public List<OmcSchemaSysBizClassVO> getAllBizTemp(){
        return(getAllBizSub(true,false));
    }
    private List<OmcSchemaSysBizClassVO> getAllBizSub(boolean isTemporary, boolean activeOnly){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        if(isTemporary){
            OmcSchemaSysBizClass.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
        }
        else{
            OmcSchemaSysBizClass.getCommonSelectSql(sqlStrBuff,variableParameter);
            if(activeOnly){
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
                sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00010}","#{funVariable_00010}"));
                variableParameter.setFunVariable_00010(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Active));
            }
        }
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysBizClassVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveBizClass", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaSysBizClassVO> getInactiveBizListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysBizClass.getCommonSelectSql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where not exists(select * from zsysbizobjectclassinfo x where a.pnames = x.pnames)");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00010}","#{funVariable_00010}"));
        variableParameter.setFunVariable_00010(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Active));
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysBizClassVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveBizClass", variableParameter);
        return(result);
    }
    @Override
    public void makeBizClassRevisible(String obid){
        updateOrFlags("psysbizobjectclassinfo",obid,OmcSystemConstants.BUSINESS_FLAG_Revisible);
    }
    @Override
    public void makeBizClassWorkflowable(String obid){
        updateOrFlags("psysbizobjectclassinfo",obid,OmcSystemConstants.BUSINESS_FLAG_Workflow);
    }
    @Override
    public void makeBizClassInactive(String obid){
        updateXorFlags("psysbizobjectclassinfo",obid,OmcSystemConstants.BUSINESS_FLAG_Active);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    private void updateOrFlags(String dbmsTable, String obid, long value){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append                                      ("update ").append(dbmsTable).append(" a  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags = ").append(OmcSchemaUtil.getBitOrStr("a.pflags","#{funVariable_00010}")).append(",");
        variableParameter.setFunVariable_00010(String.valueOf(value));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid = #{funVariable_00001}");
        variableParameter.setFunVariable_00001(obid);
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", variableParameter);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    private void updateXorFlags(String dbmsTable, String obid, long value){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append                                      ("update ").append(dbmsTable).append(" a  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags = ").append(OmcSchemaUtil.getBitXorStr("a.pflags","#{funVariable_00010}")).append(",");
        variableParameter.setFunVariable_00010(String.valueOf(value));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid = #{funVariable_00001}");
        variableParameter.setFunVariable_00001(obid);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00010}","#{funVariable_00010}"));
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", variableParameter);
    }

    @Override
    public HashMap<String, OmcSchemaSysBizClassVO> getWorkflowableList(){
        HashMap<String, OmcSchemaSysBizClassVO> map = new HashMap<String, OmcSchemaSysBizClassVO>();

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysBizClass.getCommonSelectSql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00010}","#{funVariable_00010}"));
        variableParameter.setFunVariable_00010(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Workflow));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00011}","#{funVariable_00011}"));
        variableParameter.setFunVariable_00011(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Active));
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysBizClassVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveBizClass", variableParameter);
        for(OmcSchemaSysBizClassVO vo : result){
            List<OmcSchemaSysBizClassVO> childList = getChildSystemBizClassList(true, null, vo, 100);
            for(OmcSchemaSysBizClassVO child : childList){
                map.put(child.getNames(), child);
            }
        }
        return map;
    }
    @Override
    public void refreshAllTempBizObid(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

//        sqlStrBuff.append                                      ("update zsysbizobjectclassinfo a  ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.obid = null               ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.parent_obid = null         ");
//
//        variableParameter.setSql(sqlStrBuff.toString());
//        schemaDao.update("SchemaNew.dynamicModify", variableParameter);

//        sqlStrBuff.setLength(0);

//        sqlStrBuff.append                                      ("update zsysbizobjectclassinfo a ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.obid = (select x.obid from psysbizobjectclassinfo x ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("              where x.pclass_name = a.class_name)");
//
//        variableParameter.setSql(sqlStrBuff.toString());
//        schemaDao.update("SchemaNew.dynamicModify", variableParameter);

//        sqlStrBuff.setLength(0);
//
//        sqlStrBuff.append                                      ("update zsysbizobjectclassinfo a");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.parent_obid = (select x.obid from zsysbizobjectclassinfo x ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("                     where x.class_name = a.class_parent)");
//
//        variableParameter.setSql(sqlStrBuff.toString());
//        schemaDao.update("SchemaNew.dynamicModify", variableParameter);

//        sqlStrBuff.setLength(0);
//
//        sqlStrBuff.append                                      ("update zsysbizobjectclassinfo a");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.parent_obid = '1' ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.class_name = #{funVariable_00001}");

//        variableParameter.setFunVariable_00001(OmcSystemConstants.OBJECT_ROOT);
//        variableParameter.setSql(sqlStrBuff.toString());
//        schemaDao.update("SchemaNew.dynamicModify", variableParameter);

    }

    private List<OmcSchemaSysBizClassVO> getSystemBizClassListSub(  long direction,
                                                                    boolean isTemporary,
                                                                    boolean isFirst,
                                                                    boolean firstInclude,
                                                                    List<OmcSchemaSysBizClassVO> rslt,
                                                                    OmcSchemaSysBizClassVO parmVO,
                                                                    String uniqeStr,
                                                                    int currentLevel,
                                                                    int wantedLevel){
        if (currentLevel > wantedLevel) return rslt;
        if (currentLevel > OmcSchemaConstants.C_SCHEMA_CLASS_MAXLEVEL) throw new FoundationException("omc.schema.bizclass.maxlevel");
        if (rslt == null) rslt = new ArrayList<OmcSchemaSysBizClassVO>();
        if (isFirst && firstInclude) rslt.add(parmVO);

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        if(isTemporary){
            OmcSchemaSysBizClass.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
            if (Bit.isInclude(direction, OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_BACKWARD)){
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames = #{funVariable_00001}");
                variableParameter.setFunVariable_00001(parmVO.getNamesParent());
            }else{
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames_parent = #{funVariable_00001}");
                variableParameter.setFunVariable_00001(parmVO.getNames());
            }
        }else{
            OmcSchemaSysBizClass.getCommonSelectSql(sqlStrBuff, variableParameter);
            if (Bit.isInclude(direction, OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_BACKWARD)){
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid = #{funVariable_00001}");
                sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00002}","#{funVariable_00002}"));
                variableParameter.setFunVariable_00001(parmVO.getParentObid());
                variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Active));
            }else{
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pparent_obid = #{funVariable_00001}");
                sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00002}","#{funVariable_00002}"));
                variableParameter.setFunVariable_00001(parmVO.getObid());
                variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Active));
            }
        }
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysBizClassVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveBizClass", variableParameter);

        int seq = 1;
        for(OmcSchemaSysBizClassVO bizClassVO : result){
            bizClassVO.setClassLevel(currentLevel);
            bizClassVO.setUniqueStrParent(uniqeStr);
            bizClassVO.setUniqueStr(uniqeStr + String.format("%03d", seq++));
            rslt.add(bizClassVO);
            rslt = getSystemBizClassListSub(direction,isTemporary,false,firstInclude,rslt,bizClassVO,bizClassVO.getUniqueStr(),currentLevel+1,wantedLevel);
        }
        return(rslt);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryBizClass(List<OmcSchemaSysBizClassVO> list){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsysbizobjectclassinfo");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        
        uploadCommonBatch(list,"SchemaNew.createTemporaryBizClassBatch");
    }
    /***************************************************************************************************************************************************/
    /*********************************************************SystemRelClass****************************************************************************/
    /***************************************************************************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryRelClass(List<OmcSchemaSysRelClassVO> list){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("delete from zsysrelobjectclassinfo");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        uploadCommonBatch(list,"SchemaNew.createTemporaryRelClassBatch");
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemRelClass(OmcSchemaSysRelClassVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psysrelobjectclassinfo(obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"                   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"                   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdbms_table"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pfrom_class"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pto_class"                );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pfrom_relationship"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pto_relationship"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pparent_obid"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdisplayed_name"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdisplayed_name_kr"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",prelation_from_meaning"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",prelation_to_meaning"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcardinality_from"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcardinality_to"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",prevision_rule_from"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",prevision_rule_to"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pjava_package"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psequences"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pchange_comments"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodule_name"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",premarks"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",powners"                  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"                );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"                );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{dbmsTable}"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{fromClass}"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{toClass}"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{fromRelationship}"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{toRelationship}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{parentObid}"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{displayedName}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{displayedNameKr}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{relationFromMeaning}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{relationToMeaning}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{cardinalityFrom}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{cardinalityTo}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{revisionRuleFrom}"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{revisionRuleTo}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{javaPackage}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{sequences}"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{changeComments}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{moduleName}"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{remarks}"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{owners}"                );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemRelClass(OmcSchemaSysRelClassVO parmVO){

        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psysrelobjectclassinfo a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags                        = #{flags}                           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdbms_table                   = #{dbmsTable}                       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pfrom_class                   = #{fromClass}                       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pto_class                     = #{toClass}                         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pfrom_relationship            = #{fromRelationship}                ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pto_relationship              = #{toRelationship}                  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pparent_obid                  = #{parentObid}                      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdisplayed_name               = #{displayedName}                   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdisplayed_name_kr            = #{displayedNameKr}                 ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.prelation_from_meaning        = #{relationFromMeaning}             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.prelation_to_meaning          = #{relationToMeaning}               ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pcardinality_from             = #{cardinalityFrom}                 ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pcardinality_to               = #{cardinalityTo}                   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.prevision_rule_from           = #{revisionRuleFrom}                ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.prevision_rule_to             = #{revisionRuleTo}                  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pjava_package                 = #{javaPackage}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.psequences                    = #{sequences}                       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pchange_comments              = #{changeComments}                  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodule_name                  = #{moduleName}                      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.premarks                      = #{remarks}                         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.powners                       = #{owners}                          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier                     = #{modifier}                        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid                          = #{obid}                            ");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemRelClass(OmcSchemaSysRelClassVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.RELATION_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.RELATION_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psysrelobjectclassinfo a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemRelClass(OmcSchemaSysRelClassVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete psysrelobjectclassinfo"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    @Override
    public OmcSchemaSysRelClassVO getSystemRelClassWithObid(String obid){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysRelClass.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(obid);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaSysRelClassVO result = schemaDao.select("SchemaNew.dynamicRetrieveRelClass", variableParameter);
        return(result);
    }
    @Override
    public OmcSchemaSysRelClassVO getSystemRelClassWithNames(boolean isTemporary, String names){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        if(isTemporary){
            OmcSchemaSysRelClass.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames = #{funVariable_00001}"          );
            variableParameter.setFunVariable_00001(names);
        }else{
            OmcSchemaSysRelClass.getCommonSelectSql(sqlStrBuff, variableParameter);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames  = #{funVariable_00001}"        );
            variableParameter.setFunVariable_00001(names);
        }
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaSysRelClassVO result = schemaDao.select("SchemaNew.dynamicRetrieveRelClass", variableParameter);

        return(result);
    }
    @Override
    public List<OmcSchemaSysRelClassVO> getDuplicatedRelClassList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaSysRelClass.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames in (select z.pnames from zsysrelobjectclassinfo z group by z.pnames having count(*) > 1)");
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysRelClassVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelClass", variableParameter);
        return(result);
    }
    public List<OmcSchemaSysRelClassVO> getAllRelClassList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaSysRelClass.getCommonSelectSql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1=1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00010}","#{funVariable_00010}"));
        variableParameter.setFunVariable_00010(String.valueOf(OmcSystemConstants.RELATION_FLAG_Active));

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysRelClassVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelClass", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaSysRelClassVO> getNotFoundParntRelClassList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaSysRelClass.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where not exists(select * from zsysrelobjectclassinfo x where x.pnames = a.pnames_parent)");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pnames not in(#{funVariable_00001})");

        variableParameter.setFunVariable_00001(OmcSystemConstants.OBJECT_ROOT);
        variableParameter.setSql(sqlStrBuff.toString());

        List<OmcSchemaSysRelClassVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveBizClass", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaSysRelClassVO> getChildSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, OmcSchemaSysRelClassVO parmVO, int wantedLevel){
        return getSystemRelClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_FORWARD,false,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysRelClassVO> getChildSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className, int wantedLevel){
        OmcSchemaSysRelClassVO parmVO = OmcSchemaServiceUtils.getSystemRelClassWithNames(className);
        if(NullUtil.isNull(parmVO)) return rslt;
        parmVO.setUniqueStr(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_UNIQSTR);
        return getSystemRelClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_FORWARD,false,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysRelClassVO> getParentSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, OmcSchemaSysRelClassVO parmVO, int wantedLevel){
        return getSystemRelClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_BACKWARD,false,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysRelClassVO> getParentSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className, int wantedLevel){
        OmcSchemaSysRelClassVO parmVO = OmcSchemaServiceUtils.getSystemRelClassWithNames(className);
        if(NullUtil.isNull(parmVO)) return rslt;
        parmVO.setUniqueStr(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_UNIQSTR);
        return getSystemRelClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_BACKWARD,false,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysRelClassVO> getTemporaryChildSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, OmcSchemaSysRelClassVO parmVO, int wantedLevel){
        return getSystemRelClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_FORWARD,true,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysRelClassVO> getTemporaryChildSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className, int wantedLevel){
        OmcSchemaSysRelClassVO parmVO = OmcSchemaServiceUtils.getTemporarySystemRelClassWithNames(className);
        if(NullUtil.isNull(parmVO)) return rslt;
        parmVO.setUniqueStr(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_UNIQSTR);
        return getSystemRelClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_FORWARD,true,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysRelClassVO> getTemporaryParentSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, OmcSchemaSysRelClassVO parmVO, int wantedLevel){
        return getSystemRelClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_BACKWARD,true,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }
    @Override
    public List<OmcSchemaSysRelClassVO> getTemporaryParentSystemRelClassList(boolean firstInclude, List<OmcSchemaSysRelClassVO> rslt, String className, int wantedLevel){
        OmcSchemaSysRelClassVO parmVO = OmcSchemaServiceUtils.getTemporarySystemRelClassWithNames(className);
        if(NullUtil.isNull(parmVO)) return rslt;
        parmVO.setUniqueStr(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_UNIQSTR);
        return getSystemRelClassListSub(OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_BACKWARD,true,true,firstInclude,rslt,parmVO,parmVO.getUniqueStr(),OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_STARTLEVEL,wantedLevel);
    }

    private List<OmcSchemaSysRelClassVO> getSystemRelClassListSub(  long direction,
                                                                    boolean isTemporary,
                                                                    boolean isFirst,
                                                                    boolean firstInclude,
                                                                    List<OmcSchemaSysRelClassVO> rslt,
                                                                    OmcSchemaSysRelClassVO parmVO,
                                                                    String uniqeStr,
                                                                    int currentLevel,
                                                                    int wantedLevel){
        if (currentLevel > wantedLevel) return rslt;
        if (currentLevel > OmcSchemaConstants.C_SCHEMA_CLASS_MAXLEVEL) throw new FoundationException("omc.schema.bizclass.maxlevel");
        if (rslt == null) rslt = new ArrayList<OmcSchemaSysRelClassVO>();
        if (isFirst && firstInclude) rslt.add(parmVO);

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        if(isTemporary){
            OmcSchemaSysRelClass.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
            if (Bit.isInclude(direction, OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_BACKWARD)){
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames = #{funVariable_00001}");
                variableParameter.setFunVariable_00001(parmVO.getNamesParent());
            }else{
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames_parent = #{funVariable_00001}");
                variableParameter.setFunVariable_00001(parmVO.getNames());
            }
        }else{
            OmcSchemaSysRelClass.getCommonSelectSql(sqlStrBuff, variableParameter);
            if (Bit.isInclude(direction, OmcSchemaConstants.C_SCHEMA_CLASS_EXPLOSION_BACKWARD)){
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid = #{funVariable_00001}");
                variableParameter.setFunVariable_00001(parmVO.getParentObid());
            }else{
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pparent_obid = #{funVariable_00001}");
                variableParameter.setFunVariable_00001(parmVO.getObid());
            }
        }
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysRelClassVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelClass", variableParameter);

        int seq = 1;
        for(OmcSchemaSysRelClassVO relClassVO : result){
            relClassVO.setClassLevel(currentLevel);
            relClassVO.setUniqueStrParent(uniqeStr);
            relClassVO.setUniqueStr(uniqeStr + String.format("%03d", seq++));
            rslt.add(relClassVO);
            rslt = getSystemRelClassListSub(direction,isTemporary,false,firstInclude,rslt,relClassVO,relClassVO.getUniqueStr(),currentLevel+1,wantedLevel);
        }
        return(rslt);
    }
    @Override
    public List<OmcSchemaSysRelClassVO> getAllRelTemp(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaSysRelClass.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysRelClassVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelClass", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaSysRelClassVO> getAllRel(boolean activeOnly){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaSysRelClass.getCommonSelectSql(sqlStrBuff,variableParameter);

        if(activeOnly){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00010}","#{funVariable_00010}"));
            variableParameter.setFunVariable_00010(String.valueOf(OmcSystemConstants.RELATION_FLAG_Active));
        }
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysRelClassVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelClass", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaSysRelClassVO> getInactiveRelListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysRelClass.getCommonSelectSql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where not exists(select * from zsysrelobjectclassinfo x where a.pnames = x.pnames)");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00010}","#{funVariable_00010}"));
        variableParameter.setFunVariable_00010(String.valueOf(OmcSystemConstants.RELATION_FLAG_Active));
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysRelClassVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelClass", variableParameter);
        return(result);
    }
    @Override
    public void refreshAllTempRelObid(){

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
//
//        sqlStrBuff.append                                      ("update zsysrelobjectclassinfo a  ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.obid = null               ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.parent_obid = null         ");
//
//        variableParameter.setSql(sqlStrBuff.toString());
//        schemaDao.update("SchemaNew.dynamicModify", variableParameter);
//
//        sqlStrBuff.setLength(0);
//
//        sqlStrBuff.append                                      ("update zsysrelobjectclassinfo a ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.obid = (select x.obid from psysrelobjectclassinfo x ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("              where x.pclass_name = a.class_name)");
//
//        variableParameter.setSql(sqlStrBuff.toString());
//        schemaDao.update("SchemaNew.dynamicModify", variableParameter);
//
//        sqlStrBuff.setLength(0);
//
//        sqlStrBuff.append                                      ("update zsysrelobjectclassinfo a");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.parent_obid = (select x.obid from psysrelobjectclassinfo x ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("                     where x.pclass_name = a.class_parent)");
//
//        variableParameter.setSql(sqlStrBuff.toString());
//        schemaDao.update("SchemaNew.dynamicModify", variableParameter);
//
//        sqlStrBuff.setLength(0);
//
//        sqlStrBuff.append                                      ("update zsysrelobjectclassinfo a");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.parent_obid = '1' ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.class_name = #{funVariable_00001}");
//
//        variableParameter.setFunVariable_00001(OmcSystemConstants.OBJECT_ROOT);
//        variableParameter.setSql(sqlStrBuff.toString());
//        schemaDao.update("SchemaNew.dynamicModify", variableParameter);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void refreshAllowedClassBatch(){
        List<OmcSchemaSysAllowedClsForRelVO>  list  = makeListAllowedClassForBatch();
        initializeAllAllowedClass();

        String[] obid = OmcSchemaServiceUtils.getObid(OmcSystemConstants.SYSKEY_KIND_AllowedRel,list.size());
        int i = 0;
        for (OmcSchemaSysAllowedClsForRelVO vo : list){
            vo.setObid(obid[i]);
            i++;
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list", list);
        schemaDao.insert("SchemaNew.createAllowedClassForRelationBatch", map);
    }
    private void initializeAllAllowedClass(){
        List<OmcSchemaSysAllowedClsForRelVO> list = getAllowedClassAll();
        List<OmcSchemaSysRootVO> keyList = new ArrayList<OmcSchemaSysRootVO>();
        for(OmcSchemaSysAllowedClsForRelVO vo: list){
            keyList.add((OmcSchemaSysRootVO)vo);
        }
        deleteAllowedClassBatch(list);
        deleteSystemKeyTableBatch(keyList);
    }
    private List<OmcSchemaSysAllowedClsForRelVO> getAllowedClassAll(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append                                      ("select  obid                     as obid               ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       ,pflags                   as flags              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       ,prelationship_obid       as relationship_obid  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       ,pclass_obid              as class_obid         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       ,pcreator                 as creator            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       ,pcreated                 as created            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       ,pmodifier                as modifier           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       ,pmodified                as modified           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysallowedclassforrel");
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysAllowedClsForRelVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveAllowedClass",variableParameter);
        return(result);
    }
    private void deleteCommonSystemSchemaTableBatch(Object objList, String dbmsTable){

        ArrayList list = (ArrayList)objList;
        int maxCount = OmcSchemaUtil.getDbmsInMaxCount();
        if(NullUtil.isNone(list)) return;
        int dataCount = list.size();
        if (dataCount == 0) return;
        int runCount = 0;

        Map map = new HashMap<String,Object>();
        map.put("dbmsTableName", dbmsTable);
        List<String> strList = new ArrayList<String>();

        if(dataCount == (dataCount/maxCount)*maxCount){
            runCount = dataCount/maxCount;
        }else{
            runCount = (dataCount/maxCount) + 1;
        }
        int cnt =  (list.size()/maxCount + 1);
        Object[] objAry = new Object[runCount];

        int seq = 0;
        int idx = 0;

        for(Object obj : list){
            if((seq%maxCount) == 0 && seq != 0){
                objAry[idx] = strList;
                strList = new ArrayList<String>();
                idx++;
            }
            strList.add(((OmcSchemaSysRootVO)obj).getObid());
            seq++;
        }
        if(!NullUtil.isNone(strList)) objAry[idx] = strList;

        for(int i = 0; i < objAry.length; i++){
            map.put("obidList", objAry[i]);
            schemaDao.delete("SchemaNew.deleteScheamTable", map);
        }
    }
    private void deleteSystemKeyTableBatch(List<OmcSchemaSysRootVO> list){
        deleteCommonSystemSchemaTableBatch(list,"psyskeytable");
    }
    private void deleteAllowedClassBatch(List<OmcSchemaSysAllowedClsForRelVO> list){
        deleteCommonSystemSchemaTableBatch(list,"psysallowedclassforrel");
    }
    private List<OmcSchemaSysAllowedClsForRelVO> makeListAllowedClassForBatch(){
        List<OmcSchemaSysRelClassVO> list = getAllRelClassList();
        List<OmcSchemaSysAllowedClsForRelVO> result = new ArrayList<OmcSchemaSysAllowedClsForRelVO>();
        if(!NullUtil.isNull(list)){
            for(OmcSchemaSysRelClassVO vo : list){
                makeListAllowedClassForBatchBizSub(result,vo.getObid(),vo.getFromClass(),OmcSystemConstants.FLAG_ALLOWEDRELATION_FROM);
                makeListAllowedClassForBatchBizSub(result,vo.getObid(),vo.getToClass(),OmcSystemConstants.FLAG_ALLOWEDRELATION_TO);
                makeListAllowedClassForBatchRelSub(result,vo.getObid(),vo.getFromRelationship(),OmcSystemConstants.FLAG_ALLOWEDRELATION_FROM);
                makeListAllowedClassForBatchRelSub(result,vo.getObid(),vo.getToRelationship(),OmcSystemConstants.FLAG_ALLOWEDRELATION_TO);
            }
        }
        return result;
    }
    private void makeListAllowedClassForBatchBizSub(List<OmcSchemaSysAllowedClsForRelVO> result,String relationObid, String calssNameList, long fromTo){
        long flags = OmcSystemConstants.FLAG_ALLOWEDRELATION_Default;
        flags = Bit.or(flags,fromTo,OmcSystemConstants.FLAG_ALLOWEDRELATION_BO);
        if(calssNameList.equals("All")){
            makeListAllowedClassSub(result,relationObid,flags,"All");
        }else{
            String[] classArray = calssNameList.split(",");
            HashMap<String,String> allBizVoMap = new HashMap<String,String>();
            for(int i = 0; i < classArray.length; i++){
                List<OmcSchemaSysBizClassVO> suList = OmcSchemaSysBizClass.getChildSystemBizClassList(true, null, classArray[i], 100);
                if(!NullUtil.isNull(suList)){
                    for(OmcSchemaSysBizClassVO subVo : suList){
                        allBizVoMap.put(subVo.getObid(), subVo.getNames());
                    }
                }
            }
            Set<String> keySet = allBizVoMap.keySet();
            if(keySet.size() > 0) makeListAllowedClassSub(result,relationObid,flags,keySet);
        }
    }
    private void makeListAllowedClassForBatchRelSub(List<OmcSchemaSysAllowedClsForRelVO> result,String relationObid, String calssNameList, long fromTo){
        long flags = OmcSystemConstants.FLAG_ALLOWEDRELATION_Default;
        flags = Bit.or(flags,fromTo,OmcSystemConstants.FLAG_ALLOWEDRELATION_RO);
        String[] classArray = calssNameList.split(",");
        HashMap<String,String> allBizVoMap = new HashMap<String,String>();
        for(int i = 0; i < classArray.length; i++){
            List<OmcSchemaSysRelClassVO> suList = OmcSchemaSysRelClass.getChildSystemRelClassList(true, null, classArray[i], 100);
            if(!NullUtil.isNull(suList)){
                for(OmcSchemaSysRelClassVO subVo : suList){
                    allBizVoMap.put(subVo.getObid(), subVo.getNames());
                }
            }
        }
        Set<String> keySet = allBizVoMap.keySet();
        if(keySet.size() > 0) makeListAllowedClassSub(result,relationObid,flags,keySet);
    }
    private void makeListAllowedClassSub(List<OmcSchemaSysAllowedClsForRelVO> result,String relationObid, long flags, Set<String> classObidSet){
        OmcSchemaSysAllowedClsForRelVO vo = new OmcSchemaSysAllowedClsForRelVO();
        int i = 0;
        for(String classObid : classObidSet){
            vo = new OmcSchemaSysAllowedClsForRelVO();
            //vo.setObid(obid[i]);
            vo.setFlags(flags);
            vo.setRelationshipObid(relationObid);
            vo.setClassObid(classObid);
            result.add(vo);
            i++;
        }
    }
    private void makeListAllowedClassSub(List<OmcSchemaSysAllowedClsForRelVO> result,String relationObid, long flags, String classObid){
        OmcSchemaSysAllowedClsForRelVO vo = new OmcSchemaSysAllowedClsForRelVO();
        if(classObid.equals("All")){
            List<OmcSchemaSysBizClassVO> list = OmcSchemaSysBizClass.getAll(true);
            int i = 0;
            for(OmcSchemaSysBizClassVO boVo : list){
                if(boVo.getNames() != OmcSystemConstants.OBJECT_ROOT &&
                   boVo.getNames() != OmcSystemConstants.BUSINESS_OBJECT &&
                   boVo.getNames() != OmcSystemConstants.BUSINESS_OBJECT_MASTER &&
                   boVo.getNames() != OmcSystemConstants.BUSINESS_OBJECT_ROOT)
                vo = new OmcSchemaSysAllowedClsForRelVO();
                //vo.setObid(obid[i]);
                vo.setFlags(flags);
                vo.setRelationshipObid(relationObid);
                vo.setClassObid(boVo.getObid());
                result.add(vo);
            }
        }else{
            vo.setFlags(flags);
            vo.setRelationshipObid(relationObid);
            vo.setClassObid(classObid);
            result.add(vo);
        }
    }
    @Override
    public void refreshClassInfo(){
        deleteAllClassInfo();
        List<OmcSchemaSysClassInfoVO> result = getClassInfoAll();
        createClassInfoBatch(result);
        updateUpperAndChildClassList();
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void setInstantiableFlagsForBizClass(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.setLength(0);
        sqlStrBuff.append                                      ("update psysbizobjectclassinfo a"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags = ").append(OmcSchemaUtil.getBitXorStr("a.pflags",OmcSystemConstants.BUSINESS_FLAG_Instantiable));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1=1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Active));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Instantiable));
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        
        variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.setLength(0);
        sqlStrBuff.append                                      ("update psysbizobjectclassinfo a left outer join psysbizobjectclassinfo b on a.obid = b.pparent_obid"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags = ").append(OmcSchemaUtil.getBitOrStr("a.pflags",OmcSystemConstants.BUSINESS_FLAG_Instantiable));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1=1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and b.obid is null");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.BUSINESS_FLAG_Active));
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", variableParameter);
    }
    public void setInstantiableFlagsForRelClass(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.setLength(0);
        sqlStrBuff.append                                      ("update psysrelobjectclassinfo a"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags = ").append(OmcSchemaUtil.getBitXorStr("a.pflags",OmcSystemConstants.RELATION_FLAG_Instantiable));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1=1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.RELATION_FLAG_Active));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.RELATION_FLAG_Instantiable));
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        
        variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.setLength(0);
        sqlStrBuff.append                                      ("update psysrelobjectclassinfo a left outer join psysrelobjectclassinfo b on a.obid = b.pparent_obid"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags = ").append(OmcSchemaUtil.getBitOrStr("a.pflags",OmcSystemConstants.RELATION_FLAG_Instantiable));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1=1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and b.obid is null");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.RELATION_FLAG_Active));
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", variableParameter);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    private void createClassInfoBatch(List<OmcSchemaSysClassInfoVO> list){
        String[] obid = OmcSchemaServiceUtils.getObid(OmcSystemConstants.SYSKEY_KIND_AllowedRel,list.size());
        int i = 0;
        for (OmcSchemaSysClassInfoVO vo : list){
            vo.setObid(obid[i]);
            i++;
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list", list);
        schemaDao.insert("SchemaNew.createClassInfoBatch", map);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    private void updateUpperAndChildClassList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        
        Map<String,List<String>> childMap = new HashMap<String,List<String>>();
        Map<String,String> parentMap = new HashMap<String,String>();
        getParentChildListAll(childMap,parentMap);
        
        sqlStrBuff.append                                      ("select distinct a.pclass_name as class_name "  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysclassinfo a");
        variableParameter.setSql(sqlStrBuff.toString());
        List<String> result = schemaDao.selectList("SchemaNew.getDynamicStringList", variableParameter);
        for(String str:result){
            StringBuffer childBuf = new StringBuffer();
            //List<String> classChildList = getChildClassList(str);
            List<String> classChildList = getChildClassList(str,childMap);
            
            childBuf.append(str);
            for(String childClass : classChildList){
                childBuf.append(",").append(childClass);
            }
            StringBuffer parentBuf = new StringBuffer();
            //List<String> classParentList = getParentClassList(str);
            List<String> classParentList = getParentClassList(str,parentMap);
            
            parentBuf.append(str);
            for(String childClass : classParentList){
                parentBuf.append(",").append(childClass);
            }
            variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.setLength(0);
            sqlStrBuff.append                                      ("update psysclassinfo"  );
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set pupper_class_list = #{funVariable_00001}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,plower_class_list = #{funVariable_00002}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where pclass_name = #{funVariable_00003}");
            variableParameter.setFunVariable_00001(parentBuf.toString());
            variableParameter.setFunVariable_00002(childBuf.toString());
            variableParameter.setFunVariable_00003(str);
            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }

    private List<String> getChildClassList(String className){
        List<String> classList = new ArrayList<String>();
        this.getUpperLowerClassList(className,classList,true);
        return classList;
    }
    private List<String> getParentClassList(String className){
        List<String> classList = new ArrayList<String>();
        this.getUpperLowerClassList(className,classList,false);
        return classList;
    }
    private List<String> getChildClassList(String className,Map<String,List<String>> childMap){
        List<String> classList = new ArrayList<String>();
        getChildClassListSub(className,childMap,classList);
        return classList;
    }
    private List<String> getChildClassListSub(String className,Map<String,List<String>> childMap,List<String> classList){
        List<String> list = childMap.get(className);
        if(!NullUtil.isNone(list)){
            classList.addAll(list);
            for(String className1 : list){
                getChildClassListSub(className1,childMap,classList);
            }
        }
        return classList;
    }
    
    private List<String> getParentClassList(String className,Map<String,String> parentMap){
        List<String> classList = new ArrayList<String>();
        this.getParentClassListSub(className,parentMap,classList);
        return classList;
    }
    private List<String> getParentClassListSub(String className,Map<String,String> parentMap,List<String> classList){
        String parentClass = parentMap.get(className);
        if(!StrUtil.isEmpty(parentClass)) {
            classList.add(parentClass);
            getParentClassListSub(parentClass,parentMap,classList);
        }
        return classList;
    }

    private void getUpperLowerClassList(String className, List<String> classList, boolean isChild){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        if(!isChild){
            sqlStrBuff.append                                      ("select a.pclass_name as class_name "  );
        }else{
            sqlStrBuff.append                                      ("select b.pclass_name as class_name "  );
        }

        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysclassinfo a, psysclassinfo b");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pclass_obid = b.pclass_parent_obid"  );
        if(!isChild){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   b.pclass_name = #{funVariable_00001}"   );
        }else{
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pclass_name = #{funVariable_00001}"   );
        }
        variableParameter.setFunVariable_00001(className);
        variableParameter.setSql(sqlStrBuff.toString());
        List<String> result = schemaDao.selectList("SchemaNew.getDynamicStringList", variableParameter);
        classList.addAll(result);
        for(String str:result){
            this.getUpperLowerClassList(str,classList,isChild);
        }
    }
    private void getParentChildListAll(Map<String,List<String>> childMap,Map<String,String> parentMap){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("select a.pclass_name as class_name_parent,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       b.pclass_name as class_name_child");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysclassinfo a, psysclassinfo b");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pclass_obid = b.pclass_parent_obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("order by a.pclass_name"  );
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaClassParentChildVO> result = schemaDao.selectList("SchemaNew.getParentChildList", variableParameter);

        List<String> childList = new ArrayList<String>();
        String paranetClassName = "initial";
        boolean isFirst = true;
        for(OmcSchemaClassParentChildVO vo : result){
            if(!paranetClassName.equals(vo.getClassNameParent()) && !isFirst){
                childMap.put(paranetClassName, childList);
                childList = new ArrayList<String>();
            }
            childList.add(vo.getClassNameChild());
            parentMap.put(vo.getClassNameChild(), vo.getClassNameParent());
            paranetClassName = vo.getClassNameParent();
            isFirst = false;
        }
        if(!NullUtil.isNone(childList)) childMap.put(paranetClassName, childList);
    }
    
    private List<OmcSchemaSysClassInfoVO> getClassInfoAll(){
        List<OmcSchemaSysBizClassVO> bizList = OmcSchemaServiceUtils.getAllBiz(true);
        List<OmcSchemaSysRelClassVO> relList = OmcSchemaServiceUtils.getAllRel(true);

        List<OmcSchemaSysClassInfoVO> result = new ArrayList<OmcSchemaSysClassInfoVO>();
        OmcSchemaSysClassInfoVO tempVo = new OmcSchemaSysClassInfoVO();

        String objectRootObid = "";
        long flags = 0;
        for(OmcSchemaSysBizClassVO vo: bizList){
            tempVo = new OmcSchemaSysClassInfoVO();
            flags = Bit.or(OmcSystemConstants.CLASSINFO_FLAG_Default,OmcSystemConstants.CLASSINFO_FLAG_Active,OmcSystemConstants.CLASSINFO_FLAG_Business);
            if(vo.getNames().equals(OmcSystemConstants.OBJECT_ROOT)) flags = Bit.or(flags,OmcSystemConstants.CLASSINFO_FLAG_Relation);

            if(Bit.isInclude(vo.getFlags(), OmcSystemConstants.BUSINESS_FLAG_Instantiable)) flags = Bit.or(flags,OmcSystemConstants.CLASSINFO_FLAG_Instantiable);
            if(vo.getIsReferenced() == 1) flags = Bit.or(flags,OmcSystemConstants.CLASSINFO_FLAG_IsReferenced);
            
            tempVo.setFlags(flags);
            tempVo.setClassObid(vo.getObid());
            tempVo.setNames(vo.getNames());
            tempVo.setDbmsTable(vo.getDbmsTable());
            if(vo.getNames().equals(OmcSystemConstants.OBJECT_ROOT)){
                tempVo.setFlagsClass(0);
                tempVo.setClassParentObid(OmcSystemConstants.OBJECT_ROOT_DUMMY_OBID);
                objectRootObid = vo.getObid();
            }else{
                tempVo.setFlagsClass(vo.getFlags());
                tempVo.setClassParentObid(vo.getParentObid());
            }
            tempVo.setDisplayedName(vo.getDisplayedName());
            tempVo.setDisplayedNameKr(vo.getDisplayedNameKr());
            tempVo.setJavaPackage(vo.getJavaPackage());
            result.add(tempVo);
        }
        for(OmcSchemaSysRelClassVO vo: relList){
            if(!vo.getNames().equals(OmcSystemConstants.OBJECT_ROOT)){
                tempVo = new OmcSchemaSysClassInfoVO();
                flags = Bit.or(OmcSystemConstants.CLASSINFO_FLAG_Default,OmcSystemConstants.CLASSINFO_FLAG_Active,OmcSystemConstants.CLASSINFO_FLAG_Relation);
                if(vo.getNames().equals(OmcSystemConstants.OBJECT_ROOT)) flags = Bit.or(flags,OmcSystemConstants.CLASSINFO_FLAG_Business);

                if(Bit.isInclude(vo.getFlags(), OmcSystemConstants.RELATION_FLAG_Instantiable)) flags = Bit.or(flags,OmcSystemConstants.CLASSINFO_FLAG_Instantiable);

                if(Bit.isInclude(vo.getRevisionRuleFrom(), OmcSystemConstants.RELATION_RULE_DeleteDefloat)) flags = Bit.or(flags,OmcSystemConstants.CLASSINFO_FLAG_FromDefloat);
                if(Bit.isInclude(vo.getRevisionRuleTo(), OmcSystemConstants.RELATION_RULE_DeleteDefloat)) flags = Bit.or(flags,OmcSystemConstants.CLASSINFO_FLAG_ToDefloat);
                if(vo.getIsReferenced() == 1) flags = Bit.or(flags,OmcSystemConstants.CLASSINFO_FLAG_IsReferenced);
                tempVo.setFlags(flags);
                tempVo.setClassObid(vo.getObid());
                tempVo.setNames(vo.getNames());
                tempVo.setDbmsTable(vo.getDbmsTable());
                tempVo.setDbmsTable(vo.getDbmsTable());
                if(vo.getNames().equals(OmcSystemConstants.OBJECT_ROOT)){
                    tempVo.setFlagsClass(0);
                    tempVo.setClassParentObid(OmcSystemConstants.OBJECT_ROOT_DUMMY_OBID);
                }else if(vo.getNames().equals(OmcSystemConstants.RELATION_OBJECT_ROOT)){
                    tempVo.setFlagsClass(vo.getFlags());
                    tempVo.setClassParentObid(objectRootObid);
                }else{
                    tempVo.setFlagsClass(vo.getFlags());
                    tempVo.setClassParentObid(vo.getParentObid());
                }
                tempVo.setDisplayedName(vo.getNames());
                tempVo.setDisplayedNameKr(vo.getNames());
                tempVo.setJavaPackage(vo.getJavaPackage());
                result.add(tempVo);
            }
        }
        return(result);
    }

    private void deleteAllClassInfo(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append                                      ("delete from psysclassinfo");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        sqlStrBuff.setLength(0);
        sqlStrBuff.append                                      ("delete from psyskeytable where obid in(select x.obid from psysclassinfo x)");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    /**************************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryClassAttr(List<OmcSchemaSysClassAttrInfoVO> list){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsysclassattrinfo");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        uploadCommonBatch(list,"SchemaNew.createTemporaryClassAttrBatch");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    private void uploadCommonBatch(List list,String queryId){
        Map<String,Object>  map= new HashMap<String,Object>();
        List<Object> wlist = new ArrayList<Object>();
        int i = 0;
        for(Object vo : list){
            if(i > 0 && i%OmcSchemaConstants.C_DBMS_BATCH_COUNT == 0){
                map.put("list", wlist);
                schemaDao.insert(queryId, map);
                wlist = new ArrayList<Object>();
            }
            wlist.add(vo);
            i++;
        }
        if(!NullUtil.isNone(wlist)) {
            map.put("list", wlist);
            schemaDao.insert(queryId, map);
        }
    }
    public List<OmcSchemaSysClassAttrInfoVO> getAllAttribute(boolean isTemporary, boolean activeOnly){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        if(isTemporary){
            OmcSchemaSysClassAttrInfo.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
        }else{
            OmcSchemaSysClassAttrInfo.getCommonSelectSql(sqlStrBuff,variableParameter,null);
            if(activeOnly){
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1=1 ");
                sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00001}", "#{funVariable_00001}"));
                variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSCLASSATTR_FLAG_Active));
            }
        }
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysClassAttrInfoVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveAttr", variableParameter);
        return(result);
    }

    public List<OmcSchemaSysClassAttrInfoVO> getAttributeByClass(boolean isRel, String className){
        StringBuffer sqlStrBuff = new StringBuffer();
        String classObid = "";
        if (isRel){
            OmcSchemaSysRelClassVO relVO = getSystemRelClassWithNames(false, className);
            classObid = relVO.getObid();
        }else{
            OmcSchemaSysBizClassVO bizVO = getSystemBizClassWithNames(false, className);
            classObid = bizVO.getObid();
        }
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysClassAttrInfo.getCommonSelectSql(sqlStrBuff,variableParameter,null);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1=1 ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSCLASSATTR_FLAG_Active));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pclass_obid = #{funVariable_00002}");
        variableParameter.setFunVariable_00002(classObid);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("order by a.psortings");
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysClassAttrInfoVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveAttr", variableParameter);
        return(result);
    }

    public List<OmcSchemaSysClassAttrInfoVO> getDuplicatedAttrTypeList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysClassAttrInfo.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pcolumn_alias in(select y.pcolumn_alias from (select distinct x.pcolumn_alias,x.pdata_type_str from zsysclassattrinfo x) y");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("group by  y.pcolumn_alias");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("having count(*) > 1)");
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysClassAttrInfoVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveAttr", variableParameter);
        return(result);
    }
    public List<OmcSchemaSysClassAttrInfoVO> getNotFefinedClassForAttr(){
        List<OmcSchemaSysClassAttrInfoVO> result = new ArrayList<OmcSchemaSysClassAttrInfoVO>();
        List<OmcSchemaSysClassAttrInfoVO> bizResult = getNotFefinedClassForAttrSub("BO");
        List<OmcSchemaSysClassAttrInfoVO> relResult = getNotFefinedClassForAttrSub("RO");
        if(!NullUtil.isNone(bizResult))result.addAll(bizResult);
        if(!NullUtil.isNone(relResult))result.addAll(relResult);
        return(result);
    }
    public List<OmcSchemaSysClassAttrInfoVO> getDuplicatdAttr(){
        return(getDuplicatdAttrSub());
    }
    private List<OmcSchemaSysClassAttrInfoVO> getNotFefinedClassForAttrSub(String classType){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysClassAttrInfo.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        if(classType.equals("BO")){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsysbizobjectclassinfo x where a.pclass_name = x.pnames)");

        }else if(classType.equals("RO")){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsysrelobjectclassinfo x where a.pclass_name = x.pnames)");
        }
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pclass_type = #{funVariable_00001}");
        variableParameter.setFunVariable_00001(classType);
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysClassAttrInfoVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveAttr", variableParameter);
        return(result);
    }
    private List<OmcSchemaSysClassAttrInfoVO> getDuplicatdAttrSub(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysClassAttrInfo.getCommonSelectTemporarySql(sqlStrBuff,variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("(");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.pcolumn_alias in(select x.pcolumn_alias from zsysclassattrinfo x where x.pclass_name not in(#{funVariable_00001}) group by  x.pcolumn_alias,x.pclass_name having count(*) > 1)");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("or");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.pdbms_column in(select y.pdbms_column from zsysclassattrinfo y where y.pclass_name not in(#{funVariable_00001}) group by  y.pdbms_column,y.pclass_name having count(*) > 1)");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        variableParameter.setFunVariable_00001(OmcSystemConstants.OBJECT_ROOT);
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysClassAttrInfoVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveAttr", variableParameter);
        return(result);
    }
    public void initializeAttribute(){

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

//        sqlStrBuff.append                                      ("update zsysclassattrinfo a  ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pclass_obid = null               ,");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.obid = null         ");
//
//        variableParameter.setSql(sqlStrBuff.toString());
//        schemaDao.update("SchemaNew.dynamicModify", variableParameter);
//
//        sqlStrBuff.setLength(0);
//
//        sqlStrBuff.append                                      ("update zsysclassattrinfo a ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pclass_obid = (select x.obid from psysbizobjectclassinfo x where x.pnames = a.pclass_name)");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pclass_type = #{funVariable_00001}");
//        variableParameter.setFunVariable_00001("BO");
//
//        variableParameter.setSql(sqlStrBuff.toString());
//        schemaDao.update("SchemaNew.dynamicModify", variableParameter);
//
//        sqlStrBuff.setLength(0);
//
//        sqlStrBuff.append                                      ("update zsysclassattrinfo a ");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pclass_obid = (select x.obid from psysrelobjectclassinfo x where x.pnames = a.pclass_name)");
//        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pclass_type = #{funVariable_00001}");
//        variableParameter.setFunVariable_00001("RO");
    }
    public List<OmcSchemaSysClassAttrInfoVO> getInactiveListForAttr(){

        List<OmcSchemaSysClassAttrInfoVO> result = new ArrayList<OmcSchemaSysClassAttrInfoVO>();

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysClassAttrInfo.getCommonSelectSql(sqlStrBuff,variableParameter,null);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from psysbizobjectclassinfo x, zsysclassattrinfo y");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               where a.pclass_obid   = x.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               and   x.pnames        = y.pclass_name");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               and   y.pcolumn_alias = a.pcolumn_alias");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               and   y.pdbms_column  = a.pdbms_column)");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSCLASSATTR_KIND_BO));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSCLASSATTR_FLAG_Active));

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSysClassAttrInfoVO> bizResult = schemaDao.selectList("SchemaNew.dynamicRetrieveAttr", variableParameter);

        if(!NullUtil.isNone(bizResult)) result.addAll(bizResult);

        sqlStrBuff.setLength(0);
        variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSysClassAttrInfo.getCommonSelectSql(sqlStrBuff,variableParameter,null);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from psysrelobjectclassinfo x, zsysclassattrinfo y");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               where a.pclass_obid   = x.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               and   x.pnames        = y.pclass_name");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               and   y.pcolumn_alias = a.pcolumn_alias");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               and   y.pdbms_column  = a.pdbms_column)");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSCLASSATTR_KIND_RO));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSCLASSATTR_FLAG_Active));

        variableParameter.setSql(sqlStrBuff.toString());

        List<OmcSchemaSysClassAttrInfoVO> relResult = schemaDao.selectList("SchemaNew.dynamicRetrieveAttr", variableParameter);

        if(!NullUtil.isNone(relResult)) result.addAll(relResult);
        return(result);
    }
    public OmcSchemaSysClassAttrInfoVO getTemporarySystemAttrWithObid(String obid){
        return(getSystemAttrWithObid(true,obid));
    }
    public OmcSchemaSysClassAttrInfoVO getSystemAttrWithObid(String obid){
        return(getSystemAttrWithObid(false,obid));
    }
    private OmcSchemaSysClassAttrInfoVO getSystemAttrWithObid(boolean isTemporary, String obid){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        if(isTemporary){
            OmcSchemaSysClassAttrInfo.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        }else{
            OmcSchemaSysClassAttrInfo.getCommonSelectSql(sqlStrBuff, variableParameter,obid);
        }
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid = #{funVariable_00001}");
        variableParameter.setFunVariable_00001(obid);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaSysClassAttrInfoVO result = schemaDao.select("SchemaNew.dynamicRetrieveAttr", variableParameter);
        return(result);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemClassAttr(OmcSchemaSysClassAttrInfoVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psysclassattrinfo(         obid"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pkinds"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pclass_obid"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute_name"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdbms_column"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcolumn_alias"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdata_type"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",plengths"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdefault_value"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psortings"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",powners"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pvalue_list"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdisplayed_name"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",premarks"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psequences"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pchange_comments"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{kinds}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{classObid}"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attributeName}"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{dbmsColumn}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{columnAlias}"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{dataType}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{lengths}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{defaultValue}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{sortings}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{owners}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{valueList}"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{displayedName}"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{remarks}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{sequences}"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{changeComments}" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemClassAttr(OmcSchemaSysClassAttrInfoVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();

        sqlStrBuff.append("update psysclassattrinfo a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags                        = #{flags}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pkinds                        = #{kinds}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pclass_obid                   = #{classObid}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute_name               = #{attributeName} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdbms_column                  = #{dbmsColumn}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pcolumn_alias                 = #{columnAlias}   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdata_type                    = #{dataType}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.plengths                      = #{lengths}       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdefault_value                = #{defaultValue}  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.psortings                     = #{sortings}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.powners                       = #{owners}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pvalue_list                   = #{valueList}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdisplayed_name               = #{displayedName} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.premarks                      = #{remarks}       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.psequences                    = #{sequences}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pchange_comments              = #{changeComments}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier                     = #{modifier}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified                    = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid                          = #{obid}        ");

        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemClassAttr(OmcSchemaSysClassAttrInfoVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.SYSCLASSATTR_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.SYSCLASSATTR_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psysclassattrinfo a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemClassAttr(OmcSchemaSysClassAttrInfoVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete from psysclassattrinfo"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    public void refreshClassAttrInfo(){
        deleteAllClassAttrRefInfo();
        List<OmcSchemaClassAttrRefVO> result = getClassAttrRefAll();
        createClassAttrRefInfoBatch(result);
        updateSortForFoundationAttr();
    }
    @Transactional(propagation = Propagation.REQUIRED)
    private void deleteAllClassAttrRefInfo(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append                                      ("delete from psysattrref");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    private Map<String,List<OmcSchemaSysClassAttrInfoVO>> getAttributeListForClassAll(List<OmcSchemaSysBizClassVO> bizList,List<OmcSchemaSysRelClassVO> relList){
        Map<String,List<OmcSchemaSysClassAttrInfoVO>> classAttrMap = new HashMap<String,List<OmcSchemaSysClassAttrInfoVO>>();
        List<OmcSchemaSysClassAttrInfoVO> attrList = null;
        for(OmcSchemaSysBizClassVO vo : bizList){
            OmcSchemaSysBizClass bizDom = new OmcSchemaSysBizClass(vo);
            attrList = bizDom.getAttribute();
            classAttrMap.put(vo.getNames(), attrList);
        }
        for(OmcSchemaSysRelClassVO vo : relList){
            OmcSchemaSysRelClass bizDom = new OmcSchemaSysRelClass(vo);
            attrList = bizDom.getAttribute();
            classAttrMap.put(vo.getNames(), attrList);
        }
        return classAttrMap;
    }
    private List<OmcSchemaClassAttrRefVO> getClassAttrRefAll(){
        List<OmcSchemaClassAttrRefVO> result  = new ArrayList<OmcSchemaClassAttrRefVO>();
        List<OmcSchemaSysBizClassVO> bizList = OmcSchemaSysBizClass.getAll(true);
        List<OmcSchemaSysRelClassVO> relList = OmcSchemaSysRelClass.getAll(true);

        Map<String,List<OmcSchemaSysClassAttrInfoVO>> classAttrMap = getAttributeListForClassAll(bizList,relList);
        
        int sortNumber  = 0;
        String isInherited = "Y";
        List<OmcSchemaSysClassAttrInfoVO> attrList=  null;
        for(OmcSchemaSysBizClassVO vo : bizList){
            List<OmcSchemaSysBizClassVO> parentList = OmcSchemaSysBizClass.getParentClassList(true, null, vo.getNames(), 100);
            sortNumber  = 0;
            for(int i = parentList.size() - 1; i >=0 ; i--){
                isInherited = vo.getNames().equals(parentList.get(i).getNames()) ? "N" : "Y";
                //OmcSchemaSysBizClass bizDom = new OmcSchemaSysBizClass(parentList.get(i));
                attrList = classAttrMap.get(parentList.get(i).getNames());
                makeAttrRef(sortNumber,"BO",vo.getFlags(),isInherited,vo.getDbmsTable(),vo.getNames(),parentList.get(i).getNames(),attrList,result);
                sortNumber += attrList.size();
            }
        }
        
        for(OmcSchemaSysRelClassVO vo : relList){
            if(!OmcSystemConstants.OBJECT_ROOT.equals(vo.getNames())){
                List<OmcSchemaSysRelClassVO> parentList = OmcSchemaSysRelClass.getParentClassList(true, null, vo.getNames(), 100);
                sortNumber  = 0;
                for(int i = parentList.size() - 1; i >=0 ; i--){
                    isInherited = vo.getNames().equals(parentList.get(i).getNames()) ? "N" : "Y";
                    attrList = classAttrMap.get(parentList.get(i).getNames());
                    //OmcSchemaSysRelClass bizDom = new OmcSchemaSysRelClass(parentList.get(i));
                    //List<OmcSchemaSysClassAttrInfoVO> attrList = bizDom.getAttribute();
                    makeAttrRef(sortNumber,"RO",vo.getFlags(),isInherited,vo.getDbmsTable(),vo.getNames(),parentList.get(i).getNames(),attrList,result);
                    sortNumber += attrList.size();
                }
            }
        }
        return result;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    private void updateSortForFoundationAttr(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("update psysattrref a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.psorting = case when a.pattribute_name = #{funVariable_00001} then 1001");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                      when a.pattribute_name = #{funVariable_00002} then 1002");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                      when a.pattribute_name = #{funVariable_00003} then 1003");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                      when a.pattribute_name = #{funVariable_00004} then 1004");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                      when a.pattribute_name = #{funVariable_00005} then 1005");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                      when a.pattribute_name = #{funVariable_00006} then 1006");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                      when a.pattribute_name = #{funVariable_00007} then 1007");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                      when a.pattribute_name = #{funVariable_00008} then 1008");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 else a.psorting end");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pattribute_name in(#{funVariable_00001},#{funVariable_00002},#{funVariable_00003},#{funVariable_00004},#{funVariable_00005},#{funVariable_00006},#{funVariable_00007},#{funVariable_00008})");
        variableParameter.setSql(sqlStrBuff.toString());
        variableParameter.setFunVariable_00001("locker");
        variableParameter.setFunVariable_00002("checkouter");
        variableParameter.setFunVariable_00003("checkouted");
        variableParameter.setFunVariable_00004("owner");
        variableParameter.setFunVariable_00005("creator");
        variableParameter.setFunVariable_00006("created");
        variableParameter.setFunVariable_00007("modifier");
        variableParameter.setFunVariable_00008("modified");
        schemaDao.update("SchemaNew.dynamicModify", variableParameter);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    private void createClassAttrRefInfoBatch(List<OmcSchemaClassAttrRefVO> list){
        uploadCommonBatch(list,"SchemaNew.createClassAttrRefBatch");
    }
    private void makeAttrRef(int sortNumber, String classType,long flags, String isInherited, String dbmsTable, String className,String definedClassName, List<OmcSchemaSysClassAttrInfoVO> inList,List<OmcSchemaClassAttrRefVO> result){
        int sortNumberW = sortNumber;
        for(OmcSchemaSysClassAttrInfoVO vo : inList){
            OmcSchemaClassAttrRefVO refVO = new OmcSchemaClassAttrRefVO();
            sortNumberW++;
            refVO.setClassType(classType);
            refVO.setClassName(className);
            refVO.setDbmsTable(vo.getDbmsTable());
            refVO.setFlagsClass(flags);
            refVO.setAttributeName(vo.getAttributeName());
            refVO.setFlagsAttribute(vo.getFlags());
            refVO.setIsInherited(isInherited);
            refVO.setDbmsColumn(vo.getDbmsColumn());
            refVO.setColumnAlias(vo.getColumnAlias());
            refVO.setDataType(vo.getDataType());
            refVO.setLengths(vo.getLengths());
            refVO.setNullable(vo.isNullAble() ? "Y" : "N");
            refVO.setDefaultValue(vo.getDefaultValue());
            refVO.setValueSettingInfo(vo.getValueList());
            refVO.setSorting(sortNumberW);
            refVO.setDefinedClassName(definedClassName);
            refVO.setAttributeSequence(sortNumberW);
            refVO.setDbmsTable(dbmsTable);
            result.add(refVO);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryMenuList(List<OmcSchemaMenuVO> list){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("delete from zsysmenu");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        uploadCommonBatch(list,"SchemaNew.createTemporayMenuBatch");
    }
    public Map<String,Object> getErrorListForForUploadMenuList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        Map<String,Object> map = new HashMap<String,Object>();
        boolean existsError = false;

      //--------------------------------------------------------------------------------------------------------------
        OmcSchemaMenu.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pkinds_str = 'Class Menu'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and exists(select * from zsysmenu x where a.pnames = x.pnames and x.pnames <> x.psub_names and x.pkinds_str <> 'Command')");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pnames = a.psub_names");

        List<OmcSchemaMenuVO> result = new ArrayList<OmcSchemaMenuVO>();
        variableParameter.setSql(sqlStrBuff.toString());
        result = schemaDao.selectList("SchemaNew.dynamicRetrieveMenu", variableParameter);
        if (result.size() > 0) {existsError = true; map.put("Class Menu must have only Command", result);}

        //--------------------------------------------------------------------------------------------------------------
        sqlStrBuff.setLength(0);
        variableParameter = new OmcSQLVariableParameter();
        OmcSchemaMenu.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pkinds_str = 'Class Popup Menu'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and exists(select * from zsysmenu x where a.pnames = x.pnames and x.pnames <> x.psub_names and x.pkinds_str not in('Command','Menu'))");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pnames = a.psub_names");

        variableParameter.setSql(sqlStrBuff.toString());
        result = schemaDao.selectList("SchemaNew.dynamicRetrieveMenu", variableParameter);
        if (result.size() > 0) {existsError = true; map.put("Class Poup Menu must have Command or Menu", result);}
      //--------------------------------------------------------------------------------------------------------------
        sqlStrBuff.setLength(0);
        variableParameter = new OmcSQLVariableParameter();
        OmcSchemaMenu.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pkinds_str <> 'Toolbar'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and exists(select * from zsysmenu x, zsysmenu y");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("           where a.pnames = x.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("           and   x.pnames <> x.psub_names");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("           and   x.psub_names = y.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("           and   y.pnames = y.psub_names");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("           and   y.pkinds_str in('Combo','Calendar','Text'))");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pnames = a.psub_names");

        variableParameter.setSql(sqlStrBuff.toString());
        result = schemaDao.selectList("SchemaNew.dynamicRetrieveMenu", variableParameter);
        if (result.size() > 0) {existsError = true; map.put("Combo,Calendar,Text can be Sub on only ToolBar", result);}
      //--------------------------------------------------------------------------------------------------------------
        sqlStrBuff.setLength(0);
        variableParameter = new OmcSQLVariableParameter();
        OmcSchemaMenu.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pkinds_str = 'Checkbox Group'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and exists(select * from zsysmenu x where a.pnames = x.pnames and x.pnames <> x.psub_names and x.pkinds_str not in('Checkbox'))");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pnames = a.psub_names");

        variableParameter.setSql(sqlStrBuff.toString());
        result = schemaDao.selectList("SchemaNew.dynamicRetrieveMenu", variableParameter);
        if (result.size() > 0) {existsError = true; map.put("Checkbox Group only have checkbox", result);}
      //--------------------------------------------------------------------------------------------------------------
        sqlStrBuff.setLength(0);
        variableParameter = new OmcSQLVariableParameter();
        OmcSchemaMenu.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pkinds_str = 'Radio Group'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   exists(select * from zsysmenu x where a.pnames = x.pnames and x.pnames <> x.psub_names and x.pkinds_str not in('Radio'))");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pnames = a.psub_names");

        variableParameter.setSql(sqlStrBuff.toString());
        result = schemaDao.selectList("SchemaNew.dynamicRetrieveMenu", variableParameter);
        if (result.size() > 0) {existsError = true; map.put("Radio Group only have Radio", result);}
      //--------------------------------------------------------------------------------------------------------------
        sqlStrBuff.setLength(0);
        variableParameter = new OmcSQLVariableParameter();
        OmcSchemaMenu.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pkinds_str = 'Radio Group'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   exists(select * from zsysmenu x where a.pnames = x.pnames and x.pnames <> x.psub_names and x.pkinds_str not in('Radio'))");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pnames = a.psub_names");

        variableParameter.setSql(sqlStrBuff.toString());
        result = schemaDao.selectList("SchemaNew.dynamicRetrieveMenu", variableParameter);
        if (result.size() > 0) {existsError = true; map.put("Radio Group only have Radio", result);}
      //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        sqlStrBuff.setLength(0);
        variableParameter = new OmcSQLVariableParameter();
        OmcSchemaMenu.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and (a.pnames,a.psub_names) in (select x.pnames,x.psub_names from zsysmenu x where x.pnames <> x.psub_names group by x.pnames,x.psub_names having count(*) > 1)");

        variableParameter.setSql(sqlStrBuff.toString());
        result = schemaDao.selectList("SchemaNew.dynamicRetrieveMenu", variableParameter);
        if (result.size() > 0) {existsError = true; map.put("Duplicate Error", result);}
      //--------------------------------------------------------------------------------------------------------------
        if(existsError) map.put("isSucess", "false"); else  map.put("isSucess", "true");
        return map;
    }
    public List<OmcSchemaMenuVO> getInactiveMenuListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaMenu.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsysmenu x where a.pnames = x.pnames and x.pnames = x.psub_names)");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaMenuVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveMenu", variableParameter);
        return(result);

    }
    @Override
    public List<OmcSchemaMenuVO> getMenuListForCommonUserWithObid(List<String> obidList, boolean isActiveOnly){
        return(getMenuListForCommonUserSub(obidList,isActiveOnly,true));
    }
    @Override
    public List<OmcSchemaMenuVO> getMenuListForCommonUserWithName(List<String> nameList, boolean isActiveOnly){
        return(getMenuListForCommonUserSub(nameList,isActiveOnly,false));
    }
    @Override
    public List<String> getMenuSetForCommonUserWithObid(List<String> obidList){
        return getMenuSetForCommonUserSub(obidList,true);
    }
    @Override
    public List<String> getMenuSetForCommonUserWithNames(List<String> nameList){
        return getMenuSetForCommonUserSub(nameList,false);
    }
    private List<String> getMenuSetForCommonUserSub(List<String> nameList, boolean isObid){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("kindGroupMenu", OmcSystemConstants.SYSREL_KIND_GroupMenu);
        map.put("kindRoleMenu", OmcSystemConstants.SYSREL_KIND_RoleMenu);
        map.put("kindUserMenu", OmcSystemConstants.SYSREL_KIND_UserMenu);
        map.put("activeOnly", "Y");
        map.put("relationActiveFlag", OmcSystemConstants.SYSREL_FLAG_Active);
        map.put("menuActiveFlag", OmcSystemConstants.SYSMNU_FLAG_Active);
        map.put("obidList", nameList);
        List<String> result;
        if(isObid){
            result = schemaDao.selectList("SchemaNew.getUserMenuSetWithObid", map);
        }else{
            result = schemaDao.selectList("SchemaNew.getUserMenuSetWithName", map);
        }
        
        return result;
    }
    
    
    public List<OmcSchemaMenuVO> getMenuListForCommonUserSub(List<String> nameList, boolean isActiveOnly,boolean isObid){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("kindGroupMenu", OmcSystemConstants.SYSREL_KIND_GroupMenu);
        map.put("kindRoleMenu", OmcSystemConstants.SYSREL_KIND_RoleMenu);
        map.put("kindUserMenu", OmcSystemConstants.SYSREL_KIND_UserMenu);
        if(isActiveOnly){
            map.put("activeOnly", "Y");
            map.put("relationActiveFlag", OmcSystemConstants.SYSREL_FLAG_Active);
            map.put("menuActiveFlag", OmcSystemConstants.SYSMNU_FLAG_Active);
        }
        map.put("obidList", nameList);
        List<OmcSchemaMenuVO> result;
        if(isObid){
            result = schemaDao.selectList("SchemaNew.getUserMenuWithObid", map);
        }else{
            result = schemaDao.selectList("SchemaNew.getUserMenuWithName", map);
        }
        
        return result;
    }
    
    
    @Override
    public List<OmcSchemaMenuVO> getMenuListForCommonUser(String obid, boolean isActiveOnly){
        List<String> list = new ArrayList<String>();
        list.add(obid);
        return(getMenuListForCommonUserWithObid(list,isActiveOnly));
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemMenu(OmcSchemaMenuVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psysmenu(                  obid"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pobject_kind"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdescriptions"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",plabels"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",plabels_kr"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",plink_herf"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",plink_alt"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pimages"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",paccess_expression" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodule_name"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pstring01"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pstring02"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pstring03"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pstring04"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pstring05"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values("            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("#{obid}"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{objectKind}"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{descriptions}"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{labels}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{labelsKr}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{linkHref}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{linkAlt}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{images}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{accessExpression}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{moduleName}"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{string01}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{string02}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{string03}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{string04}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{string05}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");

        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemMenu(OmcSchemaMenuVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete from psysmenu"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemMenu(OmcSchemaMenuVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psysmenu a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags             = #{flags}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pobject_kind       = #{objectKind}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pnames             = #{names}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdescriptions      = #{descriptions}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.plabels            = #{labels}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.plabels_kr         = #{labelsKr}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.plink_herf         = #{linkHref}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.plink_alt          = #{linkAlt}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pimages            = #{images}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.paccess_expression = #{accessExpression}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodule_name       = #{moduleName}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pstring01          = #{string01}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pstring02          = #{string02}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pstring03          = #{string03}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pstring04          = #{string04}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pstring05          = #{string05}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier          = #{modifier}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified          = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid             = #{obid}");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }

    public List<OmcSchemaMenuVO> getMenuListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaMenu.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pnames = a.psub_names");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaMenuVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveMenu", variableParameter);
        return(result);
    }
    public List<OmcSchemaRelationVO> getSubMenuInactiveListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaRelation.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from psysmenu x, zsysmenu y, psysmenu z");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               where x.pnames      = y.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               and   y.psub_names  = z.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               and   a.pfrom_obid  = x.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               and   a.pto_obid    = z.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("x.pflags", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_Active));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("z.pflags", "#{funVariable_00002}", "#{funVariable_00002})"));
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_Active));

        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pschema_kind", "#{funVariable_00003}", "#{funVariable_00003}"));
        variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSREL_KIND_SubMnuCmd));

        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00004}", "#{funVariable_00004}"));
        variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSREL_FLAG_Active));

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaRelationVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelation", variableParameter);
        return(result);

    }
    public List<OmcSchemaRelationVO> getSubMenuCreateListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("                              select  b.obid                                             from_obid         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("c.obid                                             to_obid           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.psortings                                        sorting           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("case when a.plabels is null then c.plabels else a.plabels end                                       attribute04       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("case when a.plink_href is null then c.plink_herf else a.plink_href end                              attribute02       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("case when a.plink_alt is null then c.plink_alt else a.plink_alt end                                 attribute03       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("case when a.pimages is null then c.pimages else a.pimages end                                       attribute05       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("case when a.paccess_expression is null then c.paccess_expression else a.paccess_expression end      attribute01       ,");

        sqlStrBuff.append(OmcFoundationConstant.newline).append("case when a.pis_hidden in ('Y','N') then a.pis_hidden");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("else");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    case when " + OmcSchemaUtil.getBitAndStr("c.pflags", "#{funVariable_00001}", "#{funVariable_00001}",false) + " then 'Y'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    else 'N'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    end");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("end                                               attribute19         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("case when a.pcalling_type in('Smart Client Function','Java Script Function','Smart Client Window Open','Contents Replace','Java Method Call') then a.pcalling_type");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("else");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    case  when " + OmcSchemaUtil.getBitAndStr("c.pflags", "#{funVariable_00002}", "#{funVariable_00002}",false) + " then  'Smart Client Function'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("          when " + OmcSchemaUtil.getBitAndStr("c.pflags", "#{funVariable_00003}", "#{funVariable_00003}",false) + " then  'Java Script Function'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("          when " + OmcSchemaUtil.getBitAndStr("c.pflags", "#{funVariable_00004}", "#{funVariable_00004}",false) + " then  'Contents Replace'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("          when " + OmcSchemaUtil.getBitAndStr("c.pflags", "#{funVariable_00005}", "#{funVariable_00005}",false) + " then  'Smart Client Window Open'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("          when " + OmcSchemaUtil.getBitAndStr("c.pflags", "#{funVariable_00006}", "#{funVariable_00006}",false) + " then  'PLSQL Function'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("          when " + OmcSchemaUtil.getBitAndStr("c.pflags", "#{funVariable_00007}", "#{funVariable_00007}",false) + " then  'Java Method Call'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("          when " + OmcSchemaUtil.getBitAndStr("c.pflags", "#{funVariable_00008}", "#{funVariable_00008}",false) + " then  'Checkbox URL Call'");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    else");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    ''");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("    end");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("end                                               attribute20       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.psequences                                      attribute06       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.pchange_comments                                attribute07       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.powners                                         attribute08       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.pdescriptions                                   attribute09       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.pmodule_name                                    attribute10       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.plabels                                         attribute11       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.plink_href                                      attribute12       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.plink_alt                                       attribute13       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.pimages                                         attribute14       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.paccess_expression                              attribute15       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.pis_hidden                                      attribute16       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("a.pcalling_type                                   attribute17       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("(select x.obid from psysrelationinfo x where x.pfrom_obid = b.obid and x.pto_obid = c.obid " + OmcSchemaUtil.getBitAndStr("x.pschema_kind", "#{funVariable_00009}", "#{funVariable_00009}") + ") obid");

        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysmenu a, psysmenu b, psysmenu c");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames     = b.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.psub_names = c.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pnames     <> a.psub_names");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00010}", "#{funVariable_00010}"));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("c.pflags", "#{funVariable_00011}", "#{funVariable_00011}"));

        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_Hidden));
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_SCFunction));
        variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_JSFunction));
        variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_ContentsRepl));
        variableParameter.setFunVariable_00005(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_SCWindow));
        variableParameter.setFunVariable_00006(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_PLSQLFunction));
        variableParameter.setFunVariable_00007(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_CheckGrpMethod));
        variableParameter.setFunVariable_00008(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_CheckGrpFunCall));
        variableParameter.setFunVariable_00009(String.valueOf(OmcSystemConstants.SYSREL_KIND_SubMnuCmd));
        variableParameter.setFunVariable_00010(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_Active));
        variableParameter.setFunVariable_00011(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_Active));

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaRelationVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelation", variableParameter);
        return(result);
    }
    public OmcSchemaMenuVO getSystemMenuWithObid(String obid){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaMenu.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001} or a.pnames = #{funVariable_00001}");
        variableParameter.setFunVariable_00001(obid);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaMenuVO result = schemaDao.select("SchemaNew.dynamicRetrieveMenu", variableParameter);
        return(result);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemMenu(OmcSchemaMenuVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.SYSMNU_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.SYSMNU_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psysmenu a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryTabLayoutList(List<OmcSchemaTabLayoutVO> list){

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsyslayouttab");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        
        uploadCommonBatch(list,"SchemaNew.createTemporayLayoutTabBatch");
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryLifeCycle(List<OmcSchemaLifeCycleVO> list){

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsyslifecycle");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);

        uploadCommonBatch(list,"SchemaNew.createTemporayLifeCycleBatch");
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryStateInfo(List<OmcSchemaLifeCycleStateInfoVO> list){

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("delete from zsyslifecyclestateinfo");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        
        uploadCommonBatch(list,"SchemaNew.createTemporayStateInfoBatch");
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryLifeCycleBranch(List<OmcSchemaLifeCycleBranchVO> list){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("delete from zsysbranch");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        
        uploadCommonBatch(list,"SchemaNew.createTemporayBranchInfoBatch");
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryTriggerParameter(List<OmcSchemaTriggerParameterVO> list){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsystriggerparameter");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        
        uploadCommonBatch(list,"SchemaNew.createTemporayTriggerParameterBatch");
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryStateTrigger(List<OmcSchemaStateTriggerVO> list){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("delete from zsysstatetrigger");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        
        uploadCommonBatch(list,"SchemaNew.createTemporayStateTriggerBatch");
    }

    public List<OmcSchemaLifeCycleVO> getLifeCycleListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaLifeCycle.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaLifeCycleVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveLifeCycle", variableParameter);
        return(result);
    }
    public List<OmcSchemaLifeCycleVO> getInActiveLifeCycleListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaLifeCycle.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsyslifecycle x where a.pnames = x.pnames)");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaLifeCycleVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveLifeCycle", variableParameter);
        return(result);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemLifeCycle(OmcSchemaLifeCycleVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.SYSLCYCLE_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.SYSLCYCLE_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psyslifecycle a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemLifeCycle(OmcSchemaLifeCycleVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psyslifecycle(             obid"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",papplied_class_list"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psequence_rule"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdefault_format"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pstate_list"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pall_state_flag"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdisplayed_name"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",papplied_format_list" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psequences"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pchange_comments"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values("              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("#{obid}"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{appliedClassList}"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{sequenceRule}"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{defaultFormat}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{stateList}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{allStateFlag}"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{displayedName}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{appliedFormatList}" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{sequences}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{changeComments}"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");

        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemLifeCycle(OmcSchemaLifeCycleVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete from psyslifecycle"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    public void modifySystemLifeCycle(OmcSchemaLifeCycleVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psyslifecycle a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.obid                  = #{obid}              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pflags                = #{flags}             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pnames                = #{names}             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.papplied_class_list   = #{appliedClassList}  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pstate_list           = #{stateList}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pall_state_flag       = #{allStateFlag}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.papplied_format_list  = #{appliedFormatList} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.psequences            = #{sequences}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pchange_comments      = #{changeComments}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.psequence_rule        = #{sequenceRule}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdefault_format       = #{defaultFormat}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdisplayed_name       = #{displayedName}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier             = #{modifier}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified             = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid             = #{obid}");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }
    public OmcSchemaLifeCycleVO getSystemLifeCycleWithObid(String obid){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaLifeCycle.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(obid);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaLifeCycleVO result = schemaDao.select("SchemaNew.dynamicRetrieveLifeCycle", variableParameter);
        return(result);
    }
    /**
     *
     * @param lifeCycleVO
     * @return
     * @see omc.schema.serviceutil.OmcSchemaUtilService#getSystemStateListWithLifeCycle(omc.schema.object.model.OmcSchemaLifeCycleVO)
     */
    public List<OmcSchemaStatesVO> getSystemStateListWithLifeCycle(OmcSchemaLifeCycleVO lifeCycleVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaStates.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.plife_cycle = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(lifeCycleVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaStatesVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveState", variableParameter);
        return(result);
    }

    /**
     *
     * @param lifeCycleVO
     * @param kinds
     * @return
     */
    public List<OmcSchemaLifeCycleRelationVO> getSystemLifeCycleRelListWithLifeCycle(OmcSchemaLifeCycleVO lifeCycleVO, long kinds){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append                                      ("select a.obid        as obid       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pflags      as flags      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pkinds      as kinds      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pfrom_obid  as from_obid  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pto_obid    as to_obid    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreator    as creator    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreated    as created    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodifier   as modifier   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodified   as modified   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psyslifecycleinfo a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pfrom_obid = #{obid}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",kinds,kinds));
        lifeCycleVO.setSql(sqlStrBuff.toString());
        List<OmcSchemaLifeCycleRelationVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveLifeCycleRelation", lifeCycleVO);
        return(result);
    }
    
    public List<OmcSchemaLifeCycleRelationVO> getSystemLifedCycleRelListWithLifeCycle(OmcSchemaLifeCycleVO lifeCycleVO, long kinds){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append                                      ("select a.obid        as obid       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pflags      as flags      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pkinds      as kinds      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pfrom_obid  as from_obid  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pto_obid    as to_obid    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreator    as creator    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreated    as created    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodifier   as modifier   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodified   as modified   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psyslifecycleinfo a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pfrom_obid = #{obid}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",kinds,kinds));
        lifeCycleVO.setSql(sqlStrBuff.toString());
        List<OmcSchemaLifeCycleRelationVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveLifeCycleRelation", lifeCycleVO);
        return(result);
    }
    
    
    
    
    /**
     *
     * @param vo
     * @see omc.schema.serviceutil.OmcSchemaUtilService#inactiviateSchemaRelation(omc.schema.object.model.OmcSchemaRelationVO)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactiviateSchemaRelation(OmcSchemaRelationVO vo){
        if(Bit.isInclude(vo.getFlags(), OmcSystemConstants.SYSREL_FLAG_Active)){
            vo.setFlags(Bit.xor(vo.getFlags(),OmcSystemConstants.SYSREL_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            sqlStrBuff.append("update psysrelationinfo a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags = #{flags}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid = #{obid}");
            vo.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", vo);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSchemaRelation(OmcSchemaRelationVO vo){
            StringBuffer sqlStrBuff = new StringBuffer();
            sqlStrBuff.append("delete from psysrelationinfo");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where obid = #{obid}");
            vo.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicDelete", vo);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySchemaRelation(OmcSchemaRelationVO vo){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psysrelationinfo a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags                        = #{flags}                           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pfrom_obid                    = #{fromObid}                        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pto_obid                      = #{toObid}                          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pschema_kind                  = #{schemaKind}                      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.psorting                      = #{sorting}                         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute01                  = #{attribute01}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute02                  = #{attribute02}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute03                  = #{attribute03}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute04                  = #{attribute04}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute05                  = #{attribute05}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute07                  = #{attribute07}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute06                  = #{attribute06}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute08                  = #{attribute08}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute09                  = #{attribute09}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute10                  = #{attribute10}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute11                  = #{attribute11}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute12                  = #{attribute12}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute13                  = #{attribute13}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute14                  = #{attribute14}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute15                  = #{attribute15}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute16                  = #{attribute16}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute17                  = #{attribute17}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute18                  = #{attribute18}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute19                  = #{attribute19}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pattribute20                  = #{attribute20}                     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier                     = #{modifier}                        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified                     = " + OmcDBMSConstants.DBMS_SYSTEM_DATE );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{obid} ");
        vo.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", vo);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSchemaRelation(OmcSchemaRelationVO vo){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psysrelationinfo(          obid"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pfrom_obid"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pto_obid"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pschema_kind"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psorting"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute01"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute02"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute03"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute04"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute05"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute06"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute07"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute08"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute09"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute10"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute11"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute12"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute13"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute14"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute15"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute16"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute17"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute18"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute19"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pattribute20"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{fromObid}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{toObid}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{schemaKind}"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{sorting}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute01}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute02}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute03}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute04}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute05}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute06}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute07}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute08}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute09}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute10}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute11}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute12}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute13}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute14}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute15}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute16}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute17}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute18}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute19}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{attribute20}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        vo.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicCreate", vo);
    }
    public List<OmcSchemaLifeCycleBranchVO> getLifeCycleTempBranchList(){
      StringBuffer sqlStrBuff = new StringBuffer();
      OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
      sqlStrBuff.append("                                      select a.psequences        sequences"      );
      sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pchange_comments  change_comments");
      sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plife_cycle       life_cycle"     );
      sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pbracnh_info      bracnh_info"    );
      sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysbranch a"                         );
      sqlStrBuff.append(OmcFoundationConstant.newline).append("order by a.plife_cycle"                    );
      variableParameter.setSql(sqlStrBuff.toString());
      List<OmcSchemaLifeCycleBranchVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveTempBranch", variableParameter);
      return(result);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryBranchList(List<OmcSchemaSeperatedBranchVO> branchList){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsysbranchseperated");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        sqlStrBuff.setLength(0);

        sqlStrBuff.append("       insert into zsysbranchseperated(plife_cycle"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pkinds"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pstates"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pto_object"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcondition_rule"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{lifeCycle}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{kinds}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{states}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{toObject}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{conditionRule}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")"                   );
        for (OmcSchemaSeperatedBranchVO vo : branchList){
            vo.setSql(sqlStrBuff.toString());
        }
        schemaDao.batchInsert("SchemaNew.dynamicCreate", branchList);
    }
    public List<OmcSchemaBranchVO> getInactiveBranchList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaBranch.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", psysstate b, psyslifecycle c");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and a.pstates = b.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and b.plife_cycle = c.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsysbranchseperated x");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               where x.plife_cycle = c.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               and x.pnames  = a.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               and x.pstates = b.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("               and x.pkinds  = a.pkinds)");
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaBranchVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveBranch", variableParameter);
        return(result);
    }
    public List<OmcSchemaBranchVO> getUploadBranchList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append(                              "select (select z.obid from psysbranch z where z.pstates = b.obid and z.pnames = a.pnames and z.pkinds = a.pkinds)     as obid,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   a.pnames          as names           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   a.pkinds          as kinds           ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   b.obid            as states          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   d.obid            as to_object       ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   a.pcondition_rule as condition_rule"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysbranchseperated a, psysstate b, psyslifecycle c, psysstate d");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.plife_cycle    = c.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pstates        = b.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   b.plife_cycle    = c.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",OmcSystemConstants.SYSBRANCH_KIND_State,OmcSystemConstants.SYSBRANCH_KIND_State));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   c.obid           = d.plife_cycle");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pto_object     = d.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("union all");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("select (select z.obid from psysbranch z where z.pstates = b.obid and z.pnames = a.pnames and z.pkinds = a.pkinds)     as obid,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   a.pnames          as names          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   a.pkinds          as kinds          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   b.obid            as states         ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   a.pto_object      as to_object      ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   a.pcondition_rule as condition_rule"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysbranchseperated a, psysstate b, psyslifecycle c");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.plife_cycle    = c.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pstates        = b.pnames");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   b.plife_cycle    = c.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",OmcSystemConstants.SYSBRANCH_KIND_Condition,OmcSystemConstants.SYSBRANCH_KIND_Condition));

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaBranchVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveBranch", variableParameter);
        return(result);
    }
    /*************************************************************************************************************/
    /********************************* File Format ***************************************************************/
    /*************************************************************************************************************/

    public OmcSchemaFileFormatVO getSystemFileFormatWithObid(String obid){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaFileFormat.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(obid);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaFileFormatVO result = schemaDao.select("SchemaNew.dynamicRetrieveFileFormat", variableParameter);
        return(result);
    }
    public OmcSchemaFileFormatVO getSystemFileFormatWithNames(String names){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaFileFormat.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(names);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaFileFormatVO result = schemaDao.select("SchemaNew.dynamicRetrieveFileFormat", variableParameter);
        return(result);
    }
    public void createSystemFileFormat(OmcSchemaFileFormatVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psysformat(                obid"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdisplayed_names"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pallowed_suffix"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values("            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("#{obid}"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{displayedNames}"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{allowedSuffix}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");

        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemFileFormat(OmcSchemaFileFormatVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.SYSFORMAT_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.SYSFORMAT_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psysformat a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemFileFormat(OmcSchemaFileFormatVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete from psysformat"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemFileFormat(OmcSchemaFileFormatVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psysformat a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags             = #{flags}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdisplayed_names   = #{displayedNames}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pallowed_suffix    = #{allowedSuffix} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier          = #{modifier}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified          = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid             = #{obid}");

        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryFileFormat(List<OmcSchemaFileFormatVO> list){

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsysformat");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        sqlStrBuff.setLength(0);
        sqlStrBuff.append("insert into zsysformat(psequences");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pchange_comments"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pallowed_suffix"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdisplayed_names"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",powners"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("#{sequences}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{changeComments}" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{allowedSuffix}"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{displayedNames}" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{owners}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        for (OmcSchemaFileFormatVO vo : list){
            vo.setSql(sqlStrBuff.toString());
        }
        schemaDao.batchInsert("SchemaNew.dynamicCreate", list);
    }
    public List<OmcSchemaFileFormatVO> getFileFormatListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaFileFormat.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaFileFormatVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveFileFormat", variableParameter);
        return(result);
    }

    public List<OmcSchemaFileFormatVO> getInActiveFileFormatListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaFileFormat.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsysformat x where a.pnames = x.pnames)");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaFileFormatVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveFileFormat", variableParameter);
        return(result);
    }

    public List<OmcSchemaFileFormatVO> getFileFormatListWithList(List<OmcSchemaFileFormatVO> list){
        List<OmcSchemaFileFormatVO> result = new ArrayList<OmcSchemaFileFormatVO>();
        OmcSchemaFileFormatVO rsltVO = new OmcSchemaFileFormatVO();
        for(OmcSchemaFileFormatVO vo : list){
            rsltVO = this.getSystemFileFormatWithNames(vo.getNames());
            if (rsltVO != null){
                result.add(rsltVO);
            }
        }
        return(result);
    }
    public List<OmcSchemaFileFormatVO> getFileFormatListWithSeperatedList(List<OmcSchemaSeperatedFormatVO> seperatedFormatList){
        List<OmcSchemaFileFormatVO> result = new ArrayList<OmcSchemaFileFormatVO>();
        OmcSchemaFileFormatVO rsltVO = new OmcSchemaFileFormatVO();
        for(OmcSchemaSeperatedFormatVO vo : seperatedFormatList){
            rsltVO = this.getSystemFileFormatWithNames(vo.getNames());
            if (rsltVO != null){
                result.add(rsltVO);
            }
        }
        return(result);
    }
    /*************************************************************************************************************/
    /********************************* Life Cycle Relation********************************************************/
    /*************************************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSchemaLifeCycleRelation(OmcSchemaLifeCycleRelationVO vo){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psyslifecycleinfo(         obid"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pkinds"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pfrom_obid"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pto_obid"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{kinds}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{fromObid}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{toObid}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        vo.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicCreate", vo);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactiviateSchemaLifeCycleRelation(OmcSchemaLifeCycleRelationVO vo){
        if(Bit.isInclude(vo.getFlags(), OmcSystemConstants.SYSLIFEINFO_FLAG_Active)){
            vo.setFlags(Bit.xor(vo.getFlags(),OmcSystemConstants.SYSLIFEINFO_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            sqlStrBuff.append("update psyslifecycleinfo a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags = #{flags}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid = #{obid}");
            vo.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", vo);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSchemaLifeCycleRelation(OmcSchemaLifeCycleRelationVO vo){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("delete from psyslifecycleinfo");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where obid = #{obid}");
        vo.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicDelete", vo);
    }
    public OmcSchemaLifeCycleRelationVO getSchemaLifeCycleRelationWithFromToObid(OmcSchemaLifeCycleRelationVO vo){
            StringBuffer sqlStrBuff = new StringBuffer();
            sqlStrBuff.append                                      ("select a.obid        as obid      ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pflags      as flags     ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pkinds      as kinds     ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pfrom_obid  as from_obid ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pto_obid    as to_obid   ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreator    as creator   ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreated    as created   ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodifier   as modifier  ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodified   as modified  ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("from psyslifecycleinfo a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pfrom_obid = #{fromObid}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pto_obid   = #{toObid}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pkinds     = #{kinds}");
            vo.setSql(sqlStrBuff.toString());
            OmcSchemaLifeCycleRelationVO result = schemaDao.select("SchemaNew.dynamicRetrieveLifeCycleRelation", vo);
            return(result);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySchemaLifeCycleRelation(OmcSchemaLifeCycleRelationVO vo){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psyslifecycleinfo a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{flags}   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pkinds     = #{kinds}   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pfrom_obid = #{fromObid}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pto_obid   = #{toObid}  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier  = #{modifier}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified  = " + OmcDBMSConstants.DBMS_SYSTEM_DATE );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{obid} ");
        vo.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", vo);
    }
    public OmcSchemaLifeCycleStateInfoVO getLifeCycleDefinitionForState(String lifeCycleNames, String stateNames){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append(                              "select   psequences                     as sequences"                  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pchange_comments               as change_comments "           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.plife_cycle                  as life_cycle"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pstates                      as states"                     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.proute_complete_action       as route_complete_action"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pdefault_route_purpose       as default_route_purpose"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.proute_auto_start_on_reject  as route_auto_start_on_reject" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.proute_how_to_on_reject      as route_how_to_on_reject"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pinbox_task_auto_complete    as inbox_task_auto_complete"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pdate_offset_day             as date_offset_day"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pparallel_process_rule       as parallel_process_rule"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsyslifecyclestateinfo a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.plife_cycle  = #{funVariable_00001} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pstates      = #{funVariable_00002} ");
        variableParameter.setSql(sqlStrBuff.toString());
        variableParameter.setFunVariable_00001(String.valueOf(lifeCycleNames));
        variableParameter.setFunVariable_00002(String.valueOf(stateNames));
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaLifeCycleStateInfoVO result = schemaDao.select("SchemaNew.dynamicRetrieveStateDefinition", variableParameter);
        return(result);
    }
    /*************************************************************************************************************/
    /********************************* Life Cycle State **********************************************************/
    /*************************************************************************************************************/


    public OmcSchemaStatesVO getSystemStateWithObid(String obid){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaStates.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(obid);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaStatesVO result = schemaDao.select("SchemaNew.dynamicRetrieveState", variableParameter);
        return(result);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemState(OmcSchemaStatesVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psysstate                 (obid"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",plife_cycle"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psequence"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{lifeCycle}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{sequence}"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemState(OmcSchemaStatesVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete from psysstate"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemState(OmcSchemaStatesVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psysstate a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags      = #{flags}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pnames      = #{names}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.plife_cycle = #{lifeCycle}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.psequence   = #{sequence} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier   = #{modifier} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified   = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid      = #{obid}");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemState(OmcSchemaStatesVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.SYSSTATE_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.SYSSTATE_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psysstate a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }

    /*************************************************************************************************************/
    /********************************* Life Cycle Branch *********************************************************/
    /*************************************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSchemaLifeCycleBranch(OmcSchemaBranchVO vo){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psysbranch(               obid"                );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pkinds"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pstates"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pto_object"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcondition_rule"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{kinds}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{states}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{toObject}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{conditionRule}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        vo.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicCreate", vo);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactiviateSchemaLifeCycleBranch(OmcSchemaBranchVO vo){
        if(Bit.isInclude(vo.getFlags(), OmcSystemConstants.SYSBRANCH_FLAG_Active)){
            vo.setFlags(Bit.xor(vo.getFlags(),OmcSystemConstants.SYSBRANCH_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            sqlStrBuff.append("update psysbranch a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags = #{flags}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid = #{obid}");
            vo.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", vo);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSchemaLifeCycleBranch(OmcSchemaBranchVO vo){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("delete from psysbranch");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where obid = #{obid}");
        vo.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicDelete", vo);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySchemaLifeCycleBranch(OmcSchemaBranchVO vo){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psysbranch a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags          = #{flags}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pkinds          = #{kinds}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pstates         = #{states}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pto_object      = #{toObject}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pcondition_rule = #{conditionRule}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier       = #{modifier}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified       = " + OmcDBMSConstants.DBMS_SYSTEM_DATE );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{obid} ");
        vo.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", vo);
    }
    /*************************************************************************************************************/
    /********************************* Trigger Parameter *********************************************************/
    /*************************************************************************************************************/
    @Override
    public OmcSchemaLifeCycleVO getSystemTriggerParameterWithObid(String obid){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaTriggerParameter.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(obid);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaLifeCycleVO result = schemaDao.select("SchemaNew.dynamicRetrieveLifeCycle", variableParameter);
        return(result);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemTriggerParameter(OmcSchemaTriggerParameterVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psystriggerparameter(obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags               ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames               ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pparm_description    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pprogram_name        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcalled_sequence     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmethod_name         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",ptarget_states       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",perror_type          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument01          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument02          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument03          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument04          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument05          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument06          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument07          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument08          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument09          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument10          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument_desc01     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument_desc02     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument_desc03     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument_desc04     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument_desc05     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument_desc06     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument_desc07     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument_desc08     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument_desc09     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pargument_desc10     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{parmDescription}   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{programName}       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{calledSequence}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{methodName}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{targetStates}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{errorType}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argument01}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argument02}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argument03}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argument04}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argument05}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argument06}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argument07}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argument08}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argument09}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argument10}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argumentDesc01}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argumentDesc02}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argumentDesc03}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argumentDesc04}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argumentDesc05}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argumentDesc06}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argumentDesc07}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argumentDesc08}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argumentDesc09}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{argumentDesc10}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");

        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemTriggerParameter(OmcSchemaTriggerParameterVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.SYSTRIGGERPGM_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.SYSTRIGGERPGM_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psystriggerparameter a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemTriggerParameter(OmcSchemaTriggerParameterVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete from psystriggerparameter"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemTriggerParameter(OmcSchemaTriggerParameterVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psystriggerparameter a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags                = #{flags}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pnames                = #{names}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pparm_description     = #{parmDescription}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pprogram_name         = #{programName}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pcalled_sequence      = #{calledSequence} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmethod_name          = #{methodName}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.ptarget_states        = #{targetStates}   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.perror_type           = #{errorType}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument01           = #{argument01}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument02           = #{argument02}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument03           = #{argument03}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument04           = #{argument04}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument05           = #{argument05}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument06           = #{argument06}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument07           = #{argument07}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument08           = #{argument08}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument09           = #{argument09}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument10           = #{argument10}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument_desc01      = #{argumentDesc01} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument_desc02      = #{argumentDesc02} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument_desc03      = #{argumentDesc03} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument_desc04      = #{argumentDesc04} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument_desc05      = #{argumentDesc05} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument_desc06      = #{argumentDesc06} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument_desc07      = #{argumentDesc07} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument_desc08      = #{argumentDesc08} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument_desc09      = #{argumentDesc09} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pargument_desc10      = #{argumentDesc10} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier             = #{modifier}       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified             = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid                = #{obid}");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }
    @Override
    public List<OmcSchemaTriggerParameterVO> getInactiveTriggerParameterList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaTriggerParameter.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where not exists(select * from zsystriggerparameter x"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 where x.pnames           = a.pnames"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.pprogram_name    = a.pprogram_name"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.pmethod_name     = a.pmethod_name"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.pcalled_sequence = a.pcalled_sequence)");
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaTriggerParameterVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveTriggerParmeter", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaTriggerParameterVO> getUploadTriggerParameterList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaTriggerParameter.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaTriggerParameterVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveTriggerParmeter", variableParameter);
        return(result);
    }

    /*************************************************************************************************************/
    /********************************* State Trigger *************************************************************/
    /*************************************************************************************************************/
    @Override
    public OmcSchemaTriggerParameterVO getSystemStateTriggerWithObid(String obid){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaTriggerParameter.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(obid);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaTriggerParameterVO result = schemaDao.select("SchemaNew.dynamicRetrieveState", variableParameter);
        return(result);
    }

    @Override
    public List<OmcSchemaStateTriggerVO> getInactiveStateTriggerList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaStateTrigger.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where not exists(select * from psyslifecycle x, psysstate y, zsysstatetrigger z"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 where a.pstates              = y.obid"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   a.pcalled_sequences    = z.pcalled_sequences"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.obid                 = y.plife_cycle"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.pnames               = z.ppolicy_name"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pkinds               = 'Promote'"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pprogram_kinds       = 'Check')"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",OmcSystemConstants.SYSSTATETRIGGER_KIND_Promote,OmcSystemConstants.SYSSTATETRIGGER_KIND_Promote));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pprogram_kinds",OmcSystemConstants.SYSSTTRIG_PKIND_Check,OmcSystemConstants.SYSSTTRIG_PKIND_Check));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("union all").append(OmcFoundationConstant.newline);
        OmcSchemaStateTrigger.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where not exists(select * from psyslifecycle x, psysstate y, zsysstatetrigger z"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 where a.pstates              = y.obid"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   a.pcalled_sequences    = z.pcalled_sequences"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.obid                 = y.plife_cycle"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.pnames               = z.ppolicy_name"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pkinds               = 'Promote'"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pprogram_kinds       = 'ActionPre')"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",OmcSystemConstants.SYSSTATETRIGGER_KIND_Promote,OmcSystemConstants.SYSSTATETRIGGER_KIND_Promote));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pprogram_kinds",OmcSystemConstants.SYSSTTRIG_PKIND_ActionPre,OmcSystemConstants.SYSSTTRIG_PKIND_ActionPre));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("union all").append(OmcFoundationConstant.newline);
        OmcSchemaStateTrigger.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where not exists(select * from psyslifecycle x, psysstate y, zsysstatetrigger z"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 where a.pstates              = y.obid"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   a.pcalled_sequences    = z.pcalled_sequences"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.obid                 = y.plife_cycle"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.pnames               = z.ppolicy_name"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pkinds               = 'Promote'"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pprogram_kinds       = 'Action')"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",OmcSystemConstants.SYSSTATETRIGGER_KIND_Promote,OmcSystemConstants.SYSSTATETRIGGER_KIND_Promote));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pprogram_kinds",OmcSystemConstants.SYSSTTRIG_PKIND_Action,OmcSystemConstants.SYSSTTRIG_PKIND_Action));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("union all").append(OmcFoundationConstant.newline);
        OmcSchemaStateTrigger.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where not exists(select * from psyslifecycle x, psysstate y, zsysstatetrigger z"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 where a.pstates              = y.obid"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   a.pcalled_sequences    = z.pcalled_sequences"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.obid                 = y.plife_cycle"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.pnames               = z.ppolicy_name"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pkinds               = 'Promote'"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pprogram_kinds       = 'ActionPost')"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",OmcSystemConstants.SYSSTATETRIGGER_KIND_Promote,OmcSystemConstants.SYSSTATETRIGGER_KIND_Promote));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pprogram_kinds",OmcSystemConstants.SYSSTTRIG_PKIND_ActionPost,OmcSystemConstants.SYSSTTRIG_PKIND_ActionPost));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("union all").append(OmcFoundationConstant.newline);
        OmcSchemaStateTrigger.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where not exists(select * from psyslifecycle x, psysstate y, zsysstatetrigger z"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 where a.pstates              = y.obid"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   a.pcalled_sequences    = z.pcalled_sequences"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.obid                 = y.plife_cycle"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.pnames               = z.ppolicy_name"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pkinds               = 'Demote'"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pprogram_kinds       = 'Check')"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",OmcSystemConstants.SYSSTATETRIGGER_KIND_Demote,OmcSystemConstants.SYSSTATETRIGGER_KIND_Demote));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pprogram_kinds",OmcSystemConstants.SYSSTTRIG_PKIND_Check,OmcSystemConstants.SYSSTTRIG_PKIND_Check));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("union all").append(OmcFoundationConstant.newline);
        OmcSchemaStateTrigger.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where not exists(select * from psyslifecycle x, psysstate y, zsysstatetrigger z"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 where a.pstates              = y.obid"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   a.pcalled_sequences    = z.pcalled_sequences"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.obid                 = y.plife_cycle"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.pnames               = z.ppolicy_name"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pkinds               = 'Demote'"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pprogram_kinds       = 'ActionPre')"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",OmcSystemConstants.SYSSTATETRIGGER_KIND_Demote,OmcSystemConstants.SYSSTATETRIGGER_KIND_Demote));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pprogram_kinds",OmcSystemConstants.SYSSTTRIG_PKIND_ActionPre,OmcSystemConstants.SYSSTTRIG_PKIND_ActionPre));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("union all").append(OmcFoundationConstant.newline);
        OmcSchemaStateTrigger.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where not exists(select * from psyslifecycle x, psysstate y, zsysstatetrigger z"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 where a.pstates              = y.obid"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   a.pcalled_sequences    = z.pcalled_sequences"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.obid                 = y.plife_cycle"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.pnames               = z.ppolicy_name"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pkinds               = 'Demote'"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pprogram_kinds       = 'Action')"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",OmcSystemConstants.SYSSTATETRIGGER_KIND_Demote,OmcSystemConstants.SYSSTATETRIGGER_KIND_Demote));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pprogram_kinds",OmcSystemConstants.SYSSTTRIG_PKIND_Action,OmcSystemConstants.SYSSTTRIG_PKIND_Action));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("union all").append(OmcFoundationConstant.newline);
        OmcSchemaStateTrigger.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where not exists(select * from psyslifecycle x, psysstate y, zsysstatetrigger z"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 where a.pstates              = y.obid"                 );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   a.pcalled_sequences    = z.pcalled_sequences"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.obid                 = y.plife_cycle"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   x.pnames               = z.ppolicy_name"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pkinds               = 'Demote'"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("                 and   z.pprogram_kinds       = 'ActionPost')"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pkinds",OmcSystemConstants.SYSSTATETRIGGER_KIND_Demote,OmcSystemConstants.SYSSTATETRIGGER_KIND_Demote));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pprogram_kinds",OmcSystemConstants.SYSSTTRIG_PKIND_ActionPost,OmcSystemConstants.SYSSTTRIG_PKIND_ActionPost));

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaStateTriggerVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveStateTrigger", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaStateTriggerVO> getUploadStateTriggerList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaStateTrigger.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        //sqlStrBuff.append(OmcFoundationConstant.newline).append("and exists(select * from psystriggerparameter x where a.ptrigger_name = x.pnames)");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaStateTriggerVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveStateTrigger", variableParameter);
        return(result);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemStateTrigger(OmcSchemaStateTriggerVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psysstatetrigger(          obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pstates"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pkinds"               );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcalled_sequences"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pprogram_kinds"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pprogram_name"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",ptrigger_name"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{states}"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{kinds}"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{calledSequences}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{programKinds}"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{programName}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{triggerName}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        parmVO.setSql(sqlStrBuff.toString());
        try {
        	schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
        }catch(Exception e){
        	System.out.println("ddddddddddddd");
        	
        	throw new FoundationException(e);
        }
        
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemStateTrigger(OmcSchemaStateTriggerVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.SYSSTATETRIGGER_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.SYSSTATETRIGGER_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psysstatetrigger a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemStateTrigger(OmcSchemaStateTriggerVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete from psysstatetrigger"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemStateTrigger(OmcSchemaStateTriggerVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psysstatetrigger a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.obid              = #{obid}            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pflags            = #{flags}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pstates           = #{states}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pkinds            = #{kinds}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pcalled_sequences = #{calledSequences} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pprogram_kinds    = #{programKinds}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pprogram_name     = #{programName}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.ptrigger_name     = #{triggerName}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier         = #{modifier}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified   = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid      = #{obid}");

        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }
    /*************************************************************************************************************/
    /********************************* File Location *************************************************************/
    /*************************************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemFileLocation(OmcSchemaFileServerVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psysfilelocation(          obid                ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pfile_server        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pfile_path          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pftp_user           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pftp_password       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pserver_protocol    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pserver_port        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pservice_domain     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pservice_port       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pservice_url        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{fileServer}       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{filePath}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{ftpUser}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{ftpPassword}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{serverProtocol}   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{serverPort}       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{serviceDomain}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{servicePort}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{serviceUrl}       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemFileLocation(OmcSchemaFileServerVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psysfilelocation a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags            = #{flags}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pfile_server      = #{fileServer}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pfile_path        = #{filePath}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pftp_user         = #{ftpUser}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pftp_password     = #{ftpPassword}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pserver_protocol  = #{serverProtocol}  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pserver_port      = #{serverPort}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pservice_domain   = #{serviceDomain}   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pservice_port     = #{servicePort}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pservice_url      = #{serviceUrl}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier         = #{modifier}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified   = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid            = #{obid}");

        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicModify", parmVO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemFileLocation(OmcSchemaFileServerVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.SYSFILELOCATION_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.SYSFILELOCATION_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psysfilelocation a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemFileLocation(OmcSchemaFileServerVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete from psysfilelocation"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }

    public List<OmcSchemaFileServerVO> getLocationListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaFileLocation.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and 1 = 1");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaFileServerVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveFileServer", variableParameter);
        return(result);
    }

    public List<OmcSchemaFileServerVO> getInActiveLocationListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaFileLocation.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsysstorelocation x where a.pnames = x.pnames and x.pkinds_str = 'Location')");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaFileServerVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveFileFormat", variableParameter);
        return(result);
    }

    /*************************************************************************************************************/
    /********************************* File Store *************************************************************/
    /*************************************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemFileStore(OmcSchemaFileServerVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psysfilestore(             obid                ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pfile_server        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pfile_path          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pftp_user           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pftp_password       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pserver_protocol    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pserver_port        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pservice_domain     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pservice_port       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pservice_url        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{fileServer}       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{filePath}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{ftpUser}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{ftpPassword}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{serverProtocol}   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{serverPort}       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{serviceDomain}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{servicePort}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{serviceUrl}       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemFileStore(OmcSchemaFileServerVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psysfilestore a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags            = #{flags}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pfile_server      = #{fileServer}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pfile_path        = #{filePath}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pftp_user         = #{ftpUser}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pftp_password     = #{ftpPassword}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pserver_protocol  = #{serverProtocol}  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pserver_port      = #{serverPort}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pservice_domain   = #{serviceDomain}   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pservice_port     = #{servicePort}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pservice_url      = #{serviceUrl}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier         = #{modifier}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified   = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid            = #{obid}");

        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }

    public List<OmcSchemaFileServerVO> getStoreListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaFileStore.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and 1 = 1");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaFileServerVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveFileServer", variableParameter);
        return(result);
    }

    public List<OmcSchemaFileServerVO> getInActiveStoreListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaFileStore.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsysstorelocation x where a.pnames = x.pnames and x.pkinds_str = 'Store')");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaFileServerVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveFileFormat", variableParameter);
        return(result);
    }

    @Override
    public OmcSchemaFileServerVO getStoreWithNames(String names){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        
        OmcSchemaFileStore.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(names);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaFileServerVO result = schemaDao.select("SchemaNew.dynamicRetrieveFileServer", variableParameter);
        return(result);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemFileStore(OmcSchemaFileServerVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.SYSFILESTORE_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.SYSFILESTORE_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psysfilestore a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemFileStore(OmcSchemaFileServerVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete from psysfilestore"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryStoreAndLocation(List<OmcSchemaFileServerVO> list){

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsysstorelocation");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list", list);
        schemaDao.insert("SchemaNew.createFileStoreLocationBatch", map);
        
    }
    /*************************************************************************************************************/
    /********************************* Role & Group  *************************************************************/
    /*************************************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryRoleNGroup(List<OmcSchemaRoleGroupVO> list){

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsysgrouprole");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list", list);
        schemaDao.insert("SchemaNew.createUserGroupBatch", map);
    }
    /*************************************************************************************************************/
    /********************************* Assign  *******************************************************************/
    /*************************************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryAssign(List<OmcSchemaStoreLocationVO> list){

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsysstorelocation");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        sqlStrBuff.setLength(0);
        sqlStrBuff.append("insert into zsysstorelocation(psequences");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pchange_comments"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pkinds"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pserver_names"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pserver_path"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pftp_user"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pftp_password"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pprotocol"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pport"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pservice_domain"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pservice_port"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pservice_url"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("#{sequences}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{changeComments}" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{kindsStr}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{serverNames}"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{serverPath}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{ftpUser}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{ftpPassword}"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{protocol}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{port}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{serviceDomain}"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{servicePort}"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{serviceUrl}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        for (OmcSchemaStoreLocationVO vo : list){
            vo.setSql(sqlStrBuff.toString());
        }
        schemaDao.batchInsert("SchemaNew.dynamicCreate", list);
    }
    /*************************************************************************************************************/
    /********************************* Access Method Set *********************************************************/
    /*************************************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryMethodSet(List<OmcSchemaMethodSetVO> list){

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsysaccessmethodset");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        sqlStrBuff.setLength(0);
        sqlStrBuff.append("insert into zsysaccessmethodset(psequences");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pchange_comments"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",paccess_constant"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pkinds_str"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",powners"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("#{sequences}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{changeComments}" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{accessConstant}" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{kindsStr}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{owners}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        for (OmcSchemaMethodSetVO vo : list){
            vo.setSql(sqlStrBuff.toString());
        }
        schemaDao.batchInsert("SchemaNew.dynamicCreate", list);
    }
    /*************************************************************************************************************/
    /********************************* Dynamic Attribute *********************************************************/
    /*************************************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryDynamicAttribute(List<OmcSchemaDynamicAttrGrpVO> list){

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsysdynamicattrgroup");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        sqlStrBuff.setLength(0);
        sqlStrBuff.append("insert into zsysstorelocation(psequences");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pchange_comments"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pkinds_str"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdescriptions"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pparent"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdisplayed_names"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("#{sequences}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{changeComments}" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{kindsStr}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{descriptions}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{parent}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{displayedNames}" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        for (OmcSchemaDynamicAttrGrpVO vo : list){
            vo.setSql(sqlStrBuff.toString());
        }
        schemaDao.batchInsert("SchemaNew.dynamicCreate", list);
    }
    /*************************************************************************************************************/
    /********************************* Site *********************************************************/
    /*************************************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporarySite(List<OmcSchemaSiteVO> list){

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsyssite");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list", list);
        schemaDao.insert("SchemaNew.createSystemSiteBatch", map);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemSite(OmcSchemaSiteVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psyssite                 (obid"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemSite(OmcSchemaSiteVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete from psyssite"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemSite(OmcSchemaSiteVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psyssite a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags      = #{flags}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier   = #{modifier} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified   = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid      = #{obid}");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemSite(OmcSchemaSiteVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.SYSSTATE_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.SYSSTATE_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psyssite a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }
    @Override
    public List<OmcSchemaSiteVO> getSiteListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaSite.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSiteVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveSite", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaSiteVO> getInActiveSiteListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaSite.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsyssite x where a.pnames = x.pnames)");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaSiteVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveSite", variableParameter);
        return(result);
    }

    @Override
    public OmcSchemaSiteVO getSiteWithObid(String obid){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSite.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(obid);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaSiteVO result = schemaDao.select("SchemaNew.dynamicRetrieveSite", variableParameter);
        return(result);
    }
    
    @Override
    public OmcSchemaSiteVO getSiteWithNames(String names){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaSite.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(names);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaSiteVO result = schemaDao.select("SchemaNew.dynamicRetrieveSite", variableParameter);
        return(result);
    }
    
    @Override
    public List<OmcSchemaRelationVO> getTemporaryUploadSite2LocationRelationList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append                                ("select b.obid  as from_obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.obid  as to_obid"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pfile_path as sorting"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",(select x.obid from psysrelationinfo x where x.pfrom_obid = b.obid and x.pto_obid = c.obid) as obid"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysstorelocation a, psyssite b, psysfilelocation c"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pkinds_str   in(#{funVariable_00001})"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pnames  = b.pnames"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pfile_server = c.pnames"  );
        variableParameter.setFunVariable_00001("Site2Location");
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaRelationVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelation", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaRelationVO> getTemporaryInactiveSite2LocationRelationList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaRelation.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", psyssite b, psysfilelocation c");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pfrom_obid = b.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pto_obid = c.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   not exists(select * from zsysstorelocation x where x.pnames = b.pnames and x.pfile_server = c.pnames and x.pkinds_str in(#{funVariable_00001}))");
        variableParameter.setFunVariable_00001("Site2Location");
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaRelationVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelation", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaRelationVO> getLifeCycleStoreRelationList(String lifeCycle, String store){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaRelation.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pfrom_obid = (select x.obid from psyslifecycle x where x.pnames = #{funVariable_00001})");
        //sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pto_obid   = (select x.obid from psysfilestore x where x.pnames = #{funVariable_00002})");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pschema_kind", "#{funVariable_00003}", "#{funVariable_00003}"));
        variableParameter.setFunVariable_00001(lifeCycle);
        variableParameter.setFunVariable_00002(store);
        variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSREL_KIND_PolicyStore));
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaRelationVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelation", variableParameter);
        return(result);
    }
    
    @Override
    public List<OmcSchemaRelationVO> getTemporaryUploadStore2LocationRelationList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append                                ("select b.obid  as from_obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.obid  as to_obid"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pfile_path as sorting"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",(select x.obid from psysrelationinfo x where x.pfrom_obid = b.obid and x.pto_obid = c.obid) as obid"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysstorelocation a, psysfilestore b, psysfilelocation c"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pkinds_str   in(#{funVariable_00001})"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pnames  = b.pnames"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pfile_server = c.pnames"  );
        variableParameter.setFunVariable_00001("Store2Location");
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaRelationVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelation", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaRelationVO> getTemporaryInactiveStore2LocationRelationList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaRelation.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", psysfilestore b, psysfilelocation c");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pfrom_obid = b.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pto_obid = c.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   not exists(select * from zsysstorelocation x where x.pnames = b.pnames and x.pfile_server = c.pnames and x.pkinds_str in(#{funVariable_00001}))");
        variableParameter.setFunVariable_00001("Store2Location");
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaRelationVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelation", variableParameter);
        return(result);
    }

    /*************************************************************************************************************/
    /********************************* User **********************************************************************/
    /*************************************************************************************************************/
    @Override
    public List<OmcSchemaUserVO> getCommonUserList(CommonUserSearchVO searchVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        
        OmcSchemaUserCommon.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1=1");
//        if(!NullUtil.isNone(searchVO.getNames())){
//            sqlStrBuff.append(OmcFoundationConstant.newline).append("and (a.pnames like #{funVariable_00001} or a.pdescriptions like #{funVariable_00001})");
//            variableParameter.setFunVariable_00001("%" + searchVO.getNames() + "%");
//        }
//        if(!searchVO.getUserInclude()){
//            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pkinds ,#{funVariable_00002}) <> #{funVariable_00002}");
//            variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSUSER_KIND_User));
//        }
//        if(!searchVO.getRoleInclude()){
//            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pkinds ,#{funVariable_00003}) <> #{funVariable_00003}");
//            variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSUSER_KIND_Role));
//        }
//        if(!searchVO.getGroupInclude()){
//            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pkinds ,#{funVariable_00004}) <> #{funVariable_00004}");
//            variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSUSER_KIND_Group));
//        }
//        if(!searchVO.getRoleGroupInclude()){
//            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pkinds ,#{funVariable_00005}) <> #{funVariable_00005}");
//            variableParameter.setFunVariable_00005(String.valueOf(OmcSystemConstants.SYSUSER_KIND_RoleGroup));
//        }
//        if(searchVO.getActiveOnly()){
//            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pflags ,#{funVariable_00006}) = #{funVariable_00006}");
//            variableParameter.setFunVariable_00006(String.valueOf(OmcSystemConstants.SYSUSER_FLAG_Active));
//        }
        sqlStrBuff.append(OmcFoundationConstant.newline).append("order by a.pnames");
        variableParameter.setSql(sqlStrBuff.toString());
        DomUtil.copyPagingEntity(searchVO,variableParameter);
        List<OmcSchemaUserVO> result = schemaDao.selectPagedList("SchemaNew.dynamicRetrieveUser", variableParameter);
        DomUtil.copyPagingEntity(variableParameter,searchVO);
        return(result);
    }
    
    @Override
    public List<OmcSchemaUserVO> getCommonUserForMenuList(List<String> obidList, Boolean isActiveOnly){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("kindGroupMenu", OmcSystemConstants.SYSREL_KIND_GroupMenu);
        map.put("kindRoleMenu", OmcSystemConstants.SYSREL_KIND_RoleMenu);
        map.put("kindUserMenu", OmcSystemConstants.SYSREL_KIND_UserMenu);
        if(isActiveOnly){
            map.put("activeOnly", "Y");
            map.put("relationActiveFlag", OmcSystemConstants.SYSREL_FLAG_Active);
            map.put("userActiveFlag", OmcSystemConstants.SYSUSER_FLAG_Active);
        }
        map.put("obidList", obidList);
        List<OmcSchemaUserVO> result = schemaDao.selectList("SchemaNew.getMenuUser", map);
        return result;
    }
    @Override
    public OmcSchemaUserVO getUserCommonWithObid(String obid){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaUserCommon.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(obid);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaUserVO result = schemaDao.select("SchemaNew.dynamicRetrieveUser", variableParameter);
        return(result);
    }
    @Override
    public OmcSchemaUserVO getUserCommonWithName(String namses){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaUserCommon.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(namses);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaUserVO result = schemaDao.select("SchemaNew.dynamicRetrieveUser", variableParameter);
        return(result);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryUserGroup(List<OmcSchemaUserVO> list){

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsysgrouprole");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        sqlStrBuff.setLength(0);
        sqlStrBuff.append("insert into zsysgrouprole(psequences");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pchange_comments"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pkinds_str"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"             );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdescriptions"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",powners"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{sequences}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{changeComments}"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{kindsStr}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{descriptions}"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{owners}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        for (OmcSchemaUserVO vo : list){
            vo.setSql(sqlStrBuff.toString());
        }
        schemaDao.batchInsert("SchemaNew.dynamicCreate", list);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemUser(OmcSchemaUserVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psysuser                 (obid"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdescriptions"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pkinds"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pparent"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",ppassword"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psite"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",ptime_stamp"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{descriptions}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{kinds}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{parent}"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{password}"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{site}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"       );//초기 Time Stamp는 flags값을 넣는다.
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemUser(OmcSchemaUserVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete from psysuser"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void setTimeStampSystemUser(OmcSchemaUserVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      update psysuser a "        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.ptime_stamp  = #{timeStamp}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames = #{names}");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemUser(OmcSchemaUserVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psysuser a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags        = #{flags}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdescriptions = #{descriptions} ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pkinds        = #{kinds}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pparent       = #{parent}       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.ppassword     = #{password}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.psite         = #{site}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier     = #{modifier}     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid      = #{obid}");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemUser(OmcSchemaUserVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.SYSUSER_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.SYSUSER_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psysuser a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }
    @Override
    public List<OmcSchemaUserVO> getUserListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaUserUser.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaUserVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveUser", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaUserVO> getRoleListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaUserRole.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaUserVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveUser", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaUserVO> getRoleGroupListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaUserRoleGroup.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaUserVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveUser", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaUserVO> getGroupListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaUserGroup.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaUserVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveUser", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaUserVO> getInActiveUserListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaUserUser.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsysgrouprole x where a.pnames = x.pnames and x.pkinds_str in(#{funVariable_00002}))");
        variableParameter.setFunVariable_00002("User");
         variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaUserVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveUser", variableParameter);
        return(result);
    }
    /**
     *
     * @return
     * @see omc.schema.serviceutil.OmcSchemaUtilService#getInActiveRoleListForUpload()
     */
    @Override
    public List<OmcSchemaUserVO> getInActiveRoleListForUpload(){
        // TODO Auto-generated method stub
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaUserRole.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsysgrouprole x where a.pnames = x.pnames and x.pkinds_str in(#{funVariable_00002}))");
        variableParameter.setFunVariable_00002("Role");
         variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaUserVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveUser", variableParameter);
        return(result);
    }
    /**
     *
     * @return
     * @see omc.schema.serviceutil.OmcSchemaUtilService#getInActiveRoleGroupListForUpload()
     */
    @Override
    public List<OmcSchemaUserVO> getInActiveRoleGroupListForUpload(){
        // TODO Auto-generated method stub
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaUserRoleGroup.getCommonSelectSql(sqlStrBuff, variableParameter);

        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsysgrouprole x where a.pnames = x.pnames and x.pkinds_str in(#{funVariable_00002}))");
        variableParameter.setFunVariable_00002("RoleGroup");
         variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaUserVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveUser", variableParameter);
        return(result);
    }
    /**
     *
     * @return
     * @see omc.schema.serviceutil.OmcSchemaUtilService#getInActiveGroupListForUpload()
     */
    @Override
    public List<OmcSchemaUserVO> getInActiveGroupListForUpload(){
        // TODO Auto-generated method stub
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaUserGroup.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsysgrouprole x where a.pnames = x.pnames and x.pkinds_str in(#{funVariable_00002}))");
        variableParameter.setFunVariable_00002("Group");
         variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaUserVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveUser", variableParameter);
        return(result);
    }
    /**
     *
     * @return
     * @see omc.schema.serviceutil.OmcSchemaUtilService#getInActiveGroupListForUpload()
     */
    @Override
    public List<OmcSchemaUserVO> getAllActiveUserList(){
        // TODO Auto-generated method stub
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaUserUser.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00002}","#{funVariable_00002}"));
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSUSER_FLAG_Active));
         variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaUserVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveUser", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaRelationVO> getInactiveUserCommonRelationList(long schemaKinds, long fromObjKinds, long toObjKinds, String kindStr){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaRelation.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", psysuser b, psysuser c");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pfrom_obid = b.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pto_obid = c.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pkinds", "#{funVariable_00001}", "#{funVariable_00001}"));
        variableParameter.setFunVariable_00001(String.valueOf(fromObjKinds));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("c.pkinds", "#{funVariable_00002}", "#{funVariable_00002}"));
        variableParameter.setFunVariable_00002(String.valueOf(toObjKinds));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pschema_kind", "#{funVariable_00003}", "#{funVariable_00003}"));
        variableParameter.setFunVariable_00003(String.valueOf(schemaKinds));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   not exists(select * from zsysgrouprole x where x.pnames = b.pnames and x.pdescriptions = c.pnames and x.pkinds_str in(#{funVariable_00004}))");
        variableParameter.setFunVariable_00004(kindStr);
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaRelationVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelation", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaRelationVO> getUserCommonRelationList(long schemaKinds, String fromNames, String toNames){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaRelation.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", psysuser b, psysuser c");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pfrom_obid = b.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pto_obid   = c.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   b.pnames = #{funVariable_00001}");
        variableParameter.setFunVariable_00001(fromNames);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   c.pnames = #{funVariable_00002}");
        variableParameter.setFunVariable_00002(toNames);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pschema_kind", "#{funVariable_00003}", "#{funVariable_00003}"));
        variableParameter.setFunVariable_00003(String.valueOf(schemaKinds));
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaRelationVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelation", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaRelationVO> getUploadUserCommonRelationList(long schemaKinds, String kindStr, long fromKinds, long toKinds){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append                                ("select b.obid  as from_obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.obid  as to_obid"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",0       as sorting"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",(select x.obid from psysrelationinfo x where x.pfrom_obid = b.obid and x.pto_obid = c.obid " + OmcSchemaUtil.getBitAndStr("x.pschema_kind", "#{funVariable_00001}", "#{funVariable_00001}") +") as obid"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysgrouprole a, psysuser b, psysuser c"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pkinds_str   in(#{funVariable_00002})"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pnames        = b.pnames"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pdescriptions = c.pnames"  );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pkinds", "#{funVariable_00003}", "#{funVariable_00003}"));
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("c.pkinds", "#{funVariable_00004}", "#{funVariable_00004}"));

        variableParameter.setFunVariable_00001(String.valueOf(schemaKinds));
        variableParameter.setFunVariable_00002(kindStr);
        variableParameter.setFunVariable_00003(String.valueOf(fromKinds));
        variableParameter.setFunVariable_00004(String.valueOf(toKinds));
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaRelationVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelation", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaRelationVO> getRelationList(long schemaKinds, String fromObid, String toObid){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        
        OmcSchemaRelation.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pfrom_obid =  #{funVariable_00001}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pto_obid   =  #{funVariable_00002}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pschema_kind", "#{funVariable_00003}", "#{funVariable_00003}"));
        
        variableParameter.setFunVariable_00001(fromObid);
        variableParameter.setFunVariable_00002(toObid);
        variableParameter.setFunVariable_00003(String.valueOf(schemaKinds));
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaRelationVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveRelation", variableParameter);
        return(result);
    }

    /*************************************************************************************************************/
    /********************************* Tab **********************************************************************/
    /*************************************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemTab(OmcSchemaTabLayoutVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psystab(                   obid                ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",plabels             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",plabels_kr          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",plink_herf          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",plink_alt           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pheights            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pusages             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psequences          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pchange_comments    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",powners             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psub_object_list    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{labels}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{labelsKr}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{linkHerf}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{linkAlt}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{heights}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{usages}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{sequences}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{changeComments}   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{owners}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{subObjectList}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemTab(OmcSchemaTabLayoutVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psystab a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags            = #{flags}            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.plabels           = #{labels}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.plabels_kr        = #{labelsKr}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.plink_herf        = #{linkHerf}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.plink_alt         = #{linkAlt}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pheights          = #{heights}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pusages           = #{usages}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.psequences        = #{sequences}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pchange_comments  = #{changeComments}   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.powners           = #{owners}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.psub_object_list  = #{subObjectList}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier         = #{modifier}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified   = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid            = #{obid}");

        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }
    @Override
    public List<OmcSchemaTabLayoutVO> getTabListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaTab.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaTabLayoutVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveTabNLayout", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaTabLayoutVO> getInActiveTabListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaTab.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsyslayouttab x where a.pnames = x.pnames and x.pkinds_str = 'Tab')");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaTabLayoutVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveTabNLayout", variableParameter);
        return(result);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactivateSystemTab(OmcSchemaTabLayoutVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.SYSTAB_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.SYSTAB_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psystab a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemTab(OmcSchemaTabLayoutVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete from psystab"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    /*************************************************************************************************************/
    /********************************* Layout **********************************************************************/
    /*************************************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSystemLayout(OmcSchemaTabLayoutVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psyslayout(                obid                ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",plabels             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",plabels_kr          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",plink_herf          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",plink_alt           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pheights            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pusages             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psequences          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pchange_comments    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",powners             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psub_object_list    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{labels}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{labelsKr}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{linkHerf}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{linkAlt}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{heights}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{usages}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{sequences}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{changeComments}   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{owners}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{subObjectList}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.insert("SchemaNew.dynamicCreate", parmVO);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifySystemLayout(OmcSchemaTabLayoutVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psyslayout a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags            = #{flags}            ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.plabels           = #{labels}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.plabels_kr        = #{labelsKr}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.plink_herf        = #{linkHerf}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.plink_alt         = #{linkAlt}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pheights          = #{heights}          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pusages           = #{usages}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.psequences        = #{sequences}        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pchange_comments  = #{changeComments}   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.powners           = #{owners}           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.psub_object_list  = #{subObjectList}    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier         = #{modifier}         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified   = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid            = #{obid}");

        parmVO.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", parmVO);
    }
    @Override
    public List<OmcSchemaTabLayoutVO> getLayoutListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaLayout.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaTabLayoutVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveTabNLayout", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaTabLayoutVO> getInActiveLayoutListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaLayout.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsyslayouttab x where a.pnames = x.pnames and x.pkinds_str = 'Layout')");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaTabLayoutVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveTabNLayout", variableParameter);
        return(result);
    }
    @Override
    public void inactivateSystemLayout(OmcSchemaTabLayoutVO parmVO){
        if(Bit.isInclude(parmVO.getFlags(), OmcSystemConstants.SYSLAYOUT_FLAG_Active)){
            parmVO.setFlags(Bit.xor(parmVO.getFlags(), OmcSystemConstants.SYSLAYOUT_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      update psyslayout a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags     = #{funVariable_00002},");
            variableParameter.setFunVariable_00002(String.valueOf(parmVO.getFlags()));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("    a.pmodified                     = ").append(OmcDBMSConstants.DBMS_SYSTEM_DATE);
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}");
            variableParameter.setFunVariable_00001(parmVO.getObid());

            variableParameter.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", variableParameter);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSystemLayout(OmcSchemaTabLayoutVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append("                                      delete from psyslayout"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
    }
    @Override
    public List<OmcSchemaMenuVO> getMenuCommandEtcList(CommonMenuSearchVO searchVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        
        OmcSchemaMenu.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1=1");
        if(!NullUtil.isNone(searchVO.getNames())){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and (a.pnames like #{funVariable_00001} or a.pdescriptions like #{funVariable_00001})");
            variableParameter.setFunVariable_00001("%" + searchVO.getNames() + "%");
        }
        if(!searchVO.getMenuInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00002}) <> #{funVariable_00002}");
            variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Menu));
        }
        if(!searchVO.getCommandInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00003}) <> #{funVariable_00003}");
            variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Command));
        }
        if(!searchVO.getToolbarInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00004}) <> #{funVariable_00004}");
            variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Toolbar));
        }
        if(!searchVO.getClassMenuInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00005}) <> #{funVariable_00005}");
            variableParameter.setFunVariable_00005(String.valueOf(OmcSystemConstants.SYSMNU_KIND_ClassMenu));
        }
        if(!searchVO.getComboInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00006}) <> #{funVariable_00006}");
            variableParameter.setFunVariable_00006(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Combo));
        }
        if(!searchVO.getCalendarInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00007}) <> #{funVariable_00007}");
            variableParameter.setFunVariable_00007(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Calendar));
        }
        if(!searchVO.getTextInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00008}) <> #{funVariable_00008}");
            variableParameter.setFunVariable_00008(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Text));
        }
        if(!searchVO.getStructureMenuInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00009}) <> #{funVariable_00009}");
            variableParameter.setFunVariable_00009(String.valueOf(OmcSystemConstants.SYSMNU_KIND_StructureMenu));
        }    
        if(!searchVO.getPopupMenuMenuInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00010}) <> #{funVariable_00010}");
            variableParameter.setFunVariable_00010(String.valueOf(OmcSystemConstants.SYSMNU_KIND_PopupMenu));
        }    
        if(!searchVO.getClassPopupMenuInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00011}) <> #{funVariable_00011}");
            variableParameter.setFunVariable_00011(String.valueOf(OmcSystemConstants.SYSMNU_KIND_ClassPopupMenu));
        }         
        if(!searchVO.getCheckBoxGroupInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00012}) <> #{funVariable_00012}");
            variableParameter.setFunVariable_00012(String.valueOf(OmcSystemConstants.SYSMNU_KIND_CheckBoxGroup));
        }         
        if(!searchVO.getCheckBoxInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00013}) <> #{funVariable_00013}");
            variableParameter.setFunVariable_00013(String.valueOf(OmcSystemConstants.SYSMNU_KIND_CheckBox));
        }         
        if(!searchVO.getRadioGroupInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00014}) <> #{funVariable_00014}");
            variableParameter.setFunVariable_00014(String.valueOf(OmcSystemConstants.SYSMNU_KIND_RadioGroup));
        }     
        if(!searchVO.getRadioInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00015}) <> #{funVariable_00015}");
            variableParameter.setFunVariable_00015(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Radio));
        }     
        if(!searchVO.getFilterGroupInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00016}) <> #{funVariable_00016}");
            variableParameter.setFunVariable_00016(String.valueOf(OmcSystemConstants.SYSMNU_KIND_FilterGroup));
        }     
        if(!searchVO.getFilterInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00017}) <> #{funVariable_00017}");
            variableParameter.setFunVariable_00017(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Filter));
        }     
        if(!searchVO.getFabelInclude()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pobject_kind , #{funVariable_00018}) <> #{funVariable_00018}");
            variableParameter.setFunVariable_00018(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Label));
        }     
        if(searchVO.getActiveOnly()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pflags , #{funVariable_00019}) = #{funVariable_00019}");
            variableParameter.setFunVariable_00019(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_Active));
        }
        if(searchVO.getIsAccessControlObjectOnly()){
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and omcBitAnd(a.pflags , #{funVariable_00020}) = #{funVariable_00020}");
            variableParameter.setFunVariable_00020(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_IsAccessControlObject));
        }
        variableParameter.setSql(sqlStrBuff.toString());
        DomUtil.copyPagingEntity(searchVO,variableParameter);
        List<OmcSchemaMenuVO> result = schemaDao.selectPagedList("SchemaNew.dynamicRetrieveMenu", variableParameter);
        DomUtil.copyPagingEntity(variableParameter,searchVO);
        return(result);
    }
    
    @Override
    public List<OmcSchemaMenuVO> getCommandList(OmcSchemaTabLayoutVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaMenu.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1= 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid = b.pto_obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   b.pfrom_obid = c.obid"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        OmcSchemaUtil.getBitAndStr("and   a.pobject_kind","#{funVariable_00002}","#{funVariable_00002}");
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Command));
        OmcSchemaUtil.getBitAndStr("and   b.pschema_kind","#{funVariable_00003}","#{funVariable_00003}");
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSREL_KIND_TabHasCommand));
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaMenuVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveMenu", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaTabLayoutVO> getTabList(OmcSchemaTabLayoutVO parmVO){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaLayout.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1= 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid = b.pto_obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   b.pfrom_obid = c.obid"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.obid = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(parmVO.getObid());
        OmcSchemaUtil.getBitAndStr("and   b.pschema_kind","#{funVariable_00002}","#{funVariable_00002}");
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSREL_KIND_LayoutHasTab));
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaTabLayoutVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveTabNLayout", variableParameter);
        return(result);
    }
    @Override
    public OmcSchemaMenuVO getSystemMenuWithNames(String names){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaMenu.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(names);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaMenuVO result = schemaDao.select("SchemaNew.dynamicRetrieveMenu", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaMenuVO> getCommandListWithSeperatedList(List<String> seperatedList){
        List<OmcSchemaMenuVO> result = new ArrayList<OmcSchemaMenuVO>();
        OmcSchemaMenuVO rsltVO = new OmcSchemaMenuVO();
        for(String str : seperatedList){
            rsltVO = this.getSystemMenuWithNames(str);
            if (rsltVO != null){
                result.add(rsltVO);
            }
        }
        return(result);
    }
    @Override
    public OmcSchemaTabLayoutVO getSystemTabWithNames(String names){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        OmcSchemaTab.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames  = #{funVariable_00001}"        );
        variableParameter.setFunVariable_00001(names);
        variableParameter.setSql(sqlStrBuff.toString());
        OmcSchemaTabLayoutVO result = schemaDao.select("SchemaNew.dynamicRetrieveTabNLayout", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaTabLayoutVO> getTabListWithSeperatedList(List<String> seperatedList){
        List<OmcSchemaTabLayoutVO> result = new ArrayList<OmcSchemaTabLayoutVO>();
        OmcSchemaTabLayoutVO rsltVO = new OmcSchemaTabLayoutVO();
        int seq = 1;
        for(String str : seperatedList){
            rsltVO = this.getSystemTabWithNames(str);
            
            if (rsltVO != null){
                rsltVO.setSequences(seq);
                result.add(rsltVO);
                seq++;
            }
        }
        return(result);
    }
    /*************************************************************************************************************/
    /********************************* Attribute *********************************************************/
    /*************************************************************************************************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadTemporaryAttr(List<OmcSchemaAttributeVO> list){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        sqlStrBuff.append("delete from zsysattribute");
        variableParameter.setSql(sqlStrBuff.toString());
        schemaDao.delete("SchemaNew.dynamicDelete", variableParameter);
        
        uploadCommonBatch(list,"SchemaNew.createTemporaryAttrDefinitionBatch");
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createSchemaAttribute(OmcSchemaAttributeVO vo){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("insert into psysattribute(             obid"                );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pflags"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pnames"              );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdisplayed_names"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pdisplayed_names_kr" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodule_name"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",premarks"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",psequences"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pchange_comments"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreator"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pcreated"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodifier"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",pmodified"           );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")values(#{obid}"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{flags}"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{names}"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{displayedNames}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{displayedNamesKr}" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{moduleName}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{remarks}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{sequences}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{changeComments}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{creator}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",#{modifier}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("," + OmcDBMSConstants.DBMS_SYSTEM_DATE);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(")");
        vo.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicCreate", vo);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inactiviateSchemaAttribute(OmcSchemaAttributeVO vo){
        if(Bit.isInclude(vo.getFlags(), OmcSystemConstants.SYSATTR_FLAG_Active)){
            vo.setFlags(Bit.xor(vo.getFlags(),OmcSystemConstants.SYSATTR_FLAG_Active));
            StringBuffer sqlStrBuff = new StringBuffer();
            sqlStrBuff.append("update psysattribute a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags = #{flags}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid = #{obid}");
            vo.setSql(sqlStrBuff.toString());
            schemaDao.update("SchemaNew.dynamicModify", vo);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSchemaAttribute(OmcSchemaAttributeVO vo){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("delete from psysattribute");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where obid = #{obid}");
        vo.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicDelete", vo);
    }
    @Override
    public void modifySchemaAttribute(OmcSchemaAttributeVO vo){
        StringBuffer sqlStrBuff = new StringBuffer();
        sqlStrBuff.append("update psysattribute a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("set a.pflags               = #{flags}"            );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdisplayed_names     = #{displayedNames}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pdisplayed_names_kr  = #{displayedNamesKr}" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodule_name         = #{moduleName}"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.premarks             = #{remarks}"          );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.psequences           = #{sequences}"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pchange_comments     = #{changeComments}"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodifier            = #{modifier}"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("   ,a.pmodified            = " + OmcDBMSConstants.DBMS_SYSTEM_DATE );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid  = #{obid} ");
        vo.setSql(sqlStrBuff.toString());
        schemaDao.update("SchemaNew.dynamicModify", vo);
    }

    @Override
    public List<OmcSchemaAttributeVO> getAttributeListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaAttribute.getCommonSelectTemporarySql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaAttributeVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveAttribute", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaAttributeVO> getInActiveAttributeListForUpload(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaAttribute.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and not exists(select * from zsysattribute x where a.pnames = x.pnames)");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaAttributeVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveAttribute", variableParameter);
        return(result);
    }
    @Override
    public List<OmcSchemaAttributeVO> getAllDefinedAttributeList(){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();

        OmcSchemaAttribute.getCommonSelectSql(sqlStrBuff, variableParameter);
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where 1 = 1");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags","#{funVariable_00001}","#{funVariable_00001}"));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("order by a.pmodule_name");
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSATTR_FLAG_Active));
        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcSchemaAttributeVO> result = schemaDao.selectList("SchemaNew.dynamicRetrieveAttribute", variableParameter);
        return(result);
    }

    public void txnBatchInsertTest() {
        Map<String,Object> map = new HashMap<String,Object>();

        Date today = new Date();
        List <TestVo> list = new ArrayList<TestVo>();
        for (int inx=0; inx<10; inx++) {
            TestVo vo = new TestVo("id" + inx, today + "value" + inx);
            list.add(vo);
        }
        map.put("list", list);

        map.put("id", "insertTest Id");
        map.put("value", "insertTest Value");

        int result = schemaDao.insert("SchemaNew.insertTest", map);

        result = schemaDao.insert("SchemaNew.forEachTest", map);
    }
    
}


class TestVo {
    private String id;
    private String value;

    /**
     * @param id
     * @param value
     */
    public TestVo(String id, String value) {
        this.id = id;
        this.value = value;
    }

    /**
     *
     *
     * @return the id
     */
    public String getId(){
        return id;
    }

    /**
     *
     *
     * @param id the id to set
     */
    public void setId(String id){
        this.id = id;
    }

    /**
     *
     *
     * @return the value
     */
    public String getValue(){
        return value;
    }

    /**
     *
     *
     * @param value the value to set
     */
    public void setValue(String value){
        this.value = value;
    }

}