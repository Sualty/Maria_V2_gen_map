package elements;

import java.awt.Image;
//TODO : bug durant l'ajout d'un tuyau de ce type dans le panneau .
public class TuyauGauche extends Element{
	private CouleurDeTuyau couleur;
	private Image image;//l'image de l'élément
	private boolean passage;//savoir si Maria peut passer sous l'élément .
	private int x;//abscisse en PIXEL
	private int y;//ordonnee en PIXEL
	
	public TuyauGauche(int x,int y,CouleurDeTuyau couleur) {
		super(x, y, true);
		this.couleur = couleur;
	}
	
	public void setPath() {
		switch(this.couleur){
		case BLEU_CLAIR:
			this.path_image = "../ressources/images/bleu/tuyau_bleu_clair_gauche.png";
			break;
		case BLEU_FONCE:
			this.path_image = "../ressources/images/bleu/tuyau_bleu_fonce_gauche.png";
			break;
		case ENTREE:
			this.path_image = "../ressources/images/bleu/tuyau_entree_gauche.png";
			break;
		case JAUNE:
			break;
		case ROUGE:
			break;
		case SORTIE:
			this.path_image = "../ressources/images/bleu/tuyau_sortie_gauche.png";
			break;
		case VERT:
			break;
		case VIOLET:
			break;
		default:
			break;

		}
	}

}
