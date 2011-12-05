package com.common.file.enums;

public enum XMLAttributesEnum {
	SVNCLIENT_CONFIGURATION("svnclient-configuration"),
	SVNREPOSITORY("svnrespository"),
	ATT_REPOSITORY_NAME("respositoryname"),
	ATT_REPOSITORY_TYP("type"),
	ATT_URL("url"),
	ATT_USER_NAME("username"),
	ATT_PASSWORD("password"),
	ENVIRONMENT("environment"),
	ATT_ENVNAME("envname"),
	NODE_MAPPING("node-mapping"),
	ATT_NODE_NAME("nodename"),
	ATT_LOCAL_NODE_ROOT("localnoderoot"),
	ATT_REMOTE_NODE_ROOT("remotenoderoot"),
	;
	
	private String enumVal;
	
	private XMLAttributesEnum(String enumVal){
		this.enumVal = enumVal;
	}

	public String getEnumVal() {
		return enumVal;
	}
	
	public static XMLAttributesEnum getEnum(String input){
		XMLAttributesEnum[] attributesEnums = values();
		XMLAttributesEnum enumOutput = null;
		for(XMLAttributesEnum loopEnum : attributesEnums){
			if(input != null || input.equals("")){
				if(loopEnum.enumVal.equalsIgnoreCase(input)){
					enumOutput = loopEnum;
				}
			}
		}
		return enumOutput;
	}
}
