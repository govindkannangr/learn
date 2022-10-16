package com.gk.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Result {

	    /*
	     * Complete the 'climbingLeaderboard' function below.
	     *
	     * The function is expected to return an INTEGER_ARRAY.
	     * The function accepts following parameters:
	     *  1. INTEGER_ARRAY ranked
	     *  2. INTEGER_ARRAY player
	     */

	public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        List<Integer>   playerGameRanks = new ArrayList();   
        if(ranked!=null && !ranked.isEmpty() && player!=null && !player.isEmpty()){
            HashSet<Integer> rankSet = new HashSet<>(ranked);  
            Comparator<Integer> cmp = new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1.compareTo(o2);
                }
            };
            for(Integer gameScore: player){
                //refresh rank list
                rankSet.add(gameScore);
                playerGameRanks.add(getRank(gameScore, rankSet, cmp));
            }
        } 
        return playerGameRanks;
    }
    
    public static int getRank(Integer score, HashSet<Integer> rankSet, Comparator<Integer> cmp){
        List<Integer> rankList = new ArrayList(rankSet);
        int index = Collections.binarySearch(rankList, score, cmp)+1;
        return Math.abs(index)-1;
    }
 
	
	public static void main(String[] args) {
        ArrayList<Integer> ranks = new ArrayList<Integer>() {{add(100); add(100); add(50); add(40); add(40); add(20); add(10);}};
        ArrayList<Integer> player = new ArrayList<Integer>() {{add(5); add(25); add(50); add(120);}};
        List<Integer> result = climbingLeaderboard(ranks, player);
        result.stream().forEach(resultVal->{
        	System.out.println(resultVal);
        });
    }

}
