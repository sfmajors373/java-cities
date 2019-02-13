package com.lambdaschool.cities;

import com.lambdaschool.cities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long>
{
}
