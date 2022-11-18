package practice03.persistence;

import practice03.protocol.MySerializableClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    private static Map<Integer, Player> playerDB;
    private static Map<Integer, Team> teamDB;

    private static void init() {

        teamDB = new HashMap<>();
        playerDB = new HashMap<>();

        teamDB.put(1, new Team("A팀"));
        teamDB.put(2, new Team("B팀"));
        teamDB.put(3, new Team("C팀"));
        teamDB.put(4, new Team("D팀"));
        teamDB.put(5, new Team("E팀"));

        playerDB.put(1, new Player("선수1", 22, teamDB.get(1)));
        playerDB.put(2, new Player("선수2", 23, teamDB.get(1)));
        playerDB.put(3, new Player("선수3", 25, teamDB.get(2)));
        playerDB.put(4, new Player("선수4", 25, teamDB.get(2)));
        playerDB.put(5, new Player("선수5", 23, teamDB.get(3)));
        playerDB.put(6, new Player("선수6", 28, teamDB.get(3)));
        playerDB.put(7, new Player("선수7", 25, teamDB.get(4)));
        playerDB.put(8, new Player("선수8", 34, teamDB.get(4)));
        playerDB.put(9, new Player("선수9", 23, teamDB.get(5)));
        playerDB.put(10, new Player("선수10", 20, teamDB.get(5)));

    }

    static {
        init();
    }


    public static Player getPlayer(int id) {
        if(1 <= id && id <= 10) return playerDB.get(id);
        return null;
    }

    public static Team getTeam(int id) {
        if(1 <= id && id <= 10) return teamDB.get(id);
        return null;
    }

    public static Player getPlayer(String name) {
        for(int i=1; i<=10; i++) {
            Player player = playerDB.get(i);
            if(player.getName().equals(name))
                return player;
        }
        return null;
    }

    public static List<MySerializableClass> getAllPlayer() {
        List<MySerializableClass> list = new ArrayList<>();
        for(int i=1; i<=10; i++)
            list.add(playerDB.get(i));

        return list;
    }

    public static List<MySerializableClass> getALlTeam() {
        List<MySerializableClass> list = new ArrayList<>();
        for(int i=1; i<=5; i++)
            list.add(teamDB.get(i));

        return list;
    }


}
