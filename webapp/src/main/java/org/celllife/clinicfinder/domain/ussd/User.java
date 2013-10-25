package org.celllife.clinicfinder.domain.ussd;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * User entity for the USSD endpoint. Contains details about the user - network
 * operator and msisdn
 */
@Embeddable
public final class User implements Serializable {

	private static final long serialVersionUID = -6685209802082539168L;

	@Basic
	private String msisdn;

	@Basic
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

	@Override
	public String toString() {
		return "User [msisdn=" + msisdn + ", mnoCode=" + mnoCode + "]";
	}
}
