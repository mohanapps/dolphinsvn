package com.svn.properties.enums;

/**
 * @author tcs_sagark
 *
 */
public enum LocalWorkSpacePathEnum {
	
	DEVELOPMENT("Development"),
	UAT("UAT"),
	MOCK("Mock"),
	PRODUCTION("Production"),
	DR("DR"),
	NEWDM_DEVELOPMENT("NewDMDevelopment");
	
	private final String  enumVal;
	
	private LocalWorkSpacePathEnum(String enumVal)
	{
		this.enumVal = enumVal;
	}

	public String getEnumVal() {
		return enumVal;
	}
	
	public static LocalWorkSpacePathEnum getEnum(String enumVal){
		LocalWorkSpacePathEnum[] localWorkSpacePaths = values();
		LocalWorkSpacePathEnum localWorkSpacePath = null;
		for(LocalWorkSpacePathEnum lwsp : localWorkSpacePaths){
			
			if(enumVal != null && lwsp.enumVal.equalsIgnoreCase(enumVal)){
				localWorkSpacePath = lwsp;
			}
		}
		return localWorkSpacePath;
	}
	
	
	

}
