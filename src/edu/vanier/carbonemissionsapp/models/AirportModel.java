package edu.vanier.carbonemissionsapp.models;

/**
 *
 * @author mjj
 */
public class AirportModel {
    private int id;
    private String iataCode;
    private String name;
    private String city;
    private String country;

    public AirportModel() {
    }

    @Override
    public String toString() {
        return name  + " (" + iataCode + ")" + 
                "\n" + city + ", " + country;
    }

    public int getId() {
        return id;
    }
    
    public String getIataCode() {
        return iataCode;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
}
