package com.shf.dcs.configuration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MediaConfiguration {
	// Upload settings
	// public static final String ROOT_FOLDER = "temp";
	public static final String ALLOW_FILE_EXT = "png,jpg,jpeg,pdf,doc,docx";
	public static final String ROOT_STATIC_FOLDER = "F:/DVC/CMS/Data/UploadFiles";
	// public static final String ROOT_STATIC_FOLDER =
	// "C:/DVC/CMS/Data/UploadFiles";
	public static final long MAX_FILESIZE = FileUtils.ONE_MB * 10;
	public static final boolean useRelativeWebsitePath = false;

	String tempUploadFolderPath;
	String allowFileExt;

	public MediaConfiguration() {
	}

	public String getStaticRootFolder() {
		if (tempUploadFolderPath == null) {
			return ROOT_STATIC_FOLDER;
		} else {
			return tempUploadFolderPath;
		}
	}

	public String getPublicUploadRootFolder(HttpServletRequest request) {
		if (useRelativeWebsitePath) {
			return request.getSession().getServletContext().getRealPath("");
		} else {
			return getStaticRootFolder();
		}
	}

	public String getFilePathFromNoRoot(String fileUrl) {
		return getStaticRootFolder() + fileUrl;
	}

	public String getTempFolderPath() {
		return tempUploadFolderPath;
	}

	public void setTempFolderPath(String tempFolderPath) {
		this.tempUploadFolderPath = tempFolderPath;
	}

	public Long getMaxUploadFileSize() {
		return MAX_FILESIZE;
	}

	public String getTempUploadFolderPath() {
		return tempUploadFolderPath;
	}

	public void setTempUploadFolderPath(String tempUploadFolderPath) {
		this.tempUploadFolderPath = tempUploadFolderPath;
	}

	public String getAllowFileExt() {
		return allowFileExt;
	}

	public void setAllowFileExt(String allowFileExt) {
		this.allowFileExt = allowFileExt;
	}
}
