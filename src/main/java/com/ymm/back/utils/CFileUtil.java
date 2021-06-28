package com.ymm.back.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;


public class CFileUtil {
	public static String uploadImage(MultipartFile file) {
		UUID uid= UUID.randomUUID();
		String savePath= System.getProperty("user.dir")+"/uploads/images";
		//String exten=FilenameUtils.getExtension(file.getOriginalFilename());
		if(!new File(savePath).exists()) {
			try {
				new File(savePath).mkdir();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//String filePath= savePath+"/"+uid+"."+exten ;
		
        try {
			//file.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uid.toString();
	}
	public static String uploadFile(MultipartFile file) {
		UUID uid= UUID.randomUUID();
		String savePath= System.getProperty("user.dir")+"/uploads/files";
		//String exten=FilenameUtils.getExtension(file.getOriginalFilename());
		if(!new File(savePath).exists()) {
			try {
				new File(savePath).mkdir();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//String filePath= savePath+"/"+uid+"."+exten ;
		
        try {
			//file.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uid.toString();
	}
}
