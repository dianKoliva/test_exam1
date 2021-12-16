package rw.ac.rca.termOneExam.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {
    @Mock
    private ICityRepository iCityRepository;

    @InjectMocks
    private CityService cityService;

    @Test
    public void getAll_withSomeElements() {

        when(iCityRepository.findAll()).thenReturn(Arrays.asList(new City("Kigali",8),
                new City("Musanze",6)));
        assertEquals("Kigali",cityService.getAll().get(0).getName());
    }




    @Test
    public void getOne(){
        Optional<City> city = Optional.of(new City("Musanze",8));
        when(iCityRepository.findById(1L)).thenReturn(city);
        Assertions.assertEquals("Musanze",cityService.getById(1L).get().getName());
    }

    @Test
    public void getOne_fail(){
        when(iCityRepository.findById(2L)).thenReturn(null);
        Assertions.assertNull(cityService.getById(2L));
    }

    @Test
    public  void create_success(){
        CreateCityDTO city=new CreateCityDTO();
        city.setName("Kigali");
        city.setWeather(23);
        City createdCity=new City("Kigali",23);

        when(iCityRepository.save(ArgumentMatchers.any(City.class))).thenReturn(createdCity);
        assertEquals("Kigali",cityService.save(city).getName());
    }


    @Test
    public  void create_fail(){
        CreateCityDTO city=new CreateCityDTO();
        city.setName("Kigali");
        city.setWeather(23);
        City createdCity=new City("Kigali",23);

        when(iCityRepository.save(ArgumentMatchers.any(City.class))).thenReturn(null);
        assertNull(cityService.save(city));
    }
}
