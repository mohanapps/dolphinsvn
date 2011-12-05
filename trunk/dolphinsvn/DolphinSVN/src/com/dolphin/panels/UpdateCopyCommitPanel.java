package com.dolphin.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;

import com.common.file.dto.SVNRepositoryDTO;
import com.dolphin.populator.DropDownPopulator;
import com.dolphin.populator.ListPopulator;
import com.dolphin.search.DolphinSVNSearcher;
import com.svn.client.authentication.SVNClientAuthenticationWizard;
import com.svn.client.dto.SVNAuthCredentialDTO;
import com.svn.client.processor.SVNClientProcessor;
import com.svn.client.utils.FileUtility;
import com.svn.client.vo.SVNCommitVo;
import com.svn.client.vo.SVNUpdateVo;

import javax.swing.JScrollPane;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.SVNRevision;

public class UpdateCopyCommitPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox sourceRepoCombo = null;
	private JLabel sourceRepoLabel = null;
	private JLabel destRepoLabel = null;
	private JComboBox destRepoCombo = null;
	private JFileChooser fileChooser = null;
	private JButton jButton = null;
	private JTextField filePathTextField = null;
	private JLabel jLabel = null;
	private JButton updateSourceButton = null;
	private JButton copyFilesButton = null;
	private JButton commitDestinationButton = null;
	private JList relativePathList = null;
	private JTextArea commentTextArea = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JComboBox sourceEnvCombo = null;
	private JComboBox destEnvCombo = null;
	private JScrollPane jScrollPane = null;
	/**
	 * This is the default constructor
	 */
	public UpdateCopyCommitPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jLabel4 = new JLabel();
		jLabel4.setBounds(new Rectangle(418, 65, 147, 27));
		jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel4.setFont(new Font("Dialog", Font.PLAIN, 12));
		jLabel4.setText("Destination Environment");
		jLabel3 = new JLabel();
		jLabel3.setBounds(new Rectangle(418, 30, 145, 23));
		jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel3.setFont(new Font("Dialog", Font.PLAIN, 12));
		jLabel3.setText("Source Environment");
		jLabel2 = new JLabel();
		jLabel2.setBounds(new Rectangle(68, 182, 96, 27));
		jLabel2.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
		jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel2.setFont(new Font("Dialog", Font.PLAIN, 12));
		jLabel2.setText("File List");
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(63, 286, 103, 29));
		jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel1.setFont(new Font("Dialog", Font.PLAIN, 12));
		jLabel1.setText("Comments");
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(27, 105, 139, 23));
		jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		jLabel.setText("Upload Release Note");
		destRepoLabel = new JLabel();
		destRepoLabel.setBounds(new Rectangle(34, 59, 133, 25));
		destRepoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		destRepoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		destRepoLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		destRepoLabel.setText("Destination Repository");
		sourceRepoLabel = new JLabel();
		sourceRepoLabel.setBounds(new Rectangle(53, 27, 111, 24));
		sourceRepoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		sourceRepoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		sourceRepoLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		sourceRepoLabel.setText("Source Repository");
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.gridy = 0;
		this.setSize(738, 451);
		this.setLayout(null);
		this.setBackground(SystemColor.control);
		this.add(getSourceEnvCombo(), null);
		this.add(getDestEnvCombo(), null);
		this.add(getSourceRepoCombo(), gridBagConstraints);
		this.add(jLabel3, null);
		this.add(sourceRepoLabel, null);
		this.add(jLabel4, null);
		this.add(destRepoLabel, null);
		this.add(getDestRepoCombo(), null);
		
		this.add(getFileChooser(),null);
		this.add(getJButton(), null);
		this.add(getFilePathTextField(), null);
		this.add(jLabel, null);
		this.add(getUpdateSourceButton(), null);
		this.add(getCopyFilesButton(), null);
		this.add(getCommitDestinationButton(), null);
		this.add(getCommentTextArea(), null);
		this.add(jLabel1, null);
		this.add(jLabel2, null);
		this.add(getJScrollPane(), null);
		getFileChooser().getFileView();
	}

	/**
	 * This method initializes sourceRepoCombo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getSourceRepoCombo() {
		if (sourceRepoCombo == null) {
			sourceRepoCombo = new JComboBox();
			sourceRepoCombo.setBounds(new Rectangle(210, 26, 151, 25));
			sourceRepoCombo.setFont(new Font("Dialog", Font.PLAIN, 12));
			sourceRepoCombo.setBounds(new Rectangle(210, 26, 180, 25));
			sourceRepoCombo.setBackground(Color.white);
			Vector<String> envVect = DropDownPopulator.getRepositoryList();
			for(String environmentName : envVect){
				sourceRepoCombo.addItem(environmentName);
			}
			List<String> envList = DropDownPopulator.getEnvList(sourceRepoCombo.getSelectedItem().toString());
			for(String envString : envList){
				sourceEnvCombo.addItem(envString);
			}
			
			sourceRepoCombo.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					 // TODO Auto-generated Event stub itemStateChanged()
					List<String> envList = DropDownPopulator.getEnvList(sourceRepoCombo.getSelectedItem().toString());
					sourceEnvCombo.removeAllItems();
					for(String envString : envList){						
						sourceEnvCombo.addItem(envString);
					}
				}
			});
			
		}
		return sourceRepoCombo;
	}

	/**
	 * This method initializes destRepoCombo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getDestRepoCombo() {
		if (destRepoCombo == null) {
			destRepoCombo = new JComboBox();
			destRepoCombo.setBounds(new Rectangle(210, 64, 151, 25));
			destRepoCombo.setFont(new Font("Dialog", Font.PLAIN, 12));
			destRepoCombo.setBounds(new Rectangle(210, 64, 180, 25));
			destRepoCombo.setBackground(Color.white);
			Vector<String> envVect = DropDownPopulator.getRepositoryList();
			for(String environmentName : envVect){
				destRepoCombo.addItem(environmentName);
			}
			List<String> envList = DropDownPopulator.getEnvList(destRepoCombo.getSelectedItem().toString());
			for(String envString : envList){
				destEnvCombo.addItem(envString);
				
			}
			destRepoCombo.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					List<String> envList = DropDownPopulator.getEnvList(destRepoCombo.getSelectedItem().toString());
					destEnvCombo.removeAllItems();
					for(String envString : envList){
						destEnvCombo.addItem(envString);
						
					}
				}
			});
		}
		return destRepoCombo;
	}

	private JFileChooser getFileChooser() {
		fileChooser = new JFileChooser();
		return fileChooser;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(480, 104, 109, 24));
			jButton.setBackground(SystemColor.control);
			jButton.setText("Browse");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fileChooser.showOpenDialog(null);
					String uploadFilePath = fileChooser.getSelectedFile().getAbsolutePath();
					filePathTextField.setText(uploadFilePath);
					List<String> pathList = ListPopulator.getRelativePathList(filePathTextField.getText());
					relativePathList.setListData(pathList.toArray());
					// TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes filePathTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFilePathTextField() {
		if (filePathTextField == null) {
			filePathTextField = new JTextField();
			filePathTextField.setBounds(new Rectangle(209, 105, 250, 24));
			filePathTextField.setBackground(Color.white);
			filePathTextField.setEditable(false);
		}
		return filePathTextField;
	}

	/**
	 * This method initializes updateSourceButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getUpdateSourceButton() {
		if (updateSourceButton == null) {
			updateSourceButton = new JButton();
			updateSourceButton.setBounds(new Rectangle(146, 391, 123, 26));
			updateSourceButton.setBackground(SystemColor.control);
			updateSourceButton.setText("Update Source");
			updateSourceButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 // TODO Auto-generated Event stub actionPerformed()
					SVNRepositoryDTO repositoryDTO = DolphinSVNSearcher.searchSVNRepository(sourceRepoCombo.getSelectedItem().toString());
					SVNAuthCredentialDTO authCredentialDTO = new SVNAuthCredentialDTO(repositoryDTO.getUserName(),repositoryDTO.getPassword());
					List<String> completeSourcePathList = ListPopulator.getCompletePathList(relativePathList,repositoryDTO,sourceEnvCombo.getSelectedItem().toString());
					ISVNAuthenticationManager authenticationManager = SVNClientAuthenticationWizard.getAuthenticationManager(authCredentialDTO);
					SVNClientProcessor clientProcessor = new SVNClientProcessor();
					File[] updatefiles = FileUtility.createFilesList(completeSourcePathList);
					SVNUpdateVo updateVo = new SVNUpdateVo(updatefiles,SVNRevision.HEAD,SVNDepth.EMPTY,false,false);
					long[] ls = clientProcessor.doUpdateProcessor(authenticationManager, updateVo);
					for(int i =0;i<ls.length;i++){
						System.out.println(completeSourcePathList.get(i));
						System.out.println("Update with Revision  - "+ls[i]);
						System.out.println();		
					}
					updateSourceButton.setEnabled(false);
					copyFilesButton.setEnabled(true);
				}
			});
		}
		return updateSourceButton;
	}

	/**
	 * This method initializes copyFilesButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCopyFilesButton() {
		if (copyFilesButton == null) {
			copyFilesButton = new JButton();
			copyFilesButton.setBounds(new Rectangle(315, 391, 123, 27));
			copyFilesButton.setBackground(SystemColor.control);
			copyFilesButton.setText("Copy Files");
			copyFilesButton.setEnabled(false);
			copyFilesButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					SVNRepositoryDTO srcRepositoryDTO = DolphinSVNSearcher.searchSVNRepository(sourceRepoCombo.getSelectedItem().toString());
					SVNRepositoryDTO destRepositoryDTO = DolphinSVNSearcher.searchSVNRepository(destRepoCombo.getSelectedItem().toString());
					List<String> completeSourcePathList = ListPopulator.getCompletePathList(relativePathList,srcRepositoryDTO,sourceEnvCombo.getSelectedItem().toString());
					List<String> completeDestinationPathList = ListPopulator.getCompletePathList(relativePathList,destRepositoryDTO,destEnvCombo.getSelectedItem().toString());
					SVNClientProcessor clientProcessor = new SVNClientProcessor();
					boolean[] copyStatus = clientProcessor.copyFilesFromList(completeSourcePathList,completeDestinationPathList);
					for(boolean b : copyStatus){
						System.out.println("Copy Flag:"+b);
					}
					commitDestinationButton.setEnabled(true);
					copyFilesButton.setEnabled(false);
				}
			});
		}
		return copyFilesButton;
	}

	/**
	 * This method initializes commitDestinationButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCommitDestinationButton() {
		if (commitDestinationButton == null) {
			commitDestinationButton = new JButton();
			commitDestinationButton.setBounds(new Rectangle(481, 392, 152, 27));
			commitDestinationButton.setBackground(SystemColor.control);
			commitDestinationButton.setText("Commit Destination");
			commitDestinationButton.setEnabled(false);
			commitDestinationButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					SVNRepositoryDTO repositoryDTO = DolphinSVNSearcher.searchSVNRepository(destRepoCombo.getSelectedItem().toString());
					SVNAuthCredentialDTO authCredentialDTO = new SVNAuthCredentialDTO(repositoryDTO.getUserName(),repositoryDTO.getPassword());
					List<String> completeSourcePathList = ListPopulator.getCompletePathList(relativePathList,repositoryDTO,destEnvCombo.getSelectedItem().toString());
					ISVNAuthenticationManager authenticationManager = SVNClientAuthenticationWizard.getAuthenticationManager(authCredentialDTO);
					SVNClientProcessor clientProcessor = new SVNClientProcessor();
					File[] commitFiles = FileUtility.createFilesList(completeSourcePathList);
					SVNCommitVo commitVo = new SVNCommitVo(commitFiles,true,commentTextArea.getText(),null,null,false,true, SVNDepth.EMPTY);
					SVNCommitInfo commitInfo = clientProcessor.doCommitProcessor(authenticationManager, commitVo);
					System.out.println(commitInfo);
					updateSourceButton.setEnabled(true);
					commitDestinationButton.setEnabled(false);
				}
			
			});
		}
		return commitDestinationButton;
	}

	/**
	 * This method initializes relativePathList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getRelativePathList() {
		if (relativePathList == null) {
			relativePathList = new JList();
			relativePathList.setBackground(Color.white);
			relativePathList.setFont(new Font("Dialog", Font.PLAIN, 12));
			relativePathList.setAutoscrolls(true);
			
			
		}
		return relativePathList;
	}

	/**
	 * This method initializes commentTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getCommentTextArea() {
		if (commentTextArea == null) {
			commentTextArea = new JTextArea();
			commentTextArea.setBounds(new Rectangle(211, 276, 401, 75));
			commentTextArea.setBackground(Color.white);
		}
		return commentTextArea;
	}

	/**
	 * This method initializes sourceEnvCombo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getSourceEnvCombo() {
		if (sourceEnvCombo == null) {
			sourceEnvCombo = new JComboBox();
			sourceEnvCombo.setBounds(new Rectangle(578, 33, 144, 22));
			sourceEnvCombo.setFont(new Font("Dialog", Font.PLAIN, 12));
			sourceEnvCombo.setBackground(Color.white);
		}
		return sourceEnvCombo;
	}

	/**
	 * This method initializes destEnvCombo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getDestEnvCombo() {
		if (destEnvCombo == null) {
			destEnvCombo = new JComboBox();
			destEnvCombo.setBounds(new Rectangle(579, 66, 143, 23));
			destEnvCombo.setFont(new Font("Dialog", Font.PLAIN, 12));
			destEnvCombo.setBackground(Color.white);
		}
		return destEnvCombo;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(211, 179, 401, 76));
			jScrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
			jScrollPane.setViewportView(getRelativePathList());
		}
		return jScrollPane;
	}
	
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
