/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : CompletionSpecForActivity.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 9. 20.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.util;

import java.util.ArrayList;
import java.util.List;

import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.intf.IfPlmDataByTriggerVO;
import com.rap.omc.intf.TriggerUtil;
import com.rap.omc.schema.object.temp.model.ActivityValidationResultVO;
import com.rap.omc.util.NullUtil;

import rap.api.object.document.model.ProjectActivityDocumentVO;
import rap.api.object.part.dom.ModelMaster;
import rap.api.object.project.dom.Projects;
import rap.api.object.project.dom.WBSItems;
import rap.api.object.project.dom.WBSPhases;
import rap.api.object.project.model.GripEventCheckVO;
import rap.api.object.project.model.ModelDevelopmentGeneralProjectVO;
import rap.api.object.project.model.ModuleDevelopmentProjectsVO;
import rap.api.object.project.model.ProjectScheduleVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.api.object.relation.model.ProjectModelVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.workflow.util.WBSAbstractSpecSpecForActivity;

/**
 * <pre>
 * Class : CompletionSpecForActivity
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class StandardSpecForActivity extends WBSAbstractSpecSpecForActivity{
    public StandardSpecForActivity(ProjectsVO projectVO, ProjectScheduleVO scheduleVO, WBSItemsVO activityVO,
            String phaseCode, String phaseTitles, String phaseCodeSystem, String phaseTitlesSystem) {
        super(projectVO, scheduleVO, activityVO,phaseCode, phaseTitles, phaseCodeSystem, phaseTitlesSystem);
    }

    /* 아래 Method 구현시 반드시 
     * 해당 Mehtod에 대한 설명을 기입하기 바랍니다.(methodDescMap에 put해야 함)
     * 해당 내용은 Activity정의시 Mehtod Lust에 나타나게 됩니다.
     */
    /************************************Sample(Naming Rule)*****************************************/
    //validateForStart+Activity Code + '_' + Desc
    public List<ActivityValidationResultVO> validateForStart_ACTIVITY_CODE_EssentialDocument(){
        return null;
    }
    //validateForComplete+Activity Code + '_' + Desc
    public List<ActivityValidationResultVO> validateForComplete_ACTIVITY_CODE_EssentialDocument(){
        return null;
    }
    /**
     * Phase start validation
     * 
     */
    public List<ActivityValidationResultVO> validateForStart_PHASE1_PhaseStart(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        result.addAll(validateForStart_Common_PhaseStartValidation());
        return result;
    }
    public List<ActivityValidationResultVO> validateForStart_PHASE2_PhaseStart(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        result.addAll(validateForStart_Common_PhaseStartValidation());
        return result;
    }
    public List<ActivityValidationResultVO> validateForStart_PHASE3_PhaseStart(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        result.addAll(validateForStart_Common_PhaseStartValidation());
        return result;
    }
    public List<ActivityValidationResultVO> validateForStart_PHASE4_PhaseStart(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        result.addAll(validateForStart_Common_PhaseStartValidation());
        return result;
    }
    public List<ActivityValidationResultVO> validateForStart_PHASE5_PhaseStart(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        result.addAll(validateForStart_Common_PhaseStartValidation());
        return result;
    }
    public List<ActivityValidationResultVO> validateForStart_PHASE6_PhaseStart(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        result.addAll(validateForStart_Common_PhaseStartValidation());
        return result;
    }
    public List<ActivityValidationResultVO> validateForStart_PHASE7_PhaseStart(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        result.addAll(validateForStart_Common_PhaseStartValidation());
        return result;
    }
    public List<ActivityValidationResultVO> validateForStart_Common_PhaseStartValidation(){
    	return new ArrayList<ActivityValidationResultVO>();
//        List<ActivityValidationResultVO> activityValidationResultList = new ArrayList<ActivityValidationResultVO>();
//        Projects projects = DomUtil.toDom(this.getProjectVO());
//        
//        // MP를 제외한 마지막 activity에서 프로젝트 모델서픽스가 BASIS,BASISO,PROJECT,APILOT 이면 완료 불가 ,MC본부는 PP확정회에서 체크
//        List<ActivityValidationResultVO> modelProjectMPStartValidate = projects.modelProjectMPStartValidaion(this.getScheduleVO());
//        activityValidationResultList.addAll(modelProjectMPStartValidate);
//        return activityValidationResultList;
    }
    
    public List<ActivityValidationResultVO> validateForComplete_PHASE1_PhaseEnd(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        result.addAll(this.validateForComplete_Common_PhaseEndValidation());
        return result;
    }
    public List<ActivityValidationResultVO> validateForComplete_PHASE2_PhaseEnd(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        result.addAll(this.validateForComplete_Common_PhaseEndValidation());
        return result;
    }
    public List<ActivityValidationResultVO> validateForComplete_PHASE3_PhaseEnd(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        result.addAll(this.validateForComplete_Common_PhaseEndValidation());
        return result;
    }
    public List<ActivityValidationResultVO> validateForComplete_PHASE4_PhaseEnd(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        result.addAll(this.validateForComplete_Common_PhaseEndValidation());
        return result;
    }  
    public List<ActivityValidationResultVO> validateForComplete_PHASE5_PhaseEnd(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        result.addAll(this.validateForComplete_Common_PhaseEndValidation());
        return result;
    }
    public List<ActivityValidationResultVO> validateForComplete_PHASE6_PhaseEnd(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        result.addAll(this.validateForComplete_Common_PhaseEndValidation());
        return result;
    }  
    public List<ActivityValidationResultVO> validateForComplete_PHASE7_PhaseEnd(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        result.addAll(this.validateForComplete_Common_PhaseEndValidation());
        return result;
    }
    /**
     * Phase end validation
     * 
     */
    public List<ActivityValidationResultVO> validateForComplete_Common_PhaseEndValidation(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
//        Projects projectsDom = DomUtil.toDom(this.getProjectVO());
//        result.addAll(projectsDom.checkIsHolding());
        return result;
    }
    
    
    
    
    /**
     * DGMS 체크리스트 완료 여부 확인
     * 
     * ACTLGEPP20784,ACTHTZPV20625,ACTPTZPP00635,ACTBTZPP10191,ACTBTZPQ10276,ACTBTZPV10260,ACTHTZDV20619,ACTHTZPP20597,ACTRAZCP20815,ACTHTZDV21319,ACTRAZPP20911,ACTLGEDV00064,ACTGWZDV20228,
     * ACTHTZPQ10089,ACTHTZPP21304,ACTDAZPV21714,ACTBTZDV20086,ACTBTZPP20040,ACTBTZPQ20127,ACTBTZPV20114,ACTLGEDV00063,ACTLGEPP00026,ACTLGEPQ00096,ACTLGEPV00081,ACTLGEPV00082,ACTPTZDV00407,
     * ACTLGEPQ00097,ACTGWZPP20213,ACTHTZDV10064,ACTHTZPP10027,ACTBTZDV10233,ACTHTZPQ10088
     */
