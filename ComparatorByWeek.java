import java.util.Comparator;
public class ComparatorByWeek implements Comparator<Player>{
    public int compare(Player p1, Player p2){
        int a = p1.getWeeks();
        int b = p2.getWeeks();
        if (a < b){
            return 1;
        }
        if (a > b){
            return -1;
        }
        return 0;
    }
}