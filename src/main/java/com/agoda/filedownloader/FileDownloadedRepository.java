/**
 * 
 */
package com.agoda.filedownloader;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agoda.filedownloader.domain.FileDownloaderDomain;

/**
 * @author Saravanan
 *
 */
@Repository
@Configurable
public interface FileDownloadedRepository extends JpaRepository<FileDownloaderDomain, Long> {

}