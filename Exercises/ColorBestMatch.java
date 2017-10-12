import java.awt.Color;

public class ColorBestMatch {
	
	public ColorBestMatch(){
		System.out.println(bestMatch(3, 2, 3));
	}
	
	public Color bestMatch(int r, int g, int b){
		Color c = Color.GRAY;
		if(!(r==g&&g==b)){
			if((r!=g&&r!=b&&g!=b)||(r==g&&Math.max(r, b)==b)||(r==b&&Math.max(r, g)==g)||(g==b&&Math.max(r, g)==r)){
				int max = Math.max(Math.max(r, g), b);
				if(max == r)
					c = Color.RED;
				else if(max == g)
					c = Color.GREEN;
				else if(max == b)
					c = Color.BLUE;
			} else if(r==g&&Math.max(r, b)==r)
				c = Color.YELLOW;
			else if(r==b&&Math.max(r, g)==r)
				c = Color.MAGENTA;
			else if(g==b&&Math.max(r, g)==g)
				c = Color.CYAN;
		}
		return c;
	}

	public static void main(String[] args){
		new ColorBestMatch();
	}
	
}
