package com.geo.gnss.util;

import com.geo.gnss.dao.ConvertParDao;

public class CalConvertPar {

	public CalConvertPar() {
		
	}
	
	public int calDefineConvertPar(){
		ConvertParDao convertPar = new ConvertParDao();
		convertPar.setRinexVersion(1);
		convertPar.setMixTure(false);
		convertPar.setTimeInterval(0);
		convertPar.setOutPutPar1(true);
		convertPar.setOutPutPar2(true);
		convertPar.setOutPutPar3(true);
		convertPar.setOutPutPar4(false);
		convertPar.setSatelliteSystem_GPS(true);
		convertPar.setSatelliteSystem_GLO(true);
		convertPar.setSatelliteSystem_BeiDou(true);
		convertPar.setSatelliteSystem_Galileo(true);
		convertPar.setSatelliteSystem_QZSS(false);
		convertPar.setSatelliteSystem_SBAS(false);
		convertPar.setFrequencyPoint(1);
		
		int nNavSys = 0;
		
		if (convertPar.isSatelliteSystem_GPS())
		{
			nNavSys |= 0x1;
		}
		if (convertPar.isSatelliteSystem_GLO())
		{
			nNavSys |= 0x2;
		}
		if (convertPar.isSatelliteSystem_BeiDou())
		{
			nNavSys |= 0x4;
		}
		if (convertPar.isSatelliteSystem_SBAS())
		{
			nNavSys |= 0x8;
		}
		if (convertPar.isSatelliteSystem_QZSS())
		{
			nNavSys |= 0x10;
		}
		
		if (convertPar.isSatelliteSystem_Galileo())
		{
			nNavSys |= 0x20;
		}
		if (convertPar.getFrequencyPoint() == 0)
		{
			nNavSys |= (1 << 8);
		}
		if (convertPar.isOutPutPar2())
		{
			nNavSys |= (1 << 9);
		}
		if (convertPar.isOutPutPar1())
		{
			nNavSys |= (1 << 10);
		}
		if (convertPar.isOutPutPar3())
		{
			nNavSys |= (1 << 12);
		}

		if (convertPar.isOutPutPar4())
		{
			nNavSys |= (1 << 13);
		}
		
		int nSel =  convertPar.getTimeInterval();
		nNavSys |= (nSel<<16);
		nSel =  convertPar.getRinexVersion();
		nNavSys |= (nSel<<24);
		
		if (nSel==1 || nSel==2 || nSel==4)
		{
			if (convertPar.isMixTure())
			{
				nNavSys |= (1 << 11); 
			}
		}
		
		return nNavSys;
	}

}
