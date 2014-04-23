package elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.lang.model.element.Element;

/**
 * Un tuyau vertical prend 3 cases de haut sur 2 cases de large . Faut en tenir compte lorsqu'on 
 * @author Leviathor
 *
 */
public class TuyauBas extends elements.Element 	{
	private CouleurDeTuyau couleur;
	private Image image;//l'image de l'élément
	private boolean passage;//savoir si Maria peut passer sous l'élément .
	private int x;//abscisse en PIXEL
	private int y;//ordonnee en PIXEL
	
	public TuyauBas(int x,int y,CouleurDeTuyau couleur) {
		super(x, y, true);
		this.couleur = couleur;
	}
	
	public void setPath() {
		switch(this.couleur){
		case BLEU_CLAIR:
			this.path_image = "../ressources/images/bleu/tuyau_bleu_clair_bas.png";
			break;
		case BLEU_FONCE:
			this.path_image = "../ressources/images/bleu/tuyau_bleu_fonce_bas.png";
			break;
		case ENTREE:
			this.path_image = "../ressources/images/bleu/tuyau_entree_bas.png";
			break;
		case JAUNE:
			break;
		case ROUGE:
			break;
//pas de case SORTIE car un tuayu sortie qui pointe vers le bas est impossible .
		case VERT:
			break;
		case VIOLET:
			break;
		default:
			break;

		}
	}
}
