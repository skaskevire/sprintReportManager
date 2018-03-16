package com.epam.srm.entity;

import java.util.ArrayList;
import java.util.List;

public class Report {
	private final String name;
	private List<ReportLine> reportLines = new ArrayList<>();

	public Report(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<ReportLine> getReportLines() {
		return reportLines;
	}

	public void addReportLine(ReportLine reportLine) {
		reportLines.add(reportLine);
	}

}
