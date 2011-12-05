package com.svn.properties.enums;

public enum PropertieFilePathEnum {
	
	SVN_CLIENT_PROPERTIES("D:/mohan/R&D API's/SVNManager/SVNProperties/src/SvnClientProperties.properties");
	private final String enumVal;
	
	private PropertieFilePathEnum(String enumVal)
	{
		this.enumVal = enumVal;
	}

	public String getEnumVal() {
		return enumVal;
	}

}
