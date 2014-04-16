package grid;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import elements.Bloc;
import elements.CouleurDeTuyau;
import elements.Element;
import elements.TuyauVerticalBas;
import elements.TuyauVerticalHaut;

public class Panneau extends JPanel{
	private Element[][] grille;
	private String path_map;

	public Panneau(String path_txt) {
		super();
		this.path_map=path_txt;
		this.setSize(768, 1344);
		this.grille = new Element[21][12];
		this.path_map = path_txt;
		this.initGrille(recupTexteMap());
		this.setLayout(new GridLayout(21,12));
		this.repaint();
	}

	public void initGrille(String texte) {
		String[] tableau = new String[264];
		for(int i=0;i<263;i++){
			String s = texte.substring(i, i+1);
			tableau[i] = s;
		}

		int index_j=0;
		for(int i=0;i<263;i++){
			String s = tableau[i];
			int index_i = i%22;
			switch(s){
			case "0":
				Bloc bloc = new Bloc(index_i*64, index_j*64);
				this.grille[index_i][index_j]=bloc;

				//System.out.println("0");
				break;
			case "1":
				grille[index_i][index_j]=null;
				//System.out.println("1");
				break;
				
			case "r":
				TuyauVerticalBas tuyau = new TuyauVerticalBas(index_i*64, index_j*64,CouleurDeTuyau.ROUGE);
				this.grille[index_i][index_j]=tuyau;
				break;
				//bug d'arrayindexoutofbounds lèl
				/*
			case "s":
				TuyauVerticalHaut sortie = new TuyauVerticalHaut(index_i*64, index_j*64,CouleurDeTuyau.SORTIE);
				this.grille[index_i][index_j]=sortie;
				*/
			case "\n":
				index_j++;
				//System.out.println("passage à la ligne");
				break;
			}
		}
	}

	public String recupTexteMap() {
		String txt="";
		try{
			FileReader r = new FileReader(new File(this.path_map));
			BufferedReader lecteur = new BufferedReader(r);
			String ligne = "";
			try{
				while((ligne = lecteur.readLine())!=null){
					txt = txt+ligne+"\n";
				}
			}
			catch(IOException e){
				System.out.println("problème à la lecture la ligne");
			}
		}
		catch(FileNotFoundException e){
			System.out.println("le fichier "+this.path_map+" n'est pas trouvé. Eclipse s'excuse pour ce problème .");
		}
		System.out.println(txt);
		return txt;
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		for(int i = 0;i<21;i++){
			for(int j = 0;j<12;j++) {
				Element element = this.grille[i][j];//TODO : init le tableau grille
				if(element!=null){
					element.draw(g2d);
				}
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test_map_gen");
		frame.setSize(1344, 768);
		Panneau panneau = new Panneau("../ressources/map0.txt");
		frame.setContentPane(panneau);
		frame.setFocusable(true);
		frame.setVisible(true);
	}
}