// package de.augsburg1871.fixtures.csv;
//
// import java.nio.charset.Charset;
// import java.nio.charset.StandardCharsets;
// import java.util.List;
//
// public class CsvByNameReader extends AbstractCsvReader {
//
// private final Charset charset;
//
// public CsvByNameReader() {
// this.charset = StandardCharsets.UTF_8;
// }
//
// public CsvByNameReader(final Charset charset) {
// this.charset = charset;
// }
//
// @Override
// public CsvMappingStrategy getMappingStrategy() {
// return CsvMappingStrategy.NAME;
// }
//
// @Override
// protected List<String> getHeadlineColums(final List<String> columns) {
// return columns;
// }
//
// @Override
// public Charset getCharset() {
// return this.charset;
// }
//
// }
