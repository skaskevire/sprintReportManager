package com.epam.srm.files.filter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class CachingXlsxFileFilter implements Filter<List<File>> {
	private Map<String, Long> usedFilesCache;

	public CachingXlsxFileFilter() {
		usedFilesCache = new HashMap<String, Long>();
	}

	@Override
	public List<File> filter(List<File> files) {
		List<File> filteredFiles = files
				.stream()
				.filter(f -> !f.isDirectory())
				.filter(f -> f.getName().substring(f.getName().lastIndexOf('.') + 1).equals("xlsx"))
				.filter(f -> !usedFilesCache.containsKey(f.getName())
						|| !usedFilesCache.get(f.getName()).equals(f.lastModified()))
				.collect(Collectors.toList());
		filteredFiles.forEach(f -> usedFilesCache.put(f.getName(), f.lastModified()));
		return filteredFiles;
	}

}
