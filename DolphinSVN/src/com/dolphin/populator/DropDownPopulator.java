package com.dolphin.populator;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.common.file.dto.SVNEnvironmentDTO;
import com.common.file.dto.SVNRepositoryDTO;
import com.svn.client.creator.SVNSOCreator;

public class DropDownPopulator {
		public static List<String> getEnvList(String repository){
			List<SVNRepositoryDTO> repoList = SVNSOCreator.createSVNRepositoryDTOList();
			List<SVNEnvironmentDTO> envList = null;
			List<String> envVect = new ArrayList<String>();
			for(SVNRepositoryDTO repositoryDTO : repoList){
				if(repository.equals(repositoryDTO.getRepositoryName())){
					envList = repositoryDTO.getEnvironmentList();
					for(SVNEnvironmentDTO environmentDTO : envList){
						envVect.add(environmentDTO.getName());
					}
				}
			}
			System.out.println(envVect);
			return envVect;
		}
		
		public static Vector<String> getRepositoryList(){
			List<SVNRepositoryDTO> repoList = SVNSOCreator.createSVNRepositoryDTOList();
			Vector<String> vector = new Vector<String>();
			for(SVNRepositoryDTO repositoryDTO : repoList){
				vector.add(repositoryDTO.getRepositoryName());
			}
			return vector;
		}
}
