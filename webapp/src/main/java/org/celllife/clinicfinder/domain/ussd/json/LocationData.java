package org.celllife.clinicfinder.domain.ussd.json;

import java.io.Serializable;

public final class LocationData implements Serializable {

    private static final long serialVersionUID = -6685209802082539168L;

    private Double xCoordinate;

    private Double yCoordinate;

    public LocationData(Double xcoordinate, Double ycoordinate) {
        this.xCoordinate = xcoordinate;
        this.yCoordinate = ycoordinate;
    }

    public LocationData() {
    }

    public Double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String toString() {
        return "LocationData [xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + "]";
    }
}
