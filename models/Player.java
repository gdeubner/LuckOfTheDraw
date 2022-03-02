package models;
public class Player {
    private int id;
    private String name;
    private int score;

    public Player (int id, String name) {
        this.id = id;
        this.name = name;
        score = 0;
    }

    public void setScore(int num){
        score = num;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public void incrementScore(){
        score += 2;
    }

    public void decrementScore(){
        score -= 1;
    }

    //for testing purposes
    @Override
    public String toString(){
        String str = "id: " + id + " name: " + name + " score: " + score;
        return str;
    }


}
