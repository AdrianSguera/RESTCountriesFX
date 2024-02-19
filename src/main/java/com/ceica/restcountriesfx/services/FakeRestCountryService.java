package com.ceica.restcountriesfx.services;

import com.ceica.restcountriesfx.models.CountryDTO;

import java.util.List;

public class FakeRestCountryService implements IRestCountry {

    @Override
    public String[] getRegions() {
        return new String[]{"Europe", "Asia", "Africa"};
    }

    @Override
    public List<CountryDTO> getCountriesByRegion(String region) {
        return List.of(new CountryDTO("Argentina", "America", "BSAS", "https://flagcdn.com/w320/ar.png", "Peso", 485000),
                new CountryDTO("España", "Europa", "Madrid", "https://flagcdn.com/w320/es.png", "Euro", 245000));
    }

    @Override
    public CountryDTO getCountryByName(String name) {
        return new CountryDTO("Argentina", "America", "BSAS", "https://flagcdn.com/w320/ar.png", "Peso", 485000);
    }
}
