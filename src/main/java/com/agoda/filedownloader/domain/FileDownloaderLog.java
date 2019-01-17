/**
 * 
 */
package com.agoda.filedownloader.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agoda.filedownloader.FileDownloadedRepository;
/**
 * @author Saravanan
 *
 */

@Component
public class FileDownloaderLog {

	

	public void formRequestDBPersist(Long pID, String pSource, String pDestination, String pStartDate, String pEndDate,
			String pProtocol, String pLargeData, String pSpeed, String pPercentageOfFailure) {
		FileDownloaderDomain aFileDownloaderDomain = new FileDownloaderDomain();
		aFileDownloaderDomain.setID(pID);
		aFileDownloaderDomain.setSource(pSource);
		aFileDownloaderDomain.setDestination(pDestination);
		aFileDownloaderDomain.setStartDate(pStartDate);
		aFileDownloaderDomain.setEndDate(pEndDate);
		aFileDownloaderDomain.setProtocol(pProtocol);
		aFileDownloaderDomain.setLargeData(pLargeData);
		aFileDownloaderDomain.setSpeed(pSpeed);
		aFileDownloaderDomain.setPercentageOfFailure(pPercentageOfFailure);
		this.saveRequest(aFileDownloaderDomain);
	}

	public void saveRequest(FileDownloaderDomain pFileDownloaderDomain) {
		 
		//fileDownloadedRepository.save(pFileDownloaderDomain);
	}

	 
}
