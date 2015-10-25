package chessgame1;

import java.util.*;
import java.io.*;

public class Torre extends Pieza {

	// Constructor
	public Torre (int color, int x, int y) {
		
		super(color, x, y, "imagenes/t");
		//System.out.println("Torre.Torre(): \t\tCreado con color <" + color + "> y posicion (" + x + ", " + y + ")");
	}

	public String toString () {

		if (color == BLANCO)
			return "T";
		else
			return "t";
	}	

	public Pieza copiar () {

		return new Torre(this.color, this.f, this.c);
	}

	public boolean validarMovimiento (Tablero tablero, int fF, int cF) {

		if (fF > 7 || fF < 0)
			return false;
		if (cF > 7 || cF < 0)
			return false;

		int difFil, difCol, diferencia;
		Pieza p;

		difFil = fF - this.f;
		difCol = cF - this.c;

		p = tablero.get(fF, cF);

		//se debe mover sobre la misma fila o sobre la misma columna
		if ((this.f == fF) || (this.c == cF)) {
			// solo podemos comer una ficha del enemigo
			if (p != null )
				if (p.getColor() == this.color)
					return false;
			
			// la torre no puede saltar sobre otras fichas
			diferencia = Math.abs(difFil) + Math.abs(difCol);
			for (int i = 1; i < diferencia; i++) {	
				p = tablero.get(this.f + i * (difFil / diferencia), this.c + i * (difCol / diferencia));
				// la torre trata de saltar
				if (p != null )
					return false;
			}
			return true;
		}
		else
			return false;
	}

	public ArrayList posiblesMovimientos(Tablero t) {

		ArrayList a = new ArrayList();

		for (int df=-7; df<=7; df++)
			if (validarMovimiento(t, this.f+df, this.c))
				a.add(new Movimiento(this, this.f+df, this.c));
		for (int dc=-7; dc<=7; dc++)
			if (validarMovimiento(t, this.f, this.c+dc))
				a.add(new Movimiento(this, this.f, this.c+dc));
		return a;
	}	
}
