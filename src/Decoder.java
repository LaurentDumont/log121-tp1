import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decoder {
	private String[] Split;
	private String Match;
	private int ID;
	private int Data1;
	private int Data2;
	private int Data3;
	private int Data4;
	private String Forme;

	public Decoder(String s) {
		Match = s;
		DataDecoder();
	}

	public void DataDecoder() {
		// compilateur pour déterminer la forme que prendre la donnée du serveur
		Pattern p = Pattern.compile("(\\d)+\\s<(.*)>(\\s(\\d)+)+");

		Matcher m = p.matcher(Match);

		// Si l'expression est trouvée
		while (m.find()) {
			// Divise l'information à chaque fois qu'il y a une espace
			Split = m.group().split(" ");
			// entre le nom de la forme sans les <> qui l'entoure
			this.Split[1] = m.group(2);
			// Convertit le String du ID en integer
			ID = Integer.parseInt(Split[0]);
			// l'ajout du nom sans <> dans sa variable
			Forme = Split[1];
			// ajout de l'information entrée
			Data1 = Integer.parseInt(Split[2]);
			Data2 = Integer.parseInt(Split[3]);
			Data3 = Integer.parseInt(Split[4]);
			// S'il y a une quatrième donnée, entrer cette information
			if (Split.length == 6) {
				Data4 = Integer.parseInt(Split[5]);
			}
		}
	}

	// getter des différentes information à être utiliser plus tard
	public int getID() {
		return ID;
	}

	public int getData1() {
		return Data1;
	}

	public int getData2() {
		return Data2;
	}

	public int getData3() {
		return Data3;
	}

	public int getData4() {
		return Data4;
	}

	public String getForme() {
		return Forme;
	}

}
