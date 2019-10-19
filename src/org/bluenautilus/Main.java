package org.bluenautilus;


import java.io.File;

public class Main {

    public static void main(String[] args) {

        final int WINDOW = 12;
         String filename = "atlas.txt";
         FileOpener opener = new FileOpener();
        File f = opener.getFile(filename);
         if(!f.exists()){
             System.out.println("File not found");
         }
         FileExaminer examiner = new FileExaminer(f);
         ChunkProcessor processor = new ChunkProcessor(WINDOW);
         try {
             examiner.readFileChunks(s ->processor.processNexttChunk(s));
         }catch (Exception e){
             System.out.println(e);
         }
         System.out.println("WINDOW: " + WINDOW +'\n' + processor.getPhraseHolder().getStats());

         NovelGenerator generator = new NovelGenerator(processor.getPhraseHolder());
         generator.generateNovel(1000, WINDOW);
    }
}
