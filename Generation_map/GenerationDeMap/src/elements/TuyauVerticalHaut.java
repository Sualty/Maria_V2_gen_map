package elements;

import java.awt.Image;
//TODO : bug durant l'ajout d'un tuyau de ce type dans le panneau .
public class TuyauVerticalHaut extends Element{
	private CouleurDeTuyau couleur;
	private Image image;//l'image de l'�l�ment
	private boolean passage;//savoir si Maria peut passer sous l'�l�ment .
	private int x;//abscisse en PIXEL
	private int y;//ordonnee en PIXEL
	
	public TuyauVerticalHaut(int x,int y,CouleurDeTuyau couleur) {
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
			this.path_image = "../ressources/images/tuyau_rouge_entree.jpg";
			break;
		case SORTIE:
			this.path_image = "../ressources/images/tuyau_sortie.jpg";
			break;
		case VERT:
			this.path_image = "../ressources/images/tuyau_vert_entree.png";
			break;
		case VIOLET:
			break;
		default:
			break;

		}
	}

}