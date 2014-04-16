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

/**
 * La version actuelle d'un panneau de jeu .
 * @author Leviathor
 *
 */
public class PanneauV2 extends JPanel{

	private Element[][] grille;
	private String path_map;

	/**
	 * Le constructeur de la classe . 
	 * Il prend en param�trele chemin du fihcier texte contenant les infos de la map .
	 * @param path_txt
	 */
	public PanneauV2(String path_txt) {
		super();
		this.path_map=path_txt;
		this.setSize(768, 1344);
		this.grille = new Element[21][12];
		this.path_map = path_txt;
		this.initGrille(recupTexteMap());
		this.setLayout(new GridLayout(21,12));
		this.repaint();
	}

	/**
	 * La grosse m�thode qui initialise la matrice d'�l�ments � partir du fichier texte de chemin path_map .
	 * @param texte
	 */
	public void initGrille(String texte) {
		String[] tableau = new String[265];//va contenir les String correspondant aux �l�ments (du genre 00,11,etc)
		int index=0;//l'index de lecture du fichier .
		for(int i=0;i<264;i++){//i est l'index du tableau o� on �crit une des cha�nes de caract�re .
			if(texte.substring(index, index+1).equals("\n")){
				tableau[i] = "\n";
				index++;
			}
			else{
				String s = texte.substring(index, index+2);
				tableau[i] = s;
				index=index+2;
			}
		}
		//voil�, on a rempli le tableau de String, maintenant on va remplir la matrice this.grille .
		int index_j=0;
		for(int i=0;i<264;i++){
			String s = tableau[i];
			int index_i = i%22;
			switch(s){
			case "00"://bloc noir
				Bloc bloc = new Bloc(index_i*64, index_j*64);
				this.grille[index_i][index_j]=bloc;
				break;
			case "11"://�l�ment vide : aucune image n'est affich�e, on voit l'image de fond .
				grille[index_i][index_j]=null;
				break;

			case "RB"://tuyau rouge dont l'entr�e est vers le bas .
				TuyauVerticalBas tuyau = new TuyauVerticalBas(index_i*64, index_j*64,CouleurDeTuyau.ROUGE);
				this.grille[index_i][index_j]=tuyau;
				break;
			case "SH"://tuyau de sortie dont l'entr�e est vers le haut .
				TuyauVerticalHaut sortie = new TuyauVerticalHaut(index_i*64, index_j*64,CouleurDeTuyau.SORTIE);
				this.grille[index_i][index_j]=sortie;
				break;
			case "\n":
				index_j++;
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
				System.out.println("probl�me � la lecture la ligne");
			}
		}
		catch(FileNotFoundException e){
			System.out.println("le fichier "+this.path_map+" n'est pas trouv�. Eclipse s'excuse pour ce probl�me .");
		}
		System.out.println("R�cup�ration de texte \n\n"+txt+"\n\n\n");
		return txt;
	}


	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		for(int i = 0;i<21;i++){
			for(int j = 0;j<12;j++) {
				Element element = this.grille[i][j];
				if(element!=null){
					element.draw(g2d);
				}
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test_map_gen");
		frame.setSize(1344,805);
		PanneauV2 panneau = new PanneauV2("../ressources/map0V2.txt");
		frame.setContentPane(panneau);
		frame.setFocusable(true);
		frame.setVisible(true);
	}
}

