package com.example.jacobkoger.newdota2applicationwsidebar.POJO_MatchHistory;

import java.util.ArrayList;
import java.util.List;


public class Result {

    private long status;
    private long numResults;
    private long totalResults;
    private long resultsRemaining;
    private List<Match> matches = new ArrayList<>();

    /**
     * @return The status
     */
    public long getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return The numResults
     */
    public long getNumResults() {
        return numResults;
    }

    /**
     * @param numResults The num_results
     */
    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    /**
     * @return The totalResults
     */
    public long getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults The total_results
     */
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * @return The resultsRemaining
     */
    public long getResultsRemaining() {
        return resultsRemaining;
    }

    /**
     * @param resultsRemaining The results_remaining
     */
    public void setResultsRemaining(int resultsRemaining) {
        this.resultsRemaining = resultsRemaining;
    }

    /**
     * @return The matches
     */
    public List<Match> getMatches() {
        return matches;
    }

    /**
     * @param matches The matches
     */
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", numResults=" + numResults +
                ", totalResults=" + totalResults +
                ", resultsRemaining=" + resultsRemaining +
                ", matches=" + matches +
                '}';
    }

}