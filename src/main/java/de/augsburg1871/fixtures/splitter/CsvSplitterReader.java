// package de.augsburg1871.fixtures.splitter;
//
// import java.io.File;
// import java.io.IOException;
// import java.nio.charset.StandardCharsets;
// import java.util.List;
//
// import org.springframework.integration.annotation.Splitter;
//
// public class CsvSplitterReader {
//
// private enum MappingStrategy {
// INDEX, NAME;
// }
//
// private int linesToSkip = 0;
// // private boolean header = true;
// private MappingStrategy mappingStrategy = MappingStrategy.NAME;
//
// public CsvSplitterReader skipLines(final int linesToSkip) {
// this.linesToSkip = linesToSkip;
// return this;
// }
//
// public CsvSplitterReader includeHeader(final boolean includeHeader) {
// // this.header = includeHeader;
// return this;
// }
//
// public CsvSplitterReader mappingStrategy(final MappingStrategy
// mappingStrategy) {
// this.mappingStrategy = mappingStrategy;
// return this;
// }
//
// @Splitter
// public List<String> split(final File file) throws IOException {
// final List<String> lines = java.nio.file.Files.readAllLines(file.toPath(),
// StandardCharsets.ISO_8859_1);
// lines.remove(0);
// return lines;
// }
//
// }