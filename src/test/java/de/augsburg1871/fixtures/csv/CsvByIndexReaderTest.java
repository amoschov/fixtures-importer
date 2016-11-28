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
// public class CsvByIndexReaderTest {
//
// private final File file = new File("src/test/resources/simple.csv");
//
// @Test
// public void read() throws IOException {
// final List<Map<String, String>> lines = new CsvByIndexReader().read(file,
// ';', 0);
//
// assertThat(lines.get(0),
// allOf(Matchers.hasEntry("0", "1a"), Matchers.hasEntry("1", "1b"),
// Matchers.hasEntry("2", "1c")));
// assertThat(lines.get(1),
// allOf(Matchers.hasEntry("0", "2a"), Matchers.hasEntry("1", "2b"),
// Matchers.hasEntry("2", "2c")));
// assertThat(lines.get(2),
// allOf(Matchers.hasEntry("0", "3a"), Matchers.hasEntry("1", "3b"),
// Matchers.hasEntry("2", "3c")));
// }
//
// }
