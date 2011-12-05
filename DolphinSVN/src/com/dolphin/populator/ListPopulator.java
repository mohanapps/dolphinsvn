package com.dolphin.populator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.ListModel;

import com.common.file.dto.SVNNodeDTO;
import com.common.file.dto.SVNRepositoryDTO;
import com.common.file.reader.CommonFileReaderFactory;
import com.dolphin.search.DolphinSVNSearcher;

public class ListPopulator {
		 public static List<String> getRelativePathList(String filePath){
			List<String> relativePath = CommonFileReaderFactory.createCommonFileReader(new File(filePath.replace('\\', '/'))).readFile().getReadLines();
			return relativePath;
		}
		 
		 public static List<String> getRelativePathFromJList(JList list){
			 ListModel listModel = list.getModel();
			 int listSize = listModel.getSize();
			 List<String> relativePathList = new ArrayList<String>();
			 for(int i=0;i<listSize;i++){
				 relativePathList.add(listModel.getElementAt(i).toString());
			 }
			 return relativePathList;
		 }
		 
		 public static List<String> getCompletePathList(JList list,SVNRepositoryDTO repositoryDTO,String env){
			 List<String> relativePath = getRelativePathFromJList(list);
			 List<String> retList = new ArrayList<String>();
			 for(String relativePathString : relativePath){
				 SVNNodeDTO nodeDTO = DolphinSVNSearcher.SearchNode(repositoryDTO,env,relativePathString);
				 String tmpCompletePath = new StringBuilder(nodeDTO.getLocalNodePath()).
				 append(relativePathString).toString();
				 retList.add(tmpCompletePath);
			 }
			 return retList;
		 }
}
