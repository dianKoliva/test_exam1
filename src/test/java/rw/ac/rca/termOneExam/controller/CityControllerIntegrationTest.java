package rw.ac.rca.termOneExam.controller;


import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.utils.APICustomResponse;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAll_success() throws JSONException {
        String response = this.restTemplate.getForObject("/api/cities/all", String.class);
        JSONAssert.assertEquals("[{\"id\":101,\"name\":\"Kigali\",\"weather\":24.0,\"fahrenheit\":0.0},{\"id\":102,\"name\":\"Musanze\",\"weather\":18.0,\"fahrenheit\":0.0},{\"id\":103,\"name\":\"Rubavu\",\"weather\":20.0,\"fahrenheit\":0.0},{\"id\":104,\"name\":\"Nyagatare\",\"weather\":28.0,\"fahrenheit\":0.0}]", response, true);
    }

    @Test
    public void getById_success() throws JSONException {
        ResponseEntity<City> response = this.restTemplate.getForEntity("/api/cities/id/101", City.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(24.0, response.getBody().getWeather(),0);
        assertEquals("Kigali", response.getBody().getName());

    }

    @Test
    public void getById_notFound() {
        ResponseEntity<APICustomResponse> response = this.restTemplate.getForEntity("/api/cities/id/554", APICustomResponse.class);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("City not found with id 554", response.getBody().getMessage());
    }

    @Test
    public void save_Sxs() {

        CreateCityDTO city1 = new CreateCityDTO();
        city1.setName("Mukamira");
        city1.setWeather(13.5);


        ResponseEntity<City> response = this.restTemplate.postForEntity("/api/cities/add", city1, City.class);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Mukamira",response.getBody().getName());
        assertEquals(13.5,response.getBody().getWeather(),0);
    }

    @Test
    public void save_failed() {

        CreateCityDTO city1 = new CreateCityDTO();
        city1.setName("Mukamira");
        city1.setWeather(13.5);


        ResponseEntity<City> response = this.restTemplate.postForEntity("/api/cities/add", city1, City.class);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Mukamira",response.getBody().getName());
        assertEquals(13.5,response.getBody().getWeather(),0);
    }



}
