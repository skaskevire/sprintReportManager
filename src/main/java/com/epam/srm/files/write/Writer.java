package com.epam.srm.files.write;

public interface Writer <T> {
	public void write(T t, String filePath);
}
