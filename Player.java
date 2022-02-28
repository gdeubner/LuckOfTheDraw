public class Player {
    private int id;
    private String name;
    private int score;

    public Player (){
        id = 0;
        name = "";
        score = 0;
    }

    public Player (int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
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

    public void decrimentScore(){
        score -= 1;
    }

    @Override
    public String toString(){
        String str = "id: " + id + " name: " + name + " score: " + score;
        return str;
    }


}
