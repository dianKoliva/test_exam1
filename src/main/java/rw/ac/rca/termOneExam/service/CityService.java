package rw.ac.rca.termOneExam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

@Service
public class CityService {

	@Autowired
	private ICityRepository cityRepository;
	
	public Optional<City> getById(long id) {
//      Optional<City> city =cityRepository.findById(id);
//		city.ifPresent(value -> value.setFahrenheit((value.getWeather() * 1.8) + 32));

		return cityRepository.findById(id);
	}

	public List<City> getAll() {
		List<City> city=new ArrayList<>();
		city=cityRepository.findAll();
		for (City value : city) {
			double weather = value.getWeather();
			value.setFahrenheit((weather * 1.8) + 32);
		}
		return city;
	}

	public boolean existsByName(String name) {


		return cityRepository.existsByName(name);
	}

	public City save(CreateCityDTO dto) {
		City city =  new City(dto.getName(), dto.getWeather());
		city.setFahrenheit((dto.getWeather()*1.8)+32);
		return cityRepository.save(city);
	}
	

}
