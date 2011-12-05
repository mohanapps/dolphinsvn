package com.svn.client.vo;

import java.io.File;
import java.io.Serializable;

import org.tmatesoft.svn.core.SVNDepth;

public class SVNBaseVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File[] files;
	private String commitMessage;
	private SVNDepth depth;
	
	public SVNBaseVo(){
		
	}
	
	public SVNBaseVo(File[] files,String commitMessage,SVNDepth depth){
		this.files = files;
		this.commitMessage = commitMessage;
		this.depth = depth;
	}
	public SVNBaseVo(File[] files,SVNDepth depth){
		this.files = files;
		this.depth = depth;
	}
	
	public File[] getFiles() {
		return files;
	}
	public void setFiles(File[] files) {
		this.files = files;
	}

	public String getCommitMessage() {
		return commitMessage;
	}

	public void setCommitMessage(String commitMessage) {
		this.commitMessage = commitMessage;
	}

	public SVNDepth getDepth() {
		return depth;
	}

	public void setDepth(SVNDepth depth) {
		this.depth = depth;
	}
	
}
