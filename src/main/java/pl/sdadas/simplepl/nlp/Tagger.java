package pl.sdadas.simplepl.nlp;

import java.util.Collections;
import java.util.List;

/**
 * @author Sławomir Dadas
 */
public interface Tagger {

    default Word[] analyze(String... sentence) {
        List<Word[]> res = analyze(Collections.singletonList(sentence));
        return res.isEmpty() ? new Word[0] : res.get(0);
    }

    List<Word[]> analyze(List<String[]> sentences);
}
