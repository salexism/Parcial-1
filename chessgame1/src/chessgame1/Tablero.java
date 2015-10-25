package chessgame1;

import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Tablero {

	private JPanel tableroJP;
	private Pieza[][] tablero;
	private Casilla[][] casillas;
	private boolean tablas = false;

	// Constructor
	public Tablero () {
		
		tablero = new Pieza[8][8];
		casillas = new Casilla[8][8];
		//System.out.println("Tablero.Tablero(): Me acaban de crear");
	}

	// inicializa un tablero como copia de otro (sin interfaz grafica)
	public Tablero (Tablero aCopiar) {

		tablero = new Pieza[8][8];
		casillas = new Casilla[8][8];

		for (int f=0; f<8; f++)
			for (int c=0; c<8; c++)
				if (aCopiar.get(f, c) != null)
					tablero[f][c] = (aCopiar.get(f, c)).copiar();
	}

	// version con GUI
	public Tablero (JFrame f) {

		this();
		Container c = f.getContentPane();
		tableroJP = new JPanel();
		c.add(tableroJP, "Center");
	}

	public void add (int x, int y, Pieza p) {
		
		tablero[x][y] = p;
	}

	public Pieza get (int f, int c) {

		return tablero[f][c];
	}

	public void mostrar () {
		
		System.out.println("Tablero.show():");
		for (int f=0; f<8; f++) {
			//System.out.print("F");
			for (int c=0; c<8; c++) {
				if (tablero[f][c] != null)
					System.out.print((tablero[f][c]).toString() + " "); //getClass().getName());
				else
					System.out.print("  ");
			}
			System.out.println();
		}
	}

	// representa la situacion actual del teclado en la pantalla
	//
	public void mostrarGUI () {
		
		for (int i=0; i < 8; i++) {
			for (int j=0; j < 8; j++) {
				if (tablero[j][i] != null)
					(casillas[j][i]).setIcon((tablero[j][i]).getImagen());
				else
					(casillas[j][i]).setIcon(null);
			}
		}
	}

	// crea el tablero inicialmente vacio
	//
	public void crearTableroGUI (JFrame f) {

        	tableroJP.setLayout(new GridLayout(10, 10));
        	filaLetras(tableroJP);
        	for (int i = 0; i < 8; i++) {
            		tableroJP.add(new BLabel((new Integer(i+1)).toString()));
            		for (int j = 0; j < 8; j++) {
				Casilla c;
				if (((i + j) % 2) == 0) c = new Casilla(i, j, Color.white);
       				else c = new Casilla(i, j, Color.gray);
				casillas[i][j] = c;

                		//int color = b.getColor(i, j);
                		//if (color != Board.EMPTY) {
                    		//	int piece = b.getPiece(i, j);
                    		//	square[i][j].setIcon(new ImageIcon(pieceImage[color][piece]));
                		//}
                		tableroJP.add(casillas[i][j]);
            		}
            		tableroJP.add(new BLabel((new Integer(i+1)).toString()));
        	}
        	filaLetras(tableroJP);
	}

	
	// Añade una fila de letras al tablero
	private void filaLetras(JPanel p) {
        	p.add(new JPanel());
        	for (int i = 0; i < 8; i++)
            		p.add(new BLabel((new Character((char) ('a' + i))).toString()));
        	p.add(new JPanel());
    	}

	// Genera todos los movimientos validos para el jugador con el color indicado
    	public ArrayList generarMovimientos (int color) {

        	ArrayList todas = new ArrayList();
        	ArrayList buenas = new ArrayList();
        	ArrayList nuevas;
        	Pieza actual;
        
		for (int j=0; j<8; j++)
            		for (int i=0; i<8; i++)
                		if (tablero[i][j] != null && tablero[i][j].getColor() == color) {
                			nuevas = get(i, j).posiblesMovimientos(this);
                			todas.addAll(nuevas);
				}
		return todas;
    	}

	// Devuelve el valor de un tablero para un jugador con el color indicado. Sus piezas aportan puntos en funcion
	// de la categoria mientras que las del rival restan igualmente en funcion de su categoria.
	//
	public int valorTablero (int color) {

		// Puntuaciones:
		// Peon: 1
		// Alfil: 3
		// Torre: 5
		// Reina: 10
		// Rey: 100
		
		int total = 0;

		for(int i=0; i<8; i++)
    			for(int j=0; j<8; j++)
    			{
    				Pieza pieza = get(i, j);
				if (pieza != null) {
    					if (pieza instanceof Peon)
    						total+=1*((pieza.getColor()==color)?1:-1);
    					else if (pieza instanceof Alfil)
    						total+=3*((pieza.getColor()==color)?1:-1);
    					
    					else if (pieza instanceof Torre)
    						total+=5*((pieza.getColor()==color)?1:-1);
    					else if (pieza instanceof Reina)
    						total+=10*((pieza.getColor()==color)?1:-1);
					else if (pieza instanceof Rey)
    						total+=100*((pieza.getColor()==color)?1:-1);
				}
    		}	
    		return total;				
	}

	// Indica que el tablero contiene una partida en situacion de empate
	//
	public void setTablas () {

		tablas = true;
	}

	public boolean getTablas () {

		return tablas;
	}

    	class BLabel extends JLabel {
        	BLabel(String s) {
            		super(s);
            		setHorizontalAlignment(CENTER);
            		setVerticalAlignment(CENTER);
		}
        }

	class Casilla extends JPanel {
		
		private JLabel l;
		// posicion
		private int f, c;
		
		public Casilla (int f, int c, Color color) {
			
			super();
			setBackground(color);
			setPreferredSize(new Dimension(42, 42));
			this.f = f;
			this.c = c;
			l = new JLabel();
        		l.setPreferredSize(new Dimension(32, 32));
        		add(l);
			addMouseListener(new CasillaMouseListener());
		}

		void setIcon(Icon i) {
        		l.setIcon(i);
    		}

		class CasillaMouseListener extends MouseAdapter {
        		/*
			public void mouseEntered(MouseEvent e) {
            			mouseIn = true;
            			repaint();
        		}
        
        		public void mouseExited(MouseEvent e) {
            			mouseIn = false;
            			repaint();
        		}
        		*/
        		public void mouseClicked(MouseEvent e) {
            			//bv.selected(y, x);
				//System.out.println("Casilla pulsada: <" + f + ", " + c + ">");
        		}
    		}
	}
}
