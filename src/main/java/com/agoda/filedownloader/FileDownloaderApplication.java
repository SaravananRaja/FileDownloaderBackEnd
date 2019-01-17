package com.agoda.filedownloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = "com.agoda.filedownloader.controller.*")
@SpringBootApplication
public class FileDownloaderApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(FileDownloaderApplication.class, args);
		test();
		
	}
	public static void test() {
		System.out.println("tsaasd" );
		
	}
	
	 

}

