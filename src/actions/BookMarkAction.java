package actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookMarkAction {
    protected ArrayList<String> bookmark;

    public void setBookmark(ArrayList<String> bookmark) {
        this.bookmark = bookmark;
    }

    public ArrayList<String> getBookmark() {
        return bookmark;
    }

    public ArrayList<String> getBookMarkfromFile()  {
        try {
            File myObj = new File("src/data/BookMark.txt");
            Scanner myReader = new Scanner(myObj);
            ArrayList<String> listBookMark = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                listBookMark.add(data);
            }
            setBookmark(listBookMark);
            return listBookMark;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    public void writeBookMark(ArrayList<String> newBM) throws IOException {
        FileWriter myWriter = new FileWriter("src/data/BookMark.txt");
        for (int i = 0; i < newBM.size(); i++) {
            myWriter.write(newBM.get(i) + "\n");
        }
        myWriter.close();
    }

    public void deleteBookMark(int index) throws IOException {
        ArrayList<String> bm = getBookMarkfromFile();
        bm.remove(index);
        writeBookMark(bm);
    }

    public void insertBookMark(String newBm) throws IOException {
        ArrayList<String> bm = getBookMarkfromFile();
        bm.add(newBm);
        writeBookMark(bm);
    }
}

