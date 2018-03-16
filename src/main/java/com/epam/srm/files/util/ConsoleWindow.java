package com.epam.srm.files.util;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsoleWindow {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleWindow.class);

	private Process window;

	@PostConstruct
	public void init() {
		try {
			window = Runtime.getRuntime()
					.exec("cmd /c start powershell.exe Get-Content sprintReportManager.log -Wait -Tail 30");

		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
