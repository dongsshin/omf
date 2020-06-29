/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : DependencyVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 10. 18.  s_dongsshin   Initial
 * ===========================================
 */
package rap.application.workflow.model;

import rap.api.object.project.model.WBSItemsVO;

/**
 * <pre>
 * Class : DependencyVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class DependencyVO {
    private String dependencyType;
    private WBSItemsVO dependentVO;
    
    public String getDependencyType(){
        return dependencyType;
    }
    
    public void setDependencyType(String dependencyType){
        this.dependencyType = dependencyType;
    }

    
    public WBSItemsVO getDependentVO(){
        return dependentVO;
    }

    
    public void setDependentVO(WBSItemsVO dependentVO){
        this.dependentVO = dependentVO;
    }

    public DependencyVO(String dependencyType, WBSItemsVO dependentVO) {
        super();
        this.dependencyType = dependencyType;
        this.dependentVO = dependentVO;
    }
    

    
}
