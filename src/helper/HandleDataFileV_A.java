package helper;

import dictionary.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HandleDataFileV_A {
    protected ArrayList<String> VNList = new ArrayList<>();
    protected ArrayList<String> ELList = new ArrayList<>();
    protected ArrayList<Word> ListWord = new ArrayList<>();
    protected HashMap<String, Word> WordList = new HashMap<String, Word>();

    public ArrayList<String> getELList() {
        return ELList;
    }

    public ArrayList<String> getVNList() {
        return VNList;
    }

    public ArrayList<Word> getListWord() {
        return ListWord;
    }

    public HashMap<String, Word> getWordList() {
        return WordList;
    }

    public HandleDataFileV_A() {
            try {
            File obj = new File("src/data/V_A.txt");
            Scanner myReader = new Scanner(obj);

            ArrayList<String> words = new ArrayList<String>();
            ArrayList<String> explains = new ArrayList<String>();
            String explain = "/";
            String word = "/";

            //String remove = myReader.nextLine();
            //System.out.println(remove);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int l = data.length();
                if (l >= 1 && data.charAt(0) == '@') {
                    data = data.substring(1, l);
                    word = data;

                    words.add(word);
                    explains.add(explain);
                    if (words.size() > 1) {
                        Word n = new Word(words.get(words.size() -2), explain, "");
                        this.ListWord.add(n);
                        WordList.put(words.get(words.size() -2), n);
                    }

                    explain = "";
                } else {
                    explain += data + "\n";
                }

                this.VNList = words;
                this.ELList = explains;
            }

        } catch(
        FileNotFoundException e)

        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

