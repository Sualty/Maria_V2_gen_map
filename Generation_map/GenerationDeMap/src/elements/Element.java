package elements;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
//concernant le booleen passage, si il est à false, il y a collision avec Maria, sinon Maria peut passer dessous .

public abstract class Element implements ImageObserver{
	
	protected String path_image;//le path de l'image de l'élément .
	
	protected boolean passage;//savoir si Maria peut passer sous l'élément .

	protected int x;//abscisse en PIXEL
	protected int y;//ordonnee en PIXEL

/**
 * le constructeur par défaut .
 */
	public Element() {
		super();
	}
	public Element(int x,int y,boolean passage) {
		super();
		this.x = x;
		this.y = y;
		this.passage = passage;
	}

	/**
	 * retourne l'image correspondant à l'élément .
	 * @return
	 * @throws IOException
	 */
	protected Image initImage() throws IOException {
		setPath();
		Image image = ImageIO.read(new File(this.path_image));
		return image;
	}

/**
 * permet de dessiner l'élément aux coordonnées x y .
 * @param g
 */
	public void draw(Graphics g) {
		Image image;
		try {
			image = initImage();
			g.drawImage(image,x,y,this);
		} catch (IOException e) {
			System.out.println("image non trouvée");
		}
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		return true;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return y;
	}
	
	/**
	 * Méthode qiu va permettre de définir le path . Abstract car par exemple pour un tuyau, y a plusieurs couleurs
	 * donc plusieurs images ...à la diférence des blocs qui sont tous à partir de la même image .
	 */
	abstract void setPath();
}
