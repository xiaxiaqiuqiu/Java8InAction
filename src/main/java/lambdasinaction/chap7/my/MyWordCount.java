package lambdasinaction.chap7.my;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class MyWordCount {

    public static final String SENTENCE =
            " Nel   mezzo del cammin  di nostra  vita " +
                    "mi  ritrovai in una  selva oscura" +
                    " che la  dritta via era   smarrita ";

    public static void main(String[] args) {
        //System.out.println("Found " + countWords(SENTENCE) + " words");
       int count =  countWords(SENTENCE);
        System.out.println(count);
    }

    public static int countWords(String s){
        Spliterator<Character> spliterator = new WordCounterSpliterator(s);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combiner);
        return wordCounter.getCounter();
    }


    private static class WordCounter{
        private final int counter;
        private final boolean lastSpace;

        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

       public WordCounter accumulate(Character c){
            if(Character.isWhitespace(c)){
               return lastSpace ? this : new WordCounter(counter, true);
            }else{
                return lastSpace ? new WordCounter(counter + 1, false):this;
            }
        }

        public WordCounter combiner(WordCounter wordCounter){
            return new WordCounter(this.counter + wordCounter.counter, lastSpace);
        }

        public int getCounter() {
            return counter;
        }
    }

    private static class WordCounterSpliterator implements  Spliterator<Character>{
        private final String str;
        private int currentIndex = 0;

        public WordCounterSpliterator(String str) {
            this.str = str;

        }

        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            action.accept(str.charAt(currentIndex));
            return ++currentIndex < str.length();
        }

        @Override
        public Spliterator<Character> trySplit() {
            int length = str.length();
            if(length < 10){
                return null;
            }
            for(int splitPos = currentIndex + length/2; splitPos < length; splitPos++){
                if(Character.isWhitespace(str.charAt(splitPos))){
                   Spliterator<Character> spliterator =  new WordCounterSpliterator(str.substring(currentIndex, splitPos));
                   currentIndex = splitPos;
                   return spliterator;
                }
            }
            return null;
        }

        @Override
        public long estimateSize() {
            return str.length() - currentIndex;
        }

        @Override
        public int characteristics() {
            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
        }
    }

}
