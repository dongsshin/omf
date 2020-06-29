/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : WBSActivityWorkflowController.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 9. 13.  kwanghyui.choi   Initial
 * ===========================================
 */
package com.rap.workflow.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.UserSessionUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.ResponseConstants;
import com.rap.omc.framework.annotation.SCRequestDataset;
import com.rap.omc.framework.controller.BaseController;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.schema.object.temp.model.ActivityValidationResultVO;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.ResponseMapper;

import rap.api.object.workflow.dom.WorkflowInboxTask;
import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.application.constants.WorkflowConstants;
import rap.application.workflow.model.ApprovalVO;
import rap.application.workflow.model.ReassignVO;
import rap.application.workflow.service.WBSActivityWorkflowService;


/**
 * <pre>
 * Class : WBSActivityWorkflowController
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
@Controller
public class WBSActivityWorkflowController extends BaseController{
    static final Logger LOGGER = LoggerFactory.getLogger(WBSActivityWorkflowController.class);
    
    @Resource(name = "wbsActivityWorkflowService")
    private WBSActivityWorkflowService wbsActivityWorkflowService;
    /**
     * 
     *
     * @param businessObjectRootVO
     * @param model
     * @return
     */
    @RequestMapping( value="/workflow/wbsActivity/retrieveWorkflowList.do", method = RequestMethod.POST )
    public String retrieveWorkflowList(@SCRequestDataset( "businessObjectRootVO" ) BusinessObjectRootVO businessObjectRootVO, ModelMap model) {
        if(NullUtil.isNull(businessObjectRootVO.getObid())) 
            throw new ApplicationException("api.object.error.workflow.illegalArgument", new Object[] { "Obid is null" });
        
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(businessObjectRootVO.getObid());
        List<ApprovalVO> approvalList     =       wbsActivityWorkflowService.retrieveWBSActivityWorkflow(businessObjectRoot);
        ResponseMapper rm = new ResponseMapper();
        rm.setData("approvalList",          approvalList);
        rm.setModelMap( model );
        return GlobalConstants.AJAX_VIEW;
    }
    
    /**
     * 
     *
     * @param approvalVO
     * @param sendBackToList
     * @param fileList
     * @param model
     * @return
     */
    @RequestMapping( value="/workflow/wbsActivity/submitApproval.do", method = RequestMethod.POST )
    public String submitApproval(@SCRequestDataset( "approvalVO" ) ApprovalVO approvalVO, @SCRequestDataset( "fileList" ) List<FilesVO> fileList, 
            @SCRequestDataset("skipWarning") String skipWarning, ModelMap model) {
        if(NullUtil.isNone(approvalVO.getObid()) ||
                NullUtil.isNone(approvalVO.getApprovalStatus()) ||
                NullUtil.isNone(approvalVO.getComments()) ||
                NullUtil.isNone(approvalVO.getWorkflowInboxTaskObid()) ||
                NullUtil.isNone(skipWarning)){
            throw new IllegalArgumentException();
        }
        ResponseMapper rm = new ResponseMapper();
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(approvalVO.getObid());
        try{
            approvalVO.setStep(NullUtil.isNull(approvalVO.getStep()) ? 1 : approvalVO.getStep());
            approvalVO.setParallelNodeProcessionRule(NullUtil.isNull(approvalVO.getParallelNodeProcessionRule()) ? WorkflowConstants.ROUTE_ACTION_ALL : approvalVO.getParallelNodeProcessionRule());
            wbsActivityWorkflowService.txnSubmitApproval(businessObjectRoot, approvalVO, Boolean.parseBoolean(skipWarning));
        }catch(Exception e) {
            rm.setStatusCode(ResponseConstants.STATUS_TRIGGER_ERROR);
            rm.setMessage( e.getMessage());
            rm.setModelMap(model);

            return GlobalConstants.AJAX_VIEW;
        }
        rm.setStatusCode(ResponseConstants.STATUS_SUCCESS);
        rm.setMessage( "Action Completed!" );
        rm.setModelMap(model);
        
        return GlobalConstants.AJAX_VIEW;
    }
    
    /**
     * 
     *
     * @param reassignVO
     * @param model
     * @return
     */
    @RequestMapping( value="/workflow/wbsActivity/reassignApprover.do", method = RequestMethod.POST )
    public String reassignApprover(@SCRequestDataset( "reassignVO" ) ReassignVO reassignVO, ModelMap model) {
        if(NullUtil.isNull(reassignVO.getObid())) throw new ApplicationException("api.object.error.workflow.illegalArgument", new Object[] { "Obid is null" });
        
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(reassignVO.getObid());
        wbsActivityWorkflowService.txnReassignApprover(businessObjectRoot, reassignVO, true);
        ResponseMapper rm = new ResponseMapper();
        rm.setStatusCode(ResponseConstants.STATUS_SUCCESS);
        rm.setMessage( "Action Completed!" );
        rm.setModelMap(model);
        return GlobalConstants.AJAX_VIEW;
    }
    
    /**
     * WBS Activity Reassign
     * @param reassignVOList
     * @param model
     * @return
     */
    @RequestMapping( value="/workflow/wbsActivity/reassignApproverList.do", method = RequestMethod.POST )
    public String reassignApproverList(@SCRequestDataset( "reassignVOList" ) List<ReassignVO> reassignVOList, ModelMap model) {
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";
        
        try {
            if ( NullUtil.isNone(reassignVOList) ) {
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Error Occurred.";
            } else {
                wbsActivityWorkflowService.txnReassignApproverList(reassignVOList); 
            }
        } catch ( Exception e ) {
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = e.getMessage();
            e.printStackTrace();
        } finally {
            ResponseMapper rm = new ResponseMapper();
            rm.setStatusCode(statusCode);
            if ( "".equals(message) ) {
                rm.setMessage( "Action Completed!" );
            } else {
                rm.setMessage( message );
            }
            
            rm.setModelMap(model);
        }
        
        return GlobalConstants.AJAX_VIEW;
    }
    
    /**
     * 
     *
     * @param businessObjectRootVO
     * @param workflowInboxTaskVO
     * @param model
     * @return
     */
    @RequestMapping( value="/workflow/wbsActivity/validateCompleteWBSActivity.do", method = RequestMethod.POST )
    public String validateCompleteWBSActivity(@SCRequestDataset( "businessObjectRootVO" ) BusinessObjectRootVO businessObjectRootVO,
            @SCRequestDataset( "workflowInboxTaskVO" ) WorkflowInboxTaskVO workflowInboxTaskVO, ModelMap model) {
        if(NullUtil.isNone(businessObjectRootVO.getObid())) throw new ApplicationException("api.object.error.workflow.illegalArgument", new Object[] { "WBS Activity obid is null" });
        if(NullUtil.isNone(workflowInboxTaskVO.getObid())) throw new ApplicationException("api.object.error.workflow.illegalArgument", new Object[] { "Workflow Inbox Task obid is null" });
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(businessObjectRootVO.getObid(), false);
        WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO.getObid(), false);
        
        List<ActivityValidationResultVO> rtnActivityValidationResultVOList = new ArrayList<ActivityValidationResultVO>();
        if(wbsActivityWorkflowService.isLastApproval(businessObjectRoot, workflowInboxTask)){
            rtnActivityValidationResultVOList = wbsActivityWorkflowService.validateCompleteWBSActivity(businessObjectRoot, workflowInboxTask);            
        }
        ResponseMapper rm = new ResponseMapper();
        rm.setData("activityValidataionResultList",          rtnActivityValidationResultVOList);
        rm.setModelMap(model);
        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping( value="/workflow/wbsActivity/isLastApproval.do", method = RequestMethod.POST )
    public String isCallCompleteWBSActivity(@SCRequestDataset( "businessObjectRootVO" ) BusinessObjectRootVO businessObjectRootVO, 
            @SCRequestDataset( "workflowInboxTaskVO" ) WorkflowInboxTaskVO workflowInboxTaskVO, ModelMap model) {
        if(NullUtil.isNull(businessObjectRootVO.getObid()) || NullUtil.isNull(workflowInboxTaskVO.getObid())) throw new IllegalArgumentException();
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(businessObjectRootVO.getObid(), false);
        WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO.getObid(), false);
        Boolean rtnResult = wbsActivityWorkflowService.isLastApproval(businessObjectRoot, workflowInboxTask);
        ResponseMapper rm = new ResponseMapper();
        rm.setData("isLastApproval",          rtnResult);
        rm.setModelMap(model);
        return GlobalConstants.AJAX_VIEW;
    }
    
    /**
     * EP WBS Activity용 결재페이지로 이동
     * @return
     */
    @RequestMapping("/epWBSActivityApproval.do")
    public String epApprove( String inboxTaskObid, String isApproved, ModelMap map ){
        String fowardURL = "/workflow/epWBSActivityApproval";
        WorkflowInboxTask workflowInboxTask = DomUtil.toDom(inboxTaskObid, false);
        if(!workflowInboxTask.getVo().getInboxTaskType().equals(WorkflowConstants.INBOXTASK_TYPE_WBSACTIVITY)) throw new ApplicationException("Inbox Task Type is not WBS Activity");
        Map<String, String> wbsActivityMap = wbsActivityWorkflowService.retrieveWBSActivityApprovalProcessInformation(workflowInboxTask);
        
        for( Map.Entry<String, String> element : wbsActivityMap.entrySet() ){
            map.addAttribute(element.getKey(), element.getValue());
        }
        
        map.addAttribute( "inboxTaskObid", inboxTaskObid );
        map.addAttribute( "inboxTaskType", workflowInboxTask.getVo().getInboxTaskType() );
        map.addAttribute( "inboxTaskStates", workflowInboxTask.getStates() );
        map.addAttribute( "isApproved", isApproved );
        
        return fowardURL;
    }
    
    @RequestMapping(value = "/workflow/wbsActivity/submitApprovalByOtherSystem", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> submitApprovalByOtherSystem(String wbsActivityObid) {
        if(NullUtil.isNull(wbsActivityObid)) throw new ApplicationException("api.object.error.workflow.illegalArgument", new Object[] { "wbsActivityObid is null" });
        
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true); // true == allow create
        UserSessionUtil.refreshUserSession(GlobalConstants.SYSTEM_USER_ID_SystemAgent);
        session.setAttribute( GlobalConstants.SESSION_USER_INFO, session );
        
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(wbsActivityObid);
        List<ApprovalVO> approvalList     =       wbsActivityWorkflowService.retrieveWBSActivityWorkflow(businessObjectRoot);
        
        for(ApprovalVO approvalVO : approvalList) {
            if(!NullUtil.isNull(approvalVO.getOriginTaskOwner()) && approvalVO.getOriginTaskOwner().equals(approvalVO.getTaskOwner())) continue; //skip
            approvalVO.setApprovalStatus(WorkflowConstants.APPROVAL_STATUS_APPROVE);
            approvalVO.setComments("Automatically approve by system.[call by other system]");
            try{
                wbsActivityWorkflowService.txnSubmitApproval(businessObjectRoot, approvalVO, true);
            }catch(Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return null;
    }
}
