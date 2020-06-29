package com.rap.omc.schema.serviceutil;
/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SchemaServiceUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2016. 06. 27. dongsik.shin Initial
 * ===========================================
 */
import java.util.List;

import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.schema.object.temp.model.SchemaBusinessObjectVO;
import com.rap.omc.schema.object.temp.model.SchemaClassAttributeVO;
import com.rap.omc.schema.object.temp.model.SchemaKeyTableVO;
import com.rap.omc.schema.object.temp.model.SchemaRelationObjectVO;
import com.rap.omc.schema.service.OmcAppicationSchemaManagementService;



/**
 * <pre>
 * Class : SchemaServiceUtil
 * Description : TODO
 * </pre>
 * 
 * @author dongsilk.shin
 */
public class SchemaServiceUtil {

    private OmcAppicationSchemaManagementService omcAppicationSchemaManagementService;
    private static SchemaServiceUtil cInstance;
    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static SchemaServiceUtil getInstance(){
        if (cInstance == null) {
            cInstance = new SchemaServiceUtil();
            cInstance.omcAppicationSchemaManagementService = (OmcAppicationSchemaManagementService)SpringFactoryUtil.getBean("omcAppicationSchemaManagementService");
        }
        return cInstance;
    }
    public static SchemaKeyTableVO getSchemKeyTable(String obid) {
        return getInstance().omcAppicationSchemaManagementService.getSchemKeyTable(obid);
    }
    public static SchemaBusinessObjectVO getBusienssObjectClass(String obid) {
        return getInstance().omcAppicationSchemaManagementService.getSchemaBusienssObjectClass(obid);
    }

    public static List<SchemaBusinessObjectVO> getBusienssObjectClassAll() {
        return getInstance().omcAppicationSchemaManagementService.getSchemaBusienssObjectClassAll();
    }
    public static List<SchemaClassAttributeVO> getSchemaAttrForClass(String className) {
        return getInstance().omcAppicationSchemaManagementService.getSchemaAttrForClass(className);
    }
    public static void createBusinessObjectClass(SchemaBusinessObjectVO businessObjectVO) {
        getInstance().omcAppicationSchemaManagementService.createBusinessObjectClass(businessObjectVO);
    }
    public static void inactiveBusinessObjectClass(String obid) {
        getInstance().omcAppicationSchemaManagementService.inactiveBusinessObjectClass(obid);
    } 
    
    
    public static SchemaRelationObjectVO getRelationObjectClass(String obid) {
        return getInstance().omcAppicationSchemaManagementService.getSchemaRelationObjectClass(obid);
    }
    
    
    
}
