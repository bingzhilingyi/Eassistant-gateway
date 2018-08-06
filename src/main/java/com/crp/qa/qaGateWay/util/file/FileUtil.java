package com.crp.qa.qaGateWay.util.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crp.qa.qaGateWay.controller.QaBaseController;

public class FileUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	private static volatile FileUtil fileUtil = null;
	
	private FileUtil() {
		
	}
	
	public static FileUtil getInstance() {
		if(fileUtil==null) {
			synchronized (FileUtil.class) {
				if(fileUtil==null) {
					fileUtil = new FileUtil();
				}
			}
		}
		return fileUtil;
	}

	public static void uploadFile(byte[] file, String filePath, String fileName) throws IOException { 
		File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.flush();
        
		try {
	        out.write(file);      
		}catch(IOException e) {
			LOGGER.error(e.getMessage(),e);
		}finally {
			try {
				if(out!=null) {
					out.close();
				}
			}catch(IOException e) {
				LOGGER.error(e.getMessage(),e);
			}
		}
        
    }
}
