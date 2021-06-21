package com.xiilab.movetofile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Calendar;


public class MoveToImageAndLabel {
	
	public static int indeximage = 1;
	public static int indexlabel= 1;
	

	public static void main(String[] args) {
		
		String BASE_DATA_PATH = "디텍팅 출발 디렉토리 패쓰";		//가져올 디렉토리
		//String BASE_DATA_PATH = "D:/test2_in/";	//test Path
		
		getFilesInAllDir(BASE_DATA_PATH);

	}
	public static void getFilesInAllDir(String BASE_DATA_PATH) {
		
		String filename = null;
		String DATA_PATH = "도착 디렉토리 패쓰";			//붙여넣기할 디렉토리
		//String DATA_PATH = "D:/test2_out/";	//test Path
		
		
		File files = new File(BASE_DATA_PATH);
		File[] imageFileList = files.listFiles();

		
		for(int i = 0 ;i<imageFileList.length;i++) {
			File file = imageFileList[i];
			
			if(file.isDirectory()) {
				
				getFilesInAllDir(file.getPath());
				
			}else if((file.getName().endsWith(".jpg"))){
			
				filename = file.getName();
		
				try {
					copyImage(BASE_DATA_PATH,filename,DATA_PATH);

				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}	//else if 끝 
		}	//for

	}
	
	public static void copyImage(String BASE_DATA_PATH,String filename,String DATA_PATH) throws Exception {

		FileInputStream fis =null;
		FileOutputStream fos = null;
		
		Calendar c = Calendar.getInstance();
		long currentTime = c.getTimeInMillis();
		
		int formatIndex = filename.lastIndexOf(".");
		String fileNameOnly = filename.substring(0, formatIndex);
		String labelName = fileNameOnly+".txt";
		
		String fileNewNameOnly = fileNameOnly+"_"+String.valueOf(currentTime);
		
		String fileNewName=fileNewNameOnly+".jpg";
		String labelNewName =fileNewNameOnly+".txt";
		
		File fileCheck = new File(BASE_DATA_PATH+"/"+labelName);
		boolean isExist = fileCheck.exists();
		
		if(isExist) {
			if(indeximage<=2000) {
				try {
					fis = new FileInputStream(BASE_DATA_PATH+"/"+filename);	//원본 파일 
					
					if(indeximage%10<7) {
						fos = new FileOutputStream(DATA_PATH+"/images/train2021/"+fileNewName);	//복사위치 + 파일명
						
					}else {
						fos = new FileOutputStream(DATA_PATH+"/images/val2021/"+fileNewName);	//복사위치 + 파일명
				
					}
					
					FileChannel fic = fis.getChannel();
					FileChannel foc = fos.getChannel();
					
					fic.transferTo(0, fic.size(), foc);
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}finally {
					fis.close();
					fos.close();
					
					System.out.println("사진 성공 " + indeximage +" 기존 파일명 : "+filename+" 변경 파일명 : "+fileNewName );
					indeximage++;
					
					copyLabel(BASE_DATA_PATH,labelName,DATA_PATH,labelNewName);
				}
			}
		}else {
			return;
		}
		

	}

	public static void copyLabel(String BASE_DATA_PATH,String labelName,String DATA_PATH,String labelNewName) throws Exception {

		FileInputStream fis =null;
		FileOutputStream fos = null;
		if(indexlabel<=2000) {
			try {
				fis = new FileInputStream(BASE_DATA_PATH+"/"+labelName);	//원본 파일 
				
				
				if(indexlabel%10<7) {
					fos = new FileOutputStream(DATA_PATH+"/labels/train2021/"+labelNewName);	//복사위치 + 파일명
				
				}else {
					fos = new FileOutputStream(DATA_PATH+"/labels/val2021/"+labelNewName);	//복사위치 + 파일명
				
				}
				
				FileChannel fic = fis.getChannel();
				FileChannel foc = fos.getChannel();
				
				fic.transferTo(0, fic.size(), foc);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			
			}finally {
				fis.close();
				fos.close();
				
				System.out.println("라벨 성공 " + indexlabel +" 기존 파일명 : "+labelName+" 변경 파일명 : "+labelNewName );
				indexlabel++;
			}
		}
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

