package dab;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

@SuppressWarnings("serial")
public class DestroyAllBaddies extends JPanel
implements ActionListener {
	protected JButton b1, b3, b2, b4;
	protected JTextField t1, t2;
	protected JLabel l1, l2;
	
	public DestroyAllBaddies() {
		b1 = new JButton("Host");
		Insets m = new Insets(10,10,10,10);
		b1.setMargin(m);
        b1.setVerticalAlignment(AbstractButton.CENTER);
        b1.setHorizontalAlignment(AbstractButton.LEFT);
        b1.setMnemonic(KeyEvent.VK_H);
        b1.setActionCommand("host");

        b3 = new JButton("Join");
        b3.setMargin(m);
        b3.setVerticalAlignment(AbstractButton.CENTER);
        b3.setHorizontalAlignment(AbstractButton.RIGHT);
        b3.setMnemonic(KeyEvent.VK_J);
        b3.setActionCommand("join");
        
        b2 = new JButton("Launch");
        b2.setMargin(m);
        b2.setVerticalAlignment(AbstractButton.BOTTOM);
        b2.setHorizontalAlignment(AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_L);
        b2.setActionCommand("launchBoth");
        b2.setVisible(false);
        
        b4 = new JButton("Launch");
        b4.setMargin(m);
        b4.setVerticalAlignment(AbstractButton.BOTTOM);
        b4.setHorizontalAlignment(AbstractButton.CENTER);
        b4.setMnemonic(KeyEvent.VK_L);
        b4.setActionCommand("launchClient");
        b4.setVisible(false);

        //Listen for actions on the buttons
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        b1.setToolTipText("Click this button to host the game!");
        b2.setToolTipText("Click this button to launch the server and game!");
        b3.setToolTipText("Click this button to join a hosted game.");
        b4.setToolTipText("Click this button to join the game!");

        l1 = new JLabel();
        l1.setText("IP Address:");
        l1.setVisible(false);
        l2 = new JLabel();
        l2.setText("Character Name:");
        l2.setVisible(false);
        
        t1 = new JTextField(20);
        t1.setVisible(false);
        
        t2 = new JTextField(20);
        t2.setVisible(false);
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        
        add(l1);
        add(t1, c);
        add(l2);
        add(t2, c);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
	}
	
	public void actionPerformed(ActionEvent e) {
		Runtime runTime = Runtime.getRuntime();
        if ("join".equals(e.getActionCommand())) {
            b3.setVisible(false);
            remove(b3);
            b1.setVisible(false);
            remove(b1);
            l1.setVisible(true);
            t1.setVisible(true);
            l2.setVisible(true);
            t2.setVisible(true);
            b4.setVisible(true);
        } else if("host".equals(e.getActionCommand())) {
            b3.setVisible(false);
            remove(b3);
            b1.setVisible(false);
            remove(b1);
            l2.setVisible(true);
            t2.setVisible(true);
            b2.setVisible(true);
        } else if("launchBoth".equals(e.getActionCommand())) {
        	try {
        		runTime.exec(DABExec.getServerExec());
        		
        		runTime.exec(DABExec.getClientExec() + t2.getText() + " localhost");
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        	System.exit(0);
        } else if("launchClient".equals(e.getActionCommand())) {
        	try {
        		runTime.exec(DABExec.getClientExec() + t2.getText() + " " + t1.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
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
        JFrame frame = new JFrame("Destroy All Baddies");
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
