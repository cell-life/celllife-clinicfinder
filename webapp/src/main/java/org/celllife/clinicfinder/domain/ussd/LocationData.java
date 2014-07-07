package org.celllife.clinicfinder.domain.ussd;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * User entity for the USSD endpoint. Contains details about the user - network
 * operator and msisdn
 */
@Embeddable
public class LocationData implements Serializable {

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

    /**
     * Determines if the x and y coordinates are null or 0 
     */
    public boolean isEmpty() {
    	if (xCoordinate != null && yCoordinate != null) {
    		if (xCoordinate == 0 && yCoordinate == 0) {
    			return true;
    		}
    		return false;
    	}
    	return true;
    }

    @Override
    public String toString() {
        return "LocationData [xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + "]";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((xCoordinate == null) ? 0 : xCoordinate.hashCode());
		result = prime * result + ((yCoordinate == null) ? 0 : yCoordinate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocationData other = (LocationData) obj;
		if (xCoordinate == null) {
			if (other.xCoordinate != null)
				return false;
		} else if (!xCoordinate.equals(other.xCoordinate))
			return false;
		if (yCoordinate == null) {
			if (other.yCoordinate != null)
				return false;
		} else if (!yCoordinate.equals(other.yCoordinate))
			return false;
		return true;
	}
}
