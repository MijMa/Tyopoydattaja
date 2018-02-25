package g;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * 
 * @author Mio
 *
 *Luokka sisältää pienen ikkunan näppäinpainalluksien vastaanottamista varten ja
 *hoitaa kuvan ottamisen sekä tallentamisen.
 */

public class Kuvittajahotkey extends JFrame implements KeyListener, ActionListener{
	
    JTextField typingArea;
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					createAndShowGUI();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
	}
    
static Kuvittajahotkey frame = new Kuvittajahotkey("Kuvan nappaus");
//Mini-ikkuna näppäinpainalluksia vastaanottamiseen
static void createAndShowGUI() throws IOException, FileNotFoundException {
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addComponentsToPane(); 
    frame.pack();
    frame.setVisible(true);
}

private void addComponentsToPane() {    
    typingArea = new JTextField(5);
    typingArea.addKeyListener(this);
    typingArea.setFocusTraversalKeysEnabled(false);    
    getContentPane().add(typingArea, BorderLayout.PAGE_START);
}

public Kuvittajahotkey(String name) {
    super(name);
}
//Tallentaa kuvan ja myös piilottaa ikkunan kuvan ottamisen ajaksi, jotta kuvassa ei ole ylimääräistä.
public void keyPressed(KeyEvent e) {
    	if (e.getKeyCode() == 65){
    		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        	BufferedImage capture = null;
    		try {
				frame.setExtendedState(JFrame.ICONIFIED);
    			capture = new Robot().createScreenCapture(screenRect);
			} catch (AWTException e2) {
				e2.printStackTrace();
			}
        	File pisteFile = null;
			try {
				pisteFile = new File("kuva" + Tekstitiedosto.getLuku(true) + ".jpg");
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
        	try {
				// uusi
        		// ImageIO.write(capture, "jpg", new File("C:\Users\(user)\workspace\Tyopoytasimulaattori_korjattu\src\kuvittajan"));
        		// vanha(edit 15.11.2017)
        		ImageIO.write(capture, "jpg", new File(Tekstitiedosto.getVanhaPolku() + "\\" + pisteFile));
        		System.out.println("Kuva tallennettu!");
        		System.out.println(Tekstitiedosto.getVanhaPolku() + "\\" + pisteFile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        	
        	frame.setExtendedState(JFrame.NORMAL);
        	try {
        		TeeTiedosto.numeroLaskuri();
        	} catch (IOException e1) {
        		e1.printStackTrace();
        	}
    	}
    }


@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}
