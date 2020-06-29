package com.rap.omc.foundation.file.model;

public class FileCopyVO {
        private String        sourceFileObid                                    ;
        private Float         copiedSizes                                       ;
        private String        filePath                                          ;
        private String        fileLocation                                      ;
        private String        sysFileName                                       ;
        
        public String getSourceFileObid(){
            return sourceFileObid;
        }
        
        public void setSourceFileObid(String sourceFileObid){
            this.sourceFileObid = sourceFileObid;
        }
        
        public Float getCopiedSizes(){
            return copiedSizes;
        }
        
        public void setCopiedSizes(Float copiedSizes){
            this.copiedSizes = copiedSizes;
        }
        
        public String getFilePath(){
            return filePath;
        }
        
        public void setFilePath(String filePath){
            this.filePath = filePath;
        }
        
        public String getFileLocation(){
            return fileLocation;
        }
        
        public void setFileLocation(String fileLocation){
            this.fileLocation = fileLocation;
        }
        
        public String getSysFileName(){
            return sysFileName;
        }
        
        public void setSysFileName(String sysFileName){
            this.sysFileName = sysFileName;
        }
        
}
