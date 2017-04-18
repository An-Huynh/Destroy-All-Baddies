package dab;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
		Insets m = new Insets(10,10,10,10); // Margin for all buttons
		// Welcome Label Settings
		welcomeLabel = new JLabel();
		welcomeLabel.setText("Welcome to...");
		
		// DAB Label Settings
		dabLabel = new JLabel();
		dabLabel.setText("Destroy All Baddies!");	
		
		// Host Button Settings
		hostButton = new JButton("Host");
		hostButton.setMargin(m);
        hostButton.setVerticalAlignment(AbstractButton.CENTER);
        hostButton.setHorizontalAlignment(AbstractButton.CENTER);
        hostButton.setMnemonic(KeyEvent.VK_H);
        hostButton.setActionCommand("host");
        hostButton.setPreferredSize(new Dimension(100,100));
        
        // Join Button Settings
        joinButton = new JButton("Join");
        joinButton.setLayout(null);
        joinButton.setMargin(m);
        joinButton.setVerticalAlignment(AbstractButton.CENTER);
        joinButton.setHorizontalAlignment(AbstractButton.CENTER);
        joinButton.setMnemonic(KeyEvent.VK_J);
        joinButton.setActionCommand("join");
        joinButton.setPreferredSize(new Dimension(100,100));
        
        // Launch Client and Server Button Settings
        launchBothButton = new JButton("Launch");
        launchBothButton.setMargin(m);
        launchBothButton.setVerticalAlignment(AbstractButton.BOTTOM);
        launchBothButton.setHorizontalAlignment(AbstractButton.CENTER);
        launchBothButton.setMnemonic(KeyEvent.VK_L);
        launchBothButton.setActionCommand("launchBoth");
        launchBothButton.setVisible(false);
        
        // Launch Client Button Settings
        launchClientButton = new JButton("Launch");
        launchClientButton.setMargin(m);
        launchClientButton.setVerticalAlignment(AbstractButton.BOTTOM);
        launchClientButton.setHorizontalAlignment(AbstractButton.CENTER);
        launchClientButton.setMnemonic(KeyEvent.VK_L);
        launchClientButton.setActionCommand("launchClient");
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
	
	public void actionPerformed(ActionEvent e) {
		//Runtime runTime = Runtime.getRuntime();
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
        } else if("launchBoth".equals(e.getActionCommand())) {
        	// Use Runtime and DABExec to execute the command line to run the server and client
        	try {
        		//runTime.exec(DABExec.getServerExec());
        		ServerExec server = new ServerExec();
        		Thread serverThread = new Thread(server);
        		serverThread.start();
        		String[] args = {nameField.getText(), "localhost"};
        		ClientExec client = new ClientExec(args);
        		Thread clientThread = new Thread(client);
        		clientThread.start();
        		frame.setVisible(false);
        		clientThread.join();
        		serverThread.join();
        		System.exit(0);
        		//runTime.exec(DABExec.getClientExec() + nameField.getText() + " localhost");
			} catch (Exception e1) {
				// Required
				e1.printStackTrace();
			}
        } else if("launchClient".equals(e.getActionCommand())) {
        	// Use Runtime and DABExec to execute the command line to run the client
        	try {
        		//runTime.exec(DABExec.getClientExec() + nameField.getText() + " " + IPAddressField.getText());
        		String[] args = {nameField.getText(), IPAddressField.getText()};
        		ClientExec client = new ClientExec(args);
        		Thread clientThread = new Thread(client);
        		clientThread.start();
        		frame.setVisible(false);
        		clientThread.join();
			} catch (Exception e1) {
				// Required
				e1.printStackTrace();
			}
        	System.exit(0);
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
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
        frame.setSize(300, 300);
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
