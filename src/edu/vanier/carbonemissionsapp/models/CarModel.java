package edu.vanier.carbonemissionsapp.models;

/*
 *
 * @author Suraj
 */
public class CarModel {
    
    private int carId;
    private int year;
    private String make;
    private String model;
    private String vehicleClass;
    private String engineSize;
    private String cylinders;
    private String transmission;
    private String fuelType;
    private double cityFuelConsumption;
    private double highwayFuelConsumption;
    private int carbonEmissions;
    private int carbonRating;

    public CarModel() {
    }
    
    public CarModel(int carId, int year, String make, String model, String vehicleClass, String engineSize, String cylinders, String transmission, String fuelType, double cityFuelConsumption, double highwayFuelConsumption, int carbonEmissions, int carbonRating) {
        this.carId = carId;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleClass = vehicleClass;
        this.engineSize = engineSize;
        this.cylinders = cylinders;
        this.transmission = transmission;
        this.fuelType = fuelType;
        this.cityFuelConsumption = cityFuelConsumption;
        this.highwayFuelConsumption = highwayFuelConsumption;
        this.carbonEmissions = carbonEmissions;
        this.carbonRating = carbonRating;
    }
    
    @Override
    public String toString() {
        return carId + ", " + year + ", " + make + ", " + model + ", " 
                + vehicleClass + ", " + engineSize + "L, " + cylinders + ", " 
                + transmission + ", " + fuelType +  ", " + carbonEmissions + "g/km, " 
                + carbonRating;
    }
    
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }
    
    public String getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }
    public String getCylinders() {
        return cylinders;
    }

    public void setCylinders(String cylinders) {
        this.cylinders = cylinders;
    }
    
    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    
    public double getCityFuelConsumption() {
        return cityFuelConsumption;
    }

    public void setCityFuelConsumption(double cityFuelConsumption) {
        this.cityFuelConsumption = cityFuelConsumption;
    }

    public double getHighwayFuelConsumption() {
        return highwayFuelConsumption;
    }

    public void setHighwayFuelConsumption(double highwayFuelConsumption) {
        this.highwayFuelConsumption = highwayFuelConsumption;
    }

    public int getCarbonEmissions() {
        return carbonEmissions;
    }

    public void setCarbonEmissions(int carbonEmissions) {
        this.carbonEmissions = carbonEmissions;
    }

    public int getCarbonRating() {
        return carbonRating;
    }

    public void setCarbonRating(int carbonRating) {
        this.carbonRating = carbonRating;
    }

}
