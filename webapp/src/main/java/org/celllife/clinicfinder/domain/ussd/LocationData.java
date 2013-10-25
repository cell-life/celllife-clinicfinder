package org.celllife.clinicfinder.domain.ussd;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * User entity for the USSD endpoint. Contains details about the user - network
 * operator and msisdn
 */
@Embeddable
public final class LocationData implements Serializable {

    private static final long serialVersionUID = -6685209802082539168L;

    @Basic
    private Double xCoordinate;

    @Basic
    private Double yCoordinate;

    public LocationData(Double xCoordinate, Double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public LocationData() {

    }

    public Double getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Double getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public String toString() {
        return "LocationData [xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + "]";
    }
}
