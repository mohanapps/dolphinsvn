package com.svn.client.admin;

import java.io.File;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNBasicClient;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;

import com.svn.client.authentication.SVNClientAuthenticationWizard;
import com.svn.client.dto.SVNAuthCredentialDTO;
import com.svn.client.vo.SVNBaseVo;
import com.svn.client.vo.SVNCommitVo;
import com.svn.client.vo.SVNUpdateVo;
import com.svn.properties.constants.SVNManagerConstants;
import com.svn.properties.log.SVNManagerLogger;

public class SVNAdminCommands implements ISVNAdminCommands {

	Logger logger = Logger.getLogger(SVNAdminCommands.class);
	public SVNCommitInfo doCommit(SVNCommitClient commitClient,SVNCommitVo commitVo) {
		// TODO Auto-generated method stub
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering doCommit method","SVNCommitClient",commitClient,"SVNCommitVo",commitVo);
		SVNCommitInfo commitInfo = null;
		try {
				commitInfo = commitClient.doCommit(commitVo.getFiles(),
					commitVo.isKeepLocks(), 
					commitVo.getCommitMessage(), 
					commitVo.getRevisionProperties(),
					commitVo.getChangelists(),
					commitVo.isKeepChangelist(),
					commitVo.isForce(),
					commitVo.getDepth());
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			SVNManagerLogger.error(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering exception doCommit method","Exception is",e.getMessage());
			e.printStackTrace();
		}
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Exiting doCommit method","SVNCommitInfo",commitInfo);
		return commitInfo;
	}

	public long[] doUpdate(SVNUpdateClient updateClient,SVNUpdateVo updateVo) {
		// TODO Auto-generated method stub
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering doUpdate method","SVNUpdateClient",updateClient,"SVNUpdateVo",updateVo);
		long[] revisionAfterUpdate = new long[updateVo.getFiles().length];
		try {
			revisionAfterUpdate = updateClient.doUpdate(updateVo.getFiles(),
					updateVo.getRevision(),
					updateVo.getDepth(),
					updateVo.isAllowUnversionedObstructions(), 
					updateVo.isDepthIsSticky());
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			SVNManagerLogger.error(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering exception doUpdate method","Exception is",e.getMessage());
			e.printStackTrace();
		}
		SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Exiting doUpdate method","Revision Version After update",revisionAfterUpdate);
		return revisionAfterUpdate;
	}

	

}
