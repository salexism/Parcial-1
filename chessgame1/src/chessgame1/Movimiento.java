package chessgame1;

import java.io.*;

public class Movimiento {

	Pieza p;
	int fF;
	int cF;

	// Constructor
	public Movimiento (Pieza p, int fF, int cF) {
	
		this.p = p;
		this.fF = fF;
		this.cF = cF;	
	}

	public Pieza getPieza () {

		return p;
	}

	public int getFilaFinal () {

		return fF;
	}

	public int getColumnaFinal () {

		return cF;
	}

	public String toString () {

		return "<"+p.toString()+"> de <"+p.getFila()+","+p.getColumna()+"> a <"+fF+","+cF+">";
	}
}
