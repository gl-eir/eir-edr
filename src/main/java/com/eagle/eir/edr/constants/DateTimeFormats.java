package com.eagle.eir.edr.constants;

import java.time.format.DateTimeFormatter;

public interface DateTimeFormats {

    DateTimeFormatter edrFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    DateTimeFormatter ss7edrFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

}
