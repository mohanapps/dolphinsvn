package com.common.file.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.common.file.dto.FileReadOuputDTO;
import com.common.file.dto.SVNEnvironmentDTO;
import com.common.file.dto.SVNNodeDTO;
import com.common.file.dto.SVNRepositoryDTO;
import com.common.file.enums.XMLAttributesEnum;

public class XMLFileReader extends DefaultHandler implements ICommonFileReader  {

	private File inputFile;
	private List<SVNRepositoryDTO> repoList = null;
	private List<SVNEnvironmentDTO> envList = null;
	private List<SVNNodeDTO> nodeList = null;
	private Map<String, String> attributeHolder = null;
	FileReadOuputDTO fileReadOuputDTO = null;
	
	public XMLFileReader(File inputFile){
		this.inputFile = inputFile;
	}
	
	public FileReadOuputDTO readFile() {
		// TODO Auto-generated method stub
		fileReadOuputDTO = new FileReadOuputDTO();
		repoList = new ArrayList<SVNRepositoryDTO>();
		envList = new ArrayList<SVNEnvironmentDTO>();
		nodeList = new ArrayList<SVNNodeDTO>();
		attributeHolder = new HashMap<String, String>();
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			sp.parse(inputFile,this);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileReadOuputDTO;
	}
	
	public void startDocument() throws SAXException {
			//System.out.println("Document started");
	}
	
	
	public void startElement(String namespaceURI, String localName,
			String qname, Attributes atts) throws SAXException {
			for(int i=0;i<=atts.getLength();i++){
				if(atts.getQName(i) != null){
					attributeHolder.put(qname+atts.getQName(i),atts.getValue(i));
				}
			}
	}
	
	public void endElement(String namespaceURI, String localName, String qname) {
		XMLAttributesEnum attributesEnum = XMLAttributesEnum.getEnum(qname);
		switch(attributesEnum){
			case NODE_MAPPING:
				addNode();
				break;
			case ENVIRONMENT:
				addEnvironment(nodeList);
				nodeList = new ArrayList<SVNNodeDTO>();
				break;
			case SVNREPOSITORY:
				addRepository(envList);
				envList = new ArrayList<SVNEnvironmentDTO>();
				attributeHolder = new HashMap<String, String>();
				break;
			case SVNCLIENT_CONFIGURATION:
				fileReadOuputDTO.setRepoList(repoList);
				//System.out.println("FINISHED");
				break;
		}
		
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException{
	//	System.out.println("Character:"+new String(ch,start,length));
	}
	
	
	private void addNode(){
		SVNNodeDTO nodeDTO = new SVNNodeDTO(attributeHolder);
		nodeList.add(nodeDTO);
		//nodeDTO.setLocalNodePath(attributeHolder.get(XMLAttributesEnum.))
	}
	
	private void addEnvironment(List<SVNNodeDTO> list){
		String envKey = new StringBuilder(XMLAttributesEnum.ENVIRONMENT.getEnumVal())
		.append(XMLAttributesEnum.ATT_ENVNAME.getEnumVal()).toString();
		SVNEnvironmentDTO environmentDTO = new SVNEnvironmentDTO(list,attributeHolder.get(envKey));
		envList.add(environmentDTO);
	}
	
	private void addRepository(List<SVNEnvironmentDTO> envList){
		SVNRepositoryDTO repositoryDTO = new SVNRepositoryDTO(envList,attributeHolder);
		repoList.add(repositoryDTO);
		
	}
	

}
