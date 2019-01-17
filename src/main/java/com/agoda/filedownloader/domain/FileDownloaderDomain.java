/**
 * 
 */
package com.agoda.filedownloader.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Saravanan
 *
 */
@Entity
@Table(name = "download")
public class FileDownloaderDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_downloader_id_seq")
	@SequenceGenerator(sequenceName = "file_downloader_id_seq", allocationSize = 1, name = "file_downloader_id_seq")
	private Long ID;

	@Column(name = "source_name")
	private String source;

	@Column(name = "destn_name")
	private String destination;

	@Column(name = "start_time")
	private String startDate;

	@Column(name = "end_time")
	private String endDate;

	@Column(name = "protocol")
	private String protocol;

	@Column(name = "data_size")
	private String largeData;

	@Column(name = "data_speed")
	private String speed;

	@Column(name = "failure_percentage")
	private String percentageOfFailure;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getLargeData() {
		return largeData;
	}

	public void setLargeData(String largeData) {
		this.largeData = largeData;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getPercentageOfFailure() {
		return percentageOfFailure;
	}

	public void setPercentageOfFailure(String percentageOfFailure) {
		this.percentageOfFailure = percentageOfFailure;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

}
