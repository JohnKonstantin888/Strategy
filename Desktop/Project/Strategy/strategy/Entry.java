package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;
import java.util.Objects;

public class Entry implements Serializable {
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public final Long getKey() {
        return key;
    }

    public final String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (key != null ? !key.equals(entry.key) : entry.key != null) return false;
        return value != null ? value.equals(entry.value) : entry.value == null;

    }

    public final int hashCode() {
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());

    }

    //    public final int hashCode() {
//        return Objects.hashCode(key) ^ Objects.hashCode(value);
//    }
//
//    public boolean equals(Object o) {
//        if (o == this)
//            return true;
//
//        return o instanceof Map.Entry<?, ?> e
//                && Objects.equals(key, e.getKey())
//                && Objects.equals(value, e.getValue());
//    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
