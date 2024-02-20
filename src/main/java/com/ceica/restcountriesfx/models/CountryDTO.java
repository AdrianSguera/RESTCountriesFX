package com.ceica.restcountriesfx.models;

public class CountryDTO {
    private String name, region, flag, coin;
    private String[] capital;
    private int population;

    public CountryDTO() {
    }

    public CountryDTO(String name, String region, String flag, String coin, String[] capital, int population) {
        this.name = name;
        this.region = region;
        this.flag = flag;
        this.coin = coin;
        this.capital = capital;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String[] getCapital() {
        return capital;
    }

    public void setCapital(String[] capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public static CountryDTO fromCountryDAO(CountryDAO countryDAO){
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName(countryDAO.name.common);
        countryDTO.setFlag(countryDAO.flags.png);
        countryDTO.setPopulation(countryDAO.population);
        countryDTO.setCapital(countryDAO.capital);
        String keySet =(String) countryDAO.currencies.keySet().toArray()[0];
        countryDTO.setCoin(countryDAO.currencies.get(keySet).name);
        return countryDTO;
    }
}
