// package de.augsburg1871.fixtures.csv;
//
// import java.io.File;
// import java.io.IOException;
// import java.nio.charset.Charset;
// import java.nio.file.Files;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Map;
//
// import com.google.common.collect.Lists;
// import com.google.common.collect.Maps;
//
// public abstract class AbstractCsvReader implements CsvReader {
//
// @Override
// public List<Map<String, String>> read(final File file, final char
// columnSeparator, final int skipLines)
// throws IOException {
// final List<String> lines = Files.readAllLines(file.toPath(), getCharset());
// for (int i = 0; i < skipLines; i++) {
// lines.remove(i);
// }
//
// final List<String> headlineColumns = getHeadlineColums(
// Arrays.asList(lines.get(0).split(String.valueOf(columnSeparator))));
//
// final List<Map<String, String>> linesByName = Lists.newArrayList();
// for (int i = 1; i < lines.size(); i++) {
// final Map<String, String> columnsByName = Maps.newHashMap();
// final List<String> columns =
// Arrays.asList(lines.get(i).split(String.valueOf(columnSeparator)));
//
// for (int j = 0; j < columns.size(); j++) {
// columnsByName.put(headlineColumns.get(j), columns.get(j));
// }
//
// linesByName.add(columnsByName);
// }
//
// return linesByName;
// }
//
// protected abstract Charset getCharset();
//
// protected abstract List<String> getHeadlineColums(List<String> columns);
//
// }
