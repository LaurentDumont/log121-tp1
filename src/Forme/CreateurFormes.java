package Forme;

import main_package.Decoder;

public class CreateurFormes {

	public Forme creerForme(String chaineForme) {
		Decoder code = new Decoder(chaineForme);

		if (code.getForme().equals("LIGNE")) {
			return new Line(code.getData1(), code.getData2(), code.getData3(), code.getData4());
		}

		else if (code.getForme().equals("CARREE")) {
			return new Square(code.getData1(), code.getData2(), code.getData3(), code.getData4());
		}

		else if (code.getForme().equals("RECTANGLE")) {
			return new Rectangle(code.getData1(), code.getData2(), code.getData3(), code.getData4());
		}

		else if (code.getForme().equals("OVALE")) {
			return new Oval(code.getData1(), code.getData2(), code.getData3(), code.getData4());
		}

		else if (code.getForme().equals("Circle")) {
			return new Circle(code.getData1(), code.getData2(), code.getData3());
		} else
			return null;
	}
}
