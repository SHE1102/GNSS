package com.geo.gnss.thread;

import java.io.File;

public class TimerClearFileThread extends Thread {

	private String appPath;
	
	public TimerClearFileThread(String appPath) {
		this.appPath = appPath;
	}
	
	@Override
	public void run() {
		super.run();
		
		System.out.println("Clear thread start.");
		
		while(true) {
			
			if (isInterrupted()) {
				break;
			}
			
			try {
				Thread.sleep(1000*60*60*24);
			} catch (InterruptedException e) {
				//e.printStackTrace();
				break;
			}
			
			System.out.println("Start clear dir and file.");
			clearConvertDir();
			clearVirtualDir();
			clearProgressDir();
			
		}
		
		System.out.println("Clear thread exit.");
	}
	
	protected void clearConvertDir() {
		String convertDir = this.appPath + File.separator + "Convert";
		clearDir(convertDir);
		System.out.println("Clear convert dir and file success.");
	}
	protected void clearVirtualDir() {
		String virtualDir = this.appPath + File.separator + "Virtual";
		clearDir(virtualDir);
		System.out.println("Clear virtual dir and file success.");
	}
	protected void clearProgressDir() {
		String progressDir = this.appPath + File.separator + "Progress";
		clearDir(progressDir);
		System.out.println("Clear progress dir and file success.");
	}
	
	protected void  clearDir(String clearDir) {
		long currentMillis = System.currentTimeMillis();
		
        File fileDir = new File(clearDir);
		
		if (!fileDir.exists()) {
			return;
		}
		
		File[] fileDirList = fileDir.listFiles();
		
		for(File tempFileDir : fileDirList) {
			long createTime = tempFileDir.lastModified();
			
			//delete the dir and files create on 1 day ago
			if ((currentMillis - createTime) >= 1000*60*60*24) {
				if (tempFileDir.isDirectory()) {
					DeleteDir(tempFileDir);
				} else if(tempFileDir.isFile()) {
					tempFileDir.delete();
				}
			}
		}
	}
	
	protected void DeleteDir(File fileDir) {
		File[] fileList = fileDir.listFiles();
		
		for(File tempFile : fileList) {
			if (tempFile.isDirectory()) {
				DeleteDir(tempFile);
			} else if(tempFile.isFile()) {
				tempFile.delete();
			}
		}
		
		fileDir.delete();
	}
	
}
