package chessgame1;

import java.util.*;
import javax.swing.*;
import java.io.*;

public abstract class Pieza implements Constantes {

	// estado de la pieza en la partida
	boolean comida = false;
	// color de la pieza
	int color;
	// posicion de la pieza en el tablero de juego	
	int f, c;
	ImageIcon icono;

	// constructor
	// string contiene la ruta parcial al icono que se completa en funcion de su color
	//
	public Pieza (int color, int x, int y, String ruta) {

		this.color = color;
		f = x;
		c = y;
		if (color == BLANCO)
			icono = new ImageIcon(ruta+"b.gif");
		else
			icono = new ImageIcon(ruta+"n.gif");

		//System.out.println("Pieza.Pieza(): Me acaban de crear");
	}

	public ImageIcon getImagen () {
		
		return icono;	
	}	

	public abstract String toString ();

	public int getColor () {
		
		return color;
	}

	public void setPosicion (int f, int c) {

		this.f = f;
		this.c = c;
	}

	public int getFila () {

		return f;
	}

	public int getColumna () {

		return c;
	}

	public void setEstado (boolean comida) {
	
		this.comida = comida;
	}

	public boolean getEstado () {

		return comida;
	}

	// Retorna un valor verdadero si la pieza puede ser comida por otra sobre el tablero t
	//
	public boolean amenazada (Tablero t) {

		for (int fila=0; fila<8; fila++)
			for (int columna=0; columna<8; columna++)
				if (t.get(fila, columna) != null) {
					Pieza p = t.get(fila, columna);
					if (p.validarMovimiento(t, this.f, this.c))
						return true;
				}
		return false;
	}

	public abstract Pieza copiar ();
	public abstract boolean validarMovimiento (Tablero tablero, int fF, int cF);
	public abstract ArrayList posiblesMovimientos(Tablero t);
}
