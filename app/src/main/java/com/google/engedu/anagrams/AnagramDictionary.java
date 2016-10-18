package com.google.engedu.anagrams;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class AnagramDictionary {

    //Constants
    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;

    //Random number generator
    private Random random = new Random();

    //Data structures to hold information
    private HashSet wordSet;
    private ArrayList wordList;
    private HashMap lettersToWord;
    private String key;

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        wordSet = new HashSet();
        wordList = new ArrayList();
        lettersToWord = new HashMap();

        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordSet.add(word); //hashmap
            wordList.add(word); //arraylist
            ArrayList al = new ArrayList();
            key = alphabeticalOrder(word);

            if (!lettersToWord.containsKey(key)){
                al.add(word);
                lettersToWord.put(key, al);
            } else {
                al = (ArrayList) lettersToWord.get(key);
                al.add(word);
                lettersToWord.put(key, al);
            }
        }
    }

    //alphabeticalOrder function
    public String alphabeticalOrder(String word){
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        String key = new String(chars);

        return key;
    }

    //Functions checks if the word is valid. (Does not contain base word and valid dictionary word)
    public boolean isGoodWord(String word, String base) {
        boolean check = true;
        if(wordSet.contains(word) && !base.contains(word))
            return true;
        else
            return false;
    }

    //Anagram Fetcher
    public ArrayList<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList();
        return result;
    }

    //Anagram Fetcher for One More Extra Letter
    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> resultant;
        ArrayList<String> result = new ArrayList<String>();

        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            String newWord = word + alphabet;
            String extendedKey = alphabeticalOrder(newWord);

            if (lettersToWord.containsKey(extendedKey)) {
                resultant = new ArrayList();
                resultant = (ArrayList) lettersToWord.get(extendedKey);

                for (int i = 0; i < resultant.size(); i++) {
                    result.add(String.valueOf(resultant.get(i)));
                }
            }
        }

        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }
}
