package org.bluenautilus;

public class NovelGenerator {

    PhraseHolder phraseHolder;

    public NovelGenerator(PhraseHolder phraseHolder){
        this.phraseHolder = phraseHolder;
    }

    public void generateNovel(int totalChars, int window){
        System.out.print(phraseHolder.firstPhrase);
        String current = phraseHolder.firstPhrase;
        for(int i=0; i<totalChars; i++) {
            String next = phraseHolder.getNextPhraseFrom(current);
            System.out.print(next.charAt(window-1));
            current = next;
        }
    }
}