//    public List<ActivityValidationResultVO> validateForComplete_Common_CheckDGMSCheckListComplete(){
//        Projects projectDom = new Projects(this.getProjectVO());
//        return projectDom.checkDGMSCheckListComplete(this.getPhaseCodeSystem());
//    }

    /**
     * GRIP 체크리스트 완료 여부 확인
     * 
     */
//    public List<ActivityValidationResultVO> validateForComplete_Common_CheckGripCheckListComplete(){
//        Projects projectDom = new Projects(this.getProjectVO());
//        return projectDom.checkGRIPCheckListComplete(this.getActivityVO().getOriginActivityCode());
//    }
    
    /**
     * DQMS 체크리스트 완료 여부 확인
     * 
     * ACTVAZPP21947,ACTRAZPP20778,ACTVAZDV21948,ACTVAZPV21949,ACTVAZCP21945,ACTRAZDV00345,ACTLGEPV00080,ACTDAZDV00487,ACTHTZDV20460,ACTHTZDV20448
     */
//    public List<ActivityValidationResultVO> validateForComplete_Common_CheckDQMSCheckListComplete(){
//        Projects projectDom = new Projects(this.getProjectVO());
//        return projectDom.checkDQMSCheckListComplete();
//    }
    /**
     * MP를 제외한 마지막 activity에서 프로젝트 모델서픽스가 BASIS,BASISO,PROJECT,APILOT 이면 완료 불가 ,MC본부는 PP확정회에서 체크
     * MC일 경우에만 Validation
     * @return
     */
