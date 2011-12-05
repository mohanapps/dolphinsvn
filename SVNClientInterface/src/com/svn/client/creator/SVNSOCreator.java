package com.svn.client.creator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;

import com.common.file.dto.SVNEnvironmentDTO;
import com.common.file.dto.SVNRepositoryDTO;
import com.common.file.reader.CommonFileReaderFactory;
import com.common.file.reader.ICommonFileReader;
import com.svn.client.admin.ISVNAdminCommands;
import com.svn.client.admin.SVNAdminCommands;
import com.svn.properties.enums.PropertieFilePathEnum;
import com.svn.properties.enums.SVNRepositoryRootEnum;
import com.svn.properties.manager.PropertiesReader;

public class SVNSOCreator {
	
	public static ISVNAdminCommands adminCommand;
	public static Map<String,SVNRepository> repositoryPool;
	public static List<SVNRepositoryDTO> repoList;
	public static List<SVNEnvironmentDTO> envList;
	
	public static ISVNAdminCommands createISVNAdminCommand(){
		if(adminCommand == null){
			adminCommand = new SVNAdminCommands();
		}
		return adminCommand;
	}
	public static SVNRepository createSVNRepository(SVNRepositoryRootEnum repositoryRootEnum,ISVNAuthenticationManager authenticationManager){
		SVNRepository repository = null;
		String key = PropertiesReader.getProperty(PropertieFilePathEnum.SVN_CLIENT_PROPERTIES.getEnumVal(),repositoryRootEnum.getEnumVal());
		if(repositoryPool == null){
			repositoryPool = new HashMap<String, SVNRepository>();
		}
		if(repositoryPool.containsKey(key)){
			repository = repositoryPool.get(key);
		}else{
			
			try {
				SVNURL svnurl = SVNURL.parseURIDecoded(key);
				repository = SVNRepositoryFactory.create( svnurl);
				repositoryPool.put(key,repository);
			} catch (SVNException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return repository;
	}
	
	public static List<SVNRepositoryDTO> createSVNRepositoryDTOList(){
		if(repoList == null){	
			File inputFile =new File("C:/Projects/DolphinSVN/SVNProperties/src/SVNConfig.xml");
			ICommonFileReader commonFileReader = CommonFileReaderFactory.createCommonFileReader(inputFile);
			repoList = commonFileReader.readFile().getRepoList();
		}
		return repoList;
	}
	
	

}
