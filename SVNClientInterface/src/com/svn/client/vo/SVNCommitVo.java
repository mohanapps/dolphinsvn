package com.svn.client.vo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNProperties;

public class SVNCommitVo extends SVNBaseVo {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 313839543456373967L;
	private boolean keepLocks;
	 private SVNProperties revisionProperties;
	 private String[] changelists;
	 private boolean keepChangelist;
	 private boolean force;
	 
	 
	 public SVNCommitVo(){
		 
	 }
	 
	 public SVNCommitVo(File[] paths, boolean keepLocks, String commitMessage, SVNProperties revisionProperties, String[] changelists, boolean keepChangelist, boolean force, SVNDepth depth){
		 super(paths,commitMessage,depth);
		 this.keepLocks = keepLocks;
		 this.revisionProperties = revisionProperties;
		 this.changelists = changelists;
		 this.keepChangelist = keepChangelist;
		 this.force = force;
		 
	 }
	 
	 
	 
	public String[] getChangelists() {
		return changelists;
	}
	public void setChangelists(String[] changelists) {
		this.changelists = changelists;
	}
	
	public boolean isForce() {
		return force;
	}
	public void setForce(boolean force) {
		this.force = force;
	}
	public boolean isKeepChangelist() {
		return keepChangelist;
	}
	public void setKeepChangelist(boolean keepChangelist) {
		this.keepChangelist = keepChangelist;
	}
	public boolean isKeepLocks() {
		return keepLocks;
	}
	public void setKeepLocks(boolean keepLocks) {
		this.keepLocks = keepLocks;
	}
	public SVNProperties getRevisionProperties() {
		return revisionProperties;
	}
	public void setRevisionProperties(SVNProperties revisionProperties) {
		this.revisionProperties = revisionProperties;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String ,Object>();
		map.put("Files",this.getFiles());
		map.put("CommitMessage",this.getCommitMessage());
		map.put("SVNProperties",this.revisionProperties);
		map.put("changelists",this.changelists);
		map.put("keepChangelist",this.keepChangelist);
		map.put("force",this.force);
		return map.toString();
	}

}
