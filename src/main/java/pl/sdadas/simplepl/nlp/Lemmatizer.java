package pl.sdadas.simplepl.nlp;

import java.util.Collections;
import java.util.List;

/**
 * @author Sławomir Dadas <sdadas@opi.org.pl>
 */
public interface Lemmatizer {

    default List<String[]> lemmatize(String text, WordSplitter splitter) {
        List<String[]> words = splitter.split(text);
        return this.lemmatize(words);
    }

    default String[] lemmatize(String... sentence) {
        List<String[]> res = lemmatize(Collections.singletonList(sentence));
        return res.isEmpty() ? new String[0] : res.get(0);
    }

    List<String[]> lemmatize(List<String[]> sentences);
}
