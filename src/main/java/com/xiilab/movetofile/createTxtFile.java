package com.xiilab.movetofile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class createTxtFile {

	//coco dataset -> train val txt 파일 만듬 
	
	public static void main(String[] args) {

		String TXT_PATH = "라벨셋 만들 경로 겸 이미지 명 읽을 경로";
		
		try {
			makeTrainTxt(TXT_PATH);
			makeValTxt(TXT_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println("text 파일 insert 성공");
		}
		
	}
	
	public static void makeTrainTxt(String TXT_PATH) throws IOException {
		
		try {
			FileOutputStream fos = new FileOutputStream(TXT_PATH+"train2021.txt");
			//String TXT_PATH = "D:/test2_out/image/";
			
			String trainPath = TXT_PATH+"images/train2021/";
			System.out.println("텍스트 파일 경로 확인 trainPath : "+trainPath);
			File files = new File(trainPath);
			File[] trainImageList = files.listFiles();
			
			String strText = "";
			
			for(int i = 0 ; i<trainImageList.length;i++) {
				File file = trainImageList[i];
				
				String trainFileName = file.getName();
				String trainTxt = "./images/train2021/"+trainFileName+"\n";
				strText += trainTxt;
			}
			
			try {
				fos.write(strText.getBytes());
				//text파일 입력
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				fos.close();
			}
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		}
	}
	
	
	public static void makeValTxt(String TXT_PATH) throws IOException {
		
		try {
			FileOutputStream fos = new FileOutputStream(TXT_PATH+"val2021.txt");
			//String TXT_PATH = "D:/test2_out/image/";
			
			String valPath = TXT_PATH+"images/val2021/";
			System.out.println("텍스트 파일 경로 확인 valPath : "+valPath);
			File files = new File(valPath);
			File[] valImageList = files.listFiles();
			
			String strText = "";
			
			for(int i = 0 ; i<valImageList.length;i++) {
				File file = valImageList[i];
				
				String valFileName = file.getName();
				String valTxt = "./images/val2021/"+valFileName+"\n";
				strText += valTxt;
			}
			
			try {
				fos.write(strText.getBytes());
				//text파일 입력
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				fos.close();
			}
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
