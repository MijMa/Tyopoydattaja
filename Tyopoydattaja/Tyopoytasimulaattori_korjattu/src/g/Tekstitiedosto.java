package g;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.File;

/**
 * 
 * @author Markus
 * 
 *Tekstitiedoston sisältämän kuvanumeron ja tallennuspaikan lukemiseen käytetty luokka.
 */

public class Tekstitiedosto {
	
	static String luku = "0";
	static String polku = "";
/*Tekstitiedostossa numero ja tiedostopolku on erotettu pilkulla,
 * jolloin tässä niistä erotetaan lukuarvo ja polku erilleen käyttöä varten.
 */
    public Tekstitiedosto() throws FileNotFoundException, IOException 
    {
    	FileInputStream tiedosto = new FileInputStream(new File("Data.txt"));
        Scanner scanner = new Scanner(tiedosto,"UTF-8").useDelimiter("/A");
        luku = scanner.hasNext() ? scanner.next() : "";
        int index = luku.lastIndexOf(",");
        if (index > 0) {
        	polku = luku.substring(index+1);
        	luku = (String) luku.subSequence(0, index);
        	System.out.println("Muistissa oleva tallennuspaikka: " + polku);
        }
        System.out.println("Kuvanumero on " + luku);
        scanner.close();
    }
/*Käytetään luku-muuttujan eli kuvanumeron siirtämiseksi luokkien välillä.
 * Tarkistaa myös, jos kyseessä on tyhjä tekstitiedosto niin se tulkitaan nollaksi,
 * jolloin TeeTiedosto-luokka osaa kirjata tiedostoon ykkösen.
 */
    public static String getLuku(boolean next) throws FileNotFoundException, IOException {
    	if(luku == "")
    		luku = "0";
    	if (next == true)
    		luku = String.valueOf(Integer.parseInt(luku) + 1);
    	return luku;
    }
/*Tämän avulla valitaan käytetäänkö kuvan tallentamisessa vanhaa muistissa olevaa tallennuspaikkaa
 * vai onko käyttäjä valinnut pääikkunassa uuden.
 */
    public static String getVanhaPolku() {
    	if(polku!="") {
    		if(polku!=Ikkuna.getPolku() && Ikkuna.getPolku()!="") {
    			return Ikkuna.getPolku();
    		}else if(polku!=Ikkuna.getPolku() && Ikkuna.getPolku()=="") {
    			return polku;
    		}			
    	}
    	return Ikkuna.getPolku();
    }
}