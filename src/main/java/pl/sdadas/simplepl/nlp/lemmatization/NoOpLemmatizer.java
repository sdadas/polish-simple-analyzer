package pl.sdadas.simplepl.nlp.lemmatization;

import pl.sdadas.simplepl.nlp.Lemmatizer;

import java.util.List;

/**
 * Mock lemmatizer that does nothing.
 *
 * @author Sławomir Dadas <sdadas@opi.org.pl>
 */
public class NoOpLemmatizer implements Lemmatizer {

    @Override
    public List<String[]> lemmatize(List<String[]> sentences) {
        return sentences;
    }
}
