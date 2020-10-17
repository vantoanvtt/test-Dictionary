package dictionary;

public class Word {
    private String word_target, word_explain, word_spelling;

    public Word(String word_target, String word_explain, String word_spelling) {
        this.word_target = word_target;
        this.word_explain = word_explain;
        this.word_spelling = word_spelling;
    }
    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }
    public void setWord_spelling(String word_spelling) {
        this.word_spelling = word_spelling;
    }

    public String getWord_target() {
        return  this.word_target;
    }
    public String getWord_explain() {
        return this.word_explain;
    }
    public String getWord_spelling() {
        return this.word_spelling;
    }

}
