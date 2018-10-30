package com.geo.gnss.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;

public class DllInterface {
	
	public interface ToRinexDLL extends Library{
		public ToRinexDLL instance = (ToRinexDLL)Native.loadLibrary("ToRinexDLL",ToRinexDLL.class);
		
		//boolean ParseDataToRinexFormat(String strSourceFullFileName, String strFileSaveName,int nFormatCtl,HWND hWnd);
		boolean ParseDataToRinexFormat_HTML(String strSourceFullFileName, String strFileSaveName,int nFormatCtl,String strProgressName);
		//boolean ParseDataToRinexFormat(String strSourceFullFileName, String strFileSaveName,int nFormatCtl,ByteByReference obsType,HWND hWnd);
		
		boolean ConvertDataToRinexFormat(String strSourceFullFileName, String strFileSaveName,boolean bRinex3,
				boolean bLeica, boolean bStonex, boolean bApplyIon, int iNavSys,boolean bCenter, 
				boolean bSingle,IntByReference pSuffix, double dT);
		
		boolean GetStaticHead(String strSourceFullFileName,IntByReference nYear, IntByReference nMonth, IntByReference nDay);
		//IntByReference year = new IntByReference();
		//int n = year.getValue();
		
		int AntMeasInfo(String strSourceFullFileName,DoubleByReference fOldHgt);
		//DoubleByReference height = new DoubleByReference();
		//double high = height.getValue();
		
		double CorrectAntennaHgt(String strSourceFullFileName,int nHeightType, double dOriHeight,boolean bCenter);
		
		boolean ModifyAntennaHeight(String strFileName, double dAntennaHeight);
		
		boolean ModifyFileTime(String strFileName,String strFileStart,String strFileEnd);
		
		boolean ModifyAnteHeightInfo(String strFileName,String strPointName,int nMeasureType,double dNewHeight);
	}
	
	public interface ToRinexDLL64 extends Library{
		public ToRinexDLL64 instance = (ToRinexDLL64)Native.loadLibrary("ToRinexDLL64",ToRinexDLL64.class);
		
		boolean ParseDataToRinexFormat_HTML(String strSourceFullFileName, String strFileSaveName,int nFormatCtl,String strProgressName);
		
	}
	
	public interface PostPositionDLL extends Library{
		public PostPositionDLL instance = (PostPositionDLL)Native.loadLibrary("PostPositionDLL",PostPositionDLL.class);
		
		//boolean SinglePosition(String fileName, double[] coord, HWND hwnd);
		boolean SinglePositionAndroid(String name, DoubleByReference coor1, 
				DoubleByReference coor2, DoubleByReference coor3, HWND hwnd);
		
	}
	public interface PostPositionDLL64 extends Library{
		public PostPositionDLL64 instance = (PostPositionDLL64)Native.loadLibrary("PostPositionDLL64",PostPositionDLL64.class);
		
		//boolean SinglePosition(String fileName, double[] coord, HWND hwnd);
		boolean SinglePositionAndroid(String name, DoubleByReference coor1, 
				DoubleByReference coor2, DoubleByReference coor3, HWND hwnd);
		
	}
	
	public interface VirtualRinexMakerDLL extends Library{
		public VirtualRinexMakerDLL instance = (VirtualRinexMakerDLL)Native.loadLibrary("VirtualRinexMakerDLL",VirtualRinexMakerDLL.class);
		
		int ToVirtualrinex(String latitude, String longitude, String altitude, int format,
				String date, String startTime, String endTime,int zone,
				int rinex3, int mixture, double timeInterval, int singleFre, int navSys,
				 String obsPath, String rinexPath, String xmlPath);
		
	}
	
	public interface VirtualRinexMakerDLL64 extends Library{
		public VirtualRinexMakerDLL64 instance = (VirtualRinexMakerDLL64)Native.loadLibrary("VirtualRinexMakerDLL64",VirtualRinexMakerDLL64.class);
		
		int ToVirtualrinex(String latitude, String longitude, String altitude, int format,
				String date, String startTime, String endTime,int zone,
				int rinex3, int mixture, double timeInterval, int singleFre, int navSys,
				String obsPath, String rinexPath, String xmlPath);
		
	}
	
	public interface StaticBaseLineSolution64 extends Library{
		public StaticBaseLineSolution64 instance = (StaticBaseLineSolution64)Native.loadLibrary("StaticBaseLineSolution64",StaticBaseLineSolution64.class);
		
		boolean GetStaticResult(String baseOfilename, String roveOfilename, 
				String baseNFilename, String roveNFilename,
				String baseGFilename, String roveGFilename, 
				String baseCFilename, String roveCFilename,
				String baseLFilename, String roveLFilename,
				String antFilename, String reportFilename);
	}
	
	public interface PPKDLL64 extends Library{
		public PPKDLL64 instance = (PPKDLL64)Native.loadLibrary("PPKDLL64",PPKDLL64.class);
		
		boolean PPKProcess(String baseOfilename, String roveOfilename, 
				String baseNFilename, String roveNFilename,
				String baseGFilename, String roveGFilename,
				String baseCFilename, String roveCFilename,
				String baseLFilename, String roveLFilename,
				String antFilename, String gridFilename,  String reportFilename,
				int PMode,double elev,int system,int KFMode, double ratio,
				double preciseCoordX,double preciseCoordY, double preciseCoordZ,
				int minWLSvNum,int minL1SvNum, double roverPeriod,double dRH);
	}
	
	public interface MergeDLL64 extends Library{
		public MergeDLL64 instance = (MergeDLL64)Native.loadLibrary("MergeDLL64",MergeDLL64.class);
		
		boolean MergeRawFile(String combineFilePath, String saveFilePath);
	}
	
	public interface CoordConvertLib64 extends Library{
		public CoordConvertLib64 instance = (CoordConvertLib64)Native.loadLibrary("CoordConvertLib64",CoordConvertLib64.class);
		
		public void BLHToxyh(String filePath,double sourceB, double sourceL, double sourceH,
				DoubleByReference destinationx, DoubleByReference destinationy, DoubleByReference destinationh);
		
		public void xyhToBLH(String filePath,double sourcex, double sourcey, double sourceh,
				DoubleByReference destinationB, DoubleByReference destinationL, DoubleByReference destinationH);
	}

}
