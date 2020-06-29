/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : MemuInfoServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 24.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.foundation.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.foundation.menu.model.MenuItemInfo;
import com.rap.omc.foundation.menu.model.MenuSubMenuInfo;
import com.rap.omc.foundation.menu.service.MenuInfoSubService;
import com.rap.omc.schema.util.OmcSchemaUtil;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : MemuInfoServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@Service("menuInfoSubService")
public class MenuInfoSubServiceImpl implements MenuInfoSubService {
    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;

    @Cacheable(value = "menuInfoCache", key = "#menuItem")
    public MenuItemInfo getMenuItemInfo(String menuItem){
        StringBuffer sqlStrBuff = new StringBuffer(); 
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter(); 
        
        sqlStrBuff.append(                                "select a.pflags               mnu_flags");
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pobject_kind         mnu_kind");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.obid                 obid");
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",case when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00001}", "#{funVariable_00001}",false)).append(" then 'Menu'");
        variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Menu));
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00002}", "#{funVariable_00002}",false)).append(" then 'Class Popup Menu'");
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSMNU_KIND_ClassPopupMenu));
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00003}", "#{funVariable_00003}",false)).append(" then 'Popup Menu'");
        variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSMNU_KIND_PopupMenu));
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00004}", "#{funVariable_00004}",false)).append(" then 'Command'");
        variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Command));
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00005}", "#{funVariable_00005}",false)).append(" then 'Toolbar'");
        variableParameter.setFunVariable_00005(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Toolbar));
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00006}", "#{funVariable_00006}",false)).append(" then 'Class Menu'");
        variableParameter.setFunVariable_00006(String.valueOf(OmcSystemConstants.SYSMNU_KIND_ClassMenu));
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00007}", "#{funVariable_00007}",false)).append(" then 'Combo'");
        variableParameter.setFunVariable_00007(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Combo));
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00008}", "#{funVariable_00008}",false)).append(" then 'Calendar'");
        variableParameter.setFunVariable_00008(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Calendar));
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00009}", "#{funVariable_00009}",false)).append(" then 'Text'");
        variableParameter.setFunVariable_00009(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Text));
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00010}", "#{funVariable_00010}",false)).append(" then 'Structure Menu'");
        variableParameter.setFunVariable_00010(String.valueOf(OmcSystemConstants.SYSMNU_KIND_StructureMenu));

        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00011}", "#{funVariable_00011}",false)).append(" then 'Checkbox Group'");
        variableParameter.setFunVariable_00011(String.valueOf(OmcSystemConstants.SYSMNU_KIND_CheckBoxGroup));

        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00012}", "#{funVariable_00012}",false)).append(" then 'Checkbox'");
        variableParameter.setFunVariable_00012(String.valueOf(OmcSystemConstants.SYSMNU_KIND_CheckBox));

        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00013}", "#{funVariable_00013}",false)).append(" then 'Radio Group'");
        variableParameter.setFunVariable_00013(String.valueOf(OmcSystemConstants.SYSMNU_KIND_RadioGroup));

        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00014}", "#{funVariable_00014}",false)).append(" then 'Radio'");
        variableParameter.setFunVariable_00014(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Radio));

        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00015}", "#{funVariable_00015}",false)).append(" then 'Filter Group'");
        variableParameter.setFunVariable_00015(String.valueOf(OmcSystemConstants.SYSMNU_KIND_FilterGroup));

        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00016}", "#{funVariable_00016}",false)).append(" then 'Filter'");
        variableParameter.setFunVariable_00016(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Filter));
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pobject_kind", "#{funVariable_00017}", "#{funVariable_00017}",false)).append(" then 'Label'");
        variableParameter.setFunVariable_00017(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Label));
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append("else '' ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("end                     as  mnu_kind_name");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pnames               as  mnu_name");
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",case when ").append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00018}", "#{funVariable_00018}",false)).append(" then 'SCFunction'");
        variableParameter.setFunVariable_00018(String.valueOf(OmcSystemConstants.SYSREL_FLAG_SCFunction));
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00019}", "#{funVariable_00019}",false)).append(" then 'JSFunction'");
        variableParameter.setFunVariable_00019(String.valueOf(OmcSystemConstants.SYSREL_FLAG_JSFunction));
        
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00020}", "#{funVariable_00020}",false)).append(" then 'SCWindow'");
        variableParameter.setFunVariable_00020(String.valueOf(OmcSystemConstants.SYSREL_FLAG_SCWindow));

        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00021}", "#{funVariable_00021}",false)).append(" then 'Contents'");
        variableParameter.setFunVariable_00021(String.valueOf(OmcSystemConstants.SYSREL_FLAG_ContentsRepl));

        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00022}", "#{funVariable_00022}",false)).append(" then 'PLSQLFunction'");
        variableParameter.setFunVariable_00022(String.valueOf(OmcSystemConstants.SYSREL_FLAG_PLSQLFunction));

        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00023}", "#{funVariable_00023}",false)).append(" then 'JavaMethod'");
        variableParameter.setFunVariable_00023(String.valueOf(OmcSystemConstants.SYSREL_FLAG_CheckGrpMethod));

        sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00024}", "#{funVariable_00024}",false)).append(" then 'URLCall'");
        variableParameter.setFunVariable_00024(String.valueOf(OmcSystemConstants.SYSREL_FLAG_CheckGrpFunCall));
        sqlStrBuff.append(OmcFoundationConstant.newline).append("else '' ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("end                     as  mnu_calling_type");

        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pdescriptions        as mnu_descriptions       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.plabels              as mnu_label              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.plabels_kr           as mnu_label_kr           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.plink_alt            as mnu_alt                ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pimages              as mnu_image              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.paccess_expression   as mnu_access             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pmodule_name         as mnu_module             ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pstring01            as mnu_pstring01          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pstring02            as mnu_pstring02          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pstring03            as mnu_pstring03          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pstring04            as mnu_pstring04          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pstring05            as mnu_pstring05          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.plink_herf           as mnu_href               ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.paccess_expression   as mnu_access_expression  ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.paccess_expression   as mnu_resolved_expression");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysmenu a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames = #{funVariable_00025}");
        variableParameter.setFunVariable_00025(menuItem);
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00026}", "#{funVariable_00026}"));
        variableParameter.setFunVariable_00026(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_Active));
        variableParameter.setSql(sqlStrBuff.toString());
        MenuItemInfo menuItemInfo = schemaDao.select("MenuInfo.getDynamicMenuItemInfo", variableParameter);
        if(!NullUtil.isNull(menuItemInfo)){
            String mnuTarget         = getMenuPropertyByObid(menuItemInfo.getObid(),"Target Location");
            String windowWidth       = getMenuPropertyByObid(menuItemInfo.getObid(),"Window Width");
            String windowHeight      = getMenuPropertyByObid(menuItemInfo.getObid(),"Window Height");
            String controlProperties = getMenuPropertyByObid(menuItemInfo.getObid(),"controlProperties");
            menuItemInfo.setMnuTarget(mnuTarget);
            menuItemInfo.setWindowHeight(windowHeight);
            menuItemInfo.setWindowWidth(windowWidth);
            menuItemInfo.setControlProperties(controlProperties);
        }
        if (!NullUtil.isNull(menuItemInfo)) {
            variableParameter = new OmcSQLVariableParameter(); 
            sqlStrBuff.setLength(0);
            sqlStrBuff.append(                                "select c.pflags               mnu_flags");
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",a.pobject_kind         mnu_kind");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.obid                 obid");
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",case when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00001}", "#{funVariable_00001}",false)).append(" then 'Menu'");
            variableParameter.setFunVariable_00001(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Menu));
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00002}", "#{funVariable_00002}",false)).append(" then 'Class Popup Menu'");
            variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSMNU_KIND_ClassPopupMenu));
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00003}", "#{funVariable_00003}",false)).append(" then 'Popup Menu'");
            variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSMNU_KIND_PopupMenu));
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00004}", "#{funVariable_00004}",false)).append(" then 'Command'");
            variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Command));
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00005}", "#{funVariable_00005}",false)).append(" then 'Toolbar'");
            variableParameter.setFunVariable_00005(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Toolbar));
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00006}", "#{funVariable_00006}",false)).append(" then 'Class Menu'");
            variableParameter.setFunVariable_00006(String.valueOf(OmcSystemConstants.SYSMNU_KIND_ClassMenu));
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00007}", "#{funVariable_00007}",false)).append(" then 'Combo'");
            variableParameter.setFunVariable_00007(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Combo));
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00008}", "#{funVariable_00008}",false)).append(" then 'Calendar'");
            variableParameter.setFunVariable_00008(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Calendar));
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00009}", "#{funVariable_00009}",false)).append(" then 'Text'");
            variableParameter.setFunVariable_00009(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Text));
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00010}", "#{funVariable_00010}",false)).append(" then 'Structure Menu'");
            variableParameter.setFunVariable_00010(String.valueOf(OmcSystemConstants.SYSMNU_KIND_StructureMenu));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00011}", "#{funVariable_00011}",false)).append(" then 'Checkbox Group'");
            variableParameter.setFunVariable_00011(String.valueOf(OmcSystemConstants.SYSMNU_KIND_CheckBoxGroup));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00012}", "#{funVariable_00012}",false)).append(" then 'Checkbox'");
            variableParameter.setFunVariable_00012(String.valueOf(OmcSystemConstants.SYSMNU_KIND_CheckBox));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00013}", "#{funVariable_00013}",false)).append(" then 'Radio Group'");
            variableParameter.setFunVariable_00013(String.valueOf(OmcSystemConstants.SYSMNU_KIND_RadioGroup));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00014}", "#{funVariable_00014}",false)).append(" then 'Radio'");
            variableParameter.setFunVariable_00014(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Radio));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00015}", "#{funVariable_00015}",false)).append(" then 'Filter Group'");
            variableParameter.setFunVariable_00015(String.valueOf(OmcSystemConstants.SYSMNU_KIND_FilterGroup));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00016}", "#{funVariable_00016}",false)).append(" then 'Filter'");
            variableParameter.setFunVariable_00016(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Filter));
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("c.pobject_kind", "#{funVariable_00017}", "#{funVariable_00017}",false)).append(" then 'Label'");
            variableParameter.setFunVariable_00017(String.valueOf(OmcSystemConstants.SYSMNU_KIND_Label));
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append("else '' ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("end    as  mnu_kind_name");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pnames               mnu_name");
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",case when ").append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00018}", "#{funVariable_00018}",false)).append(" then 'SCFunction'");
            variableParameter.setFunVariable_00018(String.valueOf(OmcSystemConstants.SYSREL_FLAG_SCFunction));
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00019}", "#{funVariable_00019}",false)).append(" then 'JSFunction'");
            variableParameter.setFunVariable_00019(String.valueOf(OmcSystemConstants.SYSREL_FLAG_JSFunction));
            
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00020}", "#{funVariable_00020}",false)).append(" then 'SCWindow'");
            variableParameter.setFunVariable_00020(String.valueOf(OmcSystemConstants.SYSREL_FLAG_SCWindow));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00021}", "#{funVariable_00021}",false)).append(" then 'Contents'");
            variableParameter.setFunVariable_00021(String.valueOf(OmcSystemConstants.SYSREL_FLAG_ContentsRepl));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00022}", "#{funVariable_00022}",false)).append(" then 'PLSQLFunction'");
            variableParameter.setFunVariable_00022(String.valueOf(OmcSystemConstants.SYSREL_FLAG_PLSQLFunction));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00023}", "#{funVariable_00023}",false)).append(" then 'JavaMethod'");
            variableParameter.setFunVariable_00023(String.valueOf(OmcSystemConstants.SYSREL_FLAG_CheckGrpMethod));

            sqlStrBuff.append(OmcFoundationConstant.newline).append("      when ").append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00024}", "#{funVariable_00024}",false)).append(" then 'URLCall'");
            variableParameter.setFunVariable_00024(String.valueOf(OmcSystemConstants.SYSREL_FLAG_CheckGrpFunCall));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("else '' ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("end    as  mnu_calling_type");

            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pdescriptions        as mnu_descriptions       ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.plabels              as mnu_label              ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.plabels_kr           as mnu_label_kr           ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.plink_alt            as mnu_alt                ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pimages              as mnu_image              ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.paccess_expression   as mnu_access             ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pmodule_name         as mnu_module             ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pstring01            as mnu_pstring01          ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pstring02            as mnu_pstring02          ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pstring03            as mnu_pstring03          ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pstring04            as mnu_pstring04          ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.pstring05            as mnu_pstring05          ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",b.pflags               as tree_flags             ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",b.pattribute01         as tree_attribute01       ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",b.pattribute02         as tree_attribute02       ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.plink_herf           as mnu_href               ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.paccess_expression   as mnu_access_expression  ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(",c.paccess_expression   as mnu_resolved_expression");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysmenu a, psysrelationinfo b, psysmenu c");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.obid         = b.pfrom_obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   b.pto_obid     = c.obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pnames       = #{funVariable_00025}");
            variableParameter.setFunVariable_00025(menuItem);
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00026}", "#{funVariable_00026}"));
            variableParameter.setFunVariable_00026(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_Active));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("c.pflags", "#{funVariable_00027}", "#{funVariable_00027}"));
            variableParameter.setFunVariable_00027(String.valueOf(OmcSystemConstants.SYSMNU_FLAG_Active));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00028}", "#{funVariable_00028}"));
            variableParameter.setFunVariable_00028(String.valueOf(OmcSystemConstants.SYSREL_FLAG_Active));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pschema_kind", "#{funVariable_00029}", "#{funVariable_00029}"));
            variableParameter.setFunVariable_00029(String.valueOf(OmcSystemConstants.SYSREL_KIND_SubMnuCmd));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("order by b.psorting");
            variableParameter.setSql(sqlStrBuff.toString());
            List<MenuSubMenuInfo> subMenuList = schemaDao.selectList("MenuInfo.getDynamicSubMenuInfo", variableParameter);
            if(!NullUtil.isNone(subMenuList)){
                for(int i = 0; i < subMenuList.size(); i++){
                    String mnuTarget         = getMenuPropertyByObid(subMenuList.get(i).getObid(),"Target Location");
                    String windowWidth       = getMenuPropertyByObid(subMenuList.get(i).getObid(),"Window Width");
                    String windowHeight      = getMenuPropertyByObid(subMenuList.get(i).getObid(),"Window Height");
                    String controlProperties = getMenuPropertyByObid(subMenuList.get(i).getObid(),"controlProperties");
                    subMenuList.get(i).setMnuTarget(mnuTarget);
                    subMenuList.get(i).setWindowHeight(windowHeight);
                    subMenuList.get(i).setWindowWidth(windowWidth);
                    subMenuList.get(i).setControlProperties(controlProperties);
                }                
            }
            menuItemInfo.setChildItemList(subMenuList);
        }
        return menuItemInfo;
    }
    private String getMenuProperty(String menuName, String propertyName){
        return "1";
    }
    private String getMenuPropertyByObid(String obid, String propertyName){
        return "1";
    }
}
