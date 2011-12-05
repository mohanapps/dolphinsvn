package com.svn.client.vo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.wc.SVNRevision;

public class SVNUpdateVo extends SVNBaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9038995542429370090L;
	private SVNRevision revision;
	private boolean allowUnversionedObstructions;
	private boolean depthIsSticky;
	
	public SVNUpdateVo(){
		super();
	}
	
	public SVNUpdateVo(File[] paths,SVNRevision revision,SVNDepth depth,boolean allowUnversionedObstructions, boolean depthIsSticky)
	{
		super(paths,depth);
		this.revision = revision;
		this.allowUnversionedObstructions =allowUnversionedObstructions;
		this.depthIsSticky = depthIsSticky;
	}
	
	public boolean isAllowUnversionedObstructions() {
		return allowUnversionedObstructions;
	}
	public void setAllowUnversionedObstructions(boolean allowUnversionedObstructions) {
		this.allowUnversionedObstructions = allowUnversionedObstructions;
	}
	public boolean isDepthIsSticky() {
		return depthIsSticky;
	}
	public void setDepthIsSticky(boolean depthIsSticky) {
		this.depthIsSticky = depthIsSticky;
	}
	public SVNRevision getRevision() {
		return revision;
	}
	public void setRevision(SVNRevision revision) {
		this.revision = revision;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String ,Object>();
		map.put("Files",this.getFiles());
		map.put("depth", this.getDepth());
		map.put("SVNRevision", this.revision);
		map.put("allowUnversionedObstructions", this.allowUnversionedObstructions);
		map.put("depthIsSticky", this.depthIsSticky);
		return map.toString();
	}
	

}
