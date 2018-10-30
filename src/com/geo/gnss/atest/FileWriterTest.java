package com.geo.gnss.atest;

import java.io.IOException;

import com.geo.gnss.util.DownloadLog;

public class FileWriterTest {


	public static void main(String[] args) throws IOException {
//        String path = "C:\\Users\\geo\\Desktop\\test.txt";
//        FileWriter fw = new FileWriter(path, true);
//        String line = String.format("%s\tname\r\n",new Date().toString());
//		fw.write(line);
//		fw.close();
//		
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String dateString = sdf.format(new Date());
//        System.out.println(dateString);
		DownloadLog log = new DownloadLog("C:\\Users\\geo\\Desktop");
		log.write("geo","Download rinex 30s");
	
	}

}
