package com.rap.omc.util.core.fileFilter;


import java.io.File;
import java.io.FileFilter;

public class FileOnlyFilter implements FileFilter{
	private final boolean hidden;
	public FileOnlyFilter(boolean includeHidden){
		this.hidden = includeHidden;
	}
	public FileOnlyFilter(){
		this.hidden = true;
	}
	public boolean accept(File file){
		return (((file.isFile()) && (!(file.isHidden()))) ? true : (this.hidden) ? file.isFile() : false);
	}
}