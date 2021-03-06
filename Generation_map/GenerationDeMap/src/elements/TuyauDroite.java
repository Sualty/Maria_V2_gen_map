package elements;

import java.awt.Image;
//TODO : bug durant l'ajout d'un tuyau de ce type dans le panneau .
public class TuyauDroite extends Element{
	private CouleurDeTuyau couleur;
	private Image image;//l'image de l'�l�ment
	private boolean passage;//savoir si Maria peut passer sous l'�l�ment .
	private int x;//abscisse en PIXEL
	private int y;//ordonnee en PIXEL
	
	public TuyauDroite(int x,int y,CouleurDeTuyau couleur) {
		super(x, y, true);
		this.couleur = couleur;
	}
	
	public void setPath() {
		switch(this.couleur){
		case BLEU_CLAIR:
			this.path_image = "../ressources/images/bleu/tuyau_bleu_clair_droite.png";
			break;
		case BLEU_FONCE:
			this.path_image = "../ressources/images/bleu/tuyau_bleu_fonce_droite.png";
			break;
		case ENTREE:
			this.path_image = "../ressources/images/bleu/tuyau_entree_droite.png";
			break;
		case JAUNE:
			break;
		case ROUGE:
			break;
		case SORTIE:
			this.path_image = "../ressources/images/bleu/tuyau_sortie_droite.png";
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
