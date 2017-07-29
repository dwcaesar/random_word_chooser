package de.wohlers.RandomWordChooser;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * author Daniel
 */
public class Persistor {

    private static final String STORE_PATH = System.getProperty("user.home") + File.separator + "RandomWordChooser.json";

    //TODO - multiple named save files / file chooser

    public Persistor() {
        init();
    }

    private void init() {
        File file = new File(STORE_PATH);
        if (!file.exists()) {
            save(Collections.<String>emptyList());
        }

    }

    public List<String> load() {
        Gson gson = new Gson();
        List<String> wordList = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(STORE_PATH))) {
            DataObject object = gson.fromJson(bufferedReader, DataObject.class);
            wordList = object.getWordList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList == null ? Collections.emptyList() : wordList;
    }

    public void save(List<String> data) {
        DataObject dataObject = new DataObject();
        dataObject.setWordList(data);
        Gson gson = new Gson();

        String json = gson.toJson(dataObject);

        try (FileWriter fileWriter = new FileWriter(STORE_PATH)) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class DataObject {
        private List<String> wordList = new ArrayList<>();

        public List<String> getWordList() {
            return wordList;
        }

        public void setWordList(List<String> wordList) {
            this.wordList = wordList;
        }
    }
}
