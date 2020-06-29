/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaUserCommon.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 5.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.util.NullUtil;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.object.model.OmcSchemaFileServerVO;
import com.rap.omc.schema.object.model.OmcSchemaMenuVO;
import com.rap.omc.schema.object.model.OmcSchemaRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaRoleGroupVO;
import com.rap.omc.schema.object.model.OmcSchemaSiteVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.object.model.OmcSchemaSysTimeZoneVO;
import com.rap.omc.schema.object.model.OmcSchemaUserVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSchemaUtil;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaUserCommon
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings({"rawtypes","unused"})
public abstract class OmcSchemaUserCommon extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaUserCommon.class);
    public OmcSchemaUserCommon(String obid) {
        super(OmcSchemaServiceUtils.getUserCommonWithObid(obid));
    }
    @Override
    public OmcSchemaUserVO getVo(){
        return (OmcSchemaUserVO)vo;
    }
    /**
     * @param vo
     */
    public OmcSchemaUserCommon(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }


    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    @Override
    protected void create(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.createSystemUser(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate(java.util.Map)
     */
    @Override
    protected void inActiviate(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.inactivateSystemUser(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete(java.util.Map)
     */
    @Override
    protected void delete(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.deleteSystemUser(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify(java.util.Map)
     */
    @Override
    protected void modify(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.modifySystemUser(this.getVo());
    }
    
    /**
     * 
     * @param Names
     * @return
     * @see omc.schema.object.dom.OmcSchemaSysRoot#getObjectInfoByName(java.lang.String)
     */
    @Override
    protected OmcSchemaUserVO getObjectInfoByName(String names){
        return(OmcSchemaServiceUtils.getUserCommonWithName(names));
    }

    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.pnames           as names           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pdescriptions    as descriptions    ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pchange_comments as change_comments ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.psequences       as sequences       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.powners          as owners          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pkinds_str       as kinds_str       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,(select x.obid from psysuser x where a.pnames  = x.pnames)  as obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsysgrouprole a");
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.obid            as obid         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pflags          as flags        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pnames          as names        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pdescriptions   as descriptions ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pkinds          as kinds        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pparent         as parent       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.ppassword       as password     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.psite           as site         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pdepartment_code           as department_code         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pdepartment_desc           as department_desc         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pdepartment_desc_kor       as department_desc_kor     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pemail_id                  as email_id                ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.ptime_stamp                as time_stamp              ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreator        as creator      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreated        as created      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodifier       as modifier     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodified       as modified     ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysuser a ");
    }
    public static void uploadTemporaryList(List<OmcSchemaRoleGroupVO> list){
        OmcSchemaServiceUtils.uploadTemporaryRoleNGroup(list);
    }
    protected void validateForInActiviate(Map map){
        super.validateForInActiviate(map);
    }
    @Override
    protected void preProcessForInActiviate(Map map){
        super.preProcessForInActiviate(map);
    }
    @Override
    protected void validateForDelete(Map map){
        super.validateForDelete(map);
    }
    @Override
    protected void preProcessForCreate(Map map){
        super.preProcessForCreate(map);
        this.processNullData();
        this.setKinds();
    }
    private void processNullData(){
        OmcSchemaUserVO thisVo = this.getVo();
        if(StrUtil.isEmpty(thisVo.getDescriptions())) thisVo.setDescriptions(thisVo.getNames());
        if(StrUtil.isEmpty(thisVo.getPassword())) thisVo.setPassword(thisVo.getNames());
        if(StrUtil.isEmpty(thisVo.getSite())) thisVo.setSite("LG");
        this.vo = thisVo;
    }
    @Override
    protected void preProcessForDelete(Map map){
        super.preProcessForDelete(map);
    }
    @Override
    protected void validateForModify(Map map){
        super.validateForModify(map);
    }
    @Override
    protected void preProcessForModify(Map map){
        super.preProcessForModify(map);
        this.processNullData();
        this.setKinds();
    }
    
    @Override
    protected void postProcessForCreate(Map map){
        super.postProcessForCreate(map);
    }
    @Override
    protected void postProcessForModify(Map map){
        super.postProcessForModify(map);
    }
    @Override
    protected void postProcessForInActiviate(Map map){
        super.postProcessForInActiviate(map);
    }
    @Override
    protected void postProcessForDelete(Map map){
        super.postProcessForDelete(map);
    }
    protected void setFlags(){
        // TODO Auto-generated method stub
        OmcSchemaUserVO thisVO = this.getVo();
        long flags = Bit.or(OmcSystemConstants.SYSUSER_FLAG_Default,OmcSystemConstants.SYSUSER_FLAG_Active);
        thisVO.setFlags(flags);
        this.setVo(thisVO);
    }
    protected abstract void setKinds();
    public void addMenu(String menu, long schemaKinds, Map<String,String> map){
        OmcSchemaMenu schemaMenu = new OmcSchemaMenu(menu);
        if(NullUtil.isNull(schemaMenu)) throw new FoundationException("Menu(" + menu + ") Not found."); 
        
        OmcSchemaRelationVO sysRelVO = new OmcSchemaRelationVO(this.getVo().getObid(),schemaMenu.getVo().getObid());
        
        if(!NullUtil.isNull(map)){
            String attr = "";
            attr = map.get("attribute01");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute01(attr);
            attr = map.get("attribute02");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute02(attr);
            attr = map.get("attribute03");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute03(attr);
            attr = map.get("attribute04");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute04(attr);
            attr = map.get("attribute05");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute05(attr);
            attr = map.get("attribute06");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute06(attr);
            attr = map.get("attribute07");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute07(attr);
            attr = map.get("attribute08");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute08(attr);
            attr = map.get("attribute09");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute09(attr);
            attr = map.get("attribute10");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute10(attr);
            attr = map.get("attribute11");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute11(attr);
            attr = map.get("attribute12");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute12(attr);
            attr = map.get("attribute13");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute13(attr);
            attr = map.get("attribute14");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute14(attr);
            attr = map.get("attribute15");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute15(attr);
            attr = map.get("attribute16");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute16(attr);
            attr = map.get("attribute17");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute17(attr);
            attr = map.get("attribute18");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute18(attr);
            attr = map.get("attribute19");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute19(attr);
            attr = map.get("attribute20");if(!NullUtil.isNone(attr)) sysRelVO.setAttribute20(attr);
        }

        if(Bit.isInclude(schemaKinds, OmcSystemConstants.SYSREL_KIND_RoleMenu)){
            OmcSchemaRelationRoleMenu userMenuDom = new OmcSchemaRelationRoleMenu(sysRelVO);
            userMenuDom.createObject(new HashMap<String,Object>());
            
        }else if(Bit.isInclude(schemaKinds, OmcSystemConstants.SYSREL_KIND_GroupMenu)){
            OmcSchemaRelationGroupMenu userMenuDom = new OmcSchemaRelationGroupMenu(sysRelVO);
            userMenuDom.createObject(new HashMap<String,Object>());
            
        }else if(Bit.isInclude(schemaKinds, OmcSystemConstants.SYSREL_KIND_UserMenu)){
            OmcSchemaRelationUserMenu userMenuDom = new OmcSchemaRelationUserMenu(sysRelVO);
            userMenuDom.createObject(new HashMap<String,Object>());
            
        }else{
            throw new FoundationException("[Foundation] Add Role's parameter invalid for Menu(" +  menu + ")."); 
        } 
    }
    public void removeMenu(String menu, long schemaKinds){
        OmcSchemaMenu schemaMenu = new OmcSchemaMenu(menu);
        if(NullUtil.isNull(schemaMenu)) throw new FoundationException("Menu(" + menu + ") Not found."); 
        List<OmcSchemaRelationVO> relVOList = OmcSchemaServiceUtils.getRelationList(schemaKinds,this.getVo().getObid(),schemaMenu.getVo().getObid());
        if(!NullUtil.isNone(relVOList)){
            if(Bit.isInclude(schemaKinds, OmcSystemConstants.SYSREL_KIND_RoleMenu)){
                for(OmcSchemaRelationVO vo : relVOList){
                    OmcSchemaRelationRoleMenu relDom = new OmcSchemaRelationRoleMenu(vo);
                    relDom.deleteObject(new HashMap<String,Object>());
                }
            }else if(Bit.isInclude(schemaKinds, OmcSystemConstants.SYSREL_KIND_GroupMenu)){
                for(OmcSchemaRelationVO vo : relVOList){
                    OmcSchemaRelationGroupMenu relDom = new OmcSchemaRelationGroupMenu(vo);
                    relDom.deleteObject(new HashMap<String,Object>());
                }
            }else if(Bit.isInclude(schemaKinds, OmcSystemConstants.SYSREL_KIND_UserMenu)){
                for(OmcSchemaRelationVO vo : relVOList){
                    OmcSchemaRelationUserMenu relDom = new OmcSchemaRelationUserMenu(vo);
                    relDom.deleteObject(new HashMap<String,Object>());
                }
            }else{
                throw new FoundationException("[Foundation] Remove Role's parameter invalid for Menu(" +  menu + ")."); 
            } 
        }
    }
    public List<OmcSchemaMenuVO> getMenuList(){
        return(this.getMenuList(true));
    }
    public List<OmcSchemaMenuVO> getMenuList(boolean isActiveOnly){
        return(OmcSchemaServiceUtils.getMenuListForCommonUser(this.getVo().getObid(),isActiveOnly));
    }
    public static List<OmcSchemaMenuVO> getMenuListForSet(List<String> obidList){
        return(getMenuListForSetWithObids(obidList,true));
    }
    public static List<OmcSchemaMenuVO> getMenuListForSetWithObids(List<String> obidList, boolean isActiveOnly){
        return(OmcSchemaServiceUtils.getMenuListForCommonUserWithObid(obidList,isActiveOnly));
    }
    public static List<OmcSchemaMenuVO> getMenuListForSetWithNames(List<String> nameList, boolean isActiveOnly){
        return(OmcSchemaServiceUtils.getMenuListForCommonUserWithNames(nameList,isActiveOnly));
    }
    public static List<String> getMenuSetForSetWithObids(List<String> obidList){
        return(OmcSchemaServiceUtils.getMenuSetForCommonUserWithObid(obidList));
    }
    public static List<String> getMenuSetForSetWithNames(List<String> nameList){
        return(OmcSchemaServiceUtils.getMenuSetForCommonUserWithNames(nameList));
    }
}
