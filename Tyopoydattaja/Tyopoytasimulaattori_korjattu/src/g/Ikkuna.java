 package g;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author Markus
 *
 *Screenshot-ohjelman p‰‰ikkuna, josta ohjataan muita toimintoja.
 */

public class Ikkuna extends JFrame {
	
	JLabel label;
	JButton nappi1;
	JButton nappi2;
	JButton nappi3;
	JFrame kuvaikkuna;
    static String polku = "";
	
	
  public Ikkuna() {
	super("J‰nn‰ ikkuna");
    //Alustetaan ikkunan tarvittavat osat ja asetetaan tarvittavat arvot nappien sijoituksiin ja ikkunan koolle.
	setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(700, 400);
    setLocationRelativeTo(null);
    setVisible(true);    
    label = new JLabel(); 
    label.setBounds(10, 10, 670, 250);
    kuvaikkuna = new JFrame();
    kuvaikkuna.setSize(1920, 1080);
    nappi1 = new JButton("Katso kuvia");
    nappi1.setBounds(100, 300, 170, 40);
    nappi2 = new JButton("Ota kuva");
    nappi2.setBounds(280, 300, 150, 40);
    nappi3 = new JButton("Valitse tallennuspaikka");
    nappi3.setBounds(440, 300, 170, 40);
    add(nappi1);
    add(nappi2);
    add(nappi3);
    kuvaikkuna.add(label);
    kuvaikkuna.setContentPane(label);
    
    //Kuvien katsomiseen k‰ytetty n‰pp‰in, joka avaa valitun kuvan uuteen ikkunaan.
    nappi1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        JFileChooser valitsija = new JFileChooser();
        valitsija.setCurrentDirectory(new File(System.getProperty("user.home")));
        valitsija.setDialogTitle("Valitse kuva");
        int returnValue = valitsija.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File valittu = valitsija.getSelectedFile();
          String path = valittu.getAbsolutePath();
          kuvaikkuna.setVisible(true);
          label.setIcon(ResizeImage(path));
          System.out.println("Avattiin " + valittu.getName());
        }
        else if(returnValue == JFileChooser.CANCEL_OPTION) {
        	System.out.println("Kuvaa ei valittuna");
        }
      }
    });
    
    //N‰pp‰imen kautta poistutaan p‰‰ikkunasta pieneen hotkey-ikkunaan, josta kuvat otetaan.
    nappi2.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e)  {
    		setVisible(false);
    		try {
				Kuvittajahotkey.createAndShowGUI();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    	}
    });
    
    //N‰pp‰imell‰ valitaan kuvien tallennuskansio.
    String choosertitle = "Tallennuspaikka";
    nappi3.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {            
    	   JFileChooser tallentaja = new JFileChooser(); 
    	    tallentaja.setCurrentDirectory(new File(System.getProperty("user.home")));
    	    tallentaja.setDialogTitle(choosertitle);
    	    tallentaja.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	    tallentaja.setAcceptAllFileFilterUsed(false);
    	    //    
    	    if (tallentaja.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) { 
    	      System.out.println("Nykyinen tallennuspaikka: " + tallentaja.getSelectedFile());
    	      //Edit 15.11.2017 / -> \\
    	      polku = tallentaja.getCurrentDirectory() + "\\" + tallentaja.getSelectedFile().getName();
    	      try {
				TeeTiedosto.polkuVahti();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    	      }
    	    else {
    	      System.out.println("Tallennuspaikkaa ei valittu");
    	      }
    	     }
    	
    	});
        
  }
  //Tallennuspaikan valitsemisessa saatu polku siirret‰‰n luokasta toiseen t‰ll‰.
  public static String getPolku() {
	  return polku;
  }
  //Hoitaa kuvan avaamisen nappi1:n takana. 
  public ImageIcon ResizeImage(String Imagepath) {
	ImageIcon MyImage = new ImageIcon(Imagepath);
	Image img = MyImage.getImage();
	Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
	ImageIcon image = new ImageIcon(newImg);
	return image; 
  }
  //Suorittaa ohjelman.
  public static void main(String[] args) throws IOException, FileNotFoundException {
	try {
		new Tekstitiedosto();
	} catch (IOException e) {
		e.printStackTrace();
	}
	new TeeTiedosto();
	new Ikkuna();
  }
}
