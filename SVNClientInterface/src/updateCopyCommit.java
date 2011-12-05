import java.io.File;
import java.util.List;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.SVNRevision;

import com.common.file.reader.CommonFileReaderFactory;
import com.svn.client.authentication.SVNClientAuthenticationWizard;
import com.svn.client.dto.SVNAuthCredentialDTO;
import com.svn.client.processor.SVNClientProcessor;
import com.svn.client.utils.CommonUtils;
import com.svn.client.utils.FileUtility;
import com.svn.client.vo.SVNCommitVo;
import com.svn.client.vo.SVNCopyFilesVo;
import com.svn.client.vo.SVNUpdateVo;
import com.svn.properties.enums.LocalWorkSpacePathEnum;
import com.svn.properties.enums.PropertieFilePathEnum;
import com.svn.properties.manager.PropertiesReader;


public class updateCopyCommit {
		public static void main(String[] args) {
			InitialTest.setupLibrary();
			List<String> relativePath = CommonFileReaderFactory.createCommonFileReader(new File("C:/testupload.txt")).readFile().getReadLines();
			String srcPath = PropertiesReader.getProperty(PropertieFilePathEnum.SVN_CLIENT_PROPERTIES.getEnumVal(),LocalWorkSpacePathEnum.UAT.getEnumVal());
			List<String> updatePath = CommonUtils.getCompletePathList(relativePath,srcPath);
			System.out.println("Complete path:"+updatePath.get(0));
			File[] updatefiles = FileUtility.createFilesList(updatePath);
			SVNAuthCredentialDTO authCredentialDTO = new SVNAuthCredentialDTO("mohan","mohan");
			ISVNAuthenticationManager authenticationManager = SVNClientAuthenticationWizard.getAuthenticationManager(authCredentialDTO);
			
			
			
			
			SVNUpdateVo updateVo = new SVNUpdateVo(updatefiles,SVNRevision.HEAD,SVNDepth.EMPTY,false,false);
			SVNClientProcessor clientProcessor = new SVNClientProcessor();
			long[] ls = clientProcessor.doUpdateProcessor(authenticationManager, updateVo);
			for(int i =0;i<ls.length;i++){
				System.out.println(updatePath.get(i));
				System.out.println("Update with Revision  - "+ls[i]);
				System.out.println();		
			}
			
			SVNCopyFilesVo copyFilesVo = new SVNCopyFilesVo(relativePath,LocalWorkSpacePathEnum.UAT,LocalWorkSpacePathEnum.MOCK);
			boolean[] copyFlag = clientProcessor.copyFiles(copyFilesVo);
			System.out.println("Copied From:"+LocalWorkSpacePathEnum.UAT.getEnumVal() +"  to  "+LocalWorkSpacePathEnum.MOCK);
			for(int i =0;i<copyFlag.length;i++){
				System.out.println(updatePath.get(i));
				System.out.println("Copy Flag - "+copyFlag[i]);
				System.out.println();
			}
			
			SVNAuthCredentialDTO authCredentialDTOAdmin = new SVNAuthCredentialDTO("admin","admin1234");
			ISVNAuthenticationManager  authenticationManagerAdmin = SVNClientAuthenticationWizard.getAuthenticationManager(authCredentialDTOAdmin);
			String destPath = PropertiesReader.getProperty(PropertieFilePathEnum.SVN_CLIENT_PROPERTIES.getEnumVal(),LocalWorkSpacePathEnum.MOCK.getEnumVal());
			List<String> commitPath = CommonUtils.getCompletePathList(relativePath,destPath);
			commitPath = CommonUtils.replaceJavaSource(commitPath);
			File[] commitFiles = FileUtility.createFilesList(commitPath);
			SVNCommitVo commitVo = new SVNCommitVo(commitFiles,true,"IDeAS Revamp CR No - 15282 Owner - Krunal",null,null,false,true, SVNDepth.EMPTY);
			SVNCommitInfo commitInfo = clientProcessor.doCommitProcessor(authenticationManagerAdmin, commitVo);
			System.out.println(commitInfo);
			
			
			
		}
}
