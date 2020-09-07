package com.mastercard.cities;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CitiesApplicationTests {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate template;

	@Test
	public void getBostonNewarkRoad() throws Exception {
		URL endPoint = new URL("http://localhost:" + port + "/connected?origin=Boston&destination=Newark");
		ResponseEntity<String> response = template.getForEntity(endPoint.toString(), String.class);
		assertThat(response.getBody(), equalTo("yes"));
	}

	@Test
	public void getBostonPhiladelphiaRoad() throws Exception {
		URL endPoint = new URL("http://localhost:" + port + "/connected?origin=Philadelphia&destination=Philadelphia");
		ResponseEntity<String> response = template.getForEntity(endPoint.toString(), String.class);
		assertThat(response.getBody(), equalTo("yes"));
	}

	@Test
	public void getPhiladelphiaAlbanyRoad() throws Exception {
		URL endPoint = new URL("http://localhost:" + port + "/connected?origin=Boston&destination=Albany");
		ResponseEntity<String> response = template.getForEntity(endPoint.toString(), String.class);
		assertThat(response.getBody(), equalTo("no"));
	}

}
