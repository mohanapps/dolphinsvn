	import java.io.File;
import java.util.List;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;

import com.common.file.reader.CommonFileReaderFactory;
import com.svn.client.authentication.SVNClientAuthenticationWizard;
import com.svn.client.dto.SVNAuthCredentialDTO;
import com.svn.client.utils.CommonUtils;
import com.svn.client.vo.SVNCommitVo;
import com.svn.client.vo.SVNCopyFilesVo;
import com.svn.client.vo.SVNUpdateVo;
import com.svn.properties.enums.LocalWorkSpacePathEnum;


public class InitialTest {

	/**
	 * @param argss
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		try {
			setupLibrary();
			addComponents();
			
			
			
			//System.out.println(new File("C:/Documents and Settings/tcs_sagark/Desktop/Test_SVN_ENV1/test1/test1/").mkdirs());
			SVNURL svnurl = SVNURL.parseURIEncoded("svn://172.16.8.50/speede_repository/trunk/Development/Test_SVN_ENV1/remat_demat.txt");
			//SVNRepository repository = SVNRepositoryFactory.create(svnurl);
			/*System.out.println("Version:"+repository.getLatestRevision());
			
			SVNClientProcessor clientProcessor =  new SVNClientProcessor();
			//SVNCommitInfo  commitInfo = clientProcessor.doCommitProcessor(new SVNAuthCredentialDTO("mohan","mohan"), getTestCommitVo());
			long[] afterRevision = clientProcessor.doUpdateProcessor(new SVNAuthCredentialDTO("mohan","mohan"), getUpdateVo());
			//boolean[] copyFlgs = clientProcessor.copyFiles(getSVNCopyFilesVo());
			//System.out.println(commitInfo);
			
			for(long l : afterRevision){
				System.out.println("After Revision:"+l);
			}*/
			/*for(boolean b : copyFlgs){
				System.out.println("Copy Flag:"+b);
			}*/
			/*System.out.println(FileUtility.copyFile("C:/Documents and Settings/tcs_sagark/Desktop/Testing/Test_SVN_ENV1/remat_demat.txt", "C:/Documents and Settings/tcs_sagark/Desktop/New Folder/remat_demat.txt"));
			System.out.println(PropertiesReader.getProperty(PropertieFilePathEnum.SVN_CLIENT_PROPERTIES.getEnumVal(),LocalWorkSpacePathEnum.DEVELOPMENT.getEnumVal()));*/

			
			/*ISVNAuthenticationManager authenticationManager = SVNClientAuthenticationWizard.getAuthenticationManager(new SVNAuthCredentialDTO("mohan","mohan"));
			repository.setAuthenticationManager(authenticationManager);
			ISVNOptions options = SVNClientAuthenticationWizard.getSVNOptions();
			String path = "C:/Documents and Settings/tcs_sagark/Desktop/DM_IDEAS_JARS/test.txt.txt";
			SVNCommitClient client = new  SVNCommitClient(authenticationManager,options);
			File[] file = {new File(path)};
			String[] changeList = {"/speede_repository/trunk/NewDM_MOCK/26OCT2010/DM_IDEAS_JARS/test.txt"}; 
			System.out.println("Entere");
			SVNCommitInfo commitInfo = client.doCommit(file,true,"Mohan test commit",null,null, false,true, SVNDepth.EMPTY);
			SVNUpdateClient updateClient = new SVNUpdateClient(authenticationManager,options);
			long[]  ls =updateClient.doUpdate(file, SVNRevision.HEAD,SVNDepth.EMPTY,false, false);
			for(long l : ls){
				System.out.println(l);
			}*/
			
			
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		
	
	
	
	}
	
	public static void setupLibrary() {
        /*
         * For using over http:// and https://
         */
        DAVRepositoryFactory.setup();
        /*
         * For using over svn:// and svn+xxx://
         */
        SVNRepositoryFactoryImpl.setup();
        
        /*
         * For using over file:///
         */
        FSRepositoryFactory.setup();
    }
	
	private static SVNCommitVo getTestCommitVo(){
		
		File[] file = getFiles();
		SVNCommitVo commitVo = new SVNCommitVo(file,true,"Mohan test commit",null,null, false,true, SVNDepth.EMPTY);
		
		return commitVo;	
	}
	
	private static SVNUpdateVo getUpdateVo(){
		File[] files = getFiles();
		SVNUpdateVo updateVo = new SVNUpdateVo(files,SVNRevision.HEAD,SVNDepth.EMPTY,false,false);
		return updateVo;
	}
	
	
	private static File[] getFiles()
	{
		String path = "C:/Documents and Settings/tcs_sagark/Desktop/Testing/Test_SVN_ENV1/remat_demat.txt";
					   	
		File[] file = {new File(path)};
		System.out.println(file[0].getPath());
		return file;
	}
	
	
	private static SVNCopyFilesVo getSVNCopyFilesVo()
	{
		List<String> list = CommonFileReaderFactory.createCommonFileReader(new File("C:/testupload.txt")).readFile().getReadLines();
		List<String> srcList = CommonUtils.getCompletePathList(list,LocalWorkSpacePathEnum.UAT.getEnumVal());
		List<String> destList = CommonUtils.getCompletePathList(list,LocalWorkSpacePathEnum.MOCK.getEnumVal());
		SVNCopyFilesVo copyFilesVo = new SVNCopyFilesVo(list,LocalWorkSpacePathEnum.UAT,LocalWorkSpacePathEnum.MOCK);
		return copyFilesVo;
	}
	
	public static void addComponents(){
		String url = "svn://172.16.8.50/speede_repository/";
		try {
			SVNRepository repository = SVNRepositoryFactory.create( SVNURL.parseURIDecoded( url ) );
			ISVNAuthenticationManager authenticationManager = SVNClientAuthenticationWizard.getAuthenticationManager(new SVNAuthCredentialDTO("mohan","mohan"));
			ISVNOptions options = SVNClientAuthenticationWizard.getSVNOptions();
			SVNCommitClient commitClient = new SVNCommitClient(authenticationManager,options);
			repository.setAuthenticationManager(authenticationManager);
			System.out.println("syso:"+repository.checkPath("/trunk/Development", -1).toString());
			SVNURL svnurl = SVNURL.parseURIEncoded("svn://172.16.8.50/speede_repository/trunk/Development/Test_SVN_ENV1/test/testfile.txt");
			SVNURL svnurl1 = SVNURL.parseURIEncoded("svn://172.16.8.50/speede_repository/trunk/Development/Test_SVN_ENV1/test/");
			/*ISVNEditor editor = repository.getCommitEditor("Test add",null,false, null);
			editor.openRoot(-1);
			editor.addDir("/trunk/Development/Test_SVN_ENV1/test/",null,-1);
			editor.addFile("/trunk/Development/Test_SVN_ENV1/test/testfile.txt",null, -1);
			//editor.add
			editor.closeDir();
			editor.closeEdit();*/
			//commitClient.doImport(new File("C:/Documents and Settings/tcs_sagark/Desktop/Test_SVN_ENV1/test/testfile.txt"),svnurl,true);
			commitClient.doImport(new File("C:/Documents and Settings/tcs_sagark/Desktop/Test_SVN_ENV1/test/testfile.txt"),svnurl,"Test Import",null,true,true,SVNDepth.INFINITY);
			SVNUpdateClient updateClient = new SVNUpdateClient(authenticationManager,options);
			updateClient.doCheckout(svnurl1,new File("C:/Documents and Settings/tcs_sagark/Desktop/Test_SVN_ENV1/test"),SVNRevision.HEAD,SVNRevision.HEAD,SVNDepth.INFINITY,true);
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
