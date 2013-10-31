package org.celllife.clinicfinder.domain.ussd;

import javax.persistence.*;
import java.util.List;

@Embeddable
public class ClosestLandmark {

    private static final long serialVersionUID = 7564371005698654945L;

    private String locationName;

    public ClosestLandmark() {

    }

    public ClosestLandmark(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

}
