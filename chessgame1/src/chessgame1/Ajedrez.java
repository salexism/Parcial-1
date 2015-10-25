package chessgame1;

import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Ajedrez extends JFrame implements Constantes{

	Tablero t;
	Jugador jH;
	Jugador jM;

	public Ajedrez () {
		
        	this.setSize(500,500);
        	this.setTitle("Ajedrez 1.0");
		//this.setVisible(true);
		this.addWindowListener( new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					dispose();
					System.exit(0);
				}
			}
		);

		// crear tablero y jugadores
		t = new Tablero(this);
		jH = new JugadorHumano("Tatiana Gonzalez", BLANCO, t);
		jM = new JugadorMaquina("CPU", NEGRO, t);

		Container cp = getContentPane();
        	cp.setLayout(new FlowLayout());

		t.crearTableroGUI(this);
		t.mostrarGUI();

		if (DEBUG)
			t.mostrar();
		show();

		// desarrollo de la partida
		while (!jH.haPerdido() && !jM.haPerdido() && !t.getTablas()) {
			
			jH.mover();
			jM.mover();
		}

		if (t.getTablas())
			JOptionPane.showMessageDialog(null, "La partida ha acabado en tablas");
		else if (jH.haPerdido())
			JOptionPane.showMessageDialog(null, jM.getNombre()+", ha ganado!!");
		else
			JOptionPane.showMessageDialog(null, jH.getNombre()+", ha ganado!!");

		this.dispose();
	}
	
	public static void main(String[] args) {

		System.out.println("Ajedrez.main(): Arrancando GUI");
		new Ajedrez();
	}
}
