package com.epam.srm.convert;

public interface Converter <I,O>{
	O convert(I i, String workbookName);
}
