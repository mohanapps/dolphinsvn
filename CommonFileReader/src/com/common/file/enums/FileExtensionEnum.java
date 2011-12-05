package com.common.file.enums;

public enum FileExtensionEnum {
	
	TXT("txt"),
	XLS("xls"),
	XML("XML");
	
	private final String enumVal;
	
	private FileExtensionEnum(String enumVal){
		this.enumVal = enumVal;
	}

	public String getEnumVal() {
		return enumVal;
	}
	
	public static FileExtensionEnum getEnum(String enumVal){
		FileExtensionEnum retEnum=null;
		if(enumVal != null && !enumVal.equals("")){
		FileExtensionEnum[] extensionEnums = values();
			for(FileExtensionEnum extensionEnum : extensionEnums){
				if(extensionEnum.getEnumVal().equalsIgnoreCase(enumVal)){
					retEnum = extensionEnum;
				}
			}
		
		}
		return retEnum;
	}

}
