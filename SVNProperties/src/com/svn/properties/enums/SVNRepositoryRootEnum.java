package com.svn.properties.enums;

public enum SVNRepositoryRootEnum {
	SPEED_ADMIN_ROOT("SPEEDADMINROOT"),
	SPEED_DEV_ROOT("SPEEDDEVROOT");
	
	private String enumVal;
	private SVNRepositoryRootEnum(String enumVal){
		this.enumVal = enumVal;
	}
	public String getEnumVal() {
		return enumVal;
	}
}
