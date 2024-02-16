package org.example.popular;

import java.util.List;

public class PersonResponse {
    private int page;
    private List<Person> results;

    public int getPage() {
        return page;
    }

    public List<Person> getResults() {
        return results;
    }
}
