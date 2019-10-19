package org.bluenautilus;

public class ChunkProcessor {

    String beginChunk = null;
    String currentChunk = null;
    int window;
    PhraseHolder phraseHolder;

    public ChunkProcessor(int window){
        phraseHolder = new PhraseHolder();
        this.window = window;
    }

    public void processNexttChunk(String CHONK){
        if(CHONK==null){
            return;
        }
        if(currentChunk == null){
            beginChunk = CHONK;
            currentChunk = CHONK;
            //do nothing
        }else{
            beginChunk = currentChunk;
            currentChunk = CHONK;
            String beginningOfNextBit = currentChunk.substring(0,window);
            //System.out.println("[" + beginChunk + "]{" + beginningOfNextBit + "}");
            processBeginChunk(beginChunk + beginningOfNextBit);
        }
    }

    private void processBeginChunk(String chunk){
        for(int i=0; i<(chunk.length()-window); i++){
            phraseHolder.acceptString(chunk.substring(i, i+window),chunk.substring(i+1, i+1+window));
        }
    }

    public PhraseHolder getPhraseHolder(){
        return this.phraseHolder;
    }
}
