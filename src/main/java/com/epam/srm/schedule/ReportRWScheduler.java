package com.epam.srm.schedule;

import static com.epam.srm.files.util.FileOperations.createOutputFileName;
import static com.epam.srm.files.util.FileOperations.createOutputFilePath;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.epam.srm.entity.Report;
import com.epam.srm.files.read.Reader;
import com.epam.srm.files.write.Writer;

@Component
public class ReportRWScheduler implements Scheduler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportRWScheduler.class);

	@Autowired
	private Writer<Report> reportWriter;

	@Autowired
	private Reader<List<Report>> reportReader;
	
	@Value("${srm.targetDir:.}")
	private String targetDirectory;

	@Scheduled(fixedDelayString = "${srm.jobDelay}")
	@Override
	public void scheduledTask() {
		long startTime = System.currentTimeMillis();
		LOGGER.info("Scheduled operation started!");
		try
		{
			List<Report> reports = reportReader.read(".");
			for (Report report : reports) {
				String outputFilePath = createOutputFilePath(createOutputFileName(report.getName()), targetDirectory);
				reportWriter.write(report, outputFilePath);
				openExcelFile(outputFilePath);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("Scheduled operation ended! Processing time: " + (System.currentTimeMillis() - startTime) + "ms");
	}
	
	private void openExcelFile(String filePath) throws IOException{
		Desktop dk = null;
		dk = Desktop.getDesktop();
		dk.open(new File(filePath));
	}
}
