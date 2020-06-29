package com.rap.config.web.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.FoundationBaseException;
import com.rap.omc.framework.util.PdmValidationUtil;
import com.rap.omc.framework.util.PdmXssValidator;
import com.rap.omc.util.NullUtil;

import net.sf.json.JSONObject;

public class XssCheckInterceptor extends UrlPatternInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(XssCheckInterceptor.class);

    private PdmXssValidator pdmXssValidator;

    public boolean checkHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        Map<String, String[]> map = request.getParameterMap();
        Set<String> keySet = map.keySet();

        Iterator<String> keys = keySet.iterator();
        while (keys.hasNext()) {
            String key = (String)keys.next();
            String[] values = map.get(key);
            if(GlobalConstants.SMARTCLIENT_FORM_KEY.equals(key)&& !NullUtil.isNone(values[0])){
                JSONObject obj = JSONObject.fromObject(values[0]);
                if (! getPdmXssValidator().checkXSSJsonObject( request.getServletPath(), obj)){
                    throw new FoundationBaseException("frame.error.xss");
                }
            }else{
                for (int inx = 0; inx < values.length; inx++) {
                    LOGGER.debug("XSS XssCheckInterceptor NO KEY, values = {} ", values[inx]);
                    if (!PdmValidationUtil.checkXSS(values[inx])) {
                        throw new FoundationBaseException("frame.error.xss");
                    }
                }
            }
        }
        return true;
    }
    /**
     *
     *
     * @return the pdmXssValidator
     */
    public PdmXssValidator getPdmXssValidator(){
        return pdmXssValidator;
    }


    /**
     *
     *
     * @param pdmXssValidator the pdmXssValidator to set
     */
    public void setPdmXssValidator(PdmXssValidator pdmXssValidator){
        this.pdmXssValidator = pdmXssValidator;
    }
}
