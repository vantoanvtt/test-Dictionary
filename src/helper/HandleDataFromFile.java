package helper;

import dictionary.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HandleDataFromFile {
    protected ArrayList<Word> ListWord = new ArrayList<>();
    protected ArrayList<String> ListWordTarget = new ArrayList<>();
    protected ArrayList<String> ListwordExplain = new ArrayList<>();
    protected ArrayList<String> ListWordSpelling = new ArrayList<>();
    protected HashMap<String, Word> WordList = new HashMap<String, Word>();

    public ArrayList<String> getListwordExplain() {
        return ListwordExplain;
    }

    public ArrayList<String> getListWordSpelling() {
        return ListWordSpelling;
    }

    public ArrayList<String> getListWordTarget() {
        return ListWordTarget;
    }

    public ArrayList<Word> getListWord() {
        return ListWord;
    }

    public HashMap<String, Word> getWordList() {
        return WordList;
    }

    public HandleDataFromFile() {
        try {
            File myObj = new File("src/data/anh-viet.txt");
            Scanner myReader = new Scanner(myObj);

            ArrayList<String> words = new ArrayList<String>();
            ArrayList<String> explains = new ArrayList<String>();
            ArrayList<String> spellings = new ArrayList<String>();
            String explain = "/";
            String[] wordsArray;

            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                int l = data.length();
                if (l >= 1 && data.charAt(0) == '@') {
                    String word = "/", spelling = "/";
                    data = data.substring(1,l);
                    word = data;
                    int index = data.indexOf('/');
                    if (index != -1) {
                        word = data.substring(0,index - 1);
                        spelling = data.substring(index, l - 1);
                    }
                    
                    words.add(word);
                    spellings.add(spelling);
                    explains.add(explain);

                    if (words.size() > 1) {
                        Word n = new Word(words.get(words.size() -2), explain, spellings.get(spellings.size() -2));
                        this.ListWord.add(n);
                        WordList.put(words.get(words.size() -2), n);
                    }
                    //this.ListWord.add(new Word(word,explain,spelling));
                    explain = "";
                } else {
                    explain += data + "\n";
                }
            }

            myReader.close();
            this.ListWordTarget = words;
            this.ListwordExplain= explains;
            this.ListWordSpelling =spellings;
            /*for (int i = 0; i < ListWordTarget.size() - 1; i++) {
                ListWord.add(new Word(ListWordTarget.get(i), ListwordExplain.get(i + 1), ListWordSpelling.get(i)));
            }*/

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
