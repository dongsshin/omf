/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : WorkflowChainedComparator.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 3. 25.  kwanghyui.choi   Initial
 * ===========================================
 */
package rap.application.workflow.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import rap.application.workflow.model.ApprovalVO;




/**
 * <pre>
 * Class : WorkflowChainedComparator
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class WorkflowChainedComparator implements Comparator<ApprovalVO> {
 
    private List<Comparator<ApprovalVO>> listComparators;
 
    @SafeVarargs
    public WorkflowChainedComparator(Comparator<ApprovalVO>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }
 
    @Override
    public int compare(ApprovalVO a1, ApprovalVO a2) {
        for (Comparator<ApprovalVO> comparator : listComparators) {
            int result = comparator.compare(a1, a2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

}
