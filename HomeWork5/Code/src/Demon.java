import java.util.ArrayList;
import java.util.HashMap;

public class Demon {
    private final HashMap<Integer, Team> teams;
    private final HashMap<String, Soldier> unformedSoldiers;

    public Demon() {
        this.teams = new HashMap<>();
        this.unformedSoldiers = new HashMap<>();
    }

    public void makeSoldier(ArrayList<String> orders) {
        String name = orders.get(0);
        String ind = orders.get(1);
        unformedSoldiers.put(name, new Soldier(name, ind));

    }

    public void formSoldier(ArrayList<String> orders) {
        int id = Integer.parseInt(orders.get(0));
        int num = Integer.parseInt(orders.get(1));
        Team team = new Team();
        for (int i = 0; i < num; i++) {
            String name = orders.get(i + 2);
            Soldier soldier = unformedSoldiers.get(name);
            team.addSoldier(soldier);
            unformedSoldiers.remove(name);
        }
        teams.put(id, team);
    }

    public void copyTeam(ArrayList<String> orders) {
        int id1 = Integer.parseInt(orders.get(0));
        int id2 = Integer.parseInt(orders.get(1));
        Team team = teams.get(id1);
        Team newTeam = team.cloneSelf();
        teams.put(id2, newTeam);
    }

    public void screenTeam(ArrayList<String> orders) {
        int id = Integer.parseInt(orders.get(0));
        int standard = Integer.parseInt(orders.get(1));
        teams.get(id).screen(standard);
    }

    public void mergeId2Id(ArrayList<String> orders) {
        int id1 = Integer.parseInt(orders.get(0));
        int id2 = Integer.parseInt(orders.get(1));
        Team team1 = teams.get(id1);
        Team team2 = teams.get(id2);
        team1.mergeTeam(team2);
        teams.remove(id2);
    }

    public void teamAddStr(ArrayList<String> orders) {
        int id = Integer.parseInt(orders.get(0));
        String str = orders.get(1);
        teams.get(id).allAddStr(str);
    }

    public void teamInterceptStr(ArrayList<String> orders) {
        int id = Integer.parseInt(orders.get(0));
        int a = Integer.parseInt(orders.get(1));
        int b = Integer.parseInt(orders.get(2));
        teams.get(id).allIntercept(a, b);
    }

    public void getTeamSize(ArrayList<String> orders) {
        int id = Integer.parseInt(orders.get(0));
        System.out.println(teams.get(id).getSize());
    }

    public void getTeamSizeWithStr(ArrayList<String> orders) {
        int id = Integer.parseInt(orders.get(0));
        String str = orders.get(1);
        System.out.println(teams.get(id).getSizeOfHasStr(str));
    }

    public void getUnformedSoldierNum() {
        System.out.println(unformedSoldiers.size());
    }

}

