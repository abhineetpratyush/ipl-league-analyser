package com.capgemini.iplleagueanalyser;

public class AllRounderDataStructure {
	
	private String playerName;
	private int combinedRunsAndWickets;
	
	public AllRounderDataStructure(String playerName, int combinedRunsAndWickets) {
		this.playerName = playerName;
		this.combinedRunsAndWickets = combinedRunsAndWickets;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	public int getCombinedRunsAndWickets() {
		return combinedRunsAndWickets;
	}
}
