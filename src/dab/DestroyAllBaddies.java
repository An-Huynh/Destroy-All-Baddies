package dab;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dab.client.GameClient;
import dab.server.GameServer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class DestroyAllBaddies extends JPanel
implements ActionListener {
	protected JButton hostButton, joinButton, launchBothButton, launchClientButton;
	protected JTextField IPAddressField, nameField;
	protected JLabel welcomeLabel, dabLabel, IPAddressLabel, nameLabel;
	protected static JFrame frame;
	
	public DestroyAllBaddies() {
		// Welcome Label Settings
		welcomeLabel = new JLabel();
		welcomeLabel.setText("Welcome to...");
		
		// DAB Label Settings
		dabLabel = new JLabel();
		dabLabel.setText("Destroy All Baddies!");
		
		// Host Icon
		ImageIcon hostIcon = createImageIcon("/resource/img/icon/host.png");
		
		// Host Button Settings
		hostButton = new JButton("Host", hostIcon);
        hostButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        hostButton.setHorizontalTextPosition(AbstractButton.CENTER);
        hostButton.setMnemonic(KeyEvent.VK_H);
        hostButton.setActionCommand("host");
        hostButton.setFocusPainted(false);
        
        // Join Icon
        ImageIcon joinIcon = createImageIcon("/resource/img/icon/join.png");
        
        // Join Button Settings
        joinButton = new JButton("Join", joinIcon);
        joinButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        joinButton.setHorizontalTextPosition(AbstractButton.CENTER);
        joinButton.setMnemonic(KeyEvent.VK_J);
        joinButton.setActionCommand("join");
        
        // Play Icon
        ImageIcon playIcon = createImageIcon("/resource/img/icon/play.png");
        
        // Play Filled Icon
        ImageIcon playFilledIcon = createImageIcon("/resource/img/icon/playFilled.png");
        
        // Launch Client and Server Button Settings
        launchBothButton = new JButton("Launch", playIcon);
        launchBothButton.setVerticalAlignment(AbstractButton.BOTTOM);
        launchBothButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        launchBothButton.setHorizontalAlignment(AbstractButton.CENTER);
        launchBothButton.setHorizontalTextPosition(AbstractButton.CENTER);
        launchBothButton.setMnemonic(KeyEvent.VK_L);
        launchBothButton.setActionCommand("launchBoth");
        launchBothButton.setRolloverIcon(playFilledIcon);
        launchBothButton.setVisible(false);
        
        // Launch Client Button Settings
        launchClientButton = new JButton("Launch", playIcon);
        launchClientButton.setVerticalAlignment(AbstractButton.BOTTOM);
        launchClientButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        launchClientButton.setHorizontalAlignment(AbstractButton.CENTER);
        launchClientButton.setHorizontalTextPosition(AbstractButton.CENTER);
        launchClientButton.setMnemonic(KeyEvent.VK_L);
        launchClientButton.setActionCommand("launchClient");
        launchClientButton.setRolloverIcon(playFilledIcon);
        launchClientButton.setVisible(false);
  
        // Listen for actions on the buttons
        hostButton.addActionListener(this);
        launchBothButton.addActionListener(this);
        joinButton.addActionListener(this);
        launchClientButton.addActionListener(this);

        // Add tool tips
        hostButton.setToolTipText("Click this button to host the game!");
        launchBothButton.setToolTipText("Click this button to launch the server and game!");
        joinButton.setToolTipText("Click this button to join a hosted game.");
        launchClientButton.setToolTipText("Click this button to join the game!");

        // IP Address Label Settings
        IPAddressLabel = new JLabel();
        IPAddressLabel.setText("IP Address:");
        IPAddressLabel.setVisible(false);
        
        // IP Address Field
        IPAddressField = new JTextField(20);
        IPAddressField.setVisible(false);
        
        // Character Name Label Settings
        nameLabel = new JLabel();
        nameLabel.setText("Character Name:");
        nameLabel.setVisible(false);
        
        // Character Name Field
        nameField = new JTextField(20);
        nameField.setVisible(false);
        
        // Constraints for the two fields to take up more space
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        
        // Add everything to the view
        add(IPAddressLabel);
        add(IPAddressField, c);
        add(nameLabel);
        add(nameField, c);
        add(welcomeLabel);
        add(dabLabel);
        add(hostButton);
        add(launchBothButton);
        add(joinButton);
        add(launchClientButton);
	}
	
	/**
	 * 
	 * Event Handler for the Buttons
	 * 
	 * @throws Exception could be thrown by Thread
	 *  
	 * @param e - ActionEvent - The action performed
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
        if ("join".equals(e.getActionCommand())) {
        	// Clear the screen and remove unused buttons
            joinButton.setVisible(false);
            remove(joinButton);
            hostButton.setVisible(false);
            remove(hostButton);
            welcomeLabel.setVisible(false);
            remove(welcomeLabel);
            dabLabel.setVisible(false);
            remove(dabLabel);
            // Add the two fields and launch button
            IPAddressLabel.setVisible(true);
            IPAddressField.setVisible(true);
            nameLabel.setVisible(true);
            nameField.setVisible(true);
            launchClientButton.setVisible(true);
            frame.setSize(300, 230);
        } else if("host".equals(e.getActionCommand())) {
        	// Clear the screen and remove unused buttons
            joinButton.setVisible(false);
            remove(joinButton);
            hostButton.setVisible(false);
            remove(hostButton);
            welcomeLabel.setVisible(false);
            remove(welcomeLabel);
            dabLabel.setVisible(false);
            remove(dabLabel);
            // Add the field and launch button
            nameLabel.setVisible(true);
            nameField.setVisible(true);
            launchBothButton.setVisible(true);
            frame.setSize(300, 180);
        } else if("launchBoth".equals(e.getActionCommand())) {
        	// Use Threads to Run the Server and Client simultaneously. Then wait for client, and the server to end.
        	try {
        		Thread serverThread = new Thread(new Runnable() {
        			public void run() {
        				GameServer.main(null);
        			}
        		});
        		serverThread.start();
        		String[] args = {nameField.getText(), "localhost"};
        		Thread clientThread = new Thread(new Runnable() {
        			public void run() {
        				GameClient.main(args);
        			}
        		});
        		clientThread.start();
        		frame.dispose();
        		clientThread.join();
        		serverThread.join();
        		System.exit(0);
			} catch (Exception e1) {
				// Required
				e1.printStackTrace();
			}
        } else if("launchClient".equals(e.getActionCommand())) {
        	// Use Threads to run the client, and then wait for the client to end
        	try {
        		String[] args = {nameField.getText(), IPAddressField.getText()};
        		Thread clientThread = new Thread(new Runnable() {
        			public void run() {
        				GameClient.main(args);
        			}
        		});
        		clientThread.start();
        		frame.dispose();
        		clientThread.join();
			} catch (Exception e1) {
				// Required
				e1.printStackTrace();
			}
        	System.exit(0);
        }
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     * 
     * @param path - String - The filepath of the image icon
     * 
     * @return an ImageIcon found at the path
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = DestroyAllBaddies.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety, 
     * this method should be invoked from the 
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {

        //Create and set up the window.
        frame = new JFrame("Destroy All Baddies");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        DestroyAllBaddies newContentPane = new DestroyAllBaddies();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.setSize(300, 155);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }
}
