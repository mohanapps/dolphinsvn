package com.dolphin.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.file.dto.SVNEnvironmentDTO;
import com.common.file.dto.SVNNodeDTO;
import com.common.file.dto.SVNRepositoryDTO;
import com.svn.client.creator.SVNSOCreator;

public class DolphinSVNSearcher {
	
	public static Map<String,Object> svnGlobalMap  = new HashMap<String, Object>(); 
	/*
	 * key = repositoryName
	 */
	public static SVNRepositoryDTO searchSVNRepository(String repositoryName){
		
		SVNRepositoryDTO retval = null;
		if(svnGlobalMap.containsKey(repositoryName)){
			retval = (SVNRepositoryDTO)svnGlobalMap.get(repositoryName);
		}else{
			List<SVNRepositoryDTO> repoList = SVNSOCreator.createSVNRepositoryDTOList();
			
			for(SVNRepositoryDTO repositoryDTO : repoList){
				if(repositoryDTO.getRepositoryName().equals(repositoryName)){
					retval = repositoryDTO;
				}
			}
			svnGlobalMap.put(repositoryName,retval);
		}
		return retval;
	}
	/*
	 * key = <repoName><EnvName><NodeName>
	 */
	
	public static SVNNodeDTO SearchNode(SVNRepositoryDTO repositoryDTO,String env,String relativePath){
		String moduleName = relativePath.substring(relativePath.indexOf('/')+1,relativePath.indexOf('/',1));
		String key = new StringBuilder(repositoryDTO.getRepositoryName()).
		append(env).
		append(env).append(moduleName).toString();
		SVNNodeDTO retNodeDTO = null;
		if(svnGlobalMap.containsKey(env)){
			retNodeDTO = (SVNNodeDTO)svnGlobalMap.get(key);
		}else{
			List<SVNEnvironmentDTO> listEnv = repositoryDTO.getEnvironmentList();
			for(SVNEnvironmentDTO environmentDTO : listEnv){
				if(env.equals(environmentDTO.getName())){
					List<SVNNodeDTO> listNode = environmentDTO.getNodeList();
					for(SVNNodeDTO nodeDTO : listNode){
						if(moduleName.equals(nodeDTO.getNodeName())){
							retNodeDTO = nodeDTO;
							break;
						}
					}
					break;
				}
			}
			svnGlobalMap.put(key,retNodeDTO);
		}
		return retNodeDTO;
				
	}

}
