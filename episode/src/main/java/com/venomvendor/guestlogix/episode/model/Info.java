package com.venomvendor.guestlogix.episode.model;

public class Info {

    private String next;
    private int pages;
    private String prev;
    private int count;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Info{" +
                "next = '" + next + '\'' +
                ",pages = '" + pages + '\'' +
                ",prev = '" + prev + '\'' +
                ",count = '" + count + '\'' +
                "}";
    }
}
