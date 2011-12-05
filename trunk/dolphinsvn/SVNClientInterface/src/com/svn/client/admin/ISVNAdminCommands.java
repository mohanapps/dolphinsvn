package com.svn.client.admin;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.wc.SVNBasicClient;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;

import com.svn.client.vo.SVNBaseVo;
import com.svn.client.vo.SVNCommitVo;
import com.svn.client.vo.SVNUpdateVo;

public interface ISVNAdminCommands {
	
	public SVNCommitInfo doCommit(SVNCommitClient commitClient,SVNCommitVo commitVo);
	public long[] doUpdate(SVNUpdateClient updateClient,SVNUpdateVo updateVo);
	
}
