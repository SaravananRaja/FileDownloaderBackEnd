package com.agoda.filedownloader.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agoda.filedownloader.FileDownloadedRepository;
import com.agoda.filedownloader.constants.FileDownloaderConstants;
import com.agoda.filedownloader.domain.FileDownloaderLog;

@Service
public class HttpFileDownloadServiceImpl implements FileDownloaderService {

	@Autowired
	FileDownloaderLog aFileDownloaderLog;
	
	
	
	public String downloadHTTP(Long pDownloadID, String pSource, String pDestination) throws IOException {
		String response = FileDownloaderConstants.EMPTY_STRING;
		URL website = new URL(pSource);
		ReadableByteChannel rbc;
		rbc = Channels.newChannel(website.openStream());
		String fileName[] = pSource.split("/");
		pDestination += fileName[fileName.length - 1];
		FileOutputStream fos = new FileOutputStream(pDestination);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		String endDate = null;
		String startDate = null;
		try {
			LocalDateTime current = LocalDateTime.now();
			startDate = current.format(formatter);
		//	fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			current = LocalDateTime.now();
			endDate = current.format(formatter);
			response = FileDownloaderConstants.SUCCESS;
		} /*catch (MalformedInputException malformedInputException) {
			malformedInputException.printStackTrace();
			response = FileDownloaderConstants.FAILURE;
		} catch (IOException ioException) {
			ioException.printStackTrace();
			response = FileDownloaderConstants.FAILURE;
		}*/ finally {
			fos.close();
			rbc.close();
		}

		aFileDownloaderLog.formRequestDBPersist(pDownloadID, pSource, pDestination, startDate, endDate,
				FileDownloaderConstants.HTTP_PROTOCOL, "BIG", "SLOW", "100%");

		return response;
	}

	public String downloadHTTPS(Long pDownloadID, String pSource, String pDestination) throws IOException {
		String response = FileDownloaderConstants.EMPTY_STRING;
		// This will get input data from the server
		InputStream inputStream = null;
		// This will read the data from the server;
		OutputStream outputStream = null;
		String fileName[] = pSource.split("/");
		pDestination += fileName[fileName.length - 1];
		String endDate = null;
		String startDate = null;
		try {
			// This will open a socket from client to server
			URL url = new URL(pSource);

			// This user agent is for if the server wants real humans to visit
			String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";

			// This socket type will allow to set user_agent
			URLConnection con = url.openConnection();

			// Setting the user agent
			con.setRequestProperty("User-Agent", USER_AGENT);

			// Getting content Length
			int contentLength = con.getContentLength();
			double contentLengthinKB = contentLength / 1024;
			System.out.println("Original File Size in KB is " + contentLengthinKB);
			URL website = new URL(pSource);
			ReadableByteChannel rbc;
			rbc = Channels.newChannel(website.openStream());
			File file = new File(pDestination);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

			try {
				LocalDateTime current = LocalDateTime.now();
				startDate = current.format(formatter);
				FileOutputStream fos = new FileOutputStream(pDestination);
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				endDate = current.format(formatter);
				response = FileDownloaderConstants.SUCCESS;
			} catch (Exception exception) {
				if (file.exists()) {
					if (this.deleteFile(file)) {
						System.out.println("File got deleted");
					}
				}
				response = FileDownloaderConstants.FAILURE;
			}
		} catch (Exception ex) {
		}

		FileDownloaderLog aFileDownloaderLog = new FileDownloaderLog();
		aFileDownloaderLog.formRequestDBPersist(pDownloadID, pSource, pDestination, startDate, endDate,
				FileDownloaderConstants.HTTP_PROTOCOL, "BIG", "SLOW", "100%");

		return response;
	}

	private boolean deleteFile(File pFileName) {
		return pFileName.delete();
	}

	private String processHTTPDownload(Long pDownloadID, String pSource, String pDestination) {
		String response = FileDownloaderConstants.EMPTY_STRING;
		try {
			response = this.downloadHTTP(pDownloadID, pSource, pDestination);
		} catch (Exception exception) {
			exception.printStackTrace();
			response = FileDownloaderConstants.FAILURE;
		}
		return response;
	}

	private String processHTTSPDownload(Long pDownloadID, String pSource, String pDestination) {
		String response = FileDownloaderConstants.EMPTY_STRING;
		try {
			response = this.downloadHTTPS(pDownloadID, pSource, pDestination);
		} catch (Exception exception) {
			exception.printStackTrace();
			response = FileDownloaderConstants.FAILURE;
		}
		return response;
	}

	@Override
	public String download(Long pDownloadID, String pSource, String pDestination, String pUserName, String pPassword,
			String pProtocol) {

		String response = FileDownloaderConstants.EMPTY_STRING;

		if (pProtocol.equalsIgnoreCase(FileDownloaderConstants.HTTP_PROTOCOL)) {
			response = this.processHTTPDownload(pDownloadID, pSource, pDestination);
		} else {
			response = this.processHTTSPDownload(pDownloadID, pSource, pDestination);
		}
		return response;
	}

}
