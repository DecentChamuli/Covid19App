package com.example.covidtracker;

class Country {
    private String CountryName;

    public Country(){}

    public Country(String CountryName){
        this.CountryName = CountryName;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }
}
