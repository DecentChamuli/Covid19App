package com.example.covidtracker;

class Country {
    private String CountryName;
    private String CountryCode;

    public Country(){}

    public Country(String CountryName, String CountryCode){
        this.CountryName = CountryName;
        this.CountryCode = CountryCode;
    }

    public String getCountryName() {
        return CountryName;
    }
    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }
    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }
}
