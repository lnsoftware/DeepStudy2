package xpadro.spring.web.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import xpadro.spring.web.model.Series;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
	"classpath:xpadro/spring/web/configuration/root-context.xml",
	"classpath:xpadro/spring/web/configuration/app-context.xml"})
public class SeriesFunctionalTesting {
	private static final String BASE_URI = "http://localhost:8080/spring-rest-api-v32/spring/series";
	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private MongoOperations mongoOps;
	
	@Before
	public void setup() {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new StringHttpMessageConverter());
		converters.add(new Jaxb2RootElementHttpMessageConverter());
		converters.add(new MappingJacksonHttpMessageConverter());
		restTemplate.setMessageConverters(converters);
		
		initializeDatabase();
	}
	
	private void initializeDatabase() {
		try {
			mongoOps.dropCollection("series");
			
			mongoOps.insert(new Series(1, "The walking dead", "USA", "Thriller"));
			mongoOps.insert(new Series(2, "Homeland", "USA", "Drama"));
		} catch (DataAccessResourceFailureException e) {
			fail("MongoDB instance is not running");
		}
	}

	
	@Test
	public void getAllSeries() {
		Series[] series = restTemplate.getForObject(BASE_URI, Series[].class);
		
		assertNotNull(series);
		assertEquals(2, series.length);
		assertEquals(1L, series[0].getId());
		assertEquals("The walking dead", series[0].getName());
		assertEquals("USA", series[0].getCountry());
		assertEquals("Thriller", series[0].getGenre());
		assertEquals(2L, series[1].getId());
		assertEquals("Homeland", series[1].getName());
		assertEquals("USA", series[1].getCountry());
		assertEquals("Drama", series[1].getGenre());
	}
	
	
	@Test
	public void getJsonSeries() {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJacksonHttpMessageConverter());
		restTemplate.setMessageConverters(converters);
		
		String uri = BASE_URI + "/{seriesId}";
		ResponseEntity<Series> seriesEntity = restTemplate.getForEntity(uri, Series.class, 1l);
		assertNotNull(seriesEntity.getBody());
		assertEquals(1l, seriesEntity.getBody().getId());
		assertEquals("The walking dead", seriesEntity.getBody().getName());
		assertEquals("USA", seriesEntity.getBody().getCountry());
		assertEquals("Thriller", seriesEntity.getBody().getGenre());
		
		assertEquals(MediaType.parseMediaType("application/json;charset=UTF-8"), seriesEntity.getHeaders().getContentType());
	}
	
	@Test
	public void getXmlSeries() {
		String uri = BASE_URI + "/{seriesId}";
		ResponseEntity<Series> seriesEntity = restTemplate.getForEntity(uri, Series.class, 1L);
		assertNotNull(seriesEntity.getBody());
		assertEquals(1l, seriesEntity.getBody().getId());
		assertEquals("The walking dead", seriesEntity.getBody().getName());
		assertEquals("USA", seriesEntity.getBody().getCountry());
		assertEquals("Thriller", seriesEntity.getBody().getGenre());
		
		assertEquals(MediaType.APPLICATION_XML, seriesEntity.getHeaders().getContentType());
	}
	
	@Test
	public void getNotFoundSeries() {
		String uri = BASE_URI + "/{seriesId}";
		
		try {
			restTemplate.getForEntity(uri, Series.class, 5L);
		} catch (HttpClientErrorException  e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}
	
	@Test
	public void insertNewSeries() {
		Series series = new Series();
		series.setId(4L);
		series.setName("mySeries");
		series.setCountry("England");
		series.setGenre("sci-fi");
		
		URI newUserLocation = restTemplate.postForLocation(BASE_URI, series);
		assertEquals(BASE_URI + "/4", newUserLocation.toString());
	}
	
	@Test
	public void deleteExistingSeries() {
		String uri = BASE_URI + "/{seriesId}";
		restTemplate.delete(uri, 2L);
		
		try {
			restTemplate.getForEntity(uri, Series.class, 2L);
		} catch (HttpClientErrorException  e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}
}