//    public List<ActivityValidationResultVO> validateForComplete_ACTLGEPP00047_CheckModelSuffixForModelProject(){
//        Projects projectDom = new Projects(this.getProjectVO());
//        return projectDom.checkModelSuffixForModelProjectMC();
//    }
    /**
     * Activity 완료 Validation : LCD사업부는(divisionUnit (name : GLZ) DV품평회에서 module명이 TBD,Commercial 로 되어있으면 완료 불가
     * @return
     */
//    public List<ActivityValidationResultVO> validateForComplete_ACTLGEDV00067_CheckModuleNameForModuleProject(){
//        Projects projectDom = new Projects(this.getProjectVO());
//        return projectDom.checkModuleNameForModuleProject();
//    }
    /**
     * Spec Activity Complete Validation
     * @return
     */
    public List<ActivityValidationResultVO> validateForComplete_ACT0000000216_CheckSpecActivity(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        return result;
    }
    /**
     * NPT Activity는 System에서만 Complete 가능.
     * ACTLGECP00012,ACTLGEPP00041,ACTLGEDV00061,ACTLGEPV00083,ACTLGEPQ00098,
     * ACTVAZPV21949,ACTVAZCP21945,ACTVAZPP21947,ACTVAZDV21948,ACTVAZCP21925,ACT0000000008
     * @return
     */
    public List<ActivityValidationResultVO> validateForComplete_NPT_ACTIVITY_CheckIsSystemComplete(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        if(!ApplicationBizConstants.INTEGRATION_USER.equals(userId) && !ApplicationBizConstants.ADMIN_USER.equals(userId)){
            Projects projects = DomUtil.toDom(this.getProjectVO());
            
            /**
             * EEAT : 터키 합작 법인인 경우 수익성 분석 보내지 않음
             * CWZ : 정수기 사업부 수익성 분석을 보내지 않음
             */
            String devSite = "";
            if(projects.getClassName().equals("ModelDevelopmentGeneralProject")){
                ModelDevelopmentGeneralProjectVO modelProjectVo = (ModelDevelopmentGeneralProjectVO)projects.getVo();
                devSite = modelProjectVo.getDevSite1Code();
                
            } else if(projects.getClassName().equals("ModelDevelopmentGeneralProject")){
                ModuleDevelopmentProjectsVO moduleProjectVO = (ModuleDevelopmentProjectsVO)projects.getVo();
                
                devSite = moduleProjectVO.getDevSite1Code();
            }

//            if(!ApplicationBizConstants.AFFILIATE_EEAT.equals(devSite) && !ApplicationBizConstants.DIVISION_CWZ.equals(projects.getVo().getDivisionCode())){
//                if(!projects.checkCostSummaryExists(this.getPhaseCodeSystem())){
//                    result.add(new ActivityValidationResultVO(ActivityValidationResultVO.RESULTCODE.error,
//                            "NPT Profit Analysis Not Complete",
//                            "NPT Profit Analysis does not completed",
//                            "Please complete NPT Profit Analysis."));
//                }
//            }
        }
        return result;
    }
    /**
     * ACT0000000043,ACT0000000335,ACT0000000638,ACT0000000711,ACT0000000731,ACTDAZPP00176
     * 개발 사이트가 EKHQ 인 경우 project의 newPartStatus가 Unregistered일 경우 완료 불가
     * 개발 사이트가 한국이 아닌 경우 문서가 등록되어 있지 않으면 완료 불가
     * @return
     */
    public List<ActivityValidationResultVO> validateForComplete_PDMS_ACTIVITY_CheckIsUnregistered(){
        List<ActivityValidationResultVO> result = new ArrayList<ActivityValidationResultVO>();
        
        Projects projects = DomUtil.toDom(this.getProjectVO());
        
        String devSite1 = null;
        String devSite2 = null;
        
        if(projects.isKindOf(ApplicationSchemaConstants.BIZCLASS_MODELDEVELOPMENTGENERALPROJECT)){
            devSite1 = ((ModelDevelopmentGeneralProjectVO)projects.getVo()).getDevSite1Code();
            devSite2 =((ModelDevelopmentGeneralProjectVO)projects.getVo()).getDevSite2Code();
        }else if (projects.isKindOf(ApplicationSchemaConstants.BIZCLASS_MODULEDEVELOPMENTPROJECTS)){
            devSite1 = ((ModuleDevelopmentProjectsVO)projects.getVo()).getDevSite1Code();
            devSite2 = ((ModuleDevelopmentProjectsVO)projects.getVo()).getDevSite2Code();
        }

        if( "EKHQ".equals(devSite1) || "EKHQ".equals(devSite2)){
            result =  projects.validateIsUnregistered();
        } else {
            WBSItems wbsItems = DomUtil.toDom(this.getActivityVO());

            List<ProjectActivityDocumentVO> relatedObjects = wbsItems.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ACTIVITYDELIVERABLES,
                    ApplicationSchemaConstants.BIZCLASS_PROJECTACTIVITYDOCUMENT, GlobalConstants.FLAG_TYPE_TO);
            
            if(relatedObjects == null || relatedObjects.size() == 0){
                result.add(new ActivityValidationResultVO(ActivityValidationResultVO.RESULTCODE.error,
                        "Unregistered",
                        "Unregistered",
                        "Please register the activity document."));
            }
        }

        return result; 
    }
    // startProcessFor+Activity Code + '_' + Desc
    public void startProcessFor_ACTIVITY_CODE_EssentialDocument(){
        ;
    }
    // postProcessFor+Activity Code + '_' + Desc
    public void postProcessFor_ACTIVITY_CODE_EssentialDocument(){
        ;
    }
    public void postProcessForCreateIFdataForMakeAsset(){
        ;
    }
    /**
     * phase start process
     * 
     */
    private void startProcessFor_Common_PhaseStart(){
        //TODO
    }
    /************************************개발비 자산화 프로세스*****************************************/
    /**
     * 자산화 대상을 위한 ....
     * 특정 Event완료시 수행되어짐.
     */
