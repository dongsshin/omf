package com.rap.omc.foundation.layout.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.foundation.layout.LayoutInfoService;
import com.rap.omc.foundation.layout.model.LayoutInfo;
import com.rap.omc.foundation.layout.model.TabInfo;
import com.rap.omc.foundation.menu.service.MenuInfoService;
import com.rap.omc.schema.util.OmcSchemaUtil;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.foundation.MenuServiceUtil;
@Service("layoutInfoService")
public class LayoutInfoServiceImpl implements LayoutInfoService{
    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;
    
    @Resource(name = "menuInfoService")
    private MenuInfoService menuInfoService;
    
    @Cacheable(value = "layoutInfoCache", key = "#layoutName")
    public LayoutInfo getLayoutInfo(String layoutName){
        StringBuffer sqlStrBuff = new StringBuffer(); 
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter(); 
        sqlStrBuff.append                                 ("select a.obid             as obid"        );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pflags           as flags"       );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pnames           as layout_name" );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.plabels          as labels"      );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.plabels_kr       as labels_kr"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.plink_herf       as link_herf"   );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.plink_alt        as link_alt"    );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.pheights         as heights"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.psub_object_list as sub_object_list_str"     );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psyslayout a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pnames = #{funVariable_00001}");
        sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
        variableParameter.setFunVariable_00001(layoutName);
        variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSLAYOUT_FLAG_Active));
        variableParameter.setSql(sqlStrBuff.toString());
        LayoutInfo keyInfo = schemaDao.select("Common.getDynamicLayoutInfo", variableParameter);
        if (!NullUtil.isNull(keyInfo)) {
            variableParameter = new OmcSQLVariableParameter(); 
            sqlStrBuff.setLength(0);
            sqlStrBuff.append                                 ("select b.obid             as obid"        );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.pflags           as flags"       );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.pnames           as tab_name" );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.plabels          as labels"      );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.plabels_kr       as labels_kr"   );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.plink_herf       as link_herf"   );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.plink_alt        as link_alt"    );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.pheights         as heights"     );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", b.psub_object_list as sub_object_list_str"     );
            sqlStrBuff.append(OmcFoundationConstant.newline).append(", a.psorting         as display_sequence"     );
            sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysrelationinfo a, psystab b" );
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pfrom_obid = #{funVariable_00001}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pto_obid   = b.obid");
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pflags", "#{funVariable_00002}", "#{funVariable_00002}"));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("a.pschema_kind", "#{funVariable_00003}", "#{funVariable_00003}"));
            sqlStrBuff.append(OmcFoundationConstant.newline).append(OmcSchemaUtil.getBitAndStr("b.pflags", "#{funVariable_00004}", "#{funVariable_00004}"));
            sqlStrBuff.append(OmcFoundationConstant.newline).append("order by a.psorting");
            variableParameter.setFunVariable_00001(keyInfo.getObid());
            variableParameter.setFunVariable_00002(String.valueOf(OmcSystemConstants.SYSREL_FLAG_Active));
            variableParameter.setFunVariable_00003(String.valueOf(OmcSystemConstants.SYSREL_KIND_LayoutHasTab));
            variableParameter.setFunVariable_00004(String.valueOf(OmcSystemConstants.SYSTAB_FLAG_Active));
            
            variableParameter.setSql(sqlStrBuff.toString());
            List<TabInfo> tabInfoList = schemaDao.selectList("Common.getDynamicTabInfo", variableParameter);
            if (!NullUtil.isNone(tabInfoList)){
                for(TabInfo tabInfo : tabInfoList){
                    tabInfo.setSubObjectList(MenuServiceUtil.getMenuItemInfoList(tabInfo.getSubObjectListStr()));
                }
                keyInfo.setSubObjectList(tabInfoList);
            }
        }
        return keyInfo;
    }
}
