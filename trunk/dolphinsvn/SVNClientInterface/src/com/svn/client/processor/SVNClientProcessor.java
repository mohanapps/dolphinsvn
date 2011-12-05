package com.svn.client.processor;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;

import com.common.file.reader.CommonFileReaderFactory;
import com.svn.client.authentication.SVNClientAuthenticationWizard;
import com.svn.client.creator.SVNSOCreator;
import com.svn.client.dto.SVNAuthCredentialDTO;
import com.svn.client.utils.CommonUtils;
import com.svn.client.utils.FileUtility;
import com.svn.client.vo.SVNBaseVo;
import com.svn.client.vo.SVNCommitVo;
import com.svn.client.vo.SVNCopyFilesVo;
import com.svn.client.vo.SVNUpdateVo;
import com.svn.properties.constants.SVNManagerConstants;
import com.svn.properties.enums.LocalWorkSpacePathEnum;
import com.svn.properties.enums.PropertieFilePathEnum;
import com.svn.properties.log.SVNManagerLogger;
import com.svn.properties.manager.PropertiesReader;

public class SVNClientProcessor {
	
	Logger logger = Logger.getLogger(SVNClientProcessor.class);
	
	public SVNCommitInfo doCommitProcessor(ISVNAuthenticationManager authenticationManager,SVNCommitVo commitVo){
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering doCommitProcessor method","authenticationManager",authenticationManager,"SVNCommitVo",commitVo);
		ISVNOptions options = SVNClientAuthenticationWizard.getSVNOptions();
		SVNCommitClient commitClient = new SVNCommitClient(authenticationManager,options);
		SVNCommitInfo  commitInfo = SVNSOCreator.createISVNAdminCommand().doCommit(commitClient, commitVo);
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Exiting doCommitProcessor method","SVNCommitInfo",commitInfo);
		return commitInfo;
	}
	
	public long[] doUpdateProcessor(ISVNAuthenticationManager authenticationManager,SVNUpdateVo updateVo){
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering doCommit method","authenticationManager",authenticationManager,"SVNUpdateVo",updateVo);
		ISVNOptions options = SVNClientAuthenticationWizard.getSVNOptions();
		SVNUpdateClient client = new SVNUpdateClient(authenticationManager,options);
		long afterRevision[] = SVNSOCreator.createISVNAdminCommand().doUpdate(client, updateVo);
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Exiting doCommit method","Revision after update",afterRevision);
		return afterRevision;
	}
	
	
	public boolean[] copyFiles(SVNCopyFilesVo copyFilesVo){
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering copyFiles method","SVNCopyFilesVo",copyFilesVo);
		String localSourceRepoPath = PropertiesReader.getProperty(PropertieFilePathEnum.SVN_CLIENT_PROPERTIES.getEnumVal(), copyFilesVo.getSource().getEnumVal());
		String localDestinationRepoPath =PropertiesReader.getProperty(PropertieFilePathEnum.SVN_CLIENT_PROPERTIES.getEnumVal(),copyFilesVo.getDestination().getEnumVal());
		List<String> relativePaths = copyFilesVo.getFilesList();
		boolean[] retval = new boolean[relativePaths.size()];
		int i=0;
		for(String relativeFilePath : relativePaths ){
			String completeSrcPath =  CommonUtils.getCompleteFilePath(localSourceRepoPath, relativeFilePath);
			String completeDestPath = CommonUtils.getCompleteFilePath(localDestinationRepoPath, relativeFilePath);
			completeDestPath = completeDestPath.replaceAll("/JavaSource/", "/src/");
			if(!(new File(completeDestPath).exists())){
				System.out.println("Debug:"+completeDestPath);
			boolean dirCreatFlag = new File(CommonUtils.getPathWithOutFileName(completeDestPath)).mkdirs();
			SVNManagerLogger.info(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Inside copyFiles method","New Directories creation status",dirCreatFlag);
			}
			retval[i] = FileUtility.copyFile(completeSrcPath,completeDestPath);
			i++;
		}
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Exiting copyFiles method","return",retval);
		return retval;
	}
	
	public boolean[] copyFilesFromList(List<String> srcFilePath,List<String> targetCopyPath){
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering copyFilesFromList method","SVNCopyFilesVo",srcFilePath,targetCopyPath);
		int filelListSize = srcFilePath.size();
		boolean[] retval = new boolean[filelListSize];
		for(int i=0;i<filelListSize;i++){
			if(!(new File(targetCopyPath.get(i)).exists())){
				System.out.println("Debug:"+targetCopyPath.get(i));
				boolean dirCreatFlag = new File(CommonUtils.getPathWithOutFileName(targetCopyPath.get(i))).mkdirs();
				SVNManagerLogger.info(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Inside copyFiles method","New Directories creation status",dirCreatFlag);
			}
			retval[i] = FileUtility.copyFile(srcFilePath.get(i),targetCopyPath.get(i));
		}
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Exiting copyFilesFromList method","return",retval);
		return retval;
	}
	
	

}
