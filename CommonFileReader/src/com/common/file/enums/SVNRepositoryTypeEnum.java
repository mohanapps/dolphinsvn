package com.common.file.enums;

public enum SVNRepositoryTypeEnum {
	
	SOURCE("source"),
	DESTINATION("destination");
	
	private String enumVal;
	
	private SVNRepositoryTypeEnum(String enumVal){
		this.enumVal = enumVal;
	}

	public String getEnumVal() {
		return enumVal;
	}
	
	public static SVNRepositoryTypeEnum getEnum(String input){
		
		SVNRepositoryTypeEnum[] enums = values();
		SVNRepositoryTypeEnum enumOutput = null;
		for(SVNRepositoryTypeEnum loopEnum : enums){
			if(input != null || input.equals("")){
				if(loopEnum.enumVal.equalsIgnoreCase(input)){
					enumOutput = loopEnum;
				}
			}
		}
		return enumOutput;
	}

}
