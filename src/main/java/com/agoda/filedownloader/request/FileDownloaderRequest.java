/**
 * 
 */
package com.agoda.filedownloader.request;

/**
 * @author Saravanan
 *
 */
public class FileDownloaderRequest {

	private Long id;

	private String aSourceLocation;

	private String aDestinationLocation;

	private String aProtcol;

	private String aUserName;

	private String aPassword;

	public String getaSourceLocation() {
		return aSourceLocation;
	}

	public void setaSourceLocation(String aSourceLocation) {
		this.aSourceLocation = aSourceLocation;
	}

	public String getaDestinationLocation() {
		return aDestinationLocation;
	}

	public void setaDestinationLocation(String aDestinationLocation) {
		this.aDestinationLocation = aDestinationLocation;
	}

	public String getaProtcol() {
		return aProtcol;
	}

	public void setaProtcol(String aProtcol) {
		this.aProtcol = aProtcol;
	}

	public String getaUserName() {
		return aUserName;
	}

	public void setaUserName(String aUserName) {
		this.aUserName = aUserName;
	}

	public String getaPassword() {
		return aPassword;
	}

	public void setaPassword(String aPassword) {
		this.aPassword = aPassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
