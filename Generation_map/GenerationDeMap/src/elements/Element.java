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
//concernant le booleen passage, si il est � false, il y a collision avec Maria, sinon Maria peut passer dessous .

public abstract class Element implements ImageObserver{
	
	protected String path_image;//le path de l'image de l'�l�ment .
	
	protected boolean passage;//savoir si Maria peut passer sous l'�l�ment .

	protected int x;//abscisse en PIXEL
	protected int y;//ordonnee en PIXEL

/**
 * le constructeur par d�faut .
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
	 * retourne l'image correspondant � l'�l�ment .
	 * @return
	 * @throws IOException
	 */
	protected Image initImage() throws IOException {
		setPath();
		Image image = ImageIO.read(new File(this.path_image));
		return image;
	}

/**
 * permet de dessiner l'�l�ment aux coordonn�es x y .
 * @param g
 */
	public void draw(Graphics g) {
		Image image;
		try {
			image = initImage();
			g.drawImage(image,x,y,this);
		} catch (IOException e) {
			System.out.println("image non trouv�e");
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
	 * M�thode qiu va permettre de d�finir le path . Abstract car par exemple pour un tuyau, y a plusieurs couleurs
	 * donc plusieurs images ...� la dif�rence des blocs qui sont tous � partir de la m�me image .
	 */
	abstract void setPath();
}
