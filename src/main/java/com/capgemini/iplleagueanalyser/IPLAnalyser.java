package com.capgemini.iplleagueanalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.capgemini.opencsvbuilder.CSVBuilderFactory;
import com.capgemini.opencsvbuilder.CustomCSVBuilderException;
import com.capgemini.opencsvbuilder.ICSVBuilder;
import com.opencsv.bean.MappingStrategy;

public class IPLAnalyser {

	private static final Logger log = LogManager.getLogger(IPLAnalyser.class); 
	private List<BatsmenDataStructure> batsmenList;
	private List<BowlersDataStructure> bowlersList; 

	public static void main( String[] args ) {
		log.info("Welcome to IPL Analyser!");
	}

	public int loadIPLBatsmenData(String csvFilePath, MappingStrategy<BatsmenDataStructure> mappingStrategy, Class<BatsmenDataStructure> batsmenDataStructureClass, char separator) throws CustomFileIOException, CustomCSVBuilderException {
		try(FileReader fileReader = new FileReader(csvFilePath);
				BufferedReader bufferedReader = new BufferedReader(fileReader);){
			ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
			if(batsmenDataStructureClass != null)
				batsmenList = csvBuilder.getCSVFileList(bufferedReader, BatsmenDataStructure.class, mappingStrategy, separator);
			else
				batsmenList = csvBuilder.getCSVFileList(bufferedReader, null, null, separator);
			return batsmenList.size();
		} catch(IOException e) {
			throw new CustomFileIOException(ExceptionTypeIO.FILE_PROBLEM);
		}
	}

	public List<BatsmenDataStructure> getBatsmenListSortedOnAverageDescending() {
		Comparator<BatsmenDataStructure> batsmenComparator = Comparator.comparing(batsman -> batsman.getAverage());
		this.sortBatsmenDataStructureDescending(batsmenComparator, batsmenList);
		return batsmenList;
	}

