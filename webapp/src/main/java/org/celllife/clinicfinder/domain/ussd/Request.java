package org.celllife.clinicfinder.domain.ussd;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Root Entity model for the USSD session data received. It contains information about each interaction with
 * the USSD menu. 
 *
 */
@Entity
@Cacheable
public class Request implements Serializable {

	private static final long serialVersionUID = 2447706927325734934L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "ussdRequestId")),
			@AttributeOverride(name = "ussdString", column = @Column(name = "ussdRequestString")),
			@AttributeOverride(name = "dateTime", column = @Column(name = "ussdRequestDateTime"))})
	private UssdRequest ussdRequest;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "msisdn", column = @Column(name = "msisdn")),
			@AttributeOverride(name = "mnoCode", column = @Column(name = "mnoCode")) })
	private User user;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "xCoordinate", column = @Column(name = "xCoordinate")),
            @AttributeOverride(name= "yCoordinate", column = @Column(name = "yCoordinate"))
    })
	private LocationData locationData;

    @Fetch(FetchMode.JOIN)
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinColumn(name = "request")
    private List<ClosestLandmark> closestLandmarks;

    private String smsText;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UssdRequest getUssdRequest() {
		return ussdRequest;
	}

	public void setUssdRequest(UssdRequest ussdRequest) {
		this.ussdRequest = ussdRequest;
	}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocationData getLocationData() {
        return locationData;
    }

    public void setLocationData(LocationData locationData) {
        this.locationData = locationData;
    }

    public List<ClosestLandmark> getClosestLandmarks() {
        return closestLandmarks;
    }

    public void setClosestLandmarks(List<ClosestLandmark> closestLandMarks) {
        this.closestLandmarks = closestLandMarks;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    @Override
	public String toString() {
		return "Request [id=" + id + ", ussdRequest=" + ussdRequest + ", user="
				+ user + ", locationData=" + locationData + ", closestLandmarks=" + closestLandmarks
				+ ", smsText=" + smsText + "]";
	}
}
