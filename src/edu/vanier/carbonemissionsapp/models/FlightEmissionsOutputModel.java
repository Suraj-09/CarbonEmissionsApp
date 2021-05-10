/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.carbonemissionsapp.models;


/**
 *
 * @author cstuser
 */
public class FlightEmissionsOutputModel {
    private String status;
    private OutputModel output;
    private String version;

    public FlightEmissionsOutputModel() {
    }

    public String getStatus() {
        return status;
    }

    public OutputModel getOutput() {
        return output;
    }

    public String getVersion() {
        return version;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOutput(OutputModel output) {
        this.output = output;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "FlightEmissionsOutputModel{" + 
                "status=" + status + 
                ", output=" + output + 
                ", version=" + version + 
                '}';
    }

    

}