//    public void postProcessFor_Common_MakeAsset(){
//        Projects projectDom = new Projects(this.getProjectVO());
//        projectDom.setDevelopmentCostTarget();
//    }
    /************************************프로젝트 완료 프로세스*****************************************/
    /**
     * 프로젝트 완료 처리
     * 특정 Activity 완료시
     * ACTHTZMP20488,ACTHTZMP10100,ACTLGEMP00104,ACTDAZMP21090,ACTBTZMP20139,ACTBTZMP10291,ACTVAZMP21800,ACTRAZMP21515,ACTBTZMP10369,ACTRAZMP20773,ACTDAZMP21297,ACT0000000159
     */
    public void postProcessFor_Common_CompleteProject(){
        Projects projectDom = new Projects(this.getProjectVO());
        if( !ApplicationSchemaConstants.STATE_PROJECT_COMPLETED.equals(projectDom.getVo().getStates())) {
            projectDom.completeProject();
        }
    }
    /**
     * UX Scenario Review Status Complete
     */
    public void postProcessFor_ACT0000000268_UpdateProjectActivity1CompYn(){
        this.getProjectVO().setActivity1CompYn("Y");
        new Projects(this.getProjectVO()).modifyObject();
    }
    /**
     * UX ODR Complete
     */
    public void postProcessFor_ACT0000000269_UpdateProjectActivity2CompYn(){
        this.getProjectVO().setActivity2CompYn("Y");
        new Projects(this.getProjectVO()).modifyObject();
    }
    /**
     * UX Platform Review Complete
     */
    public void postProcessFor_ACT0000000266_UpdateProjectActivity3CompYn(){
        this.getProjectVO().setActivity3CompYn("Y");
        new Projects(this.getProjectVO()).modifyObject();
    }
    /**
     * UX Similer & duplicate Complete
     */
    public void postProcessFor_ACT0000000267_UpdateProjectActivity4CompYn(){
        this.getProjectVO().setActivity4CompYn("Y");
        new Projects(this.getProjectVO()).modifyObject();
    }
    /**
     * S~B등급 DV/PV 품평회 완료시 알림
     */
    public void postProcessFor_ACTLGEDV00067_ACTLGEPV00086_SendEvaluationReviewCompleteMail(){
        WBSItems wbsItems = DomUtil.toDom(getActivityVO());
        wbsItems.sendEvaluationReviewCompleteMail();
    }
    
    /**
     * Gate Review Activity 완료시 budgetUseFlag Y로 변경
     */
