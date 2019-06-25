package pl.sdadas.simplepl.nlp;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Sławomir Dadas
 */
public class Word implements Serializable {

    private String original;

    private String base;

    private Map<String, String> tags;

    public Word(String original) {
        this.original = original;
        this.tags = new LinkedHashMap<>();
    }

    public Word(String original, String base) {
        this.original = original;
        this.base = base;
        this.tags = new LinkedHashMap<>();
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public void addTag(String base, String tag) {
        this.tags.put(base, tag);
    }

    public String getTag() {
        return this.tags.get(this.base);
    }
}
