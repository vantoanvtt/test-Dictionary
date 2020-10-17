package dictionary;

import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {
    private ArrayList<Word> words = new ArrayList<>();
    private HashMap<String, Word> hashMapWord = new HashMap<>();

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public void setHashMapWord(HashMap<String, Word> hashMapWord) {
        this.hashMapWord = hashMapWord;
    }

    public HashMap<String, Word> getHashMapWord() {
        return hashMapWord;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public Word lookUp(String wordSearch) {
        return getHashMapWord().get(wordSearch);
    }
}
