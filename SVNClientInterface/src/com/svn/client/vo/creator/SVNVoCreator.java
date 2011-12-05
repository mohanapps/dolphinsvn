package com.svn.client.vo.creator;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNProperties;

import com.svn.client.vo.SVNCommitVo;
import com.svn.client.vo.SVNCopyFilesVo;
import com.svn.properties.constants.SVNManagerConstants;
import com.svn.properties.enums.LocalWorkSpacePathEnum;
import com.svn.properties.log.SVNManagerLogger;

public class SVNVoCreator {
	
	private static Logger logger = Logger.getLogger(SVNVoCreator.class);
	
	public static SVNCommitVo createSVNCommitVo(File[] paths, boolean keepLocks, String commitMessage, SVNProperties revisionProperties, String[] changelists, boolean keepChangelist, boolean force, SVNDepth depth){
		 SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering createSVNCommitVo method","Files[]",paths,"keepLock",keepLocks,"Commit Message",commitMessage,"SVN Properties",revisionProperties,"changeLists",changelists,"keepChangeList",keepChangelist,"force",force,"Depth",depth);
		 SVNCommitVo commitVo = new SVNCommitVo(paths,keepLocks,commitMessage,revisionProperties,changelists,keepChangelist,force,depth);
		 SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Exiting createSVNCommitVo method",commitVo);
		 return commitVo;
	}
	
	public static SVNCopyFilesVo createSVNCopyFilesVo(List<String> filesList,LocalWorkSpacePathEnum source,LocalWorkSpacePathEnum destination)
	{
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering createSVNCopyFilesVo method","filesList",filesList,"source",source,"destination",destination);
		SVNCopyFilesVo  copyFilesVo = new SVNCopyFilesVo(filesList,source,destination);
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Exiting createSVNCopyFilesVo method","SVNCopyFilesVo",copyFilesVo);
		return copyFilesVo;
	}

}
