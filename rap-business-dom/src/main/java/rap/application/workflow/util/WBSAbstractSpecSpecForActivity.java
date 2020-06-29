package rap.application.workflow.util;

import java.util.HashMap;
import java.util.Map;

import rap.api.object.project.model.ProjectScheduleVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSItemsVO;

public class WBSAbstractSpecSpecForActivity {
	
	public WBSAbstractSpecSpecForActivity(ProjectsVO projectVO, ProjectScheduleVO scheduleVO, WBSItemsVO activityVO,
            String phaseCode, String phaseTitles, String phaseCodeSystem, String phaseTitlesSystem) {
        super();
        this.projectVO = projectVO;
        this.scheduleVO = scheduleVO;
        this.activityVO = activityVO;
        this.phaseCode = phaseCode;
        this.phaseTitles = phaseTitles;
        this.phaseCodeSystem = phaseCodeSystem;
        this.phaseTitlesSystem = phaseTitlesSystem;
	}
	
	public static final Map<String,String> methodDescMap = new HashMap<String,String>();
    static {
      methodDescMap.put("validateForStart_PHASE1_PhaseStart"                        , "[PHASE1] Start Check Method");
      methodDescMap.put("validateForStart_PHASE2_PhaseStart"                        , "[PHASE2] Start Check Method");
      methodDescMap.put("validateForStart_PHASE3_PhaseStart"                        , "[PHASE3] Start Check Method");
      methodDescMap.put("validateForStart_PHASE4_PhaseStart"                        , "[PHASE4] Start Check Method");
      methodDescMap.put("validateForStart_PHASE5_PhaseStart"                        , "[PHASE5] Start Check Method");
      methodDescMap.put("validateForStart_PHASE6_PhaseStart"                        , "[PHASE6] Start Check Method");
      methodDescMap.put("validateForStart_PHASE7_PhaseStart"                        , "[PHASE7] Start Check Method");
      
      methodDescMap.put("startProcessFor_PHASE1_PhaseStart"                         , "[PHASE1] Start Execution Method");
      methodDescMap.put("startProcessFor_PHASE2_PhaseStart"                         , "[PHASE2] Start Execution Method");
      methodDescMap.put("startProcessFor_PHASE3_PhaseStart"                         , "[PHASE3] Start Execution Method");
      methodDescMap.put("startProcessFor_PHASE4_PhaseStart"                         , "[PHASE4] Start Execution Method");
      methodDescMap.put("startProcessFor_PHASE5_PhaseStart"                         , "[PHASE5] Start Execution Method");
      methodDescMap.put("startProcessFor_PHASE6_PhaseStart"                         , "[PHASE6] Start Execution Method");
      methodDescMap.put("startProcessFor_PHASE7_PhaseStart"                         , "[PHASE7] Start Execution Method");
      
      methodDescMap.put("validateForComplete_PHASE1_PhaseEnd"                       , "[PHASE1] Completion Check Method");
      methodDescMap.put("validateForComplete_PHASE2_PhaseEnd"                       , "[PHASE2] Completion Check Method");
      methodDescMap.put("validateForComplete_PHASE3_PhaseEnd"                       , "[PHASE3] Completion Check Method");
      methodDescMap.put("validateForComplete_PHASE4_PhaseEnd"                       , "[PHASE4] Completion Check Method");
      methodDescMap.put("validateForComplete_PHASE5_PhaseEnd"                       , "[PHASE5] Completion Check Method");
      methodDescMap.put("validateForComplete_PHASE6_PhaseEnd"                       , "[PHASE6] Completion Check Method");
      methodDescMap.put("validateForComplete_PHASE7_PhaseEnd"                       , "[PHASE7] Completion Check Method");
      
      methodDescMap.put("postProcessFor_PHASE1_PhaseEnd"                            , "[PHASE1] Completion Execution Method");
      methodDescMap.put("postProcessFor_PHASE2_PhaseEnd"                            , "[PHASE2] Completion Execution Method");
      methodDescMap.put("postProcessFor_PHASE3_PhaseEnd"                            , "[PHASE3] Completion Execution Method");
      methodDescMap.put("postProcessFor_PHASE4_PhaseEnd"                            , "[PHASE4] Completion Execution Method");
      methodDescMap.put("postProcessFor_PHASE5_PhaseEnd"                            , "[PHASE5] Completion Execution Method");
      methodDescMap.put("postProcessFor_PHASE6_PhaseEnd"                            , "[PHASE6] Completion Execution Method");
      methodDescMap.put("postProcessFor_PHASE7_PhaseEnd"                            , "[PHASE7] Completion Execution Method");
      
      methodDescMap.put("validateForComplete_Common_CheckDGMSCheckListComplete"               , "[Common] Check DGMS CheckList");
      methodDescMap.put("validateForComplete_Common_CheckGripCheckListComplete"               , "[Common] Check GRIP CheckList");
      methodDescMap.put("validateForComplete_Common_CheckDQMSCheckListComplete"               , "[Common] Check DQMS CheckList");
      methodDescMap.put("validateForComplete_ACTLGEPP00047_CheckModelSuffixForModelProject"   , "[MC] Check Model Suffix for Model Project");
      methodDescMap.put("validateForComplete_ACTLGEDV00067_CheckModuleNameForModuleProject"   , "[LCD] Check Module Name for Module Project");
      methodDescMap.put("validateForComplete_ACT0000000216_CheckSpecActivity"                 , "[Common] Check Spec Activity");
      methodDescMap.put("validateForComplete_NPT_ACTIVITY_CheckIsSystemComplete"              , "[NPT] Check is System Complete");
      methodDescMap.put("validateForComplete_PDMS_ACTIVITY_CheckIsUnregistered"             , "[Part DMS] Check is Registered");

      methodDescMap.put("postProcessFor_Common_MakeAsset"                                     , "[Common] Making Asset Process");
      methodDescMap.put("postProcessFor_Common_CompleteProject"                               , "[Common] Complete Project");
      methodDescMap.put("postProcessFor_ACT0000000268_UpdateProjectActivity1CompYn"           , "[UX] Scenario Review Status Complete");
      methodDescMap.put("postProcessFor_ACT0000000269_UpdateProjectActivity2CompYn"           , "[UX] ODR Complete");
      methodDescMap.put("postProcessFor_ACT0000000266_UpdateProjectActivity3CompYn"           , "[UX] Platform Review Complete");
      methodDescMap.put("postProcessFor_ACT0000000267_UpdateProjectActivity4CompYn"           , "[UX] Similer & duplicate Complete");
      methodDescMap.put("postProcessFor_ACTLGEDV00067_ACTLGEPV00086_SendEvaluationReviewCompleteMail" , "[Common] Send Evaluation Review Complete Mail");
      //methodDescMap.put("postProcessFor_ACT0000000222_SetBudgetUseFlagY" , "[Gate Review] Set budgetUseFlag to Y");
      
      methodDescMap.put("startProcessFor_Common_nptInterfaceBPM"                               , "[NPT] Interface to BPM");
      methodDescMap.put("startProcessFor_Common_nptInterfaceCPUnitBPM"                         , "[NPT CPUnit] Interface to BPM");
      methodDescMap.put("startProcessFor_Common_immediatelyComplete"                           , "[Common] Immediately complete");
      methodDescMap.put("startProcessFor_Grip_immediatelyComplete"                             , "[GRIP] Immediately complete");

  }
    private ProjectsVO projectVO;
    private ProjectScheduleVO scheduleVO;
    private WBSItemsVO activityVO;
    String  phaseCode;
    String  phaseTitles;
    String  phaseCodeSystem;
    String  phaseTitlesSystem;
    public Map<String, String> getMethodDescMap(){
        return methodDescMap;
    }
	public ProjectsVO getProjectVO() {
		return projectVO;
	}
	public void setProjectVO(ProjectsVO projectVO) {
		this.projectVO = projectVO;
	}
	public ProjectScheduleVO getScheduleVO() {
		return scheduleVO;
	}
	public void setScheduleVO(ProjectScheduleVO scheduleVO) {
		this.scheduleVO = scheduleVO;
	}
	public WBSItemsVO getActivityVO() {
		return activityVO;
	}
	public void setActivityVO(WBSItemsVO activityVO) {
		this.activityVO = activityVO;
	}
	public String getPhaseCode() {
		return phaseCode;
	}
	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}
	public String getPhaseTitles() {
		return phaseTitles;
	}
	public void setPhaseTitles(String phaseTitles) {
		this.phaseTitles = phaseTitles;
	}
	public String getPhaseCodeSystem() {
		return phaseCodeSystem;
	}
	public void setPhaseCodeSystem(String phaseCodeSystem) {
		this.phaseCodeSystem = phaseCodeSystem;
	}
	public String getPhaseTitlesSystem() {
		return phaseTitlesSystem;
	}
	public void setPhaseTitlesSystem(String phaseTitlesSystem) {
		this.phaseTitlesSystem = phaseTitlesSystem;
	}
	public static Map<String, String> getMethoddescmap() {
		return methodDescMap;
	}
    
}
