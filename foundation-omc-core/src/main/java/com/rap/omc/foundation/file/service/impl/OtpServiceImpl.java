/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ECOServiceImpl.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 15. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.file.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.foundation.file.model.OtpVO;
import com.rap.omc.foundation.file.service.OtpService;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : ECOServiceImpl
 * Description : TODO
 * </pre>
 *
 * @author hyeyoung.park
 */
@Service("otpService")
public class OtpServiceImpl implements OtpService {

    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;

    private SecureRandom nonceGenerator = null;
    private int numBitsInNonce = 48;
    private static final Logger LOGGER = LoggerFactory.getLogger(OtpServiceImpl.class);
    private static final long OTP_DURATION =  6 * 60 * ( 60 * 1000 );   // 6 * 5 (분)  = 30분

    public OtpServiceImpl() {
        try {
          nonceGenerator = SecureRandom.getInstance("SHA1PRNG");
          nonceGenerator.setSeed((new Date()).getTime());
      } catch (NoSuchAlgorithmException e) {
          LOGGER.error(e.getMessage());
          throw new FoundationException("omc.error.otp.NoSuchAlgorithmException", e);
      }
    }
    /**
     *
     * @return
     * @see lge.gpdm.checkin.service.OtpService#createOtp()
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createOtp(OtpVO input){
        input.setNonce( getNonce() );
        schemaDao.insert("file.insertOtp", input);
    }
    @Override
    public OtpVO checkAndGetOtp(OtpVO input){
        OtpVO vo = this.getOtp(input);
        if(NullUtil.isNull(vo))   {
            LOGGER.debug("★★★★★★★★★★OTP VO(" + input.getNonce() + ") is null★★★★★★★★★★");
            throw new FoundationException("OTP Invalid(NULL) : " + input.getNonce());
        }
        makeInvalid(input.getNonce());
        return vo;
    }
    @Override
    public OtpVO getOtp(OtpVO input){
        return schemaDao.select("file.getOtp", input);
    }
    private String getNonce() {
        String nonce = null;
        byte[] bytes = new byte[numBitsInNonce];
        nonceGenerator.nextBytes(bytes);
        try {
            nonce = new String(Base64.encodeBase64(bytes), "US-ASCII").replaceAll("&", "a").replaceAll("[+]", "1");
        } catch (UnsupportedEncodingException e1) {
            throw new RuntimeException("Nonce not created",e1);
        }
        return nonce;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void makeInvalid(String nounce){
        OtpVO vo = new OtpVO();
        vo.setNonce(nounce);
        vo.setUsedCount(vo.getUsedCount() + 1 );
        vo.setUsedTime(new Date());
        schemaDao.update("file.updateOtp", vo);
    }
}
