package supermarket.util;

import java.util.ArrayList;
import java.util.List;

public class DataStore<T> {

    private final List<T> data = new ArrayList<>();

    public void add(T item) {

        data.add(item);
    }

    public List<T> getAll() {

        return data;
    }

    public int size() {

        return data.size();
    }
}