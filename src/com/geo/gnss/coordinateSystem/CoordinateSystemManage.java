package com.geo.gnss.coordinateSystem;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.geo.gnss.dao.CoordinateSystem;
import com.geo.gnss.dao.CorrectPar;
import com.geo.gnss.dao.EllipsoidPar;
import com.geo.gnss.dao.FourPar;
import com.geo.gnss.dao.HeightFittingPar;
import com.geo.gnss.dao.ProjectPar;
import com.geo.gnss.dao.SevenPar;
import com.geo.gnss.dao.VerticalPar;

public class CoordinateSystemManage {

	protected String filePath;
	
	public CoordinateSystemManage(String filePath) {
		this.filePath = filePath;
	}
	
	public CoordinateSystem readCoordinateSystem(){
		CoordinateSystem coordinateSystem = new CoordinateSystem();
		
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File(filePath));
			
			Element root = document.getRootElement();
			Element systemTag = root.element("CoordinateSystem");
			
			List<Element> elementList = systemTag.elements();
			
			String tagName = "",content="";
			for(Element tagElement : elementList){
				tagName = tagElement.getName();
				
				if("CoordinateSystem_EllipsoidParameter".equals(tagName)){
					EllipsoidPar ellipsoidPar = new EllipsoidPar();
					
					content = tagElement.elementTextTrim("Name");
					ellipsoidPar.setName(content);
					content = tagElement.elementTextTrim("Axis");
					ellipsoidPar.setAxis(Double.parseDouble(content));
					content = tagElement.elementTextTrim("ReciprocalofFlatRate");
					ellipsoidPar.setFlatRate(Double.parseDouble(content));
					
					coordinateSystem.ellipsoidPar = ellipsoidPar;
				} else if("CoordinateSystem_ProjectParameter".equals(tagName)){
					ProjectPar projectPar = new ProjectPar();
					
					content = tagElement.elementTextTrim("Type");
					projectPar.setType(Integer.parseInt(content));
					content = tagElement.elementTextTrim("CentralMeridian");
					projectPar.setCentralMeridian(Double.parseDouble(content));
					content = tagElement.elementTextTrim("Tx");
					projectPar.setTx(Double.parseDouble(content));
					content = tagElement.elementTextTrim("Ty");
					projectPar.setTy(Double.parseDouble(content));
					content = tagElement.elementTextTrim("TK");
					projectPar.setTk(Double.parseDouble(content));
					content = tagElement.elementTextTrim("ProjectionHeight");
					projectPar.setProjectionHeight(Double.parseDouble(content));
					content = tagElement.elementTextTrim("ReferenceLatitude");
					projectPar.setReferenceLatitude(Double.parseDouble(content));
					
					coordinateSystem.projectPar = projectPar;
				} else if("CoordinateSystem_SevenParameter".equals(tagName)){
					SevenPar sevenPar = new SevenPar();
					
					content = tagElement.elementTextTrim("Use");
					sevenPar.setUse("1".equals(content));
					content = tagElement.elementTextTrim("Mode");
					sevenPar.setMode(Integer.parseInt(content));
					content = tagElement.elementTextTrim("DX");
					sevenPar.setDx(Double.parseDouble(content));
					content = tagElement.elementTextTrim("DY");
					sevenPar.setDy(Double.parseDouble(content));
					content = tagElement.elementTextTrim("DZ");
					sevenPar.setDz(Double.parseDouble(content));
					content = tagElement.elementTextTrim("RX");
					sevenPar.setRx(Double.parseDouble(content));
					content = tagElement.elementTextTrim("RY");
					sevenPar.setRy(Double.parseDouble(content));
					content = tagElement.elementTextTrim("RZ");
					sevenPar.setRz(Double.parseDouble(content));
					content = tagElement.elementTextTrim("K");
					sevenPar.setDk(Double.parseDouble(content));
					
					coordinateSystem.sevenPar = sevenPar;
				} else if("CoordinateSystem_FourParameter".equals(tagName)){
					FourPar fourPar = new FourPar();
					
					content = tagElement.elementTextTrim("Use");
					fourPar.setUse("1".equals(content));
					content = tagElement.elementTextTrim("Cx");
					fourPar.setCx(Double.parseDouble(content));
					content = tagElement.elementTextTrim("Cy");
					fourPar.setCy(Double.parseDouble(content));
					content = tagElement.elementTextTrim("Ca");
					fourPar.setCa(Double.parseDouble(content));
					content = tagElement.elementTextTrim("Ck");
					fourPar.setCk(Double.parseDouble(content));
					content = tagElement.elementTextTrim("Orgx");
					fourPar.setOrgx(Double.parseDouble(content));
					content = tagElement.elementTextTrim("Orgy");
					fourPar.setOrgy(Double.parseDouble(content));
					
					coordinateSystem.fourPar = fourPar;
					
				} else if("CoordinateSystem_HeightFittingParameter".equals(tagName)){
					HeightFittingPar heightFittingPar = new HeightFittingPar();
					
					content = tagElement.elementTextTrim("Use");
					heightFittingPar.setUse("1".equals(content));
					content = tagElement.elementTextTrim("a0");
					heightFittingPar.setA0(Double.parseDouble(content));
					content = tagElement.elementTextTrim("a1");
					heightFittingPar.setA1(Double.parseDouble(content));
					content = tagElement.elementTextTrim("a2");
					heightFittingPar.setA2(Double.parseDouble(content));
					content = tagElement.elementTextTrim("a3");
					heightFittingPar.setA3(Double.parseDouble(content));
					content = tagElement.elementTextTrim("a4");
					heightFittingPar.setA4(Double.parseDouble(content));
					content = tagElement.elementTextTrim("a5");
					heightFittingPar.setA5(Double.parseDouble(content));
					content = tagElement.elementTextTrim("x0");
					heightFittingPar.setX0(Double.parseDouble(content));
					content = tagElement.elementTextTrim("y0");
					heightFittingPar.setY0(Double.parseDouble(content));
					
					coordinateSystem.heightFittingPar = heightFittingPar;
				} else if("CoordinateSystem_CorrectParameter".equals(tagName)){
					CorrectPar correctPar = new CorrectPar();
					
					content = tagElement.elementTextTrim("Use");
					correctPar.setUse("1".equals(content));
					content = tagElement.elementTextTrim("Dx");
					correctPar.setDx(Double.parseDouble(content));
					content = tagElement.elementTextTrim("Dy");
					correctPar.setDy(Double.parseDouble(content));
					content = tagElement.elementTextTrim("Dh");
					correctPar.setDh(Double.parseDouble(content));
					
					coordinateSystem.correctPar = correctPar;
				} else if("VerticalParameter".equals(tagName)){
					VerticalPar verticalPar = new VerticalPar();
					
					content = tagElement.elementTextTrim("Use");
					verticalPar.setUse("1".equals(content));
					content = tagElement.elementTextTrim("Orgx");
					verticalPar.setOrgx(Double.parseDouble(content));
					content = tagElement.elementTextTrim("Orgy");
					verticalPar.setOrgy(Double.parseDouble(content));
					content = tagElement.elementTextTrim("NorthSlope");
					verticalPar.setNorthSlope(Double.parseDouble(content));
					content = tagElement.elementTextTrim("EastSlope");
					verticalPar.setEastSlope(Double.parseDouble(content));
					
					coordinateSystem.verticalPar = verticalPar;
				}
			}
			
		} catch (DocumentException e) {
			//e.printStackTrace();
		}
		return coordinateSystem;
	}
	
	public String getJson(){
		CoordinateSystem coordinateSystem = readCoordinateSystem();
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		
		sb.append("\"EllipsoidPar\":");
		sb.append("{");
		sb.append("\"Name\":\"");
		sb.append(coordinateSystem.ellipsoidPar.getName());
		sb.append("\",\"Axis\":");
		sb.append(coordinateSystem.ellipsoidPar.getAxis());
		sb.append(",\"FaltRate\":");
		sb.append(coordinateSystem.ellipsoidPar.getFlatRate());
		sb.append("}");
		
		sb.append(",");
		
		sb.append("\"ProjectPar\":");
		sb.append("{");
		sb.append("\"Type\":\"");
		sb.append(coordinateSystem.projectPar.getType());
		sb.append("\",\"CenterMeridian\":");
		sb.append(coordinateSystem.projectPar.getCentralMeridian());
		sb.append(",\"Tx\":");
		sb.append(coordinateSystem.projectPar.getTx());
		sb.append(",\"Ty\":");
		sb.append(coordinateSystem.projectPar.getTy());
		sb.append(",\"Tk\":");
		sb.append(coordinateSystem.projectPar.getTk());
		sb.append(",\"ProjectHeight\":");
		sb.append(coordinateSystem.projectPar.getProjectionHeight());
		sb.append(",\"ReferenceLatitude\":");
		sb.append(coordinateSystem.projectPar.getReferenceLatitude());
		sb.append("}");
		
		sb.append(",");
		
		sb.append("\"SevenPar\":");
		sb.append("{");
		sb.append("\"Use\":\"");
		sb.append(coordinateSystem.sevenPar.isUse());
		sb.append("\",\"Mode\":\"");
		sb.append(coordinateSystem.sevenPar.getMode());
		sb.append("\",\"Dx\":");
		sb.append(coordinateSystem.sevenPar.getDx());
		sb.append(",\"Dy\":");
		sb.append(coordinateSystem.sevenPar.getDy());
		sb.append(",\"Dz\":");
		sb.append(coordinateSystem.sevenPar.getDz());
		sb.append(",\"Rx\":");
		sb.append(coordinateSystem.sevenPar.getRx());
		sb.append(",\"Ry\":");
		sb.append(coordinateSystem.sevenPar.getRy());
		sb.append(",\"Rz\":");
		sb.append(coordinateSystem.sevenPar.getRz());
		sb.append(",\"Dk\":");
		sb.append(coordinateSystem.sevenPar.getDk());
		sb.append("}");
		
		sb.append(",");
		
		sb.append("\"FourPar\":");
		sb.append("{");
		sb.append("\"Use\":\"");
		sb.append(coordinateSystem.fourPar.isUse());
		sb.append("\",\"Cx\":");
		sb.append(coordinateSystem.fourPar.getCx());
		sb.append(",\"Cy\":");
		sb.append(coordinateSystem.fourPar.getCy());
		sb.append(",\"Ca\":");
		sb.append(coordinateSystem.fourPar.getCa());
		sb.append(",\"Ck\":");
		sb.append(coordinateSystem.fourPar.getCk());
		sb.append(",\"Orgx\":");
		sb.append(coordinateSystem.fourPar.getOrgx());
		sb.append(",\"Orgy\":");
		sb.append(coordinateSystem.fourPar.getOrgy());
		sb.append("}");
		
		sb.append(",");
		
		sb.append("\"HeightFittingPar\":");
		sb.append("{");
		sb.append("\"Use\":\"");
		sb.append(coordinateSystem.heightFittingPar.isUse());
		sb.append("\",\"A0\":");
		sb.append(coordinateSystem.heightFittingPar.getA0());
		sb.append(",\"A1\":");
		sb.append(coordinateSystem.heightFittingPar.getA1());
		sb.append(",\"A2\":");
		sb.append(coordinateSystem.heightFittingPar.getA2());
		sb.append(",\"A3\":");
		sb.append(coordinateSystem.heightFittingPar.getA3());
		sb.append(",\"A4\":");
		sb.append(coordinateSystem.heightFittingPar.getA4());
		sb.append(",\"A5\":");
		sb.append(coordinateSystem.heightFittingPar.getA5());
		sb.append(",\"X0\":");
		sb.append(coordinateSystem.heightFittingPar.getX0());
		sb.append(",\"Y0\":");
		sb.append(coordinateSystem.heightFittingPar.getY0());
		sb.append("}");
		
		sb.append(",");
		
		sb.append("\"VerticalPar\":");
		sb.append("{");
		sb.append("\"Use\":\"");
		sb.append(coordinateSystem.verticalPar.isUse());
		sb.append("\",\"NorthSlope\":");
		sb.append(coordinateSystem.verticalPar.getNorthSlope());
		sb.append(",\"EastSlope\":");
		sb.append(coordinateSystem.verticalPar.getEastSlope());
		sb.append(",\"Orgx\":");
		sb.append(coordinateSystem.verticalPar.getOrgx());
		sb.append(",\"Orgy\":");
		sb.append(coordinateSystem.verticalPar.getOrgy());
		sb.append("}");
		
		sb.append(",");
		
		sb.append("\"CorrectPar\":");
		sb.append("{");
		sb.append("\"Use\":\"");
		sb.append(coordinateSystem.correctPar.isUse());
		sb.append("\",\"Dx\":");
		sb.append(coordinateSystem.correctPar.getDx());
		sb.append(",\"Dy\":");
		sb.append(coordinateSystem.correctPar.getDy());
		sb.append(",\"Dh\":");
		sb.append(coordinateSystem.correctPar.getDh());
		sb.append("}");
		
		sb.append("}");
		
		//System.out.println(sb.toString());
		return sb.toString();
	}

}