//    public void postProcessFor_ACT0000000222_SetBudgetUseFlagY(){
//        Projects projects = DomUtil.toDom( getProjectVO() );
//        projects.setBudgetUseFlagY();
//    }
//    
    /**
     * phase end process
     * SCM Interface ( Plan End, Actual End 차이 Interface )
     * 디자인 프로젝트 > 모든 Event 완료 및 완료보고서 승인완료 상태일 경우 Project 완료처리.
     */
    private void postProcessFor_Common_PhaseEnd(){
        WBSPhases wbsPhases =  DomUtil.toDom( this.getActivityVO() );
        // 모든 event가 완료되었을 때 Complete 되는 Project일 경우 Complete
        Projects projects = DomUtil.toDom(this.getProjectVO());
        if( !ApplicationSchemaConstants.STATE_PROJECT_COMPLETED.equals(projects.getStates()) 
                && projects.isAllPhaseEnd(this.getScheduleVO(), wbsPhases.getNames()) && projects.isAutoCompleteWhenAllPhaseEnd()){
            projects.completeProject();
        }
//        
//        //  CP Unit 확정일자 업데이트 및 MDMS 전송
//        if("Y".equals(this.getProjectVO().getInitialProjectFlag())){                        //  Initial Project 체크
//            ProjectSchedule projectSchedule = DomUtil.toDom(this.getScheduleVO());
//            WBSPhasesVO firstWBSPhasesVO = projectSchedule.getFirstPhase(true);
//            
//            if(wbsPhases.getVo().getObid().equals(firstWBSPhasesVO.getObid())){             //  프로젝트의 첫번째 이벤트단계 체크    
//                List<ModelMasterVO> relatedModelList = projects.getRelatedModelList(true);
//                if(relatedModelList.size() > 0){
//                    ModelMaster modelMaster = DomUtil.toDom(relatedModelList.get(0));
//                    String cpUnitCode = modelMaster.getVo().getCpUnitCode();
//                    
//                    if(!NullUtil.isNone(cpUnitCode)){
//                        CPUnitVO cpUnitVo = ObjectRoot.searchObject(ApplicationSchemaConstants.BIZCLASS_CPUNIT, cpUnitCode, false);
//                        CPUnit cpUnitDom = DomUtil.toDom(cpUnitVo);
//                             
//                        cpUnitDom.interfaceConfirmDateToMDMS(this.getProjectVO(), this.getScheduleVO(), wbsPhases);
//                    }
//                }
//            }
//        }
    }
    
    public void startProcessFor_PHASE1_PhaseStart(){
        this.startProcessFor_Common_PhaseStart();
    }
    public void startProcessFor_PHASE2_PhaseStart(){
        this.startProcessFor_Common_PhaseStart();
    }
    public void startProcessFor_PHASE3_PhaseStart(){
        this.startProcessFor_Common_PhaseStart();
        //updateProjectDevCostStartYyyyddmm();
    }
    public void startProcessFor_PHASE4_PhaseStart(){
        this.startProcessFor_Common_PhaseStart();
        //updateProjectDevCostStartYyyyddmm();
    }
    public void startProcessFor_PHASE5_PhaseStart(){
        this.startProcessFor_Common_PhaseStart();
        //updateProjectDevCostStartYyyyddmm();
    }
