package com.rap.omc.framework.view;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.AbstractView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.ResponseConstants;
import com.rap.omc.dataaccess.exception.DaoException;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.framework.exception.NoAuthorityException;
import com.rap.omc.framework.exception.NoAuthorityTokenException;
import com.rap.omc.framework.exception.NoSessionException;
public class AjaxExceptionView extends AbstractView {

    public static final String DEFAULT_EXCEPTION_ATTRIBUTE = "exception";

    public static final String DEFAULT_EXCEPTION_CODE_ATTRIBUTE = "exceptionCode";
	
    private String exceptionAttribute = DEFAULT_EXCEPTION_ATTRIBUTE;

    @Resource(name = "messageSourceAccessor")
    private MessageSourceAccessor messageSourceAccessor;

    public void setExceptionAttribute(String exceptionAttribute){
        this.exceptionAttribute = exceptionAttribute;
    }

    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception{

        Exception ex = (Exception)model.get(this.exceptionAttribute);
        Map<String, Object> exceptionModel = new HashMap<String, Object>();
       
        if (ex != null) {
            
            if (ex instanceof FoundationException ){
            	FoundationException be = (FoundationException)ex;
                int statusCode = ((FoundationException)ex).getStatusCode()==0 ? ResponseConstants.STATUS_SYSTEM_DEFAULT_ERROR : ((FoundationException)ex).getStatusCode();
                
                exceptionModel.put(GlobalConstants.M_STATUS_CODE, statusCode );
                exceptionModel.put(GlobalConstants.M_MESSAGE, be.getMessage());
                
            }else if (ex instanceof ApplicationException ){
                ApplicationException be = (ApplicationException)ex;           
                int statusCode = ((ApplicationException)ex).getStatusCode()==0 ? ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR : ((ApplicationException)ex).getStatusCode();
                
                exceptionModel.put(GlobalConstants.M_STATUS_CODE,  statusCode);
                exceptionModel.put(GlobalConstants.M_MESSAGE, be.getMessage());
                
            }else if (ex instanceof NoAuthorityException ) {
                NoAuthorityException be = (NoAuthorityException)ex;
                exceptionModel.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_AUTHORIZATION_FAIL);
                exceptionModel.put(GlobalConstants.M_MESSAGE, be.getMessage());
                
            }else if (ex instanceof NoAuthorityTokenException ) {
                NoAuthorityTokenException be = (NoAuthorityTokenException)ex;
                exceptionModel.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_AUTHORIZATION_FAIL);
                exceptionModel.put(GlobalConstants.M_MESSAGE, be.getMessage());
                
            }else if (ex instanceof NoSessionException) {
                NoSessionException be = (NoSessionException)ex;
                exceptionModel.put(GlobalConstants.M_STATUS_CODE,  ResponseConstants.STATUS_LOGIN_REQUIRED);
                exceptionModel.put(GlobalConstants.M_MESSAGE, be.getMessage());
                
            }else {
                if (ex instanceof DaoException) {
                    exceptionModel.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_TRANSACTION_FAILED);                            
                    exceptionModel.put(GlobalConstants.M_MESSAGE,messageSourceAccessor.getMessage("com.fail.database"));
                    
                } else {
                    exceptionModel.put(GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_UNKNOWN_HOST_ERROR);
                    exceptionModel.put(GlobalConstants.M_MESSAGE, ex.getMessage());
                    
                }
            }
            writeExceptionContent(response, HttpServletResponse.SC_OK, exceptionModel);
        }
    }

    protected void writeExceptionContent(HttpServletResponse response, int httpStatus, Map<String, Object> model) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(model);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(httpStatus);
        response.getWriter().write(jsonStr);
    }
}
