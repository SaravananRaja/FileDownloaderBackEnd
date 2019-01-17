/**
 * 
 */
package com.agoda.filedownloader.service;

/**
 * @author Saravanan
 *
 */
public interface FileDownloaderService {

	public String download(Long pDownloadID,String pSource, String pDestination, String pUserName, String pPassword, String pProtocol);

}