	private void sortBatsmenDataStructureDescending(Comparator<BatsmenDataStructure> batsmenComparator, List<BatsmenDataStructure> list) {
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < list.size() - i- 1; j++) {
				BatsmenDataStructure batsmanOne = list.get(j);
				BatsmenDataStructure batsmanTwo = list.get(j + 1);
				if(batsmenComparator.compare(batsmanOne, batsmanTwo) < 0) {
					list.set(j, batsmanTwo);
					list.set(j + 1, batsmanOne);
				}
			}
		}
	}

	public List<BatsmenDataStructure> getBatsmenListSortedOnStrikeRateDescending() {
		Comparator<BatsmenDataStructure> batsmenComparator = Comparator.comparing(batsman -> batsman.getStrikeRate());
		this.sortBatsmenDataStructureDescending(batsmenComparator, batsmenList);
		return batsmenList;
	}

	public List<BatsmenDataStructure> getBatsmenListSortedOnFoursDescending() {
		Comparator<BatsmenDataStructure> batsmenComparator = Comparator.comparing(batsman -> batsman.getNumOfFours());
		this.sortBatsmenDataStructureDescending(batsmenComparator, batsmenList);
		return batsmenList;
	}

	public List<BatsmenDataStructure> getBatsmenListSortedOnSixesDescending() {
		Comparator<BatsmenDataStructure> batsmenComparator = Comparator.comparing(batsman -> batsman.getNumOfSixes());
		this.sortBatsmenDataStructureDescending(batsmenComparator, batsmenList);
		return batsmenList;
	}

	public List<BatsmenDataStructure> getBatsmenListSortedOnStrikeRateWithMaxFours() {
		Comparator<BatsmenDataStructure> batsmenComparator = Comparator.comparing(batsman -> batsman.getStrikeRate());
		this.sortBatsmenDataStructureDescending(batsmenComparator, batsmenList);
		List<BatsmenDataStructure> equalStrikeRateList = new ArrayList<>();
		equalStrikeRateList.add(batsmenList.get(0));
		for(int listItr = 1; listItr < batsmenList.size(); listItr++) {
			if(batsmenList.get(listItr).getStrikeRate() == batsmenList.get(0).getStrikeRate()) 
				equalStrikeRateList.add(batsmenList.get(listItr));
			else
				break;
		}
		Comparator<BatsmenDataStructure> batsmenComparatorForTieBreaker = Comparator.comparing(batsman -> batsman.getNumOfFours());
		this.sortBatsmenDataStructureDescending(batsmenComparatorForTieBreaker, equalStrikeRateList);
		return equalStrikeRateList;
	}

	public List<BatsmenDataStructure> getBatsmenListSortedOnStrikeRateWithMaxSixes() {
		Comparator<BatsmenDataStructure> batsmenComparator = Comparator.comparing(batsman -> batsman.getStrikeRate());
		this.sortBatsmenDataStructureDescending(batsmenComparator, batsmenList);
		List<BatsmenDataStructure> equalStrikeRateList = new ArrayList<>();
		equalStrikeRateList.add(batsmenList.get(0));
		for(int listItr = 1; listItr < batsmenList.size(); listItr++) {
			if(batsmenList.get(listItr).getStrikeRate() == batsmenList.get(0).getStrikeRate()) 
				equalStrikeRateList.add(batsmenList.get(listItr));
			else
				break;
		}
		Comparator<BatsmenDataStructure> batsmenComparatorForTieBreaker = Comparator.comparing(batsman -> batsman.getNumOfSixes());
		this.sortBatsmenDataStructureDescending(batsmenComparatorForTieBreaker, equalStrikeRateList);
		return equalStrikeRateList;
	}

	public List<BatsmenDataStructure> getBatsmenListSortedOnAverageWithMaxStrikeRate() {
		Comparator<BatsmenDataStructure> batsmenComparator = Comparator.comparing(batsman -> batsman.getAverage());
		this.sortBatsmenDataStructureDescending(batsmenComparator, batsmenList);
		List<BatsmenDataStructure> equalAverageList = new ArrayList<>();
		equalAverageList.add(batsmenList.get(0));
		for(int listItr = 1; listItr < batsmenList.size(); listItr++) {
			if(batsmenList.get(listItr).getAverage() == batsmenList.get(0).getAverage()) 
				equalAverageList.add(batsmenList.get(listItr));
			else
				break;
		}
		Comparator<BatsmenDataStructure> batsmenComparatorForTieBreaker = Comparator.comparing(batsman -> batsman.getStrikeRate());
		this.sortBatsmenDataStructureDescending(batsmenComparatorForTieBreaker, equalAverageList);
		return equalAverageList;
	}

	public List<BatsmenDataStructure> getBatsmenListSortedOnRunsWithMaxAverage() {
		Comparator<BatsmenDataStructure> batsmenComparator = Comparator.comparing(batsman -> batsman.getRunsScored());
		this.sortBatsmenDataStructureDescending(batsmenComparator, batsmenList);
		List<BatsmenDataStructure> equalRunsList = new ArrayList<>();
		equalRunsList.add(batsmenList.get(0));
		for(int listItr = 1; listItr < batsmenList.size(); listItr++) {
			if(batsmenList.get(listItr).getRunsScored() == batsmenList.get(0).getRunsScored()) 
				equalRunsList.add(batsmenList.get(listItr));
			else
				break;
		}
		Comparator<BatsmenDataStructure> batsmenComparatorForTieBreaker = Comparator.comparing(batsman -> batsman.getAverage());
		this.sortBatsmenDataStructureDescending(batsmenComparatorForTieBreaker, equalRunsList);
		return equalRunsList;
	}

	public int loadIPLBowlersData(String csvFilePath, MappingStrategy<BowlersDataStructure> mappingStrategy,
			Class<BowlersDataStructure> bowlersDataStructureClass, char separator) throws CustomFileIOException, CustomCSVBuilderException {
		try(FileReader fileReader = new FileReader(csvFilePath);
				BufferedReader bufferedReader = new BufferedReader(fileReader);){
			ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
			if(bowlersDataStructureClass != null)
				bowlersList = csvBuilder.getCSVFileList(bufferedReader, BowlersDataStructure.class, mappingStrategy, separator);
			else
				bowlersList = csvBuilder.getCSVFileList(bufferedReader, null, null, separator);
			return bowlersList.size();
		} catch(IOException e) {
			throw new CustomFileIOException(ExceptionTypeIO.FILE_PROBLEM);
		}
	}

	public List<BowlersDataStructure> getBowlersListSortedOnAverage() {
		Comparator<BowlersDataStructure> bowlersComparator = Comparator.comparing(bowler -> bowler.getAverage());
		this.sortBowlersDataStructureAscending(bowlersComparator, bowlersList);
		List<BowlersDataStructure> nonZeroAverageBowlersList = new ArrayList<>();
		for(int listItr = 0; listItr < bowlersList.size(); listItr++) 
			if(bowlersList.get(listItr).getAverage() != 0) 
				nonZeroAverageBowlersList.add(bowlersList.get(listItr));
		return nonZeroAverageBowlersList;
	}

	private void sortBowlersDataStructureAscending(Comparator<BowlersDataStructure> bowlersComparator,
			List<BowlersDataStructure> list) {
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < list.size() - i- 1; j++) {
				BowlersDataStructure bowlerOne = list.get(j);
				BowlersDataStructure bowlerTwo = list.get(j + 1);
				if(bowlersComparator.compare(bowlerOne, bowlerTwo) > 0) {
					list.set(j, bowlerTwo);
					list.set(j + 1, bowlerOne);
				}
			}
		}
	}

	public List<BowlersDataStructure> getBowlersListSortedOnStrikeRate() {
		Comparator<BowlersDataStructure> bowlersComparator = Comparator.comparing(bowler -> bowler.getStrikeRate());
		this.sortBowlersDataStructureAscending(bowlersComparator, bowlersList);
		List<BowlersDataStructure> nonZeroStrikeRateBowlersList = new ArrayList<>();
		for(int listItr = 0; listItr < bowlersList.size(); listItr++) 
			if(bowlersList.get(listItr).getAverage() != 0) 
				nonZeroStrikeRateBowlersList.add(bowlersList.get(listItr));
		return nonZeroStrikeRateBowlersList;
	}

	public List<BowlersDataStructure> getBowlersListSortedOnEconomyRate() {
		Comparator<BowlersDataStructure> bowlersComparator = Comparator.comparing(bowler -> bowler.getEconomyRate());
		this.sortBowlersDataStructureAscending(bowlersComparator, bowlersList);
		return bowlersList;
	}
}