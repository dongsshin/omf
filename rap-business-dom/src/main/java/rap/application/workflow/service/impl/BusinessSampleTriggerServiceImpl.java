/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : BusinessSampleTriggerServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 4. 9.  kwanghyui.choi   Initial
 * ===========================================
 */
package rap.application.workflow.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import rap.application.workflow.service.BusinessSampleTriggerService;


/**
 * <pre>
 * Class : BusinessSampleTriggerServiceImpl
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
@Service("businessSampleTriggerService")
public class BusinessSampleTriggerServiceImpl implements BusinessSampleTriggerService{
    static final Logger LOGGER = LoggerFactory.getLogger(BusinessSampleTriggerServiceImpl.class);
    
    /**
     * 
     * @param a
     * @param b
     * @param c
     * @see lgcns.rnd.application.workflow.service.BusinessSampleTriggerService#testDo(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void testTriggerMethod1(String a, String b, String c){
        LOGGER.debug("[testTriggerMethod1]===>"+a +":"+b+":"+c);
    }

    /**
     * 
     * @param a
     * @param b
     * @param c
     * @param d
     * @see lgcns.rnd.application.workflow.service.BusinessSampleTriggerService#testTriggerMethod2(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void testTriggerMethod1(String a, String b, String c, String d){
        LOGGER.debug("[testTriggerMethod1]===>"+a +":"+b+":"+c+":"+d);
        
    }

    /**
     * 
     * @param a
     * @param b
     * @see lgcns.rnd.application.workflow.service.BusinessSampleTriggerService#testTriggerMethod3(java.lang.String, java.lang.String)
     */
    @Override
    public void testTriggerMethod1(String a, String b){
         LOGGER.debug("[testTriggerMethod3]===>"+a +":"+b);
        
    }

    /**
     * 
     * @param a
     * @see lgcns.rnd.application.workflow.service.BusinessSampleTriggerService#testTriggerMethod4(java.lang.String)
     */
    @Override
    public void testTriggerMethod1(String a){
        LOGGER.debug("[testTriggerMethod4]===>"+a);
        
    }

}
