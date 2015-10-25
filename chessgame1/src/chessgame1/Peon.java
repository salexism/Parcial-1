package chessgame1;

import java.util.*;
import java.io.*;

public class Peon extends Pieza {

	// Constructor
	public Peon (int color, int x, int y) {
		
		super(color, x, y, "imagenes/p");
		
		//System.out.println("Peon.Peon(): \t\tCreado con color <" + color + "> y posicion (" + x + ", " + y + ")");
	}

	public String toString () {

		if (color == BLANCO)
			return "P";
		else
			return "p";
	}

	public Pieza copiar () {

		return new Peon(this.color, this.f, this.c);
	}

	public boolean validarMovimiento (Tablero tablero, int fF, int cF) {

		if (fF > 7 || fF < 0)
			return false;
		if (cF > 7 || cF < 0)
			return false;

		Pieza p;

		// valores para el movimiento de la pieza
		int incremento = (this.color==BLANCO)?-1:1;
		int filaSalida = (this.color==BLANCO)?6:1;

		p = tablero.get(fF, cF);

		//System.out.println("Peon.validarMovimiento(): posicion actual del peon <"+this.f+","+this.c+">");
		//System.out.println("Peon.validarMovimiento(): lo movemos a             <"+fF+","+cF+">");

		// movimiento normal de los peones
		if ( (this.c == cF) && (this.f + incremento == fF) ) {
			// no puede comer asi
			if ( p != null )
				return false;
			return true;
		}
		//movimiento inicial de los peones
		else if ( (this.c == cF) && (this.f == filaSalida) && (fF == filaSalida + 2*incremento) ) {
			// no puede comer asi
			if ( p != null )
				return false;

			// verificamos que no haya una ficha en la mitad
			p = tablero.get(this.f + incremento, this.c);
			// no puede saltar a otra ficha
			if ( p != null )
				return false;
			return true;
		}
		//movimiento de comer
		else if ( ((cF == this.c + 1) || (cF == this.c - 1)) && (fF == this.f + incremento) ) {
			// no hay nada que comer
			if ( p == null )
				return false;
			// no es una ficha rival
			if ( p.getColor() == this.color )
				return false;
			return true;
		}

		return false;
	}

    public ArrayList posiblesMovimientos(Tablero t) {

	ArrayList a = new ArrayList();

	for (int df=-2; df<=2; df++)
		if (df != 0)
			for (int dc=-1; dc<=1; dc++)
				if (validarMovimiento(t, this.f+df, this.c+dc)) {
					a.add(new Movimiento(this, this.f+df, this.c+dc));
					//System.out.println("Peon.posiblesMovimientos(): De <"+this.f+","+this.c+"> a <"+(this.f+df)+","+(this.c+dc)+">");
				}
	return a;
    }	
}
