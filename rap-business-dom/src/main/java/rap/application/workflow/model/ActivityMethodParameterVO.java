/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : ActivityMethodParameterVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 10. 19.  s_dongsshin   Initial
 * ===========================================
 */
package rap.application.workflow.model;

import java.lang.reflect.Method;

/**
 * <pre>
 * Class : ActivityMethodParameterVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class ActivityMethodParameterVO {
    private Method method;
    private Object objct;
    private String program;
    private String methodName;
    
    public Method getMethod(){
        return method;
    }
    
    public void setMethod(Method method){
        this.method = method;
    }
    
    public Object getObjct(){
        return objct;
    }
    
    public void setObjct(Object objct){
        this.objct = objct;
    }
    
    public String getProgram(){
        return program;
    }
    
    public void setProgram(String program){
        this.program = program;
    }
    
    public String getMethodName(){
        return methodName;
    }
    
    public void setMethodName(String methodName){
        this.methodName = methodName;
    }

    public ActivityMethodParameterVO(Method method, Object objct, String program, String methodName) {
        super();
        this.method = method;
        this.objct = objct;
        this.program = program;
        this.methodName = methodName;
    }
    
}
