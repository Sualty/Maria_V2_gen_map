package sons;

import java.applet.*;
import java.net.*;

public class AudioWav {

	private static String level1 = "../ressources/sons/mb.wav";
    private static String level2 = "../ressources/sons/mp.wav";
    private static String levelComplete = "../ressources/sons/level_complete.wav";

    private URL u1;
    private AudioClip s1 = null;
    private URL u3;
    private AudioClip s3 = null;
    private URL u2;
    private AudioClip s2 = null;
    
    public AudioWav() {
        chargement();
    }
    /** charge les sons depuis le dossier /bin */
    private void chargement() {
    	try{
    		u1 = new URL("file:"+ level1);
    		s1 = Applet.newAudioClip(u1);
    		u2 = new URL("file:"+level2);
    		s2 = Applet.newAudioClip(u2);
    		u3 = new URL("file:"+ levelComplete);
    		s3 = Applet.newAudioClip(u3);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void stop(){
    	try{
    		s1.stop();
    		s2.stop();
    		s3.stop();

    	}catch(Exception e){}
    }
    
/** lance le son */
    public void playLevel1() {
    	try{
    		s1.loop();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
/** arrete le son */
    public void stopLevel1() {
    	try{
    		s1.stop();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    /** lance le son */
    public void playLevel2() {
    	try{
    		s2.loop();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
/** arrete le son */
    public void stopLevel2() {
    	try{
    		s2.stop();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /** lance le son */
    public void playLevelComplete() {
    	try{
    		s3.loop();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
/** arrete le son */
    public void stopLevelComplete() {
    	try{
    		s3.stop();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

}