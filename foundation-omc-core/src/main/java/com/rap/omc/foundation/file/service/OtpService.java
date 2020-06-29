/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ECOService.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 15. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.file.service;

import com.rap.omc.foundation.file.model.OtpVO;

/**
 * <pre>
 * Class : ECOService
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public interface OtpService {
    public void createOtp(OtpVO input);
    public OtpVO checkAndGetOtp(OtpVO input);
    public OtpVO getOtp(OtpVO input);
}
