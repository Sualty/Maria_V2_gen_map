package sons;

import java.applet.AudioClip;

import t2s.SIVOXDevint;

public class Sons {
    private SIVOXDevint voix;
    private static String vert = "../ressources/sons/VERT.wav";
    private static String orange = "../ressources/sons/ORANGE.wav";
    private static String violet = "../ressources/sons/VIOLET.wav";
    private static String bleu = "../ressources/sons/BLEU.wav";
    private static String bleu_clair = "../ressources/sons/BLEU_CLAIR.wav";
    private static String bleu_fonce = "../ressources/sons/bleuFonce.wav";
    private static String bravo_niveau = "../ressources/sons/BRAVO_TU_AS_TROUVE_LA_SORTIE.wav";
    private static String rouge = "../ressources/sons/ROUGE.wav";
    private static String jaune= "../ressources/sons/JAUNE.wav";
    private static String pas = "../ressources/sons/PAS.wav";
    private static String pop = "../ressources/sons/TUYAU_1.wav";
    private static String changement_niveau = "../ressources/sons/DragosteaDinTei.wav";
    private static String tuyau = "../ressources/sons/TUYAU.wav";
    private static String jouer ="../ressources/sons/jouer.wav";
    private static String options ="../ressources/sons/options.wav";
    private static String score = "../ressources/sons/score.wav";
    private static String quitter = "../ressources/sons/quitter.wav";
    private static String mystere = "../ressources/sons/mystere.wav";
    
    public Sons() {
    	this.voix = new SIVOXDevint();
    }
      
    public void playVert() {
    	voix.playWav(vert);
    }
    
    public void playMystère(){
    	voix.playWav(mystere);
    }
       
    public void playRouge() {
    	voix.playWav(rouge);
    }
    
    public void playTuyau() {
    	voix.playWav(tuyau);
    }
    
    public void playJaune() {
    	voix.playWav(jaune);
    }
    
    public void playMenu(String option){
    	option=option.toLowerCase();
    	switch(option){
    	case "jouer" :
    		voix.playWav(jouer);
    		break;
    	case "options" : 
    		voix.playWav(options);
    		break;
    	case "scores":
    		voix.playWav(score);
    		break;
    	case "quitter" :
    		voix.playWav(quitter);
    		break;
    	default :
    		voix.playShortText(option);
    		break;
    	}
    }
    
    public void playPas() {
    	voix.playWav(pas);
    }
    
    public void playPop() {
    	voix.playWav(pop);
    }
    
    public void playChangementNiveau(){
    	voix.playWav(changement_niveau);
    }
    
	public void playOrange() {
		voix.playWav(orange);
	}

	public void playBleuMarine() {
		voix.playWav(bleu_fonce);
		
	}

	public void playBleuCiel() {
		voix.playWav(bleu_clair);
		
	}

	public void playViolet() {
		voix.playWav(violet);
		
	}
}
