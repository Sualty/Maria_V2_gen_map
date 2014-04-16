package elements;

public class Bloc extends Element {
	
	public Bloc(int x,int y) {
		super(x,y,false);
	}
	
	public void setPath() {
		this.path_image = "../ressources/images/bloc_noir.jpg";
	}
}
