package chessgame1;

import java.io.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.*;

public class JugadorHumano extends Jugador {

	// Constructor
	public JugadorHumano (String nombre, int color, Tablero t) {
		
		super(nombre, color, t);
		//System.out.println("JugadorHumano.JugadorHumano(): " + nombre + " creado.");
	}

	public void mover () {
		
		int fI = 0;
		int cI = 0;
		int fF = 0;
		int cF = 0;
		boolean valido = false;
		boolean direccionValida;
		String movimiento; // = JOptionPane.showInputDialog("Movimiento:\nEj: A2 A4");
		String titulo = new String("Movimiento de "+ this.nombre + ", color " +(this.color==BLANCO?"BLANCO":"NEGRO")+":\nEj: A7 A5");

		while (!valido) {

			movimiento = JOptionPane.showInputDialog(titulo);
			while (movimiento == null || movimiento.equals("") ) {
				JOptionPane.showMessageDialog( null, this.nombre+", introduce un movimiento." );
				movimiento = JOptionPane.showInputDialog(titulo);
			}

			// validar formato del movimiento introducido
			movimiento = movimiento.toUpperCase();
			direccionValida = true;
			if (movimiento.length() != 5) {
				JOptionPane.showMessageDialog( null, this.nombre+", formato de direccion invalido." );
				direccionValida = false;
			}
			if (direccionValida && ((movimiento.charAt(0) < 'A' || movimiento.charAt(0) > 'H') || (!Character.isDigit(movimiento.charAt(1))))) {
				JOptionPane.showMessageDialog( null, this.nombre+", formato de la direccion de inicio invalido" );
				direccionValida = false;
			}
			if (direccionValida && (movimiento.charAt(2) != ' ')) {
				JOptionPane.showMessageDialog( null, this.nombre+", formato de direccion invalido." );
				direccionValida = false;
			}
			if (direccionValida && ((movimiento.charAt(3) < 'A' || movimiento.charAt(3) > 'H') || (!Character.isDigit(movimiento.charAt(4))))) {
				JOptionPane.showMessageDialog( null, this.nombre+", formato de la direccion de fin invalido." );
				direccionValida = false;
			}
			if (direccionValida) {
				fI = Integer.parseInt(movimiento.charAt(1)+"") - 1;
	   			cI = movimiento.charAt(0) - 'A';
				fF = Integer.parseInt(movimiento.charAt(4)+"") - 1;
	   			cF = movimiento.charAt(3) - 'A';

				valido = jugadaValida(fI, cI, fF, cF);
				if (!valido)
					JOptionPane.showMessageDialog( null, this.nombre+", ese movimiento es invalido" );
			}
		}

		realizarJugada(fI, cI, fF, cF);
	}

	public boolean jugadaValida (int fI, int cI, int fF, int cF) {

		Pieza p, fFin;
		//Cuadro c;
		
		p = tablero.get(fI, cI);

		// no hay ficha
		if ( p == null )
			return false;

		//no es el color que esta jugando
		if ( p.getColor() != this.color )
			return false;

		return p.validarMovimiento(tablero, fF, cF);
	}
}
