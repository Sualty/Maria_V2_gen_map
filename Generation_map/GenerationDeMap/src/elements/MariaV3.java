package elements;

import grid.PanneauV3;
import grid.PanneauV4;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import sons.AudioWav;
import sons.Sons;
public class MariaV3{
	private Sons sons;
	private int centerX, centerY;
	private static final int MOVESPEED = 1;
	private static final int JUMPSPEED = 1;
	public static int persoHeight= 128;
	public static int persoWidth = 68;
	private int hauteur = 0;
	private int depart = 0;
	private Robot robot;

	private Image droite1;
	private Image gauche1;
	private Image droite2;
	private Image gauche2;
	private Image tombe;
	private Image attented;
	private Image attenteg;
	private Image level_complete_image;
	private Image imageCourante;

	private int compteurPas;

	private int speedX = 0, speedY = 0;

	private boolean isJumping;
	private boolean isFalling;
	private boolean isMovingRight, isMovingLeft;

	private PanneauV3 panel;
	private boolean levelcomplete;
	private boolean cantGoRight;
	private boolean cantGoLeft;



	public MariaV3(int centerX, int centerY, PanneauV3 panel) {
		this.sons = new Sons();
		this.centerX = centerX;
		this.centerY = centerY;
		this.panel = panel;
		this.setCantGoLeft(false);
		this.setCantGoRight(false);
		this.setLevelcomplete(false);
		initImages();
		try {
			setRobot(new Robot());
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	private void initImages() {
		try {
			droite1 = ImageIO.read(new File(
					"../ressources/images/persoMaria/maria_pas1d.png"));
			gauche1 = ImageIO.read(new File(
					"../ressources/images/persoMaria/maria_pas1g.png"));
			droite2 = ImageIO.read(new File(
					"../ressources/images/persoMaria/maria_pas2d.png"));
			gauche2 = ImageIO.read(new File(
					"../ressources/images/persoMaria/maria_pas2g.png"));
			tombe = ImageIO.read(new File(
					"../ressources/images/persoMaria/maria_tombe.png"));
			attented = ImageIO.read(new File(
					"../ressources/images/persoMaria/attented.png"));
			attenteg = ImageIO.read(new File(
					"../ressources/images/persoMaria/attenteg.png"));
			level_complete_image = ImageIO.read(new File(
					"../ressources/images/persoMaria/completelevel.png"));
			setImageCourante(attented);
			setCompteurPas(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update(){
		centerX += speedX;
		if(isFalling){
			setImageCourante(tombe);
			centerY += JUMPSPEED;
			if (centerY >= depart+hauteur ) {
				centerY = hauteur+depart;
				setImageCourante(attented);
				isFalling = false;
				hauteur = 0;
				depart = 0;
			}
		}		
		if (!isJumping) {
			speedY = -JUMPSPEED;
		} else {
			centerY += speedY;
			speedY++;
			if (centerY >= 448) {
				centerY = 448;
				isJumping = false;
				speedY = 0;
			}
		}
	}

	public void actionDroite() {

		isMovingRight = true;
		isMovingLeft = false;
		speedX = MOVESPEED;
		if(getCompteurPas()%12<6)
			setImageCourante(droite1);
		else setImageCourante(droite2);
		setCompteurPas(getCompteurPas() + 1);
	}

	public void actionGauche() {

		isMovingRight = false;
		isMovingLeft = true;
		speedX = -MOVESPEED;
		if(getCompteurPas()%12<6)
			setImageCourante(gauche1);
		else setImageCourante(gauche2);
		setCompteurPas(getCompteurPas() + 1);

	}

	public void actionBas() {
		//nyah	
	}

	public void finishLevel(){
		AudioWav audio = new AudioWav();
		audio .playLevelComplete();
		try{
			Thread.sleep(5337);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		audio.stop();
		this.centerX=-80000;
	}



	public void actionChute(){
//nyah
	}

	public void stopDroit(){
		setImageCourante(attented);
		isMovingRight = false;
		stop();
	}

	public void stopGauche(){
		setImageCourante(attenteg);
		isMovingLeft = false;
		stop();
	}

	public void stopBas(){
		isMovingRight = false;
		isMovingLeft = false;
		stop();
	}

	public void stop(){
		if(!isMovingLeft && !isMovingRight){
			speedX = 0;
		}
		if(!isMovingLeft && isMovingRight){
			actionGauche();
		}
		if(isMovingLeft && !isMovingRight){
			actionDroite();
		}
	}

	public boolean isJumping() {
		return isJumping;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Image getImageCourante() {
		return imageCourante;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public boolean isLevelcomplete() {
		return levelcomplete;
	}

	public void setLevelcomplete(boolean levelcomplete) {
		this.levelcomplete = levelcomplete;
	}

	public void setImageCourante(Image imageCourante) {
		this.imageCourante = imageCourante;
	}

	/**
	 * @return the level_complete_image
	 */
	public Image getLevel_complete_image() {
		return level_complete_image;
	}

	/**
	 * @param level_complete_image the level_complete_image to set
	 */
	public void setLevel_complete_image(Image level_complete_image) {
		this.level_complete_image = level_complete_image;
	}

	/**
	 * @return the cantGoRight
	 */
	public boolean isCantGoRight() {
		return cantGoRight;
	}

	/**
	 * @return the cantGoLeft
	 */
	public boolean isCantGoLeft() {
		return cantGoLeft;
	}

	/**
	 * @param cantGoRight the cantGoRight to set
	 */
	public void setCantGoRight(boolean cantGoRight) {
		this.cantGoRight = cantGoRight;
	}

	/**
	 * @param cantGoLeft the cantGoLeft to set
	 */
	public void setCantGoLeft(boolean cantGoLeft) {
		this.cantGoLeft = cantGoLeft;
	}

	public int getCompteurPas() {
		return compteurPas;
	}

	public void setCompteurPas(int compteurPas) {
		this.compteurPas = compteurPas;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

}
