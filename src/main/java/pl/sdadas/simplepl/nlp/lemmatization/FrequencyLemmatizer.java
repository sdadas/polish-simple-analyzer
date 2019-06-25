package pl.sdadas.simplepl.nlp.lemmatization;

import morfologik.stemming.IStemmer;
import morfologik.stemming.WordData;
import morfologik.stemming.polish.PolishStemmer;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.util.fst.FST;
import pl.sdadas.simplepl.fst.FSTUtils;
import pl.sdadas.simplepl.nlp.Lemmatizer;
import pl.sdadas.simplepl.nlp.Tagger;
import pl.sdadas.simplepl.nlp.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple lemmatizer based on morfologik, selecting the most popular lemma (by occurrences in polish).
 *
 * @author Sławomir Dadas <sdadas@opi.org.pl>
 */
public class FrequencyLemmatizer implements Lemmatizer, Tagger {

    private static final String FST_PATH = "frequency_polish.fst";

    private FST<Long> frequencies;

    private IStemmer stemmer;

    public FrequencyLemmatizer() {
        this.stemmer = new PolishStemmer();
        this.frequencies = FSTUtils.load(FST_PATH, Long.class);
    }

    @Override
    public List<String[]> lemmatize(List<String[]> sentences) {
        List<String[]> res = new ArrayList<>();
        for (String[] sentence : sentences) {
            String[] lemmas = Arrays.stream(sentence).map(this::analyze).map(Word::getBase).toArray(String[]::new);
            res.add(lemmas);
        }
        return res;
    }

    @Override
    public List<Word[]> analyze(List<String[]> sentences) {
        List<Word[]> res = new ArrayList<>();
        for (String[] sentence : sentences) {
            Word[] words = Arrays.stream(sentence).map(this::analyze).toArray(Word[]::new);
            res.add(words);
        }
        return res;
    }

    private Word analyze(String word) {
        List<WordData> data = stemmer.lookup(word);
        if(!StringUtils.isAllLowerCase(word)) {
            data = new ArrayList<>(data);
            data.addAll(stemmer.lookup(StringUtils.lowerCase(word)));
        }
        Word result = new Word(word, word);
        if(data.isEmpty()) checkInterp(result);
        long currentFreq = -1;
        for (WordData wd : data) {
            String newStem = wd.getStem().toString();
            result.addTag(newStem, StringUtils.substringBefore(wd.getTag().toString(), "+"));
            long newFreq = frequency(newStem);
            if(newFreq > currentFreq) {
                currentFreq = newFreq;
                result.setBase(newStem);
            }
        }
        if(StringUtils.length(word) == 1) {
            result.setBase(word);
        }
        return result;
    }

    private void checkInterp(Word result) {
        if(result.getTags().isEmpty() && !StringUtils.isAlphanumeric(result.getOriginal())) {
            result.addTag(result.getOriginal(), "interp");
        }
    }

    private long frequency(String word) {
        Long freq = FSTUtils.get(this.frequencies, word.toLowerCase());
        return freq != null ? freq : 0;
    }
}
