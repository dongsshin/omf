package com.rap.config.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.rap.config.PropertiesUtil;
import com.rap.omc.framework.exception.FoundationBaseException;
import com.rap.omc.framework.file.upload.PdmFileUpload;
import com.rap.omc.framework.file.upload.policy.FileUploadPolicy;
import com.rap.omc.framework.file.upload.policy.PdmFileUploadPolicy;

@Configuration
public class FileUploadConfig {
	@Autowired
    ResourceLoader resourceLoader;
	@Bean
    public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		Resource uploadTempDir = resourceLoader.getResource(PropertiesUtil.getString("system.file.multipartResolver.uploadTempDir"));
		try {
			multipartResolver.setUploadTempDir(uploadTempDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		multipartResolver.setMaxInMemorySize(20000);
		multipartResolver.setDefaultEncoding("UTF-8");
		return multipartResolver;
		
    }
	@Bean
    public PdmFileUploadPolicy globalPolicy() {
		PdmFileUploadPolicy globalPolicy = new PdmFileUploadPolicy();
		Resource uploadTempDir = resourceLoader.getResource(PropertiesUtil.getString("system.file.global.uploadTargetDir"));
		try {
			globalPolicy.setUploadTargetDir(uploadTempDir);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FoundationBaseException(e);
		}
		
		globalPolicy.setAllowPattern("*.fme;*.psd;*.ai;*.tif;*.hwp;*.gif;*.dat;*.img;*.eds;*.ftp;*.gbr;*.ibs;*.igs;*.pdbb;*.ppsx;*.sdm;*.aa;*.ai;*.catdrawing;*.catpart;*.catproduct;*.dwg;*.dxf;*.prt;*.step;*.stp;*.x_t;*.msg;*.dbc;*.bin;*.dll;*.esf;*.hex;*.lgu;*.mbn;*.md5;*.mot;*.oft;*.rom;*.rpd;*.s19;*.vs3;*.bi_;*.docm;*.csv;*.doc;*.docx;*.pdf;*.ppt;*.pptx;*.txt;*.xls;*.xlsx;*.7z;*.alz;*.egg;*.gz;*.rar;*.zip;*.partial;*.jpg;*.png;*.cdd;*.xlsm;*.rtf;*.html;*.eml");
		globalPolicy.setDenyPattern("*");
		globalPolicy.setDirDepth("4");
		globalPolicy.setMaxFileSize("50GB");
		globalPolicy.setZeroSizeAllow(false);
		return globalPolicy;
    }
	@Bean
	public PdmFileUpload fileUpload() {
		PdmFileUpload pdmFileUpload = new PdmFileUpload();
		pdmFileUpload.setGlobalPolicy(globalPolicy());
		Map<String, FileUploadPolicy> optionalPolicyMap = new HashMap<String,FileUploadPolicy>();
		optionalPolicyMap.put("policy1", policy1());
		optionalPolicyMap.put("policy2", policy2());
		pdmFileUpload.setOptionalPolicyMap(optionalPolicyMap);
		return pdmFileUpload;
	}
	@Bean
    public PdmFileUploadPolicy policy1() {
		PdmFileUploadPolicy globalPolicy = new PdmFileUploadPolicy();
		Resource uploadTempDir = resourceLoader.getResource(PropertiesUtil.getString("system.file.policy1.uploadTargetDir"));
		try {
			globalPolicy.setUploadTargetDir(uploadTempDir);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FoundationBaseException(e);
		}
		globalPolicy.setAllowPattern("*.fme;*.psd;*.ai;*.tif;*.hwp;*.gif;*.dat;*.img;*.eds;*.ftp;*.gbr;*.ibs;*.igs;*.pdbb;*.ppsx;*.sdm;*.aa;*.ai;*.catdrawing;*.catpart;*.catproduct;*.dwg;*.dxf;*.prt;*.step;*.stp;*.x_t;*.msg;*.dbc;*.bin;*.dll;*.esf;*.hex;*.lgu;*.mbn;*.md5;*.mot;*.oft;*.rom;*.rpd;*.s19;*.vs3;*.bi_;*.docm;*.csv;*.doc;*.docx;*.pdf;*.ppt;*.pptx;*.txt;*.xls;*.xlsx;*.7z;*.alz;*.egg;*.gz;*.rar;*.zip;*.partial;*.jpg;*.png;*.cdd;*.xlsm;*.rtf;*.html;*.eml");
		globalPolicy.setDenyPattern("*");
		globalPolicy.setDirDepth("4");
		globalPolicy.setMaxFileSize("50GB");
		globalPolicy.setZeroSizeAllow(false);
		return globalPolicy;
    }
	@Bean
    public PdmFileUploadPolicy policy2() {
		PdmFileUploadPolicy globalPolicy = new PdmFileUploadPolicy();
		Resource uploadTempDir = resourceLoader.getResource(PropertiesUtil.getString("system.file.policy2.uploadTargetDir"));
		try {
			globalPolicy.setUploadTargetDir(uploadTempDir);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FoundationBaseException(e);
		}
		globalPolicy.setAllowPattern("*.fme;*.psd;*.ai;*.tif;*.hwp;*.gif;*.dat;*.img;*.eds;*.ftp;*.gbr;*.ibs;*.igs;*.pdbb;*.ppsx;*.sdm;*.aa;*.ai;*.catdrawing;*.catpart;*.catproduct;*.dwg;*.dxf;*.prt;*.step;*.stp;*.x_t;*.msg;*.dbc;*.bin;*.dll;*.esf;*.hex;*.lgu;*.mbn;*.md5;*.mot;*.oft;*.rom;*.rpd;*.s19;*.vs3;*.bi_;*.docm;*.csv;*.doc;*.docx;*.pdf;*.ppt;*.pptx;*.txt;*.xls;*.xlsx;*.7z;*.alz;*.egg;*.gz;*.rar;*.zip;*.partial;*.jpg;*.png;*.cdd;*.xlsm;*.rtf;*.html;*.eml");
		globalPolicy.setDenyPattern("*");
		globalPolicy.setDirDepth("4");
		globalPolicy.setMaxFileSize("50GB");
		globalPolicy.setZeroSizeAllow(false);
		return globalPolicy;
    }
	
}