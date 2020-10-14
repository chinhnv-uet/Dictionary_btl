package sample;

import java.io.*;

public class Dictionary {
    public int length = 0;
    public Word[] WordInDict;

    public Dictionary() {
        WordInDict = new Word[60000];
        try {
            FileReader fr = new FileReader("EngVieDictionary.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            boolean isReading = true;
            while (isReading) {
                if (s.length() > 0 && s.charAt(0) == '@') {
                    String word = "";
                    int i = 1;
                    for (; i < s.length(); i++) {
                        if (s.charAt(i) != '/') {
                            word += s.charAt(i);
                        } else {
                            break;
                        }
                    }
                    word = word.substring(0, word.length() - 1);
                    String pronun = "";
                    for (int j = i; j < s.length(); j++) {
                        pronun += s.charAt(j);
                    }
                    String meaning = "", nextLine;
                    while (true) {
                        nextLine = br.readLine();
                        if (nextLine == null || nextLine.length() == 0) {
                            break;
                        } else {
                            meaning += nextLine + '\n';
                        }
                    }
                    Word newWord = new Word(word, pronun, meaning);
                    WordInDict[length] = newWord;
                    length += 1;

                    if ((nextLine = br.readLine()) == null) {
                        isReading = false;
                    } else {
                        s = nextLine;
                    }
                } else {
                    isReading = false; /////////////////////////// notice
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Word findWord(String s) {
        boolean isInDictionary = false;
        Word res = new Word();
        for (int i = 0; i < length; i++) {
            if (WordInDict[i].getWord().equals(s)) {
                isInDictionary = true;
                res = WordInDict[i];
                break;
            }
        }
        if (isInDictionary == true) {
            return res;
        } else {
            return null;
        }
    }

    public void updateDataToDictionaryFile() {
        String str;
        FileWriter fw;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            fw = new FileWriter("EngVieDictionary.txt");
            for (int i = 0; i < Main.dict.length; i++) {
                str = "@" + Main.dict.WordInDict[i].getWord() + " " + Main.dict.WordInDict[i].getPronun() + '\n' + Main.dict.WordInDict[i].getMeaning() + '\n';
                fw.write(str);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
