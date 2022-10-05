import java.util.Comparator;
public class ComparatorByTotal implements Comparator<Player>{
    public int compare(Player p1, Player p2){
        return (p2.getTotal() - p1.getTotal());
    }
}