//    public void startProcessFor_PHASE6_PhaseStart(){
//        this.startProcessFor_Common_PhaseStart();
//        // MP Start > ModelDevelopmentProjects, ModuleDevelopmentProjects일 경우 devCostEndYyyyddmm에 값이 없으면 update
//        Projects projects = DomUtil.toDom(this.getProjectVO());
//        projects.startPhase6UpdateDevCostEndYyyyddmm(); 
//    }
    public void startProcessFor_PHASE7_PhaseStart(){
        this.startProcessFor_Common_PhaseStart();
    }
    public void postProcessFor_PHASE1_PhaseEnd(){
        this.postProcessFor_Common_PhaseEnd();
    }
    public void postProcessFor_PHASE2_PhaseEnd(){
        this.postProcessFor_Common_PhaseEnd();
    }
    public void postProcessFor_PHASE3_PhaseEnd(){
        this.postProcessFor_Common_PhaseEnd();
        
        postProcessFor_PHASE3_PhaseEnd_nptInterfaceGERPFP();        
    }
    public void postProcessFor_PHASE4_PhaseEnd(){
        this.postProcessFor_Common_PhaseEnd();
    }
    public void postProcessFor_PHASE5_PhaseEnd(){
        this.postProcessFor_Common_PhaseEnd();
    }
    public void postProcessFor_PHASE6_PhaseEnd(){
        this.postProcessFor_Common_PhaseEnd();
    }
    public void postProcessFor_PHASE7_PhaseEnd(){
        this.postProcessFor_Common_PhaseEnd();
    }
    /**
     * ACTLGEDV00061,ACTLGECP00012,ACTLGEPV00083,ACTLGEPP00041,ACTLGEPQ00098
     * NPT 수익성 분석 Activity BPM Interface
     */
    public void startProcessFor_Common_nptInterfaceBPM(){
        
        /* 2018-11-06 수익성 분석 Activity 실행 시 Current Event 를 Activity의 system_event로 Update */
        Projects projectDom = DomUtil.toDom(this.getProjectVO());
        projectDom.getVo().setCurrentEventCode(getActivityVO().getPhaseNameSystem());
        projectDom.modifyObject();
        
        IfPlmDataByTriggerVO triggerVO = TriggerUtil.createDataByTriggerVO(this.getProjectVO(), 
                ApplicationBizConstants.INF_TARGET_SYSTEM_PROJECT_BPM, ApplicationBizConstants.IF_TYPE_BPM_OUT01);
        WBSItems wbsItems = DomUtil.toDom(getActivityVO());
        triggerVO.setAttribute1(wbsItems.getNptInterfaceBPMActivityOwnerMailId());
        triggerVO.setAttribute2(wbsItems.getVo().getOriginActivityCode());
        TriggerUtil.createDataByTrigger(triggerVO);
    }
   
    /**
     * ACT0000000008
     * NPT CPUnit 수익성 분석 Activity BPM Interface
     */
    public void startProcessFor_Common_nptInterfaceCPUnitBPM(){
        IfPlmDataByTriggerVO triggerVO = TriggerUtil.createDataByTriggerVO(this.getProjectVO(), 
                ApplicationBizConstants.INF_TARGET_SYSTEM_PROJECT_BPM, ApplicationBizConstants.IF_TYPE_BPM_OUT03);
        WBSItems wbsItems = DomUtil.toDom(getActivityVO());
        triggerVO.setAttribute1(wbsItems.getNptInterfaceBPMActivityOwnerMailId());
        triggerVO.setAttribute2(wbsItems.getVo().getOriginActivityCode());
        
        String cpUnit = "";
        Projects projects = DomUtil.toDom(this.getProjectVO().getObid());
        List<ProjectModelVO> relModelList = projects.getRelationship(ApplicationSchemaConstants.RELCLASS_PROJECTMODEL, GlobalConstants.FLAG_TYPE_TO);
        if( relModelList != null && relModelList.size() > 0  ){
            for(ProjectModelVO modelVO : relModelList){
                
                if(  !NullUtil.isNone(modelVO.getRepresentativeModelFlag()) && "Y".equals(modelVO.getRepresentativeModelFlag()) ){
                    ModelMaster  toModelDom = (ModelMaster) DomUtil.toDom(modelVO.getToObid());
                    
                    if( ! NullUtil.isNull(toModelDom) ){
                        cpUnit = toModelDom.getVo().getCpUnitCode();
                    }
                }
            }
        }
        
        triggerVO.setAttribute3(cpUnit + ":" + this.getProjectVO().getCurrentEventCode());
        TriggerUtil.createDataByTrigger(triggerVO);
    }

