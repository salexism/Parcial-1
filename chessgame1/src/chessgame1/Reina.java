package chessgame1;

import java.util.*;
import java.io.*;

public class Reina extends Pieza {

	// Constructor
	public Reina (int color, int x, int y) {
		
		super(color, x, y, "imagenes/d");
		//System.out.println("Reina.Reina(): \t\tCreado con color <" + color + "> y posicion (" + x + ", " + y + ")");
	}

	public String toString () {

		if (color == BLANCO)
			return "D";
		else
			return "d";
	}

	public Pieza copiar () {

		return new Reina(this.color, this.f, this.c);
	}

	public boolean validarMovimiento (Tablero tablero, int fF, int cF) {

		if (fF > 7 || fF < 0)
			return false;
		if (cF > 7 || cF < 0)
			return false;

		Torre t;
		Alfil a;
		
		//la reina se mueve como una torre o un alfil
		t = new Torre(this.color, this.f, this.c);
		a = new Alfil(this.color, this.f, this.c);
		
		//si se mueve como una torre o como un alfil, está bien
		if (t.validarMovimiento(tablero, fF, cF) || a.validarMovimiento(tablero, fF, cF))
			return true;
		return false;
	}

	public ArrayList posiblesMovimientos(Tablero t) {

		ArrayList a = new ArrayList();

		for (int df=-7; df<=7; df++)
			for (int dc=-7; dc<=7; dc++)
				if (validarMovimiento(t, this.f+df, this.c+dc))
					a.add(new Movimiento(this, this.f+df, this.c+dc));
		return a;
	}	
}
