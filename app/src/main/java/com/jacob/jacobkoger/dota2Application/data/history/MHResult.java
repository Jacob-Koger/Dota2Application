package com.jacob.jacobkoger.dota2Application.data.history;

import java.util.List;

public class MHResult {

    private long status;
    private long numResults;
    private long totalResults;
    private long resultsRemaining;
    private List<MHMatch> matches;

    public long getStatus() {
        return status;
    }

    public long getNumResults() {
        return numResults;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public long getResultsRemaining() {
        return resultsRemaining;
    }

    public List<MHMatch> getMatches() {
        return matches;
    }

}
