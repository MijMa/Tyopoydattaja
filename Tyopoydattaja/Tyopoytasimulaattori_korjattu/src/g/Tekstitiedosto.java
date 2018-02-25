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
 *Tekstitiedoston sis�lt�m�n kuvanumeron ja tallennuspaikan lukemiseen k�ytetty luokka.
 */

public class Tekstitiedosto {
	
	static String luku = "0";
	static String polku = "";
/*Tekstitiedostossa numero ja tiedostopolku on erotettu pilkulla,
 * jolloin t�ss� niist� erotetaan lukuarvo ja polku erilleen k�ytt�� varten.
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
/*K�ytet��n luku-muuttujan eli kuvanumeron siirt�miseksi luokkien v�lill�.
 * Tarkistaa my�s, jos kyseess� on tyhj� tekstitiedosto niin se tulkitaan nollaksi,
 * jolloin TeeTiedosto-luokka osaa kirjata tiedostoon ykk�sen.
 */
    public static String getLuku(boolean next) throws FileNotFoundException, IOException {
    	if(luku == "")
    		luku = "0";
    	if (next == true)
    		luku = String.valueOf(Integer.parseInt(luku) + 1);
    	return luku;
    }
/*T�m�n avulla valitaan k�ytet��nk� kuvan tallentamisessa vanhaa muistissa olevaa tallennuspaikkaa
 * vai onko k�ytt�j� valinnut p��ikkunassa uuden.
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