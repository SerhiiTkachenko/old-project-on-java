package ua.com.proteins.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriter {

	enum Folder{
		PRODUCT
	}
	
	boolean write(Folder folder, MultipartFile file, Integer id);
}
