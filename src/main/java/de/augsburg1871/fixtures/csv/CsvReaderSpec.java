// package de.augsburg1871.fixtures.csv;
//
// import java.io.File;
// import java.io.IOException;
// import java.nio.charset.Charset;
// import java.nio.charset.StandardCharsets;
// import java.util.List;
// import java.util.Map;
//
// import org.springframework.integration.annotation.ServiceActivator;
//
// import com.google.common.collect.Lists;
//
// public class CsvReaderSpec {
//
// final List<CsvReader> readers = Lists.newArrayList();
// private Charset charset = StandardCharsets.UTF_8;
// private char columnSeparator = ';';
// private int linesToSkip = 0;
// private CsvMappingStrategy mappingStrategy = CsvMappingStrategy.NAME;
//
// public CsvReaderSpec get() {
// readers.add(new CsvByNameReader(charset));
// readers.add(new CsvByIndexReader(charset));
// return this;
// }
//
// public CsvReaderSpec columnSeparator(final char columnSeparator) {
// this.columnSeparator = columnSeparator;
// return this;
// }
//
// public CsvReaderSpec skipLines(final int linesToSkip) {
// this.linesToSkip = linesToSkip;
// return this;
// }
//
// public CsvReaderSpec mappingStrategy(final CsvMappingStrategy
// mappingStrategy) {
// this.mappingStrategy = mappingStrategy;
// return this;
// }
//
// public CsvReaderSpec charset(final Charset charset) {
// this.charset = charset;
// return this;
// }
//
// @ServiceActivator
// public List<Map<String, String>> read(final File file) throws IOException {
// for (final CsvReader csvReader : readers) {
// if (csvReader.getMappingStrategy() == mappingStrategy) {
// return csvReader.read(file, columnSeparator, linesToSkip);
// }
// }
//
// throw new IllegalStateException(
// "Datei kann nicht gelesen werden. Kein Reader für '" + mappingStrategy + "'
// registriert.");
// }
//
// }