//    public void updateProjectDevCostStartYyyyddmm(){
//        // phase3 phase4 phase5 시작될 때 VC가 아니고 devCostStartYyyyddmm이 없을 경우 devCostStartYyyyddmm 업데이트 ( Model, Module )
//        Projects projects = DomUtil.toDom(this.getProjectVO());
//        projects.updateProjectDevCostStartYyyyddmm(); 
//    }
    /**
     * activity start 시 바로 완료 처리
        ACT0000000846   CP Start    
        ACT0000000847   PP Start    
        ACT0000000848   DV Start    
        ACT0000000849   PV Start    
        ACT0000000845   MP Start
        
        ATM-00000000930 (Auto) CP Event End
        ATM-00000000931 (Auto) PP Event End
        ATM-00000000932 (Auto) DV Event End
        ATM-00000000933 (Auto) PV Event End
     */
    public void startProcessFor_Common_immediatelyComplete(){
        WBSItems wbsItems = DomUtil.toDom(this.getActivityVO());
//        wbsItems.forceCompleteWBSActivityFromWBS(this.getScheduleVO(), "Auto Complete." , false);
        // 자동 완료 위치 변경 선행 Activity Complete 후로 이동
        wbsItems.getVo().setOutDataAttributeValue("autoCompleteActivity", "Y");
    }
    
    public void startProcessFor_Grip_immediatelyComplete(){
        WBSItems wbsItems = DomUtil.toDom(this.getActivityVO());
        
        String names =this.getProjectVO().getNames() +"_" + wbsItems.getVo().getOriginActivityCode();
        GripEventCheckVO eventCheckVo = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_GRIPEVENTCHECK, names, false);
        
        if( !NullUtil.isNull(eventCheckVo) ){
            wbsItems.forceCompleteWBSActivityFromWBS(this.getScheduleVO(), "Grip Auto Complete." , false);
        }
    }
    
    
    /**
     * GERP FP Model Interface
     */
    public void postProcessFor_PHASE3_PhaseEnd_nptInterfaceGERPFP(){     
        if( ApplicationBizConstants.DIVISION_CVZ.equals(this.getProjectVO().getDivisionCode()) || ApplicationBizConstants.DIVISION_CDZ.equals(this.getProjectVO().getDivisionCode())){
            IfPlmDataByTriggerVO triggerVO = TriggerUtil.createDataByTriggerVO(this.getProjectVO(), 
                    ApplicationBizConstants.INF_TARGET_SYSTEM_PROJECT_GERP, ApplicationBizConstants.INF_TARGET_SYSTEM_PROJECT_002);

            triggerVO.setAttribute1(getActivityVO().getObid());
            triggerVO.setAttribute2(null);
            triggerVO.setAttribute3("A");
            
            TriggerUtil.createDataByTrigger(triggerVO);   
        }
    }
}
