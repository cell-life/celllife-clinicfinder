package org.celllife.clinicfinder.domain.ussd;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

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
    @TableGenerator(
            name="RequestIdGen", 
            table="hibernate_sequences", 
            pkColumnName="sequence_name", 
            valueColumnName="sequence_next_hi_value", 
            pkColumnValue="request",
            initialValue=1,
            allocationSize=1)
    @GeneratedValue(strategy=GenerationType.TABLE, generator="RequestIdGen")
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

    @Override
	public String toString() {
		return "Request [id=" + id + ", ussdRequest=" + ussdRequest + ", user="
				+ user + ", locationData=" + locationData + "]";
	}
}
