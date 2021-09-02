package com.laura.saidiatoto;

public class NearbyHospitalsModel {
    private String hospitalName,latitude,longitude,number,genLocation;
    //private String k ="";
    public NearbyHospitalsModel() {
    }

    public NearbyHospitalsModel(String hospitalName, String latitude, String longitude, String number, String genLocation) {
        this.hospitalName = hospitalName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.number = number;
        this.genLocation = genLocation;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getNumber() {
        return number;
    }

    public String getGenLocation() {
        return genLocation;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setGenLocation(String genLocation) {
        this.genLocation = genLocation;
    }
}
