public class Player {
    private String name;
    private int lives;
    private int totalTime=0;
    private static int questionsAnswered=0;
    private int roomcompleted = 0;
    
    public Player(String name, int lives) {
        this.name = name;
        this.lives= lives;
        
    }
    public void applyQuestionsAnswered(int questions) {
    	questionsAnswered = questions;
    }

    public String getName() {
        return name;
    }
    
    public void setLives(int lives) {
    	this.lives = lives;
    }
    
    public int getLives() {
    	return this.lives;
    }
    
    public void setTotaltime(int time) {
    	totalTime = totalTime+time;
    }
    public int getTotaltime() {
    	return totalTime;
    }

    public void setQuestionsAnswered() {
    	questionsAnswered =  questionsAnswered+1;
    }
    public int getQuestionsAnswered() {
    	return questionsAnswered;
    }
    public void setRoom(int rooom) {
    	this.roomcompleted = rooom;
    	
    }
    public int getRoom() {
    	return roomcompleted; 
    }
    
    


    // Additional attributes and methods can be added as needed
}