package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import sample.Main;

import java.util.ArrayList;

public class HandleSearch {
    private ArrayList<String> sgList = new ArrayList<>();

    public ArrayList<String> getSgList() {
        return sgList;
    }

    public HandleSearch(String s) {
        ArrayList<String> list = Main.databaseAV.getListWordTarget();
        int index;
        for (index = 0; index < list.size(); index++) {
            if (list.get(index).startsWith(s)) {
                break;
            }
        }
        for (int i = index; i < list.size(); i++) {
            if (list.get(i).startsWith(s)) {
                sgList.add(list.get(i));
            } else {
                break;
            }
        }
    }
}
