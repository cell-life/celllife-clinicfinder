package org.celllife.clinicfinder.domain.datamart;

import javax.persistence.*;

import java.util.Date;

/**
 * Entity object containing the USSD Session data specifically flattened for use in reports.
 *
 * The data is extracted from the Entity object Request.
 */
@Entity
@Cacheable
public class UssdClinicFinder {

    @Id
    @TableGenerator(
            name="UssdClinicFinderIdGen", 
            table="hibernate_sequences", 
            pkColumnName="sequence_name", 
            valueColumnName="sequence_next_hi_value", 
            pkColumnValue="ussd_clinic_finder",
            initialValue=1,
            allocationSize=1)
    @GeneratedValue(strategy=GenerationType.TABLE, generator="UssdClinicFinderIdGen")
    private Long id;

    @Basic(optional=false)
    private String ussdRequestId;

    private String ussdString;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional=false)
    private Date dateTime;

    @Basic(optional=false)
    private String msisdn;

    @Basic(optional=false)
    private String mnoCode;

    private Double xCoordinate;

    private Double yCoordinate;
    
    private String closestClinicName;
    
    private String closestClinicId;
    
    private String provinceName;
    
    private String districtName;
    
    private String smsText;

    public UssdClinicFinder() {

    }

    public UssdClinicFinder(String ussdRequestId, Date dateTime, String msisdn, String mnoCode, Double xcoordinate, Double ycoordinate) {
        this.ussdRequestId = ussdRequestId;
        this.dateTime = dateTime;
        this.msisdn = msisdn;
        this.mnoCode = mnoCode;
        this.xCoordinate = xcoordinate;
        this.yCoordinate = ycoordinate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUssdRequestId() {
        return ussdRequestId;
    }

    public void setUssdRequestId(String ussdRequestId) {
        this.ussdRequestId = ussdRequestId;
    }

    public String getUssdString() {
        return ussdString;
    }

    public void setUssdString(String ussdString) {
        this.ussdString = ussdString;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date date) {
        this.dateTime = date;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getMnoCode() {
        return mnoCode;
    }

    public void setMnoCode(String mnoCode) {
        this.mnoCode = mnoCode;
    }

    public Double getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(Double xcoordinate) {
        this.xCoordinate = xcoordinate;
    }

    public Double getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(Double ycoordinate) {
        this.yCoordinate = ycoordinate;
    }

	public String getClosestClinicName() {
		return closestClinicName;
	}

	public void setClosestClinicName(String closestClinicName) {
		this.closestClinicName = closestClinicName;
	}

	public String getClosestClinicId() {
		return closestClinicId;
	}

	public void setClosestClinicId(String closestClinicId) {
		this.closestClinicId = closestClinicId;
	}

	public String getSmsText() {
		return smsText;
	}

	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

}