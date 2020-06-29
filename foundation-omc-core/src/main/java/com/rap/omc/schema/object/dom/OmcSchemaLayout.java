/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaLayout.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 6.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.schema.object.model.OmcSchemaRelationVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.object.model.OmcSchemaTabLayoutVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaLayout
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings("rawtypes")
public class OmcSchemaLayout extends OmcSchemaSysRoot{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaLayout.class);
    @Override
    public OmcSchemaTabLayoutVO getVo(){
        return (OmcSchemaTabLayoutVO)vo;
    }
    public OmcSchemaLayout(OmcSchemaSysRootVO vo) {
        super(vo);
        // TODO Auto-generated constructor stub
    }
    public OmcSchemaLayout(String obid) {
        super(OmcSchemaServiceUtils.getSystemFileFormatWithObid(obid));
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    @Override
    protected void create(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.createSystemLayout(this.getVo());
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate(java.util.Map)
     */
    @Override
    protected void inActiviate(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.inactivateSystemLayout(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete(java.util.Map)
     */
    @Override
    protected void delete(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.deleteSystemLayout(this.getVo());
    }

    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify(java.util.Map)
     */
    @Override
    protected void modify(Map map){
        // TODO Auto-generated method stub
        OmcSchemaServiceUtils.modifySystemLayout(this.getVo());
    }

    /**
     * 
     * @param Names
     * @return
     * @see omc.schema.object.dom.OmcSchemaSysRoot#getObjectInfoByName(java.lang.String)
     */
    @Override
    protected OmcSchemaSysRootVO getObjectInfoByName(String Names){
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setFlags()
     */
    @Override
    protected void setFlags(){
        OmcSchemaTabLayoutVO thisVO = this.getVo();
        long flags = Bit.or(OmcSystemConstants.SYSLAYOUT_FLAG_Default,OmcSystemConstants.SYSLAYOUT_FLAG_Active);
        thisVO.setFlags(flags);
        this.setVo(thisVO);
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setClassKind()
     */
    @Override
    protected void setClassKind(){
        OmcSchemaTabLayoutVO thisVO = this.getVo();
        thisVO.setClassKinds(OmcSystemConstants.SYSKEY_KIND_Layout);
        this.setVo(thisVO);
    }
    @Override
    protected void validateForInActiviate(Map map){
        super.validateForInActiviate(map);
    }
    @Override
    protected void preProcessForInActiviate(Map map){
        super.preProcessForInActiviate(map);
    }
    @Override
    protected void postProcessForInActiviate(Map map){
        super.postProcessForInActiviate(map);
    }
    @Override
    protected void validateForDelete(Map map){
        super.validateForDelete(map);
    }
    @Override
    protected void preProcessForCreate(Map map){
        super.preProcessForCreate(map);
        this.seperateSubObjList();
    }
    @Override
    protected void preProcessForDelete(Map map){
        super.preProcessForDelete(map);
    }
    @Override
    protected void postProcessForCreate(Map map){
        super.postProcessForCreate(map);
        this.applySubObjList();
    }
    @Override
    protected void postProcessForDelete(Map map){
        super.postProcessForDelete(map);
    }
    @Override
    protected void validateForModify(Map map){
        super.validateForModify(map);
    }
    @Override
    protected void preProcessForModify(Map map){
        super.preProcessForModify(map);
        this.seperateSubObjList();
    }
    @Override
    protected void postProcessForModify(Map map){
        super.postProcessForModify(map);
        this.applySubObjList();
    }
    public static void getCommonSelectTemporarySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.psequences          as sequences       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pkinds_str          as kinds_str       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pnames              as names          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plabels             as labels         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plabels_kr          as labels_kr      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plink_herf          as link_herf      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plink_alt           as link_alt       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pheights            as heights        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pusages             as usages         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pchange_comments    as change_comments");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.psub_object_list    as sub_object_list");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.powners             as owners"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,(select x.obid from psyslayout x where x.pnames = a.pnames) as obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from zsyslayouttab a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pkinds_str = 'Layout'");
    }
    public static void getCommonSelectSql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter){
        sqlStrBuff.append                                      ("select a.obid                as obid           ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pflags              as flags          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pnames              as names          ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plabels             as labels         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plabels_kr          as labels_kr      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plink_herf          as link_herf      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.plink_alt           as link_alt       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pheights            as heights        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pusages             as usages         ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.psequences          as sequences      ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pchange_comments    as change_comments");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.psub_object_list    as sub_object_list");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.powners             as owners"         );
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreator            as creator        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pcreated            as created        ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodifier           as modifier       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pmodified           as modified       ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psyslayout a");
    }
    
    public static List<OmcSchemaTabLayoutVO> getUploadList(){
        return(OmcSchemaServiceUtils.getLayoutListForUpload());
    }
    public static List<OmcSchemaTabLayoutVO> getInactiveList(){
        return(OmcSchemaServiceUtils.getInActiveLayoutListForUpload());
    }
    private void seperateSubObjList(){
        OmcSchemaTabLayoutVO thisVo = this.getVo();
        List<String> list = new ArrayList<String>();
        String formatList[] = thisVo.getSubObjectList().split(Pattern.quote(","));
        //formatList = StrUtil.uniquize(formatList);
        for(int i = 0; i < formatList.length; i++){
            if(formatList[i] != null){
                if(!StrUtil.isEmpty(formatList[i].trim())) list.add(formatList[i].trim());
            }
        }
        thisVo.setSubObjectArrayList(list);
        this.vo = thisVo;
    }
    private  List<OmcSchemaRelationVO> getRelationListForTab(){
        return(OmcSchemaServiceUtils.getSchemaRelationList(this.getVo(),OmcSystemConstants.SYSREL_KIND_LayoutHasTab,false));
    }
    private  List<OmcSchemaTabLayoutVO> getUploadTabList(){
        return(OmcSchemaServiceUtils.getTabListWithSeperatedList(this.getVo().getSubObjectArrayList()));
    }
    private void applySubObjList(){
        List<String>  subObjectArrayList = this.getVo().getSubObjectArrayList();
        //List<OmcSchemaMenuVO> savedCommandList = this.getCommandList();
        List<OmcSchemaTabLayoutVO> newList          = this.getUploadTabList();
        List<OmcSchemaRelationVO> savedRelList = this.getRelationListForTab();
        
        for(OmcSchemaTabLayoutVO newVo : newList){
            for(OmcSchemaRelationVO savedVO : savedRelList){
                if(savedVO.getToObid().equals(newVo.getObid())) {
                    savedVO.setSorting((int)newVo.getSequences());
                }
            }
           
        }
        OmcSchemaRelationLayout2Tab dom;
        for(OmcSchemaRelationVO savedVO : savedRelList){
            boolean isInactive = true;
            for(OmcSchemaTabLayoutVO newVo : newList){
                if(savedVO.getToObid().equals(newVo.getObid())) {
                    isInactive = false;
                    //if(!Bit.isInclude(savedVO.getFlags(), OmcSystemConstants.SYSREL_FLAG_Active))
                    {
                        dom = new OmcSchemaRelationLayout2Tab(savedVO);
                        Map<String, Object> map = new HashMap<String, Object>();
                        dom.modifyObject(map);
                    }
                }
            }
            if(isInactive) 
            {
                dom = new OmcSchemaRelationLayout2Tab(savedVO);
                Map<String, Object> map = new HashMap<String, Object>();
                dom.inActiviate(map);
            }
        }
        
        for(OmcSchemaTabLayoutVO newVo : newList){
            boolean isNotExists = true;
            for(OmcSchemaRelationVO savedVO : savedRelList){
                if(savedVO.getToObid().equals(newVo.getObid())) {
                    isNotExists = false;break;
                }
            }
            if(isNotExists) 
            {
                OmcSchemaRelationVO relVo = new OmcSchemaRelationVO();
                relVo.setFromObid(this.getVo().getObid());
                relVo.setToObid(newVo.getObid());
                relVo.setSorting((int)newVo.getSequences());
                dom = new OmcSchemaRelationLayout2Tab(relVo);
                Map<String, Object> map = new HashMap<String, Object>();
                dom.createObject(map);
            }
        }
    }
    
}
