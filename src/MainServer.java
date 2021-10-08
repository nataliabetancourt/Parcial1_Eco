import java.awt.Rectangle;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.Particle;
import processing.core.PApplet;

public class MainServer extends PApplet implements IObserver{

	private Communication com;
	private Gson gson;
	private Particle pBase;
	private ArrayList<Particle> particleGroups;
	
	public static void main(String[] args) {
		PApplet.main("MainServer");
	}
		
	
	@Override
	public void settings() {
		size(900, 600);
	}
	
	@Override
	public void setup() {
		
		com = Communication.getInstance();
		com.setObserver(this);
		
		particleGroups = new ArrayList<>();
		
	}
	
	@Override
	public void draw() {
		background(255);
		
		//Draw particles added to the list
		for (int i = 0; i < particleGroups.size(); i++) {
			Particle p = particleGroups.get(i);
			noStroke();
			fill(p.getR(), p.getG(), p.getB());
			circle(p.getxPos(), p.getyPos(), 60);
			
			//Make the balls move
			p.move();
			
			//When the mouse hovers over the ball, stop movement
			if (dist(mouseX, mouseY, p.getxPos(), p.getyPos()) < 30) {
				//Stop moving
				p.setMoving(false);
				
				//Rectangle with group name
				strokeWeight(3);
				stroke(80);
				fill(255);
				rectMode(CENTER);
				rect(p.getxPos(), p.getyPos()+5, 80, 40);
				fill(80);
				textAlign(CENTER);
				text(p.getName(), p.getxPos(), p.getyPos());
			} else {
				p.setMoving(true);
			}
		}
		
		
		
	}


	@Override
	public void notifyMessage(String message) {
		if (message.equals("delete")) {
			System.out.println("delete particles");
			particleGroups.clear();
		} else {
			gson = new Gson();
			pBase = gson.fromJson(message, Particle.class);
			
			//Adding the amount of particles of the group to the list
			for (int i = 0; i < pBase.getAmount(); i++) {
				//Creating new particles based on the amount and info from the base one imported from android
				particleGroups.add(new Particle(pBase.getxPos(), pBase.getyPos(), pBase.getR(), pBase.getG(), 	
												pBase.getB(), pBase.getAmount(), pBase.getName()));
			}
		}
		
	}
}
