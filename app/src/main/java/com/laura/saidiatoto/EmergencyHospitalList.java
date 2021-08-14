package com.laura.saidiatoto;

public class EmergencyHospitalList {
    private String emergencyHospitalname;
    private String emergencyHospitalLocation;
    private String getEmergencyHospitalNumber;

    public EmergencyHospitalList(){

    }

    public EmergencyHospitalList(String emergencyHospitalname, String emergencyHospitalLocation, String getEmergencyHospitalNumber) {
        this.emergencyHospitalname = emergencyHospitalname;
        this.emergencyHospitalLocation = emergencyHospitalLocation;
        this.getEmergencyHospitalNumber = getEmergencyHospitalNumber;
    }

    public String getEmergencyHospitalname() {
        return emergencyHospitalname;
    }

    public void setEmergencyHospitalname(String emergencyHospitalname) {
        this.emergencyHospitalname = emergencyHospitalname;
    }

    public String getEmergencyHospitalLocation() {
        return emergencyHospitalLocation;
    }

    public void setEmergencyHospitalLocation(String emergencyHospitalLocation) {
        this.emergencyHospitalLocation = emergencyHospitalLocation;
    }

    public String getGetEmergencyHospitalNumber() {
        return getEmergencyHospitalNumber;
    }

    public void setGetEmergencyHospitalNumber(String getEmergencyHospitalNumber) {
        this.getEmergencyHospitalNumber = getEmergencyHospitalNumber;
    }

    @Override
    public String toString() {
        return "EmergencyHospitalList{" +
                "emergencyHospitalname='" + emergencyHospitalname + '\'' +
                ", emergencyHospitalLocation='" + emergencyHospitalLocation + '\'' +
                ", getEmergencyHospitalNumber='" + getEmergencyHospitalNumber + '\'' +
                '}';
    }
}
