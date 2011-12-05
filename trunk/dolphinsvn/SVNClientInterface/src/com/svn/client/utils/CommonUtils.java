package com.svn.client.utils;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {
	
	public static String getCompleteFilePath(String startPath,String relativePath){
		return new StringBuilder(startPath)
		.append(relativePath)
		.toString();
	}
	
	public static String getPathWithOutFileName(String path)
	{
		return path.substring(0,path.lastIndexOf('/'));
	}

	public static List<String> getCompletePathList(List<String> relativePathList,String startPath)
	{
		List<String> retList = new ArrayList<String>();
		for(String relativePath : relativePathList){
			String filePath= getCompleteFilePath(startPath,relativePath);
			retList.add(filePath);
		}
		return retList;
	}
	
	public static List<String> replaceJavaSource(List<String> inputList){
		List<String> retList = new ArrayList<String>();
		for(int i=0;i< inputList.size();i++){
			String path = inputList.get(i);
			path = path.replaceAll("/JavaSource/", "/src/");
			retList.add(path);
		}
		System.out.println(retList);
		return retList;
	}
	 
	
	
}
