package com.epam.srm.files.read;

public interface Reader<T> {
	public T read(String directoryPath);
}
