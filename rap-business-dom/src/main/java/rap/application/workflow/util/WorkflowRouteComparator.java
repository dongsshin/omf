/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : WorkflowRouteComparator.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 3. 25.  kwanghyui.choi   Initial
 * ===========================================
 */
package rap.application.workflow.util;

import java.util.Comparator;

import rap.application.workflow.model.ApprovalVO;




/**
 * <pre>
 * Class : WorkflowRouteComparator
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class WorkflowRouteComparator implements Comparator<ApprovalVO> {
 
    @Override
    public int compare(ApprovalVO a1, ApprovalVO a2) {
        return a1.getRouteStateSequence()- a2.getRouteStateSequence();
    }
}
