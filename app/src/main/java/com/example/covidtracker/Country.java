package com.example.covidtracker;

class Country {
    private String CountryName;
    private String CountryCode;
    private int Vaccine;

    public Country(){}

    public Country(String CountryName, String CountryCode){
        this.CountryName = CountryName;
        this.CountryCode = CountryCode;
    }

    public Country(String CountryName, int Vaccine){
        this.CountryName = CountryName;
        this.Vaccine = Vaccine;
    }

    public void setVaccine(int vaccine) {
        Vaccine = vaccine;
    }
    public int getVaccine() {
        return Vaccine;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }
    public String getCountryName() {
        return CountryName;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }
    public String getCountryCode() {
        return CountryCode;
    }
}