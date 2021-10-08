package model;

public class Particle {

    private int xPos, yPos, r, g, b, amount;
    private int dirX, dirY, speed;
    private String name;
    private boolean isMoving;

    public Particle() {}

    public Particle(int xPos, int yPos, int r, int g, int b, int amount, String name) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.r = r;
        this.g = g;
        this.b = b;
        this.amount = amount;
        this.name = name;
        
        //Random directions and speed for movement
        speed = (int)(Math.random()*6+1);
        dirX = (int)(Math.random()*2+0);
        	//Based on the random, decide which direction the particle will move
        	if (dirX == 0) {
				dirX = 1;
			} else {
				dirX = -1;
			}
        	
        dirY = (int)(Math.random()*2+0);
        	//Based on the random, decide which direction the particle will move
    		if (dirY == 0) {
    			dirY = 1;
    		} else {
    			dirY = -1;
    		}
    		
    		//To control the balls movements
    		isMoving = true;
    }
    
    public void move() {
    	if (isMoving) {
			xPos += (speed*dirX);
			yPos += (speed*dirY);
			
			//X limit
			if (xPos < 30 || xPos > 870) {
				dirX = dirX*(-1);
			}
			
			//Y limit
			if (yPos < 30 || yPos > 570) {
				dirY = dirY*(-1);
			}
		}
    }
    
    public int getDirX() {
		return dirX;
	}

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
    
    
}
