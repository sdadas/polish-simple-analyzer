package pl.sdadas.simplepl;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import pl.sdadas.simplepl.nlp.Lemmatizer;
import pl.sdadas.simplepl.nlp.WordSplitter;
import pl.sdadas.simplepl.nlp.lemmatization.FrequencyLemmatizer;
import pl.sdadas.simplepl.nlp.tokenization.AlphanumericSplitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Sławomir Dadas
 */
public class Application {

    public static void main(String [] args) {
        WordSplitter splitter = new AlphanumericSplitter();
        Lemmatizer lemmatizer = new FrequencyLemmatizer();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                String line = reader.readLine();
                line = StringUtils.strip(StringEscapeUtils.unescapeJava(line));
                String[] words = splitter.flatSplit(line);
                String[] lemmas = lemmatizer.lemmatize(words);
                System.out.println(StringEscapeUtils.escapeJava(String.join(" ", words)));
                System.out.println(StringEscapeUtils.escapeJava(String.join(" ", lemmas)));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
