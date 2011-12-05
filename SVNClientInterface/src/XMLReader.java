import java.io.File;
import java.util.List;

import com.common.file.dto.SVNEnvironmentDTO;
import com.common.file.dto.SVNNodeDTO;
import com.common.file.dto.SVNRepositoryDTO;
import com.common.file.reader.CommonFileReaderFactory;
import com.common.file.reader.ICommonFileReader;


public class XMLReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File inputFile =new File("D:/mohan/R&D API's/SVNManager/SVNProperties/src/SVNConfig.xml");
		ICommonFileReader commonFileReader = CommonFileReaderFactory.createCommonFileReader(inputFile);
		List<SVNRepositoryDTO> repoList = commonFileReader.readFile().getRepoList();
		for(SVNRepositoryDTO repositoryDTO : repoList){
			System.out.print("URL: "+repositoryDTO.getUrl()+" UserName: "+repositoryDTO.getUserName()+" Password: "+repositoryDTO.getPassword()
					+ " REPO NAME: "+ repositoryDTO.getRepositoryName()+" Type: "+repositoryDTO.getRepositoryTypeEnum().getEnumVal());
			System.out.println();
			List<SVNEnvironmentDTO> envlist = repositoryDTO.getEnvironmentList();
			for(SVNEnvironmentDTO environmentDTO : envlist){
				System.out.println("Environment: "+environmentDTO.getName());
				List<SVNNodeDTO> nodeList = environmentDTO.getNodeList();
				for(SVNNodeDTO nodeDTO : nodeList){
					System.out.print("Node Name: "+ nodeDTO.getNodeName()+" Local: "+nodeDTO.getLocalNodePath()+" Remote: "+nodeDTO.getRemoteNodePath());
					System.out.println();
				}
			}
		}

	}

}
