package com.dolphin.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;

import com.common.file.dto.SVNRepositoryDTO;
import com.common.file.reader.CommonFileReaderFactory;
import com.common.file.reader.ICommonFileReader;
import com.dolphin.panels.UpdateCopyCommitPanel;

public class DolphinApplication {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="91,-8"

	private JPanel jContentPane = null;

	private JMenuBar jJMenuBar = null;

	private JMenu svnToolsMenu = null;

	private JMenu deploymentToolsMenu = null;

	private JMenu helpMenu = null;

	private JMenuItem exitMenuItem = null;

	private JMenuItem aboutMenuItem = null;

	private JMenuItem deployWarSubMenu = null;

	private JMenuItem deployEarSubMenu = null;

	private JMenuItem updateCopyCommitSubMenu = null;

	private JDialog aboutDialog = null;  //  @jve:decl-index=0:visual-constraint="97,426"

	private JPanel aboutContentPane = null;

	private JLabel aboutVersionLabel = null;

	private JEditorPane jEditorPane = null;

	private JPanel basePanel = null;  //  @jve:decl-index=0:visual-constraint="667,44"

	private JSplitPane jSplitPane = null;
	
	public static List<SVNRepositoryDTO> repoList = null;

	/**
	 * This method initializes jFrame
	 * 
	 * @return javax.swing.JFrame
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setJMenuBar(getJJMenuBar());
			jFrame.setSize(943, 644);
			jFrame.setContentPane(getJContentPane());
			jFrame.setTitle("Application");
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getBasePanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.setBackground(SystemColor.control);
			jJMenuBar.add(getSvnToolsMenu());
			jJMenuBar.add(getDeploymentToolsMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getSvnToolsMenu() {
		if (svnToolsMenu == null) {
			svnToolsMenu = new JMenu();
			svnToolsMenu.setText("SVN Tools");
			svnToolsMenu.setBackground(Color.white);
			svnToolsMenu.setFont(new Font("Dialog", Font.PLAIN, 12));
			svnToolsMenu.add(getUpdateCopyCommitSubMenu());
			svnToolsMenu.add(getExitMenuItem());
			svnToolsMenu.addMouseListener(new java.awt.event.MouseAdapter() {   
				public void mouseExited(java.awt.event.MouseEvent e) {    
					svnToolsMenu.setForeground(Color.black); // TODO Auto-generated Event stub mouseExited()
				}
				public void mouseEntered(java.awt.event.MouseEvent e) {
					svnToolsMenu.setBackground(Color.blue);
					svnToolsMenu.setForeground(Color.blue);// TODO Auto-generated Event stub mouseEntered()
				}
			});
		}
		return svnToolsMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getDeploymentToolsMenu() {
		if (deploymentToolsMenu == null) {
			deploymentToolsMenu = new JMenu();
			deploymentToolsMenu.setText("Deployment tools");
			deploymentToolsMenu.setFont(new Font("Dialog", Font.PLAIN, 12));
			deploymentToolsMenu.add(getDeployWarSubMenu());
			deploymentToolsMenu.add(getDeployEarSubMenu());
			deploymentToolsMenu.addMouseListener(new java.awt.event.MouseAdapter() {   
				public void mouseExited(java.awt.event.MouseEvent e) {    
					deploymentToolsMenu.setForeground(Color.black); // TODO Auto-generated Event stub mouseExited()
				}
				public void mouseEntered(java.awt.event.MouseEvent e) {
					deploymentToolsMenu.setBackground(Color.blue);
					deploymentToolsMenu.setForeground(Color.blue);// TODO Auto-generated Event stub mouseEntered()
				}
			});
		}
		return deploymentToolsMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.setFont(new Font("Dialog", Font.PLAIN, 12));
			helpMenu.add(getAboutMenuItem());
			helpMenu.addMouseListener(new java.awt.event.MouseAdapter() {   
				public void mouseExited(java.awt.event.MouseEvent e) {    
					helpMenu.setForeground(Color.black); // TODO Auto-generated Event stub mouseExited()
				}
				public void mouseEntered(java.awt.event.MouseEvent e) {
					
					helpMenu.setForeground(Color.blue);// TODO Auto-generated Event stub mouseEntered()
				}
			});
			
		}
		return helpMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.setFont(new Font("Dialog", Font.PLAIN, 12));
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.setFont(new Font("Dialog", Font.PLAIN, 12));
			aboutMenuItem.setBackground(Color.white);
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog aboutDialog = getAboutDialog();
					aboutDialog.pack();
					Point loc = getJFrame().getLocation();
					loc.translate(20, 20);
					aboutDialog.setLocation(loc);
					aboutDialog.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}

	/**
	 * This method initializes aboutDialog	
	 * 	
	 * @return javax.swing.JDialog
	 */
	private JDialog getAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new JDialog(getJFrame(), true);
			aboutDialog.setTitle("About");
			aboutDialog.setSize(new Dimension(123, 53));
			aboutDialog.setContentPane(getAboutContentPane());
		}
		return aboutDialog;
	}

	/**
	 * This method initializes aboutContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getAboutContentPane() {
		if (aboutContentPane == null) {
			aboutContentPane = new JPanel();
			aboutContentPane.setLayout(new BorderLayout());
			aboutContentPane.add(getAboutVersionLabel(), BorderLayout.CENTER);
		}
		return aboutContentPane;
	}

	/**
	 * This method initializes aboutVersionLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getAboutVersionLabel() {
		if (aboutVersionLabel == null) {
			aboutVersionLabel = new JLabel();
			aboutVersionLabel.setText("Version 1.0");
			aboutVersionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return aboutVersionLabel;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getDeployWarSubMenu() {
		if (deployWarSubMenu == null) {
			deployWarSubMenu = new JMenuItem();
			deployWarSubMenu.setText("Deploy War");
			deployWarSubMenu.setBackground(Color.white);
			deployWarSubMenu.setFont(new Font("Dialog", Font.PLAIN, 12));
			deployWarSubMenu.setText("Deploy War");
			deployWarSubMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
					Event.CTRL_MASK, true));
		}
		return deployWarSubMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getDeployEarSubMenu() {
		if (deployEarSubMenu == null) {
			deployEarSubMenu = new JMenuItem();
			deployEarSubMenu.setText("Deploy Ear");
			deployEarSubMenu.setBackground(Color.white);
			deployEarSubMenu.setFont(new Font("Dialog", Font.PLAIN, 12));
			deployEarSubMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
					Event.CTRL_MASK, true));
		}
		return deployEarSubMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getUpdateCopyCommitSubMenu() {
		if (updateCopyCommitSubMenu == null) {
			updateCopyCommitSubMenu = new JMenuItem();
			updateCopyCommitSubMenu.setText("Transfer Files");
			updateCopyCommitSubMenu.setBackground(Color.white);
			updateCopyCommitSubMenu.setFont(new Font("Dialog", Font.PLAIN, 12));
			updateCopyCommitSubMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
			updateCopyCommitSubMenu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jContentPane.removeAll();
					jContentPane.add(new UpdateCopyCommitPanel());
					//jContentPane.repaint();
					jContentPane.revalidate();
					System.out.println("Entered");
					// TODO Auto-generated Event stub actionPerformed()
				}
			});
			
		}
		return updateCopyCommitSubMenu;
	}

	/**
	 * This method initializes jEditorPane	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJEditorPane() {
		if (jEditorPane == null) {
			jEditorPane = new JEditorPane();
		}
		return jEditorPane;
	}

	/**
	 * This method initializes basePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBasePanel() {
		if (basePanel == null) {
			basePanel = new JPanel();
			basePanel.setLayout(new  BorderLayout());
			basePanel.setSize(new Dimension(629, 362));
			basePanel.setBackground(SystemColor.control);
		}
		return basePanel;
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	

	/**
	 * Launches this application
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setupLibrary();
				DolphinApplication application = new DolphinApplication();
				application.getJFrame().setVisible(true);
			}
		});
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

}
