package chessgame1;

import java.util.*;
import java.io.*;

public class Rey extends Pieza {

	// Constructor
	public Rey (int color, int x, int y) {
		
		super(color, x, y, "imagenes/r");
		//System.out.println("Rey.Rey(): \t\tCreado con color <" + color + "> y posicion (" + x + ", " + y + ")");
	}

	public String toString () {

		if (color == BLANCO)
			return "R";
		else
			return "r";
	}

	public Pieza copiar () {

		return new Rey(this.color, this.f, this.c);
	}

	public boolean validarMovimiento (Tablero tablero, int fF, int cF) {	

		if (fF > 7 || fF < 0)
			return false;
		if (cF > 7 || cF < 0)
			return false;		

		int difFil, difCol;
		Pieza p;
		
		p = tablero.get(fF, cF);

		difFil = this.f - fF;
		difCol = this.c - cF;

		//solo se mueve una posicion alrededor
		if (Math.abs(difFil) <= 1 && Math.abs(difCol)<= 1) {
			// solo podemos comer piezas enemigas
			if (p != null )
				if (p.getColor() == this.color)
					return false;
			return true;
		}
		else
			return false;
	}

	public ArrayList posiblesMovimientos(Tablero t) {

		ArrayList a = new ArrayList();

		for (int df=-1; df<=1; df++)
			for (int dc=-1; dc<=1; dc++)
				if (validarMovimiento(t, this.f+df, this.c+dc))
					a.add(new Movimiento(this, this.f+df, this.c+dc));
		return a;
	}	
}
