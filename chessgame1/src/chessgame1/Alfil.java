package chessgame1;

import java.util.*;
import java.io.*;

public class Alfil extends Pieza {

	// Constructor
	public Alfil (int color, int x, int y) {
		
		super(color, x, y, "imagenes/a");
		//System.out.println("Alfil.Alfil(): \t\tCreado con color <" + color + "> y posicion (" + x + ", " + y + ")");
	}

	public String toString () {

		if (color == BLANCO)
			return "A";
		else
			return "a";
	}

	public Pieza copiar () {

		return new Alfil(this.color, this.f, this.c);
	}

	public boolean validarMovimiento (Tablero tablero, int fF, int cF) {
		
		if (fF > 7 || fF < 0)
			return false;
		if (cF > 7 || cF < 0)
			return false;

		// diferencia entre filas y columnas para el movimiento
		int difFil, difCol;

		Pieza p;

		p = tablero.get(fF, cF);

		difFil = Math.abs(this.f - fF);
		difCol = Math.abs(this.c - cF);
		
		//un valor igual para ambas diferencias indica un moviento en diagonal
		if ( (difFil == difCol) ) {
			// la casilla destino no esta vacia
			if (p != null )
				//no se puede comer una pieza de igual color
				if (p.getColor() == this.color)
					return false;

			// el alfil no puede saltar a otras fichas
			for ( int i = 1; i < difFil; i++ ) {
				p = tablero.get( this.f + i * ((fF - this.f) / difFil), this.c + i * ((cF - this.c) / difFil));
				// trata de saltar a otra ficha
				if (p != null )
					return false;
			}
			return true;
		}
		// no es un movimiento en diagonal
		else
			return false;
	}

	public ArrayList posiblesMovimientos(Tablero t) {

		ArrayList a = new ArrayList();

		for (int df=-7; df<=7; df++)
			if (df != 0)
				for (int dc=-7; dc<=7; dc++)
					if (dc != 0)
						if (validarMovimiento(t, this.f+df, this.c+dc)) {
							a.add(new Movimiento(this, this.f+df, this.c+dc));
							//System.out.println("Alfil.posiblesMovimientos(): De <"+this.f+","+this.c+"> a <"+(this.f+df)+","+(this.c+dc)+">");
						}
		return a;
	}
}
