package morpheus.view;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

/**
 *  
 * @author Luca Mengozzi		 
 * 
 */
public class GraphicNumbers {
	
	private final int MAX = 1000000;
	private Map<Integer, Sprite> map = new HashMap<Integer, Sprite>();
	private SpriteSheet s = new SpriteSheet(new Texture("res/nerip.png"), 25);
	private Texture score = new Texture("res/scoreggia.png");
	
	public GraphicNumbers(){
		
		for (int i=0; i<10; i++){
			
			map.put(i, new Sprite(s, i+1, 1));
		}
	}
	
	public void render(Graphics2D g, int p){
		
		//Divido per 200 per gestirlo meglio e per aumentare la difficolta di gioco
		p = p / 200;
		
		//Variabile che mi tiene il primo numero
		int f = 0;
		
		score.render(g, 25, 49);
		
		//Decido che un milione è il massimo
		if (p >= MAX){
			
			map.get(1).render(g, 138, 50);
			map.get(0).render(g, 160, 50);
			map.get(0).render(g, 182, 50);
			map.get(0).render(g, 204, 50);
			map.get(0).render(g, 226, 50);
			map.get(0).render(g, 248, 50);
			map.get(0).render(g, 270, 50);
		}
		else{
			
			p = p % MAX;	
		}
		
		if(p < MAX && p >= 100000){
			
			f = Integer.parseInt(Integer.toString(p).substring(0, 1));
			for(int i=0; i<10; i++){
				
				if (f==i){
					
					map.get(i).render(g, 138, 50);
				}
			}	
			//Aggiro il problema numerico dello zero
			if(Integer.parseInt(Integer.toString(p).substring(1, 2))==0){
				
				map.get(0).render(g, 160, 50);
			}
			if(Integer.parseInt(Integer.toString(p).substring(2, 3))==0){
				
				map.get(0).render(g, 182, 50);
			}
			if(Integer.parseInt(Integer.toString(p).substring(3, 4))==0){
				
				map.get(0).render(g, 204, 50);
			}
			if(Integer.parseInt(Integer.toString(p).substring(4, 5))==0){
				
				map.get(0).render(g, 226, 50);
			}
			if(Integer.parseInt(Integer.toString(p).substring(5, 6))==0){
				
				map.get(0).render(g, 248, 50);
			}
			f = f * 100000;
			p = p % f;	
		}
		
		if(p < 100000 && p >= 10000){
			
			f = Integer.parseInt(Integer.toString(p).substring(0, 1));
			for(int i=0; i<10; i++){
				
				if (f==i){
					
					map.get(i).render(g, 160, 50);
				}
			}	
			//Aggiro il problema numerico dello zero
			if(Integer.parseInt(Integer.toString(p).substring(1, 2))==0){
				
				map.get(0).render(g, 182, 50);
			}
			if(Integer.parseInt(Integer.toString(p).substring(2, 3))==0){
				
				map.get(0).render(g, 204, 50);
			}
			if(Integer.parseInt(Integer.toString(p).substring(3, 4))==0){
				
				map.get(0).render(g, 226, 50);
			}
			if(Integer.parseInt(Integer.toString(p).substring(4, 5))==0){
				
				map.get(0).render(g, 248, 50);
			}
			f = f * 10000;
			p = p % f;	
		}
		
		if(p < 10000 && p >= 1000){
			
			f = Integer.parseInt(Integer.toString(p).substring(0, 1));
			for(int i=0; i<10; i++){
				
				if (f==i){
					
					map.get(i).render(g, 182, 50);
				}
			}
			//Aggiro il problema numerico dello zero
			if(Integer.parseInt(Integer.toString(p).substring(1, 2))==0){
				
				map.get(0).render(g, 204, 50);
			}
			if(Integer.parseInt(Integer.toString(p).substring(2, 3))==0){
				
				map.get(0).render(g, 226, 50);
			}
			if(Integer.parseInt(Integer.toString(p).substring(3, 4))==0){
				
				map.get(0).render(g, 248, 50);
			}
			f = f * 1000;
			p = p % f;	
		}
		
		if(p < 1000 && p >= 100){
			
			f = Integer.parseInt(Integer.toString(p).substring(0, 1));
			for(int i=0; i<10; i++){
				
				if (f==i){
					
					map.get(i).render(g, 204, 50);
				}
			}
			//Aggiro il problema numerico dello zero
			if(Integer.parseInt(Integer.toString(p).substring(1, 2))==0){
				
				map.get(0).render(g, 226, 50);
			}
			if(Integer.parseInt(Integer.toString(p).substring(2, 3))==0){
				
				map.get(0).render(g, 248, 50);
			}
			f = f * 100;
			p = p % f;
			
		}
		
		if(p < 100 && p >= 10){
			
			f = Integer.parseInt(Integer.toString(p).substring(0, 1));
			for(int i=0; i<10; i++){
				
				if (f==i){
					
					map.get(i).render(g, 226, 50);
				}
			}	
			//Aggiro il problema numerico dello zero
			if(Integer.parseInt(Integer.toString(p).substring(1, 2))==0){
				
				map.get(0).render(g, 248, 50);
			}
			f = f * 10;
			p = p % f;	
		}
		
		if(p < 10){
			
			f = Integer.parseInt(Integer.toString(p).substring(0, 1));
			for(int i=0; i<10; i++){
				
				if (f==i){
					
					map.get(i).render(g, 248, 50);
				}
			}	
		}
		
		
	}	
}
