package com.capgemini.iplleagueanalyser;

import com.opencsv.bean.CsvBindByName;

public class BatsmenDataStructure {
	
	@CsvBindByName(column = "PLAYER", required = true)
	private String playerName;
	
	@CsvBindByName(column = "Mat", required = true)
	private int numOfMatchesPlayed;
	
	@CsvBindByName(column = "Inns", required = true)
	private int numOfInningsPlayed;
	
	@CsvBindByName(column = "NO", required = true)
	private int numOfTimesRemainedNotOut;
	
	@CsvBindByName(column = "Runs", required = true)
	private int runsScored;
	
	@CsvBindByName(column = "HS", required = true)
	private String highestScore;
	
	@CsvBindByName(column = "Avg", required = true)
	private double average;
	
	@CsvBindByName(column = "BF", required = true)
	private int ballsFaced;
	
	@CsvBindByName(column = "SR", required = true)
	private double strikeRate;
	
	@CsvBindByName(column = "100", required = true)
	private int numOfCenturies;
	
	@CsvBindByName(column = "50", required = true)
	private int numOfHalfCenturies;
	
	@CsvBindByName(column = "4s", required = true)
	private int numOfFours;
	
	@CsvBindByName(column = "6s", required = true)
	private int numOfSixes;

	public String getPlayerName() {
		return playerName;
	}

	public double getAverage() {
		return average;
	}
}
