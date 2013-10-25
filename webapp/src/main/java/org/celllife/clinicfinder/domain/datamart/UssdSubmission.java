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
public class UssdSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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

    private String closestLandmarks;

    private String smsText;

    public UssdSubmission() {

    }

    public UssdSubmission(String ussdRequestId, Date dateTime, String msisdn, String mnoCode, Double xcoordinate, Double ycoordinate, String closestLandmarks, String smsText) {
        this.ussdRequestId = ussdRequestId;
        this.dateTime = dateTime;
        this.msisdn = msisdn;
        this.mnoCode = mnoCode;
        this.xCoordinate = xcoordinate;
        this.yCoordinate = ycoordinate;
        this.closestLandmarks = closestLandmarks;
        this.smsText = smsText;
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

    public String getClosestLandmarks() {
        return closestLandmarks;
    }

    public void setClosestLandmarks(String closestLandmarks) {
        this.closestLandmarks = closestLandmarks;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    /*@Override
	public String toString() {
		return "UssdPageVisit [id=" + id + ", ussdSessionId=" + ussdSessionId
				+ ", date=" + date + ", msisdn=" + msisdn + ", mnoCode="
				+ mnoCode + ", contentVersion=" + contentVersion
				+ ", serviceId=" + serviceId + ", serviceTitle=" + serviceTitle
				+ ", smsId=" + smsId + ", themeId=" + themeId + ", themeTitle="
				+ themeTitle + "]";
	}*/
}