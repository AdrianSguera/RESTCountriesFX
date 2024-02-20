package com.ceica.restcountriesfx.services;

import com.ceica.restcountriesfx.models.CountryDAO;
import com.ceica.restcountriesfx.models.CountryDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RestCountryService implements IRestCountry {
    @Override
    public String[] getRegions() {
        String data = getFromApi("https://restcountries.com/v3.1/all");
        Gson gson = new Gson();
        CountryDAO[] countries = gson.fromJson(data, CountryDAO[].class);
        List<String> regions = new ArrayList<>();
        for (CountryDAO country : countries)
            if (!regions.contains(country.region))
                regions.add(country.region);
        String[] regionsArray = new String[regions.size()];
        for (int i = 0; i < regions.size(); i++) {
            regionsArray[i] = regions.get(i);
        }
        return regionsArray;
    }

    @Override
    public List<CountryDTO> getCountriesByRegion(String region) {
        String data = getFromApi("https://restcountries.com/v3.1/region/" + region);
        Gson gson = new Gson();
        List<CountryDAO> countriesDAO = gson.fromJson(data, new TypeToken<List<CountryDAO>>(){}.getType());
        List<CountryDTO> countriesDTO = new ArrayList<>();
        for (CountryDAO country : countriesDAO)
            countriesDTO.add(CountryDTO.fromCountryDAO(country));
        return countriesDTO;
    }

    @Override
    public CountryDTO getCountryByName(String name) {
        return null;
    }

    private static String getFromApi(String petition) {
        try {
            URL url = new URI(petition).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        RestCountryService restCountryService = new RestCountryService();
        System.out.println(restCountryService.getRegions());
    }
}
