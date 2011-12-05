package com.svn.client.authentication;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.svn.client.dto.SVNAuthCredentialDTO;
import com.svn.properties.constants.SVNManagerConstants;
import com.svn.properties.log.SVNManagerLogger;

public class SVNClientAuthenticationWizard {
	
		public static ISVNOptions options;
		private static Logger logger = Logger.getLogger(SVNClientAuthenticationWizard.class);
		
		
		public static ISVNAuthenticationManager getAuthenticationManager(SVNAuthCredentialDTO authCredentialDTO)
		{
			SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering getAuthenticationManager method", authCredentialDTO);
			ISVNAuthenticationManager authenticationManager = SVNWCUtil.createDefaultAuthenticationManager(authCredentialDTO.getUserId(),authCredentialDTO.getPassword());
			SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Exiting getAuthenticationManager method","ISVNAuthenticationManager",authenticationManager);
			return authenticationManager;
		}
		
		public static ISVNOptions getSVNOptions(){
			SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Entering getSVNOptions method");
			if(options != null){
				options = SVNWCUtil.createDefaultOptions(true);
			}
			SVNManagerLogger.debug(logger, SVNManagerConstants.APPL_SVNCLIENTINTERFACE,SVNManagerConstants.MODULE_SVNCLIENTINTERFACE,"Exiting getSVNOptions method");
			return options;
		}
		
}
