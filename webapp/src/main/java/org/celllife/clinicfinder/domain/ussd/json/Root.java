package org.celllife.clinicfinder.domain.ussd.json;

import java.io.Serializable;

public class Root implements Serializable {
	
	private static final long serialVersionUID = 2911038049026490513L;

	UssdSubmissionRequest ussdSubmissionRequest;
	
	public Root() {
	}

    public UssdSubmissionRequest getUssdSubmissionRequest() {
        return ussdSubmissionRequest;
    }

    public void setUssdSubmissionRequest(UssdSubmissionRequest ussdSubmissionRequest) {
        this.ussdSubmissionRequest = ussdSubmissionRequest;
    }

    @Override
	public String toString() {
		return "Root [ussdSubmissionRequest="
				+ ussdSubmissionRequest + "]";
	}
}
