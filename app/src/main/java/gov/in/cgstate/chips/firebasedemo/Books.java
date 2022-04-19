package gov.in.cgstate.chips.firebasedemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Books {
    private Map<String, Book> books = new HashMap<>();

    public Map<String, Book> getBooks() {
        return books;
    }

    public void setBooks(Map<String, Book> books) {
        this.books = books;
    }
}
