package com.mastercard.cities.service.support;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataUtil {

	private static final Logger LOG = LogManager.getLogger(DataUtil.class);

	@Value("${filepath.cities:city.txt}")
	private String inputFile;

	@Value("${temp.directory}")
	private String tempDirectory;

	public boolean matchCollections(String origin, String destination) throws IOException {

		boolean match = false;
		Path path = Paths.get(tempDirectory, inputFile);
		List<String> originConnectedKeys = null;
		List<String> destinationConnectedKeys = null;

		try (Stream<String[]> filteredLines = Files.lines(path).map(line -> line.split(","))
				.filter(city -> Arrays.asList(city).contains(origin))) {

			originConnectedKeys = filteredLines.flatMap((p) -> Arrays.asList(p).stream()).distinct()
					.collect(Collectors.toList());
			LOG.info("--------origin list -----------");
			originConnectedKeys.stream().forEach(e -> LOG.info(e));

		} catch (IOException e) {
			throw new IOException(e);
		}

		try (Stream<String[]> filteredLines = Files.lines(path).map(line -> line.split(","))
				.filter(city -> Arrays.asList(city).contains(destination))) {

			destinationConnectedKeys = filteredLines.flatMap((p) -> Arrays.asList(p).stream()).distinct()
					.collect(Collectors.toList());
			LOG.info("--------destination list-----------");
			destinationConnectedKeys.stream().forEach(e -> LOG.info(e));

		} catch (IOException e) {
			throw new IOException(e);
		}

		final List<String> _destinationConnectedKeys = destinationConnectedKeys;
		match = originConnectedKeys.stream()
				.flatMap(x -> _destinationConnectedKeys.stream().filter(y -> x.equals(y)).limit(1)).findFirst()
				.isPresent();
		LOG.info("--------lists are match " + match +  " -----------");
		return match;

	}

}
