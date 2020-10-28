package com.capgemini.iplleagueanalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		this.sortBatsmenDataStructureDescending(batsmenComparator);
		return batsmenList;
	}

	private void sortBatsmenDataStructureDescending(Comparator<BatsmenDataStructure> batsmenComparator) {
		for(int i = 0; i < batsmenList.size(); i++) {
			for(int j = 0; j < batsmenList.size() - i- 1; j++) {
				BatsmenDataStructure batsmanOne = batsmenList.get(j);
				BatsmenDataStructure batsmanTwo = batsmenList.get(j + 1);
				if(batsmenComparator.compare(batsmanOne, batsmanTwo) < 0) {
					batsmenList.set(j, batsmanTwo);
					batsmenList.set(j + 1, batsmanOne);
				}
			}
		}
	}

	public List<BatsmenDataStructure> getBatsmenListSortedOnStrikeRateDescending() {
		Comparator<BatsmenDataStructure> batsmenComparator = Comparator.comparing(batsman -> batsman.getStrikeRate());
		this.sortBatsmenDataStructureDescending(batsmenComparator);
		return batsmenList;
	}

	public List<BatsmenDataStructure> getBatsmenListSortedOnFoursDescending() {
		Comparator<BatsmenDataStructure> batsmenComparator = Comparator.comparing(batsman -> batsman.getNumOfFours());
		this.sortBatsmenDataStructureDescending(batsmenComparator);
		return batsmenList;
	}

	public List<BatsmenDataStructure> getBatsmenListSortedOnSixesDescending() {
		Comparator<BatsmenDataStructure> batsmenComparator = Comparator.comparing(batsman -> batsman.getNumOfSixes());
		this.sortBatsmenDataStructureDescending(batsmenComparator);
		return batsmenList;
	}
}