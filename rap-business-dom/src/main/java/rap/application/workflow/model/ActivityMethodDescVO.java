/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : ActivityMethodDesc.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 9. 21.  s_dongsshin   Initial
 * ===========================================
 */
package rap.application.workflow.model;


/**
 * <pre>
 * Class : ActivityMethodDescVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class ActivityMethodDescVO {
    private String method;
    private String shortName;
    private String methodDesc;
    
    public ActivityMethodDescVO(String method, String shortName, String methodDesc) {
        super();
        this.method = method;
        this.shortName = shortName;
        this.methodDesc = methodDesc;
    }

    public String getMethod(){
        return method;
    }
    
    
    public String getShortName(){
        return shortName;
    }

    
    public void setShortName(String shortName){
        this.shortName = shortName;
    }

    public void setMethod(String method){
        this.method = method;
    }
    
    public String getMethodDesc(){
        return methodDesc;
    }
    
    public void setMethodDesc(String methodDesc){
        this.methodDesc = methodDesc;
    }
    
}
