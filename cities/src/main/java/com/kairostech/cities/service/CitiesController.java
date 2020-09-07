package com.mastercard.cities.service;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.cities.service.support.DataUtil;

@RestController
public class CitiesController {

	private static final Logger LOG = LogManager.getLogger(CitiesController.class);

	@Value("${filepath.cities:city.txt}")
	private String inputFile;

	@Value("${temp.directory}")
	private String tempDirectory;

	@Autowired
	private DataUtil dataUtil;

	@RequestMapping(value = "/connected", method = RequestMethod.GET)
	public String user(@RequestParam(value = "origin", defaultValue = "city1") String origin,
			@RequestParam(value = "destination", defaultValue = "city2") String destination) {

		boolean result = false;

		try {
			result = dataUtil.matchCollections(origin, destination);
		} catch (IOException e) {
			LOG.error(e);
		}

		return result ? "yes" : "no";
	}

	@RequestMapping("/")
	public String index() {
		return "Please use this end point- http://localhost:8080/connected?origin=city1&destination=city2";
	}

}
