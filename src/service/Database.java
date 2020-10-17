package service;

import dictionary.Word;
import sample.Main;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Database {
    protected ArrayList<String> listWordTarget = new ArrayList<>();
    protected ArrayList<String> listWordExplain = new ArrayList<>();
    protected ArrayList<String> listWordSpelling = new ArrayList<>();
    protected ArrayList<Word> ListWord = new ArrayList<>();
    protected HashMap<String, Word> WordListHashMap = new HashMap<String, Word>();

    public ArrayList<String> getListWordExplain() {
        return listWordExplain;
    }

    public ArrayList<String> getListWordSpelling() {
        return listWordSpelling;
    }

    public ArrayList<String> getListWordTarget() {
        return listWordTarget;
    }

    public ArrayList<Word> getListWord() {
        return ListWord;
    }

    public HashMap<String, Word> getWordListHashMap() {
        return WordListHashMap;
    }

    public static Connection ConnectDb(){
        try {
           // Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/dictionary","root","");
            System.out.println("connected");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static Connection conn = ConnectDb();

    public Database() {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM tbl_edict");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String s1 = rs.getString("word");
                String s2 = rs.getString("detail");
                String s3 = rs.getString("spelling");
                listWordTarget.add(s1);
                listWordExplain.add(s2);
                listWordSpelling.add(s3);
                Word w = new Word(s1, s2, s3);
                ListWord.add(w);
                WordListHashMap.put(s1,w);
            }
            Collections.sort(listWordTarget);
        } catch (Exception e) {
        }
    }

    public static void AddWord(Word add) {
        try {
            String sql = "INSERT INTO tbl_edict(word, detail, spelling) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setInt(1,id);
            ps.setString(1, add.getWord_target());
            ps.setString(2, add.getWord_explain());
            ps.setString(3, add.getWord_spelling());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void DeleteWord(Word delete) {
        try {
            String sql = "DELETE FROM tbl_edict WHERE word = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setInt(1,id);
            ps.setString(1, delete.getWord_target());
            ps.executeUpdate();
            Main.databaseAV.getListWordTarget().remove(delete.getWord_target());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
