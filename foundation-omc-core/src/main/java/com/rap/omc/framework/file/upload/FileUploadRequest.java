package com.rap.omc.framework.file.upload;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadRequest {
	
	private List<String> uniqueIdList;
	private List<MultipartFile> fileDataList;

	public List<String> getUniqueIdList() {
		return uniqueIdList;
	}

	public void setUniqueIdList(List<String> uniqueIdList) {
		this.uniqueIdList = uniqueIdList;
	}
	public List<MultipartFile> getFileDataList() {
		return fileDataList;
	}

	public void setFileDataList(List<MultipartFile> fileDataList) {
		this.fileDataList = fileDataList;
	}

	public String toString(){
	    StringBuffer sb = new StringBuffer();
	    sb.append("UniqueId : ").append(uniqueIdList.toString()).append("\n");
	    return sb.toString();
	}
}
