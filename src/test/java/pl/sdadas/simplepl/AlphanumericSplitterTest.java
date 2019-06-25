package pl.sdadas.simplepl;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import pl.sdadas.simplepl.nlp.WordSplitter;
import pl.sdadas.simplepl.nlp.tokenization.AlphanumericSplitter;

/**
 * @author Sławomir Dadas
 */
public class AlphanumericSplitterTest {

    @Test
    public void simpleTest() {
        WordSplitter splitter = new AlphanumericSplitter();
        expect(splitter, "", "");
        expect(splitter, " ", "");
        expect(splitter, "Ala ma kota", "Ala ma kota");
        expect(splitter, "Ala ma kota.", "Ala ma kota .");
        expect(splitter, "A jakże--rzekł Jan, śpiewając 100lat...", "A jakże - - rzekł Jan , śpiewając 100lat . . .");
    }

    private void expect(WordSplitter splitter, String input, String output) {
        String[] result = splitter.flatSplit(input);
        String[] expected = StringUtils.split(output);
        Assert.assertArrayEquals(expected, result);
    }
}
