package actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HistoryAction {
        protected ArrayList<String> history;

        public void setHistory(ArrayList<String> history) {
            this.history = history;
        }

        public ArrayList<String> getHistory() {
            return history;
        }

        public ArrayList<String> getHistoryfromFile()  {
            try {
                File myObj = new File("src/data/History.txt");
                Scanner myReader = new Scanner(myObj);
                ArrayList<String> listHistory = new ArrayList<>();
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    listHistory.add(data);
                }
                setHistory(listHistory);
                return listHistory;

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            return null;
        }

        public void writeHistory(ArrayList<String> newH) throws IOException {
            FileWriter myWriter = new FileWriter("src/data/History.txt");
            for (int i = 0; i < newH.size(); i++) {
                myWriter.write(newH.get(i) + "\n");
            }
            myWriter.close();
        }

        public void insertHistory(String newH) throws IOException {
            ArrayList<String> H = getHistoryfromFile();
            H.add(0,newH);
            writeHistory(H);
        }
        public void deleteBookMark(int index) throws IOException {
            ArrayList<String> bm = getHistoryfromFile();
            bm.remove(index);
            writeHistory(bm);
        }
    }

