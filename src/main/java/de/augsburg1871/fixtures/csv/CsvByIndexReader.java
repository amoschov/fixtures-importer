// package de.augsburg1871.fixtures.csv;
//
// import java.nio.charset.Charset;
// import java.nio.charset.StandardCharsets;
// import java.util.List;
//
// import com.google.common.collect.Lists;
//
// public class CsvByIndexReader extends AbstractCsvReader {
//
// private final Charset charset;
//
// public CsvByIndexReader() {
// this.charset = StandardCharsets.UTF_8;
// }
//
// public CsvByIndexReader(final Charset charset) {
// this.charset = charset;
// }
//
// @Override
// public CsvMappingStrategy getMappingStrategy() {
// return CsvMappingStrategy.INDEX;
// }
//
// @Override
// protected List<String> getHeadlineColums(final List<String> columns) {
//
// final List<String> columnIndex = Lists.newArrayList();
// for (int i = 0; i < columns.size(); i++) {
// columnIndex.add(String.valueOf(i));
// }
//
// return columnIndex;
// }
//
// @Override
// public Charset getCharset() {
// return this.charset;
// }
//
// }
