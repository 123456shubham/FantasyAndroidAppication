package com.aryan.stumps11.CreateTeam;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SelectedData {

    private static final SelectedData obj=new SelectedData();
    private SelectedData(){}

    public static SelectedData getSelectedData(){
        return obj;
    }

    public enum Role{
        WK,BAT,BOWL,ALL
    }

    private static final HashMap<String,Boolean> map = new HashMap<>();
    private static final List<data> data = new ArrayList<>();
    private static final HashMap<String,data> captainMap = new HashMap<>();
    private static int wicketKeeper = 0;
    private static int batsman = 0;
    private static int bowler = 0;
    private static int allRounder = 0;
    private static int credit = 0;



    public int getPlayerCount(){
        int count = 0;
        for(com.aryan.stumps11.CreateTeam.SelectedData.data data : captainMap.values()){

                count ++;

        }
        return count;
    }

    public int getTeamCount(String teamName){
        int count = 0;
        try{
            for(com.aryan.stumps11.CreateTeam.SelectedData.data data : captainMap.values()){

                if (data.team.equals(teamName)){
                    count ++;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public float getCreditPoints() {
        float count = 0;
        try {
            for (com.aryan.stumps11.CreateTeam.SelectedData.data data : captainMap.values()) {

               // count += Float.parseFloat(data.getPoints());
                count += Float.parseFloat(data.points);





            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("njkhjk",count+"");

        return count;




    }
    public int getRoleCount(String role){
        int count = 0;
        for(com.aryan.stumps11.CreateTeam.SelectedData.data data : captainMap.values()){
            if (data.role.equals(role)){
                count ++;
            }
        }
        return count;
    }

    public void setViceCaption(String key,boolean isTrue){

        for(com.aryan.stumps11.CreateTeam.SelectedData.data data : captainMap.values()){
            if (data.getId()==key){
                data.setVCaption(isTrue);
            }
        }

    }

    public void setCaption(String key,boolean isTrue){

        for(com.aryan.stumps11.CreateTeam.SelectedData.data data : captainMap.values()){
            if (data.getId()==key){
                data.setCaption(isTrue);
            }
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int getWicketKeeper() {
        map.entrySet()
                .stream().filter(x->"wk".equals(x.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return wicketKeeper;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int countWk() {
        int value=
         map.entrySet()
                .stream().filter(x->"wk".equals(x.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).size();
        return  value;

    }

    public static void setWicketKeeper(int wicketKeeper) {
        SelectedData.wicketKeeper = wicketKeeper;
    }

    public static int getBatsman() {
        return batsman;
    }

    public static void setBatsman(int batsman) {
        SelectedData.batsman = batsman;
    }

    public static int getBowler() {
        return bowler;
    }

    public static void setBowler(int bowler) {
        SelectedData.bowler = bowler;
    }

    public static int getAllRounder() {
        return allRounder;
    }

    public static void setAllRounder(int allRounder) {
        SelectedData.allRounder = allRounder;
    }

    public static int getCredit() {
        return credit;
    }

    public static void setCredit(int credit) {
        SelectedData.credit = credit;
    }



    public HashMap<String,data> getData(){
        return captainMap;
    }

    public boolean getPlayer(String key){
        if (captainMap.containsKey(key)){
            return true;
        }
        else{
            return false;
        }
    }

    public void clearData(){
        map.clear();
        data.clear();
//        captainMap.clear();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean putPlayer(String key, boolean b){
        if (map.size()<12){
            map.put(key,b);

            return true;
        }
        else{
           // map.remove(key,b);
            return false;
        }

    }

    public static void removePlayer(String key){
        captainMap.remove(key);
    }

    public static class data{
         private String id;
         private String name;
         private String team;
         private String role;
         private String points;
         private boolean caption=false;
         private boolean vcaption=false;

         public data(String id, String name, String team, String role, String points) {
             this.id = id;
             this.name = name;
             this.team = team;
             this.role = role;
             this.points =points;
         }

         public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getTeam() {
            return team;
        }

        public String getRole() {
            return role;
        }

        public String  getPoints() {
            return points;
        }
        public void setCaption(boolean isTrue){
             this.caption=isTrue;
        }
        public void setVCaption(boolean isTrue){
            this.vcaption=isTrue;
        }

        public boolean isCaption() {
            return caption;
        }

        public boolean isVcaption() {
            return vcaption;
        }

        public void setVcaption(boolean vcaption) {
            this.vcaption = vcaption;
        }
    }
}
