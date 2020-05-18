package com.gmail.glebgomenyuk.dto;

public class PageCountDTO {
    private long count;
    private long size;

    public PageCountDTO(long count, long size) {
        this.count = count;
        this.size = size;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long coun) {
        this.count = count;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
