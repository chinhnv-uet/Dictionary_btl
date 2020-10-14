package sample;

public class Word {
    private String word;
    private String pronun;
    private String meaning;

    public Word() {

    }

    public Word(String word, String pronun, String meaning) {
        this.word = word;
        this.pronun = pronun;
        this.meaning = meaning;
    }

    public Word(Word n) {
        this.word = n.getWord();
        this.meaning = n.getMeaning();
        this.pronun = n.getPronun();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronun() {
        return pronun;
    }

    public void setPronun(String pronun) {
        this.pronun = pronun;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

}
