/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaStoreLocation.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 23.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.List;
import java.util.Map;

import com.rap.omc.schema.object.model.OmcSchemaStoreLocationVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;

/**
 * <pre>
 * Class : OmcSchemaStoreLocation
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaLocation extends OmcSchemaSysRoot{
    public static void uploadTemporaryList(List<OmcSchemaStoreLocationVO> list){
        uploadStoreTemporaryList(list);
        uploadLoacationTemporaryList(list);
        uploadStoreLoacationTemporaryList(list);
    }
    private static void uploadStoreTemporaryList(List<OmcSchemaStoreLocationVO> list){
        
    }
    private static void uploadLoacationTemporaryList(List<OmcSchemaStoreLocationVO> list){
        
    }
    private static void uploadStoreLoacationTemporaryList(List<OmcSchemaStoreLocationVO> list){
        
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#create(java.util.Map)
     */
    @Override
    protected void create(Map map){
        // TODO Auto-generated method stub
        
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#inActiviate(java.util.Map)
     */
    @Override
    protected void inActiviate(Map map){
        // TODO Auto-generated method stub
        
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#delete(java.util.Map)
     */
    @Override
    protected void delete(Map map){
        // TODO Auto-generated method stub
        
    }
    /**
     * 
     * @param map
     * @see omc.schema.object.dom.OmcSchemaSysRoot#modify(java.util.Map)
     */
    @Override
    protected void modify(Map map){
        // TODO Auto-generated method stub
        
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
        // TODO Auto-generated method stub
        
    }
    /**
     * 
     * @see omc.schema.object.dom.OmcSchemaSysRoot#setClassKind()
     */
    @Override
    protected void setClassKind(){
        // TODO Auto-generated method stub
        
    }
}
