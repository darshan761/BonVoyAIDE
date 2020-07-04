package com.example.bonvoyaide.models;

public class Country {

    private String country;
    private String Slug;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSlug() {
        return Slug;
    }

    public void setSlug(String slug) {
        Slug = slug;
    }

    @Override
    public String toString() {
        return "Country{" +
                "country='" + country + '\'' +
                ", Slug='" + Slug + '\'' +
                '}';
    }
}
