/**
     * The {@code Player} class represents a player in the game, storing information about the player's
     * name, number of lives, total time spent in the game, number of questions answered, and the last
     * room the player completed.
     */
public class Player {
    private String name;
    private int lives;
    private int totalTime=0;
    private static int questionsAnswered=0;
    private int roomcompleted = 0;
    
    /**
     * Constructs a new {@code Player} with the specified name and number of lives.
     *
     * @param name The name of the player.
     * @param lives The initial number of lives the player has.
     */

    public Player(String name, int lives) {
        this.name = name;
        this.lives= lives;
        
    }
    /**
     * Sets the total number of questions answered by the player.
     *
     * @param questions The number of questions answered.
     */

    public void applyQuestionsAnswered(int questions) {
    	questionsAnswered = questions;
    }
    /**
     * Returns the name of the player.
     *
     * @return The name of the player.
     */

    public String getName() {
        return name;
    }
    /**
     * Sets the number of lives remaining for the player.
     *
     * @param lives The number of lives remaining.
     */

    public void setLives(int lives) {
    	this.lives = lives;
    }
    /**
     * Returns the number of lives remaining for the player.
     *
     * @return The number of lives remaining.
     */

    public int getLives() {
    	return this.lives;
    }
    /**
     * Adds a specified amount of time to the player's total time spent in the game.
     *
     * @param time The amount of time to add.
     */

    public void setTotaltime(int time) {
    	totalTime = totalTime+time;
    }
    /**
     * Returns the total time spent by the player in the game.
     *
     * @return The total time spent in the game.
     */

    public int getTotaltime() {
    	return totalTime;
    }
    /**
     * Increments the total number of questions answered by the player by one.
     */

    public void setQuestionsAnswered() {
    	questionsAnswered =  questionsAnswered+1;
    }
    /**
     * Returns the total number of questions answered by the player.
     *
     * @return The total number of questions answered.
     */

    public int getQuestionsAnswered() {
    	return questionsAnswered;
    }
    /**
     * Sets the last room completed by the player.
     *
     * @param rooom The last room completed.
     */

    public void setRoom(int rooom) {
    	this.roomcompleted = rooom;
    	
    }
    /**
     * Returns the last room completed by the player.
     *
     * @return The last room completed.
     */

    public int getRoom() {
    	return roomcompleted; 
    }
    
    


    // Additional attributes and methods can be added as needed
}