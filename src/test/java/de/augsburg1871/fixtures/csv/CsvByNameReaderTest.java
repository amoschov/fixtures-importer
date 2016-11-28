// package de.augsburg1871.fixtures.csv;
//
// import static org.hamcrest.CoreMatchers.allOf;
// import static org.junit.Assert.assertThat;
//
// import java.io.File;
// import java.io.IOException;
// import java.util.List;
// import java.util.Map;
//
// import org.hamcrest.Matchers;
// import org.junit.Test;
//
// public class CsvByNameReaderTest {
//
// private final File file = new File("src/test/resources/simple.csv");
//
// @Test
// public void read() throws IOException {
// final List<Map<String, String>> lines = new CsvByNameReader().read(file, ';',
// 0);
//
// assertThat(lines.get(0),
// allOf(Matchers.hasEntry("A", "1a"), Matchers.hasEntry("B", "1b"),
// Matchers.hasEntry("C", "1c")));
// assertThat(lines.get(1),
// allOf(Matchers.hasEntry("A", "2a"), Matchers.hasEntry("B", "2b"),
// Matchers.hasEntry("C", "2c")));
// assertThat(lines.get(2),
// allOf(Matchers.hasEntry("A", "3a"), Matchers.hasEntry("B", "3b"),
// Matchers.hasEntry("C", "3c")));
// }
//
// }
