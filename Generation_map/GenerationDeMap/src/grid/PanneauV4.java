package grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sons.AudioWav;
import sons.Sons;
import elements.Bloc;
import elements.CouleurDeTuyau;
import elements.Element;
import elements.Maria;
import elements.TuyauVerticalBas;
import elements.TuyauVerticalHaut;

/**
 * La version actuelle d'un panneau de jeu .
 * @author Leviathor
 *
 */
public class PanneauV4 extends JPanel implements KeyListener, Runnable {

	private Element[][] grille;
	private String path_map;

	protected Sons sons;
	public AudioWav audio;
	protected Maria maria;
	protected JFrame map;
	//protected JButton quitButton;
	protected final static int pi = 64;


	//protected Chrono chrono;
	protected int scoreNiveau;
	//protected GestionScore gestionScore;
	
	//protected Score_de_niveau score_actuel;
	//protected Jauge jauge;
	
	/**
	 * Le constructeur de la classe . 
	 * Il prend en paramètrele chemin du fihcier texte contenant les infos de la map .
	 * @param path_txt
	 */
	public PanneauV4(String path_txt) {
		super();
		
		this.sons = new Sons();
		this.audio = new AudioWav();
		this.map = map;
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setBackground(new Color(0,113,188));
		
		this.path_map=path_txt;
		this.setSize(768, 1344);
		this.grille = new Element[21][12];
		this.path_map = path_txt;
		this.initGrille(recupTexteMap());
		this.initLevel(0,128);
		this.setLayout(new GridLayout(21,12));
		this.repaint();
	}

	
	//initialisation de maria
	private void initLevel(int x, int y) {
		maria = new Maria(x, y,this);
		new Thread(this).start();
		//maria.actionChute();
	}
	
	public void run() {
		while (true) {
			repaint();
			maria.update();
			try {
				Thread.sleep(4);
			} catch (InterruptedException e) {
				System.exit(0);
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			audio.stop();
			break;
		case KeyEvent.VK_LEFT:
			if(!maria.isCantGoLeft()){
				maria.actionGauche();
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(!maria.isCantGoRight()){
				maria.actionDroite();	
			}
			break;
		case KeyEvent.VK_DOWN:
				maria.actionBas();
			break;
			/*
		case KeyEvent.VK_ESCAPE:
			audio.stop();
			new MenuJeu("Maria Brosse");
			map.dispose();
			break;
			*/
		}
	}
	

	public void keyReleased(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			maria.stopGauche();
			break;
		case KeyEvent.VK_RIGHT:
			maria.stopDroit();
			break;
		}
	}
	

	public boolean tuyauBasLoc(int x, int y) {
		return true;
	}


	public boolean murDroitLoc(int x, int y) {
		return true;
	}


	public boolean murGaucheLoc(int x, int y) {
		if(x<=40) return false;
		return true;
	}



	public void actionTuyauDroite(int y) {
		// TODO Auto-generated method stub
		
	}


	public void actionTuyauGauche(int y) {
		// TODO Auto-generated method stub
		
	}


	public boolean tuyauDroiteLoc(int x, int y) {
		return true;
	}


	public boolean tuyauGaucheLoc(int x, int y) {
		return true;
	}


	public int videLoc(int x, int y) {
		if(x==0 && y==192) return 4*64;
		return -1;
	}


	public void actionTuyauBas(int centerY) {
		maria.finishLevel();
	}
	
	
	/**
	 * La grosse méthode qui initialise la matrice d'éléments à partir du fichier texte de chemin path_map .
	 * @param texte
	 */
	public void initGrille(String texte) {
		String[] tableau = new String[265];//va contenir les String correspondant aux éléments (du genre 00,11,etc)
		int index=0;//l'index de lecture du fichier .
		for(int i=0;i<264;i++){//i est l'index du tableau où on écrit une des chaînes de caractère .
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
		//voilà, on a rempli le tableau de String, maintenant on va remplir la matrice this.grille .
		int index_j=0;
		for(int i=0;i<264;i++){
			String s = tableau[i];
			int index_i = i%22;
			switch(s){
			case "00"://bloc noir
				Bloc bloc = new Bloc(index_i*64, index_j*64);
				this.grille[index_i][index_j]=bloc;
				break;
			case "11"://élément vide : aucune image n'est affichée, on voit l'image de fond .
				grille[index_i][index_j]=null;
				break;

			case "RB"://tuyau rouge dont l'entrée est vers le bas .
				TuyauVerticalBas tuyau = new TuyauVerticalBas(index_i*64, index_j*64,CouleurDeTuyau.ROUGE);
				this.grille[index_i][index_j]=tuyau;
				break;
			case "SH"://tuyau de sortie dont l'entrée est vers le haut .
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
				System.out.println("problème à la lecture la ligne");
			}
		}
		catch(FileNotFoundException e){
			System.out.println("le fichier "+this.path_map+" n'est pas trouvé. Eclipse s'excuse pour ce problème .");
		}
		System.out.println("Récupération de texte \n\n"+txt+"\n\n\n");
		return txt;
	}


	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		
		//g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(maria.getImageCourante(), maria.getCenterX(), maria.getCenterY(), this);
		
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

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

