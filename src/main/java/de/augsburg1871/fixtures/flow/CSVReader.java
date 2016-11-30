package de.augsburg1871.fixtures.flow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessagingException;

import com.google.common.collect.Lists;

public class CSVReader {

	private final Log log = LogFactory.getLog(CSVReader.class);

	@ServiceActivator
	public Collection<CSVRecord> read(final File csv) throws MessagingException {
		try {
			final FileInputStream fileInputStream = new FileInputStream(csv);
			final Reader reader = new InputStreamReader(fileInputStream, StandardCharsets.ISO_8859_1);
			return CSVFormat.EXCEL.withFirstRecordAsHeader()
					.withIgnoreEmptyLines()
					.withDelimiter(';')
					.parse(reader)
					.getRecords();
		} catch (final IOException e) {
			log.error("Datei konnte nicht verarbeitet werden.", e);
		}
		return Lists.<CSVRecord>newArrayList();
	}

}
