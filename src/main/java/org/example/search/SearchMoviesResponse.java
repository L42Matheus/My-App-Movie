package org.example.search;

import java.util.List;

public class SearchMoviesResponse {

    private int page;
    private List<KnownFor> results;

    public int getPage() {
        return page;
    }

    public List<KnownFor> getResults() {
        return results;
    }
}
