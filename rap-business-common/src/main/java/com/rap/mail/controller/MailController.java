/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : MailController.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 4. 29. youngmi.won Initial
 * ===========================================
 */
package com.rap.mail.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rap.mail.service.MailService;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.controller.BaseController;



/**
 * <pre>
 * Class : MailController
 * Description : 메일발송 관련 기능 구현 Controller
 * </pre>
 * 
 * @author youngmi.won
 */
@Controller
public class MailController extends BaseController {
	static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);

    @Resource(name = "mailService")
    private MailService mailService;

    /**
     * Send warning e-mail
     * @param obid
     * @param map
     * @return
     */
	@RequestMapping( "/common/mail/sendMailToApprover.do" )
    public String sendMailToApprover( String obid, ModelMap map ){
        if( !StringUtils.isEmpty( obid ) ){
            mailService.sendMailForDelayEco( obid );
        }
        return GlobalConstants.AJAX_VIEW;
    }
}
