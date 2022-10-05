import java.util.ArrayList;
public class Player{
    private String name;
    private ArrayList<String> picks = new ArrayList<>();
    private int weekPoints;
    private int totalPoints;
    int cnt = 0;

    public Player(String name){
        this.name = name;
        weekPoints = 0;
        totalPoints = 0;
    }
    public Player(String name, int weekpts, int totalpts){
        this.name = name;
        weekPoints = weekpts;
        totalPoints = totalpts;
    }

    public void addPick(String pick){
        picks.add(pick);
        cnt++;
    }

    public String getName(){
        return name;
    }
    public ArrayList<String> getPicks(){
        return picks;
    }
    public int getWeeks(){
        return weekPoints;
    }
    public int getTotal(){
        return totalPoints;
    }
    public void incrementPoints(){
        weekPoints++;
        totalPoints++;
    }
    public void setWeeks(int pts){
        weekPoints = pts;
    }
    public void setTotal(int pts){
        totalPoints = pts;
    }

    public String toString(){
        return String.format("%-15s\t%-5d\t%-5d", name, weekPoints, totalPoints);
    }
}