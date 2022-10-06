import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.io.PrintWriter;
public class PickEm {
    public static void main(String[] args){
        String[] matchups = thisWeeksGames();
        ArrayList<Player> players = new ArrayList<>();
        int done = setPlayers(players);
        boolean continues = true;
        Scanner keyboard = new Scanner(System.in);
        while (continues){
            System.out.println("What would you like to do?");
            System.out.println("1: View all players/picks in alphabetical order");
            System.out.println("2: Update points with winners");
            System.out.println("3: Sort by weekly points");
            System.out.println("4: Sort by total points");
            System.out.println("5: Leave :(");
            try{
                int choice = keyboard.nextInt();
                switch(choice){
                    case 1:
                        players.sort(new ComparatorByName());
                        for (Player player : players){
                            System.out.println(player.getName() + "\t" + (player.getPicks()));
                        }
                        break;
                    case 2:
                        done = winners(matchups, players, done);
                        break;
                    case 3:
                        players.sort(new ComparatorByWeek());
                        for (Player player : players){
                            System.out.println(player);
                        }
                        break;
                    case 4:
                        players.sort(new ComparatorByTotal());
                        for (Player player : players){
                            System.out.println(player);
                        }
                        break;
                    case 5:
                        continues = false;
                        break;
                    default:
                        System.out.println("Choose a number I asked you to please");
                        break;
                }
            }
            catch(InputMismatchException e){
                System.out.println("number pls");
            }
        }
        try{
            File file = new File("Picks.csv");
            PrintWriter writer = new PrintWriter(file);
            writer.println(done);
            for (Player player : players){
                writer.print(player.getName() + ",");
                ArrayList<String> chosen = player.getPicks();
                for (String pick : chosen){
                    writer.print(pick + ",");
                }
                writer.print(player.getWeeks() + ",");
                writer.println(player.getTotal());
            }
            writer.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Could not write to this file");
        }
        
    }

    public static int winners(String[] matchups, ArrayList<Player> players, int start){
        int cnt = start;
        String matchup;
        String[] teams = new String[2];
        String winner = "";
        Scanner keyboard = new Scanner(System.in);
        do{
            try{
                matchup = matchups[cnt];
                teams = matchup.split("\\|");
                System.out.println("Who won in " + matchup);
                System.out.println("Just type 1 for the first team, 2 for the second, 3 for tie, or -1 to exit");
                int winnern = keyboard.nextInt();
                if (winnern == -1){
                    break;
                }
                else if(winnern == 1){
                    String[] attempt = teams[0].split(" ");
                    winner = attempt[attempt.length-1];
                    System.out.println(winner);
                    for (Player player:players){
                        //System.out.println(player.getPicks()[cnt]);
                        if (player.getPicks().contains(winner)){
                            player.incrementPoints();
                        }
                    }
                    
                }
                else if(winnern == 2){
                    String[] attempt = teams[1].split(" ");
                    winner = attempt[attempt.length-1];
                    System.out.println(winner);
                    for (Player player:players){
                        //System.out.println(player.getPicks()[cnt]);
                        if (player.getPicks().contains(winner)){
                            player.incrementPoints();
                        }
                    }
                }
                else if (winnern == 3){}
                else{
                    System.out.println("Please input one of the three options");
                    cnt--;
                }
                cnt++;
            }
            catch(InputMismatchException e){
                System.out.println("Number please :)");
                keyboard.next();
            }
        }
        while(cnt < matchups.length && matchups[cnt] != null);
        return cnt;
    }
    public static int setPlayers(ArrayList<Player> players){
        File file = new File("Picks.csv");
        int ans = 0;
        try{
            Scanner inputs = new Scanner(file);
            ans = Integer.parseInt(inputs.nextLine());
            while(inputs.hasNextLine()){
                int cnt = 1;
                String[] values = inputs.nextLine().split(",");
                Player temp = new Player(values[0]);
                while(cnt < values.length -2){
                    temp.addPick(values[cnt]);
                    cnt++;
                    //System.out.println(values[cnt-1]);
                }
                temp.setWeeks(Integer.parseInt(values[cnt]));
                temp.setTotal(Integer.parseInt(values[cnt+1]));
                players.add(temp);
            }

        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
            System.exit(0);
        }
        return ans;
    }


    public static String[] thisWeeksGames(){
        File file = new File("NFLSchedule.csv");
        String[] games = new String[16];
        try{
            Scanner input = new Scanner(file);
            String thisweek = input.nextLine();
            String[] rightWeek = input.nextLine().split(",");
            //System.out.println(rightWeek[0]);
            int cnt = 0;
            //rightWeek = input.nextLine().split(",");
            try{
                while(!rightWeek[0].matches("Week*.") && input.hasNextLine()){
                    games[cnt] = rightWeek[0] + "|" + rightWeek[1] ;
                    cnt++;
                    if(input.hasNext()){
                        rightWeek = input.nextLine().split(",");
                    }
                }
            }
            catch(Exception e){

            }

        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
            System.exit(0);
        }
        
        return games;
    }
    
}
