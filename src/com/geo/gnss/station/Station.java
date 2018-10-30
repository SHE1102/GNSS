package com.geo.gnss.station;

public class Station {
	private String name="",id="",remark="";
	private double x=0,y=0,z=0;
	private double destinationB = 0,destinationL = 0,destinationH = 0;
	private double N1=0,E1=0,U1=0;
	private double N2=0,E2=0,U2=0;
	private double targetDistance = 0;
	private boolean workFlag;
	private String PDFLink="";
	private int gpsCount=0,glonassCount=0,beidouCount=0;
	
	public boolean isWorkFlag() {
		return workFlag;
	}
	public void setWorkFlag(boolean workFlag) {
		this.workFlag = workFlag;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setZ(double z) {
		this.z = z;
		XYZToBLH(x, y, z);
	}
	public void setN1(double n1) {
		N1 = n1;
	}
	public void setE1(double e1) {
		E1 = e1;
	}
	public void setU1(double u1) {
		U1 = u1;
	}
	public void setN2(double n2) {
		N2 = n2;
	}
	public void setE2(double e2) {
		E2 = e2;
	}
	public void setU2(double u2) {
		U2 = u2;
	}
	public int getGpsCount() {
		return gpsCount;
	}
	public void setGpsCount(int gpsCount) {
		this.gpsCount = gpsCount;
	}
	public int getGlonassCount() {
		return glonassCount;
	}
	public void setGlonessCount(int glonassCount) {
		this.glonassCount = glonassCount;
	}
	public int getBeidouCount() {
		return beidouCount;
	}
	public void setBeidouCount(int beidouCount) {
		this.beidouCount = beidouCount;
	}
	public void setTargetDistance(double targetDistance) {
		this.targetDistance = targetDistance;
	}
	public void calTargetDistance(String lat,String lon){
		double sourceB = Double.parseDouble(lat);
		double sourceL = Double.parseDouble(lon);
		
		this.targetDistance = algorithm(destinationL,destinationB,sourceL,sourceB);
	}
	
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public String getRemark() {
		
		return remark;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}
	public double getLatitude() {
		return destinationB;
	}
	public String getLatitudeText() {
		return getStrDegreeFromDecimalDegree(destinationB);
	}
	public double getLongitude() {
		return destinationL;
	}
	public String getLongitudeText() {
		return getStrDegreeFromDecimalDegree(destinationL);
	}
	public String getPDFLink() {
		return PDFLink;
	}
	public void setPDFLink(String pDFLink) {
		PDFLink = pDFLink;
	}
	public String getStrDegreeFromDecimalDegree(double angle ) {
		boolean bNegative = false;
		if (angle<1E-4 && Math.abs(angle)>1E-6)
		{
			bNegative = true;
		}

		//转化为度分秒
		double du, fen, miao;
		String tem = String.valueOf(Math.abs(angle));
		String[] temArray = tem.split("\\.");
		du = Integer.parseInt(temArray[0]);
		fen = Math.abs(angle) - du;
		
		double fenTem;
		fenTem = fen*60;
		tem = Double.toString(fenTem);
		temArray = tem.split("\\.");
		fen = Integer.parseInt(temArray[0]);
		miao = fenTem - fen;
		
		miao = miao*60;

		if (Math.abs(miao-60.0)<1E-4)
		{
			miao = 0.0;
			fen = fen+1.0;
			if (Math.abs(fen-60.0)<1E-4)
			{
				fen = 0.0;
				du += 1.0;
			}
		}

		//秒调整——等于60处理
		if (Math.abs(miao-60.0)<1E-5) {
			miao = 0;
			fen++;
		}

		//分调整——等于60处理
		if (Math.abs(fen-60)<1E-5) {
			fen = 0;
			du++;
		}
		///////////////////////////////

		String strangle;
		strangle = String.format("%03d°%02d′%03.2f″",(int)du,(int)fen,miao);
		
		if (bNegative) {
			strangle = String.format("-%s", strangle);
		}
		
		return strangle;
	}
	
	public double getHeight() {
		return destinationH;
	}
	public double getN1() {
		return N1;
	}
	public double getE1() {
		return E1;
	}
	public double getU1() {
		return U1;
	}
	public double getN2() {
		return N2;
	}
	public double getE2() {
		return E2;
	}
	public double getU2() {
		return U2;
	}
	public double getTargetDistance(){
		return targetDistance;
	}
	public String getTargetDistanceText() {
		String distanceText = "";
		double temp = targetDistance/1000;
		temp = (double)Math.round(temp*10)/10;
		if (temp > 1E-2) {
			distanceText = Double.toString(temp) + "km";
		}
		return distanceText;
	}
	
	private void XYZToBLH(double SourceX, double SourceY, double SourceZ){
		
		double da = 6378137.0;
		double df = 298.257223563;

		double e2;
		double A,F;
		double N;
		F=1.0/df;
		A=da;
		e2 = 2 * F - F * F;
		double dl=Math.atan2(SourceY, SourceX);
		destinationL=dl;

		double b1,b2,h1,h2;
		h1=Math.sqrt(Math.pow(SourceX,2)+Math.pow(SourceY,2)+Math.pow(SourceZ,2))-A;
		b1=Math.atan2((SourceZ/Math.sqrt(SourceX*SourceX+SourceY*SourceY)),(1.-e2*A/(A+h1)));
		
		if (Math.abs(SourceZ) < 1E-4)
		{
			destinationB = 0;
			destinationL = 0;
			destinationH = 0;
			return;
		}

		do
		{
			N=A/(Math.sqrt(1.-e2*Math.sin(b1)*Math.sin(b1)));
			h2=h1;b2=b1;
			h1=SourceZ/Math.sin(b1)-N*(1-e2);
			b1=Math.atan2((SourceZ/Math.sqrt(SourceX*SourceX+SourceY*SourceY)),(1.-e2*N/(N+h1)));

		}while(Math.abs(b2-b1)>Math.pow(10.0,-11)||Math.abs(h2-h1)>Math.pow(10.0,-5));

		destinationB=b1;
		N=A/(Math.sqrt(1.-e2*Math.sin(b1)*Math.sin(b1)));
		destinationH=SourceZ/Math.sin(b1)-N*(1-e2);

		destinationB = destinationB*180.0/Math.PI;
		destinationL = destinationL*180.0/Math.PI;
	}
	
	public double algorithm(double longitude1, double latitude1, double longitude2, double latitude2) {
        double Lat1 = rad(latitude1);
        double Lat2 = rad(latitude2);
        
        double a = Lat1 - Lat2;
        double b = rad(longitude1) - rad(longitude2); 

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
        		+ Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));

        s = s * 6378137.0;
        s = Math.round(s * 10000d) / 10000d;

        return s;
	 }

	 private static double rad(double d) {
	        return d * Math.PI / 180.00; 
	 }
         
}
