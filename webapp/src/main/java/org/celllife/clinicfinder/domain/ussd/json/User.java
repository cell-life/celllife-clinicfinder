package org.celllife.clinicfinder.domain.ussd.json;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;

public final class User implements Serializable {

	private static final long serialVersionUID = -6685209802082539168L;

	private String msisdn;

	private String mnoCode;

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

	public String toString() {
		return "User [msisdn=" + msisdn + ", mnoCode=" + mnoCode + "]";
	}
}
