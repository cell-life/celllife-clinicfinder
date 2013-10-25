package org.celllife.clinicfinder.domain.ussd;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * USSD Session entity for the USSD endpoint. Contains details about the session
 * (times)
 */
@Embeddable
public final class UssdRequest implements Serializable {

	private static final long serialVersionUID = 5766201813161201442L;

	private String id;
	
	@Basic
	private String ussdString;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getUssdString() {
		return ussdString;
	}

	public void setUssdString(String string) {
		this.ussdString = string;
	}

	@Override
	public String toString() {
		return "UssdRequest [id=" + id + ", string=" + ussdString
				+ ", startDateTime=" + dateTime + "]";
	}
}
