package rw.ac.rca.termOneExam.utils;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CityUtilTest {
    @Autowired
    private ICityRepository cityRepository;

    @Test
    public void lessThan40_success(){
        List<City> cities = cityRepository.findAll();
        for(City city: cities){
            Assertions.assertTrue(city.getWeather() < 40);
        }
    }
    @Test
    public void greaterThan10_success(){
        List<City> cities = cityRepository.findAll();
        for(City city: cities){
            Assertions.assertTrue(city.getWeather() > 10);
        }
    }
    @Test
    public void ContainMusanzeAndKigali_success(){
        List<City> cities = cityRepository.findAll();
        List<String> cities_name = new ArrayList<String>();
        for(City city: cities){
            cities_name.add(city.getName());
        }
        Assertions.assertTrue(cities_name.contains("Musanze"));
       Assertions.assertTrue(cities_name.contains("Kigali"));
    }

    @Test
    public void testSpying(){
        ArrayList<City> arrayListSpy = Mockito.spy(ArrayList.class);
        City city = new City(10,"Nyabihu",34,62);
        arrayListSpy.add(city);

        arrayListSpy.add(city);
        arrayListSpy.add(city);

        when(arrayListSpy.size()).thenReturn(5);
        arrayListSpy.add(city);
        Assertions.assertEquals(5, arrayListSpy.size());

    }

    @Test
    public void testMocking(){
        City city = new City(10,"Kigali",67,78);
        List<City> mockList = Mockito.mock(List.class);
        when(mockList.size()).thenReturn(4);
        Assertions.assertEquals(4, mockList.size());

        when(mockList.get(0)).thenReturn(city);
        Assertions.assertEquals("Kigali", mockList.get(0).getName());
    }


}
