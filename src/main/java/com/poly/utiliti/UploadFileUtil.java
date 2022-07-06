package com.poly.utiliti;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadFileUtil {

	public File handleUploadFile(
			MultipartFile uploadedFile
	) {
		String folderPath = "E:\\ky_6\\java5\\demo\\demo_spring\\src\\main\\webapp\\storage";
		File myUploadFolder = new File(folderPath);
		//
//		kiểm tra xem có thư mục không, nếu không thì tạo mới
		if(!myUploadFolder.exists()) {
			myUploadFolder.mkdirs();
		}

		File savedFile = null;
		try {
			//lưu file vào thư mục đã chọn
			String uuid = UUID.randomUUID().toString();
			String fileName = uuid + "_" + uploadedFile.getOriginalFilename();
			savedFile = new File(myUploadFolder, fileName);
			uploadedFile.transferTo(savedFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return savedFile;
	}
	
}













