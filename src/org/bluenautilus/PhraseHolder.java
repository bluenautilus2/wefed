package org.bluenautilus;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class PhraseHolder {

    HashMap<String, HashMap<String, Integer>> bigGiantMap = new HashMap<>();
    String firstPhrase;
    Random random = new Random(System.currentTimeMillis());

    public void acceptString(String keyString, String followingString){
          if(firstPhrase==null) firstPhrase = keyString;

          HashMap<String, Integer> entry = bigGiantMap.get(keyString);
          if (entry == null){
              HashMap<String, Integer> newEntry = new HashMap<>();
              newEntry.put(followingString, 1);
              bigGiantMap.put(keyString, newEntry);
          }else{
              Integer count = entry.get(followingString);
              if(count == null){
                  entry.put(followingString, 1);
              }else{
                  Integer newCount = count+1;
                  entry.put(followingString, newCount);
              }
          }
    }

    public String getStats(){
        StringBuilder builder = new StringBuilder();
        builder.append("Total phrases stored: " + bigGiantMap.size() + '\n');
        Set<String> keys = bigGiantMap.keySet();
        builder.append("First phrase: [" + firstPhrase + "]\n");

        int averageTotal = 0;
        int averageCount = 0;
        int highest = 0;
        for(String key: keys){
            HashMap<String, Integer> entry = bigGiantMap.get(key);
            Set<String> entryKeys = entry.keySet();
            averageCount += entryKeys.size();
            for(String entryKey:entryKeys){
                Integer weight = entry.get(entryKey);
                if(weight>highest) highest = weight;
                averageTotal+= weight;
            }
        }
        builder.append("Largest Weight: " + highest + '\n');
        builder.append("Average weight: " + averageTotal/averageCount + '\n');

        return builder.toString();
    }

    public String getFirstPhrase() {
        return firstPhrase;
    }

    public String getNextPhraseFrom(String input){
        HashMap<String, Integer> entry = bigGiantMap.get(input);
        return chooseCarefully(entry);
    }

    private String chooseCarefully(HashMap<String, Integer> entry){
        RandomCollection<String> rc = new RandomCollection<>();
        for(String key:entry.keySet()){
            Integer weight = entry.get(key);
            rc.add(weight, key);
        }
        return rc.next();
    }

}
