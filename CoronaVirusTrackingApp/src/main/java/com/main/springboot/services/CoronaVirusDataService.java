package com.main.springboot.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.main.springboot.models.LocationStats;

@Service
public class CoronaVirusDataService {

	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	private List<LocationStats> allStats = null;

	@PostConstruct
	@Scheduled(fixedDelay = 10000)
	public void fetchVirusData() {
		allStats = new ArrayList<LocationStats>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(VIRUS_DATA_URL)).build();

		try {
			HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
			StringReader csvReader = new StringReader(httpResponse.body());
			CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
			for (CSVRecord csvRecord : csvParser) {
				LocationStats locationStats = new LocationStats();
				locationStats.setCountry(csvRecord.get("Country/Region"));
				locationStats.setState(csvRecord.get("Province/State"));
				locationStats.setLatestTotalCases(Integer.parseInt(csvRecord.get(csvRecord.size() - 1)));
				locationStats.setIncreasedCasesPerDay(Integer.parseInt(csvRecord.get(csvRecord.size()-1))-Integer.parseInt(csvRecord.get(csvRecord.size()-2)));
				
				
				allStats.add(locationStats);

			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (InterruptedException in) {
			in.getStackTrace();
		}

	}

	public List<LocationStats> getAllStats() {
		return allStats;
	}
	
	public int getIncreaseCasesToday() {
		ListIterator<LocationStats> localstatIterator= allStats.listIterator();
				int totalCoronaCasesperDay=0;
				while(localstatIterator.hasNext()) {
					LocationStats locationStats=localstatIterator.next();
						totalCoronaCasesperDay	+= locationStats.getIncreasedCasesPerDay();
				}
				return totalCoronaCasesperDay;
	}

	public Long getTotalNumberOfCasesToday() {
		ListIterator<LocationStats> localStatIterator = getAllStats().listIterator();
		Long totalNumberOfCasesToday = 0l;
		while (localStatIterator.hasNext()) {
			LocationStats locationStats = localStatIterator.next();
			totalNumberOfCasesToday += (long) locationStats.getLatestTotalCases();
		}
		return totalNumberOfCasesToday;
	}

	
	}


