package edu.iis.mto.similarity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import edu.iis.mto.searcher.SearchResult;
import edu.iis.mto.searcher.SequenceSearcher;
import org.junit.jupiter.api.Test;



class SimilarityFinderTest {
    SimilarityFinder similarityFinder = new SimilarityFinder(new SequenceSearcher() {
        @Override
        public SearchResult search(int elem, int[] sequence) {
            int iterator = 0;
            for (int checked : sequence) {
                if (checked == elem) {
                    return SearchResult.builder().withFound(true).withPosition(iterator).build();
                }
                iterator++;
            }
            return SearchResult.builder().withFound(false).withPosition(-1).build();
        }
    });
    @Test
    void similarityFinderArrayIsTheSame() {

        int[] seq1 = {1,2,3,4};
        int[] seq2 = {1,2,3,4};
        assertEquals(1, similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }
    @Test
    void  similarityFinderArrayIsDiffrend() {

        int[] seq1 = {1,2,3,4};
        int[] seq2 = {1,2,3,4,5,6};
        assertEquals(0.6666666666666666, similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }
    @Test
    void similarityFinderArrayIsDiffrend2() {

        int[] seq1 = {2,4,5,6};
        int[] seq2 = {1,2,3,4,5,6,9};
        assertEquals(0.5714285714285714, similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }
    @Test
    void similarityFinderArrayIsdifferent() {

        int[] seq1 = {4,5,6,7};
        int[] seq2 = {0,1,2,3};
        assertEquals(0, similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }
    @Test
    void similarityFinderEmpty() {

        int[] seq1 = {};
        int[] seq2 = {};
        assertEquals(1, similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }
    @Test
    void similarityFinderHalfArray() {

        int[] seq1 = {1,2};
        int[] seq2 = {1,2,3,4};
        assertEquals(0.5, similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

}