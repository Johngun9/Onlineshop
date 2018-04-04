package net.johngun.onlineshop.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	
	private static final String ABS_PATH = "D:\\projects\\shoping\\Onlineshop\\online-shop\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH ="";
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		
		//Maing Sure all dierctories exists
		// else Create them
		if(!new File(ABS_PATH).exists()){
			
			new File( ABS_PATH).mkdirs();
			
		}
		
		if(!new File(REAL_PATH).exists()){
			new File(REAL_PATH).mkdirs();
		}
		
		try{
			// Uploading to Server
			file.transferTo(new File(REAL_PATH + code +".jpg"));
			
			//Project directory upload
			file.transferTo(new File(ABS_PATH +code+ ".jpg"));
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
