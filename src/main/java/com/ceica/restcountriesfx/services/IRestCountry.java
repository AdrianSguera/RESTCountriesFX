package com.ceica.restcountriesfx.services;

import com.ceica.restcountriesfx.models.CountryDTO;

import java.util.List;

public interface IRestCountry {
    public String[] getRegions();
    public List<CountryDTO> getCountriesByRegion(String region);
    public CountryDTO getCountryByName(String name);
}
