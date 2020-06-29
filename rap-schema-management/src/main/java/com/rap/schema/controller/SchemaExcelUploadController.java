package com.rap.schema.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rap.omc.api.util.general.UserManagementUtil;
import com.rap.omc.dataaccess.paging.model.PagingList;
import com.rap.omc.foundation.user.model.CommonUserSearchVO;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.foundation.user.service.FoundationUserDetailsService;
import com.rap.omc.schema.object.model.OmcSchemaUserVO;
import com.rap.schema.service.SchemaManagementService;
@RestController
public class SchemaExcelUploadController {
    static final Logger LOGGER = LoggerFactory.getLogger(SchemaExcelUploadController.class);
    
    @Autowired
    private SchemaManagementService schemaManagementService;
	@Autowired
	FoundationUserDetailsService foundationUserDetailsService;
	
	@Autowired
	UserSession userSession;

	@RequestMapping("/loadSchemaExcel")
    public void loadSchemaExcel(){
		List<OmcSchemaUserVO> list = UserManagementUtil.getCommonUserList(new CommonUserSearchVO());
        PagingList<OmcSchemaUserVO> pagingList = (PagingList<OmcSchemaUserVO>)list;
        int totalRow =  pagingList.getRows();
        int currentPage = pagingList.getCurrentPage();
		System.out.println("totalRow    : " + totalRow);
		System.out.println("currentPage : " + currentPage);
    	schemaManagementService.loadSchemaExcel();
    }
	@RequestMapping("/schemaSetup")
    public void schemaSetup(){
    	schemaManagementService.schemaSetup();
    }
//	@RequestMapping("/autoLogin")
//    public String autoLogin(HttpServletRequest request) throws Exception{
//    	UserDetails userDetails = foundationUserDetailsService.loadUserByUsername("XP3866");
//    	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword());
//    	SecurityContext securityContext = SecurityContextHolder.getContext();
//    	securityContext.setAuthentication(usernamePasswordAuthenticationToken);
//    	HttpSession session = request.getSession(true);
//    	session.setAttribute("SPRING_SECURITY_CONTEXY", securityContext);
//    	return "Auto Login Success";
//    }
}
