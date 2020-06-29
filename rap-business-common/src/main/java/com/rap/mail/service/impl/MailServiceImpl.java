/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : MailServiceImpl.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 5. 7. youngmi.won Initial
 * ===========================================
 */
package com.rap.mail.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rap.mail.model.MailVO;
import com.rap.mail.service.MailService;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.service.IdGenerateService;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.mail.OmcMailSender;
import com.rap.omc.framework.mail.OmcMailSenderFactory;
import com.rap.omc.util.NullUtil;

import rap.api.object.common.dom.MailSendLog;
import rap.api.object.common.model.MailSendLogVO;
import rap.api.object.organization.model.UsersVO;
import rap.application.constants.ApplicationBizConstants;
import rap.application.constants.ApplicationSchemaConstants;



/**
 *
 * <pre>
 * Class : MailServiceImpl
 * Description : TODO
 * </pre>
 *
 * @author youngmi.won
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

    static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private UserSession userSession;

    @Resource(name = "mailSenderFactory")
    private OmcMailSenderFactory mailSenderFactory;
    
    @Resource(name = "idGenerateService")
    private IdGenerateService idGenerateService;

    /**
     * 메일 발송 : MailSendLog 생성 미포함
     * 
     * @param mailVO
     * @see lgcns.rnd.application.mail.service.MailService#sendMail(lgcns.rnd.application.mail.model.MailVO)
     */
    @Override
    public void sendMail( MailVO mailVO ) {
        OmcMailSender mailSender = mailSenderFactory.createMailSender("mailPolicy1");
        
        // From User 정보가 있을 경우만 설정 (없을 경우 시스템 담당자:Administrator default 설정됨)
        if( mailVO.getFromUserVO() != null ){
            // From User 상태가 Active 일 경우에만 설정하도록 변경 2015.12.23 : youngmi.won
            UsersVO fromUserVO = mailVO.getFromUserVO();
            if( ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE.equals(fromUserVO.getStates()) && !NullUtil.isNone(fromUserVO.getEmailAddress())){
                mailSender.setFromMailAddress(fromUserVO.getEmailAddress(), fromUserVO.getDescriptions());
            }
        }
        
        //참조자 추가 2018.01.03 jangsu1.kim
        if(!NullUtil.isNone(mailVO.getCcEmailList())){
            
            int size = mailVO.getCcEmailList().size();
            
            List<String> mailList = new ArrayList<String>();
            for( int inx = 0; inx < size; inx++ ){
                if(!NullUtil.isNone(mailVO.getCcEmailList().get(inx))){
                    mailList.add(mailVO.getCcEmailList().get(inx));
                }
            }
            
            String ccList[] = new String[mailList.size()];
            for( int inx = 0; inx < ccList.length; inx++ ){
                ccList[inx] = mailList.get(inx);
            }
            mailSender.setCcMailAddress(ccList);
        }
        
        
        int size = mailVO.getToEmailList().size();
        
        List<String> toMailList = new ArrayList<String>();
        for( int inx = 0; inx < size; inx++ ){
            if(!NullUtil.isNone(mailVO.getToEmailList().get(inx))){
                toMailList.add(mailVO.getToEmailList().get(inx));
            }
        }
        
        String toList[] = new String[toMailList.size()];
        for( int inx = 0; inx < toList.length; inx++ ){
            toList[inx] = toMailList.get(inx);
        }
        mailSender.setToMailAddress( toList );
        mailSender.setSubject( mailVO.getSubject() );

        Map<String,Object> templateData = new HashMap<String,Object>();
        templateData.put( "contents", mailVO.getMailContents() );

        // context-mail.xml
        // 1) mailTemplateMap 추가
        // 2) bean 추가
        mailSender.setHtmlTemplate( "commonMailTemplate", templateData );
        mailSender.send();
    }

    /**
     * 
     * @param mailVO
     * @param mailType
     * @param obid
     * @see lgcns.rnd.application.mail.service.MailService#sendMailByUser(lgcns.rnd.application.mail.model.MailVO, java.lang.String, java.lang.String)
     */
    @Override
    public void sendMailByUser( MailVO mailVO, String mailType, String obid ){
        this.sendMailIncludeLog( mailVO, mailType, ApplicationBizConstants.MAIL_SEND_TYPE_MANUAL, obid );
    }

    /**
     * 
     * @param mailVO
     * @param mailType
     * @param obid
     * @see lgcns.rnd.application.mail.service.MailService#sendMailBySystem(lgcns.rnd.application.mail.model.MailVO, java.lang.String, java.lang.String)
     */
    @Override
    public void sendMailBySystem(MailVO mailVO, String mailType, String obid){
        this.sendMailIncludeLog( mailVO, mailType, ApplicationBizConstants.MAIL_SEND_TYPE_SYSTEM, obid );
    }

    /**
     * 메일 발송 : MailSendLog 생성 포함
     * 
     * @param mailVO
     * @param mailType : 메일발송을 구분할 수 있는 구분 문자열
     * @param sendType : System(시스템에서 자동으로 발송되는 메일) or Manual(사용자가 기능을 통해 발송한 메일)
     * @param obid
     * @see lgcns.rnd.application.mail.service.MailService#sendMailIncludeLog(lgcns.rnd.application.mail.model.MailVO, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void sendMailIncludeLog( MailVO mailVO, String mailType, String sendType, String obid ){
        this.sendMail( mailVO );
        
        // Create Log
        if( mailVO != null && mailVO.getToEmailList() != null && mailVO.getToEmailList().size() > 0 ){
            List<String> toEmailList = mailVO.getToEmailList();
            BusinessObjectRootVO boVo = (BusinessObjectRootVO)DomUtil.toDom( obid ).getVo();
            for( int inx = 0; inx < toEmailList.size(); inx++ ){
                this.txnCreateMailSendLog( mailType, sendType, toEmailList.get(inx), boVo.getClassName(), obid, boVo.getNames() );
            }
        }
    }

    /**
     * 수신한 메일에서 'Send warning e-mail' 버튼 클릭 시 메일 재발송 기능
     * @param inboxTaskObid
     * @see lgcns.rnd.application.mail.service.MailService#sendMailForDelayEco(java.lang.String)
     */
    @Override
    public void sendMailForDelayEco( String inboxTaskObid ) {
        if( userSession != null && !StringUtils.isEmpty(userSession.getUserId()) ){
//            // Select 설정
//            StringBuffer selectPattern = new StringBuffer();
//            StringUtil.constructSelectPattern(selectPattern, "@W2O.[fromClass] targetClassName");
//            StringUtil.constructSelectPattern(selectPattern, "@W2O.[fromObid] targetObid");
//            StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[creator],1) creatorName");
//            StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[creator],5) creatorEmail");
//            StringUtil.constructSelectPattern(selectPattern, "@USR.[titles] approverName");
//            StringUtil.constructSelectPattern(selectPattern, "@USR.[emailAddress] approverEmail");
//            StringUtil.constructSelectPattern(selectPattern, "\"getObjectInfo\"(@W2O.[fromObid],'names,states,titles,requestedApprovalDate') objInfo");
//            StringUtil.constructSelectPattern(selectPattern, "SortBy@this.[created]");
//            
//            // From 설정
//            StringBuffer fromPattern = new StringBuffer();
//            fromPattern.append("<this>ThisConnectedWithTo<[WorkflowProjectTask]@I2P>+");
//            fromPattern.append("<[WorkflowProjectTask]@I2P>FromConnectedWithThis<[Users]@USR>+");
//            fromPattern.append("<this>ThisConnectedWithTo<[WorkflowRouteTask]@I2R>+");
//            fromPattern.append("<[WorkflowRouteTask]@I2R>FromConnectedWithThis<[WorkflowRoute]@WR>+");
//            fromPattern.append("<[WorkflowRoute]@WR>ThisConnectedWithTo<[WorkflowObjectRoute]@W2O>+");
//            
//            // Where 조건 설정
//            StringBuffer wherePattern = new StringBuffer();
//            StringBuffer paramPattern = new StringBuffer();
//            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[routeInstructions]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationBizConstants.INSTRUCTION_TYPE_APPROVAL); // RouteInstruction = Approval
//            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[approvalStatus]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationBizConstants.APPROVAL_STATUS_NONE);  // 승인되지 않은 건
//            StringUtil.constructWherePattern(wherePattern, paramPattern, "(omcGetLocalDate-omcConvertUtcToLocal(@this.[created]))", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, "1");    // 요청일이 현재시간 기준보다 24시간 이전인 건
//            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, inboxTaskObid);
//            
//            List<WorkflowInboxTaskVO> result = ObjectRoot.searchObjects(
//                    ApplicationSchemaConstants.BIZCLASS_WORKFLOWINBOXTASK,
//                    GlobalConstants.FLAG_TYPE_ALL,
//                    GlobalConstants.FLAG_TYPE_ALL,
//                    GlobalConstants.FLAG_TYPE_ALL,
//                    GlobalConstants.FLAG_TYPE_ALL,
//                    GlobalConstants.FLAG_TYPE_ALL,
//                    GlobalConstants.FLAG_TYPE_ALL,
//                    GlobalConstants.FLAG_TYPE_ALL,
//                    GlobalConstants.FLAG_TYPE_ALL,
//                    GlobalConstants.FLAG_TYPE_ALL,
//                    false,
//                    false,
//                    false,
//                    false,
//                    selectPattern.toString(),
//                    fromPattern.toString(),
//                    wherePattern.toString(),
//                    paramPattern.toString(),
//                    true,
//                    0
//            );
//
//            WorkflowInboxTaskVO inboxTaskVO = null;
//            MailVO mailVO = null;
//            String targetNames = "";
//            String reqAppDateStr = "";
//            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH");
//            Date reqAppDate = null;
//            Date currDate = new Date(System.currentTimeMillis());
//            long diff = 0;
//            long remainTime = 0;
//            long delayTime = 0;
//            
//            if( result != null && result.size() == 1 ){
//                inboxTaskVO = result.get(0);
//                HashMap<String, Object> outData = inboxTaskVO.getOutData();
//                String objSplitInfo[] = StringUtil.split( outData.get("objInfo").toString(), "^");
//                targetNames = objSplitInfo[0];
//                reqAppDateStr = StringUtils.isEmpty(objSplitInfo[3]) ? "" : objSplitInfo[3].split(" ")[0];
//                if( !StringUtils.isEmpty(objSplitInfo[3]) ){
//                    try {
//                        reqAppDate = formatter.parse(objSplitInfo[3]);
//                        diff = reqAppDate.getTime() - currDate.getTime();
//                        remainTime = diff / (60 * 60 * 1000);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
//                diff = currDate.getTime() - inboxTaskVO.getCreated().getTime();
//                delayTime = diff / (60 * 60 * 1000);
//                
//                outData.put("names", targetNames);
//                outData.put("states", objSplitInfo[1]);
//                outData.put("titles", objSplitInfo[2]);
//                outData.put("reqAppDate", reqAppDateStr);
//                outData.put("remainTime", remainTime);
//                outData.put("delayTime", delayTime);
//                
//                mailVO = mailAlertService.generateMailInfoForVC( inboxTaskVO, false );
//                sendMail( mailVO );     // 메일발송
//                
//                // 메일 발송이력 저장
//                if( mailVO != null && mailVO.getToEmailList() != null && mailVO.getToEmailList().size() > 0 ){
//                    for( int inx = 0; inx < mailVO.getToEmailList().size(); inx++ ){
//                        txnCreateMailSendLog(
//                                ApplicationBizConstants.MAIL_TYPE_NAME_ECO_DELAY,
//                                ApplicationBizConstants.MAIL_SEND_TYPE_MANUAL,
//                                mailVO.getToEmailList().get(inx),
//                                inboxTaskVO.getOutData().get( "targetClassName" ).toString(),
//                                inboxTaskVO.getOutData().get( "targetObid" ).toString(),
//                                targetNames
//                        );
//                    }
//                }
//            }
//            else{
//                // ymwon error 발생정보 전달 필요 ( commonMailTemplate.html > sendMail : showResult )
//                throw new ApplicationException( "plm.common.error.nodata", new Object[] { inboxTaskObid } );
//            }
        }
        else{
            // ymwon error 발생정보 전달 필요 ( commonMailTemplate.html > sendMail : showResult )
            throw new ApplicationException( "plm.common.error.nosession" );
        }
    }

    /**
     * 메일 발송이력 저장
     * @param mailTypeName
     * @param mailSendType
     * @param toUserEmailAddress
     * @param targetClassName
     * @param targetObid
     * @param targetNames
     * @see lgcns.rnd.application.mail.service.MailService#txnCreateMailSendLog(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void txnCreateMailSendLog( String mailTypeName, String mailSendType, String toUserEmailAddress, String targetClassName, String targetObid, String targetNames ) {
        String titles = "[" + mailTypeName + "] " + targetClassName + " : " + targetNames;
        if( titles != null && titles.length() > 250 ){
            titles = titles.substring(0, 250) + "...";
        }
        MailSendLogVO logVO = new MailSendLogVO();
        logVO.setClassName( ApplicationSchemaConstants.BIZCLASS_MAILSENDLOG );
        logVO.setNames( idGenerateService.generateUniqueName(ApplicationSchemaConstants.BIZCLASS_MAILSENDLOG) );
        logVO.setTitles( titles );
        logVO.setLifeCycle( ApplicationSchemaConstants.LIFECYCLE_WITHOUT_STATE );
        logVO.setStates( ApplicationSchemaConstants.STATE_WITHOUT_STATE_EXISTS );
        logVO.setMailTypeName( mailTypeName );
        logVO.setMailSendType( mailSendType );
        logVO.setUserEmailAddress( toUserEmailAddress );
        logVO.setTargetClassName( targetClassName );
        logVO.setTargetObid( targetObid );
        logVO.setTargetNames( targetNames );
        
        MailSendLog createDom = (MailSendLog)DomUtil.toDom( logVO );
        createDom.createObject();
    }

    /**
     * Mail Template에서 Mail Contents 치환된 최종 HTML 문자열 반환
     * @param mailVO
     * @return
     * @see lgcns.rnd.application.mail.service.MailService#getMailContents(lgcns.rnd.application.mail.model.MailVO)
     */
    @Override
    public String getMailContents( MailVO mailVO ) {
        OmcMailSender mailSender = mailSenderFactory.createMailSender("mailPolicy1");
        
        Map<String,Object> templateData = new HashMap<String,Object>();
        templateData.put( "contents", mailVO.getMailContents() );
        mailSender.setHtmlTemplate( "commonMailTemplate", templateData );
        
        return mailSender.getStrTempleteResult();
    }

    /**
     * 모바일/EP 결재 Detail 내용 Generation
     * @param detailContents
     * @return
     * @see lgcns.rnd.application.mail.service.MailService#getMobileApprovalDetail(java.lang.String)
     */
    @Override
    public String getMobileApprovalDetail( String detailContents ) {
        OmcMailSender mailSender = mailSenderFactory.createMailSender("mailPolicy1");
        
        Map<String,Object> templateData = new HashMap<String,Object>();
        templateData.put( "contents", detailContents );
        
        mailSender.setHtmlTemplate( "mobileApprovalDetailTemplate", templateData );
        
        return mailSender.getStrTempleteResult();
    }
}
