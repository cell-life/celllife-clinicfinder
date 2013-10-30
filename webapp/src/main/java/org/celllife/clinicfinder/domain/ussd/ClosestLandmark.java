package org.celllife.clinicfinder.domain.ussd;

import javax.persistence.*;
import java.util.List;

@Entity
public class ClosestLandmark {

    private static final long serialVersionUID = 7564371005698654945L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String locationName;

    public ClosestLandmark() {

    }

    public ClosestLandmark(String locationName) {
        this.locationName = locationName;
    }

    @ManyToOne
    @JoinColumn(name = "request")
    private Request request;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

}
