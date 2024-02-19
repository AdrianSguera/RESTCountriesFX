package com.ceica.restcountriesfx.models;

public class CountryDTO {
    private String name, region, capital, flag, coin;
    private int population;

    public CountryDTO() {
    }

    public CountryDTO(String name, String region, String capital, String flag, String coin, int population) {
        this.name = name;
        this.region = region;
        this.capital = capital;
        this.flag = flag;
        this.coin = coin;
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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
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
}
