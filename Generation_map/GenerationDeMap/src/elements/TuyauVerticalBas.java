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
 * @author Magikarp
 *
 */
public class TuyauVerticalBas extends elements.Element 	{
	private CouleurDeTuyau couleur;
	private Image image;//l'image de l'élément
	private boolean passage;//savoir si Maria peut passer sous l'élément .
	private int x;//abscisse en PIXEL
	private int y;//ordonnee en PIXEL
	
	public TuyauVerticalBas(int x,int y,CouleurDeTuyau couleur) {
		super(x, y, true);
		this.couleur = couleur;
	}
	
	public void setPath() {
		switch(this.couleur){
		case BLEU_CLAIR:
			break;
		case BLEU_FONCE:
			break;
		case ENTREE:
			break;
		case JAUNE:
			break;
		case ORANGE:
			break;
		case ROUGE:
			this.path_image = "../ressources/images/tuyau_rouge_sortie.png";
			break;
		case SORTIE:
			this.path_image = "../ressources/images/tuyau_entree.jpg";
			break;
		case VERT:
			this.path_image = "../ressources/images/tuyau_vert_sortie.png";
			break;
		case VIOLET:
			break;
		default:
			break;

		}
	}
}
