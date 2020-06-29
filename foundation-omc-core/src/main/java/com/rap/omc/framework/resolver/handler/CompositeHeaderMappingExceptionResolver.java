package com.rap.omc.framework.resolver.handler;                                                                                   
                                                                                                                                  
                                                                                                                                  
import java.util.Enumeration;                                                                                            
import java.util.Properties;                                                                                             
                                                                                                                         
import javax.servlet.http.HttpServletRequest;                                                                            
import javax.servlet.http.HttpServletResponse;                                                                           
                                                                                                                         
import org.springframework.util.PatternMatchUtils;                                                                       
import org.springframework.web.servlet.ModelAndView;                                                                     
                                                                                                                         
                                                                                                                         
public class CompositeHeaderMappingExceptionResolver extends AbstractHeaderMappingExceptionResolver                      
{                                                                                                                        
                                                                                                                                  
    private Properties headerMappings;                                                                                   
                                                                                                                         
    private String errorView;                                                                                            
                                                                                                                         
                                                                                                                         
    public void setHeaderMappings(Properties mappings)                                                                   
    {                                                                                                                    
        this.headerMappings = mappings;                                                                                  
        }                                                                                                                
                                                                                                                                  
                                                                                                                         
    public void setErrorView(String errorView)                                                                           
    {                                                                                                                    
        this.errorView = errorView;                                                                                      
        }                                                                                                                
                                                                                                                                  
                                                                                                                         
    protected boolean supportsHeader(HttpServletRequest request){                                                        
        if (this.headerMappings != null) {                                                                               
            for (Enumeration names = this.headerMappings.propertyNames(); names.hasMoreElements();) {                    
                String headerName = (String)names.nextElement();                                                         
                                                                                                                         
                String headerValue = this.headerMappings.getProperty(headerName);                                        
                String orginalHeaderVaule = request.getHeader(headerName);                                               
                if ((orginalHeaderVaule != null) &&                                                                      
                (PatternMatchUtils.simpleMatch(headerValue, orginalHeaderVaule))) return true;                           
                }                                                                                                        
            }                                                                                                            
        else {                                                                                                           
            return false;                                                                                                
            }                                                                                                            
        return false;                                                                                                    
        }                                                                                                                
                                                                                                                                  
                                                                                                                         
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,                  
            Object handler, Exception ex){                                                                                        
        String viewName = this.errorView;                                                                                
                                                                                                                         
        if (viewName != null) {                                                                                          
            return getModelAndView(viewName, ex);                                                                        
            }                                                                                                            
        return null;                                                                                                     
        }                                                                                                                
}                                                                                                                    