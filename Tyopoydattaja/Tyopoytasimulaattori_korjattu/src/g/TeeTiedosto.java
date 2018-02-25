package g;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Markus
 *
 *Hoitaa tekstitiedoston luomisen, jos sitä ei ole ja pitää tiedoston ajantasalla kuvia otettaessa.
 */

public class TeeTiedosto	{

/*Jos tiedosto joudutaan luomaan, tämä lisää sinne numeroksi ykkösen 
 *ja siitä eteenpäin tallentaa numeron sekä kuvien tallennuspaikan muistiin tekstitiedostoon. 
 */
public TeeTiedosto() throws IOException {

	File data = new File("Data.txt");
	Writer kirjoittaja = new FileWriter(data);
	int n = Integer.parseInt(Tekstitiedosto.getLuku(false));

	if (n<1) {
		kirjoittaja.write(String.valueOf(1));
	}
	else {
		kirjoittaja.write(String.valueOf(n)+","+Tekstitiedosto.getVanhaPolku());
	}
	kirjoittaja.close();
}


//Kirjaa tiedostoon aina senhetkisen kuvanumeron ja tallennuspaikan kun kuva otetaan.
	public static void numeroLaskuri() throws IOException {
		Writer kirjoittaja2 = new FileWriter("Data.txt");
		kirjoittaja2.write(Tekstitiedosto.getLuku(false)+","+Tekstitiedosto.getVanhaPolku());
		kirjoittaja2.close();

	}
/*Pitää kirjaa tallennuspaikasta, jos sitä vaihdetaan pääikkunassa, 
 * eikä käytetä enää viimeksi tallennettua paikkaa.	
 */
	public static void polkuVahti() throws IOException {
		Writer kirjoittaja3 = new FileWriter("Data.txt");
		kirjoittaja3.write(Tekstitiedosto.getLuku(false)+","+Ikkuna.getPolku());
		kirjoittaja3.close();
	}
}