/**
 * 
 */
package com.agoda.filedownloader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agoda.filedownloader.FileDownloadedRepository;
import com.agoda.filedownloader.constants.FileDownloaderConstants;
import com.agoda.filedownloader.request.FileDownloaderRequest;
import com.agoda.filedownloader.service.FileDownloaderService;
import com.agoda.filedownloader.service.HttpFileDownloadServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Saravanan
 *
 */
@RestController
@Component
public class FileDownloaderController {
	
	@Autowired
	FileDownloaderService fileDownloaderService;
	
   
	// Agoda
	@RequestMapping(value = "/agoda/download", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody String processDownloadRequest(@RequestBody String pFileDownloaderRequest) {
		String response = FileDownloaderConstants.EMPTY_STRING;
		ObjectMapper mapper = new ObjectMapper();
		try {

			FileDownloaderRequest fileDownloaderRequest = mapper.readValue(pFileDownloaderRequest,
					FileDownloaderRequest.class);
			if (fileDownloaderRequest.getaProtcol() != null
					&& fileDownloaderRequest.getaProtcol() != FileDownloaderConstants.EMPTY_STRING) {

				if (fileDownloaderRequest.getaProtcol().equalsIgnoreCase(FileDownloaderConstants.HTTP_PROTOCOL)
						|| fileDownloaderRequest.getaProtcol()
								.equalsIgnoreCase(FileDownloaderConstants.HTTPS_PROTOCOL)) {
					fileDownloaderService = new HttpFileDownloadServiceImpl();
					response = fileDownloaderService.download(fileDownloaderRequest.getId(),fileDownloaderRequest.getaSourceLocation(),
							fileDownloaderRequest.getaDestinationLocation(), fileDownloaderRequest.getaUserName(),
							fileDownloaderRequest.getaPassword(), fileDownloaderRequest.getaProtcol());
				
				}
			}

		} catch (Exception exception) {

			System.out.println("Exception Occured in Downloading a File");
		} finally {
			mapper = null;

		}
		return response;
	}
}
