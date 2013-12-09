package org.celllife.clinicfinder.application.clinicservice

/**
 * Data bean which contains clinic information returned by the Clinic Service
 */
class Clinic implements Serializable {

	private Long id;
	
	private String name;

	private String code;

	private String shortName;
	
	private String phoneNumber;
	
	private String address;

	private String coordinates;

	private String subDistrictName;
	
	private String districtName;
	
	private String provinceName;
	
	public Clinic() {
		
	}
	
	public Clinic(String name, String shortName, String address, String phoneNumber) {
		this.name = name;
		this.shortName = shortName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
		
	}
	
	public String getSubDistrictName() {
		return subDistrictName;
	}
	
	public void setSubDistrictName(String subDistrictName) {
		this.subDistrictName = subDistrictName;
	}
	
	public String getDistrictName() {
		return districtName;
	}
	
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}
