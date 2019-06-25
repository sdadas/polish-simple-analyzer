package pl.sdadas.simplepl;

import org.junit.Assert;
import org.junit.Test;
import pl.sdadas.simplepl.nlp.lemmatization.FrequencyLemmatizer;

/**
 * @author Sławomir Dadas <sdadas@opi.org.pl>
 */
public class FrequencyLemmatizerTest {

    private FrequencyLemmatizer object = new FrequencyLemmatizer();

    @Test
    public void lemmaTest() {
        check(array(""), array(""));
        check(array("się"), array("się"));
        check(array("nie_ma_w_słowniku"), array("nie_ma_w_słowniku"));
        check(array("mam", "ale", "i", "kota"), array("mieć", "ale", "i", "kot"));
        check(array("zażółcić", "gęślą", "jaźń"), array("zażółcić", "gęśla", "jaźń"));
        check(array("a", "t", "w", "o", "v"), array("a", "t", "w", "o", "v"));
    }

    private String[] array(String... values) {
        return values;
    }

    private void check(String[] input, String[] output) {
        String[] lemmas = object.lemmatize(input);
        Assert.assertArrayEquals(lemmas, output);
    }
}
