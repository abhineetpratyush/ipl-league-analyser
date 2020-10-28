package com.capgemini.iplleagueanalyser;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IPLAnalyser {
	
	private static final Logger log = LogManager.getLogger(IPLAnalyser.class); 
	
    public static void main( String[] args ) {
        log.info("Welcome to IPL Analyser!");
    }

	public void loadIPLBatsmenData(String mostRunsCsvFilePath) {
		// TODO Auto-generated method stub
		
	}

	public List<BatsmenDataStructure> getBatsmenListSortedOnAverageDescending() {
		// TODO Auto-generated method stub
		return null;
	}
}
