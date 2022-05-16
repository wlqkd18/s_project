package com.care.root.auto;

public class XSSClass {
	
	public static String[] replaceParamter(String[] strRep) {
		if(strRep.length > 0) {
			for(int i = 0; i < strRep.length; i++) {
				strRep[i] = strRep[i].replace("<", "&lt;");
				strRep[i] = strRep[i].replace(">", "&gt;");
				strRep[i] = strRep[i].replace("\"", "&quot;");
			}
		}
		
		return strRep;
	}
}
