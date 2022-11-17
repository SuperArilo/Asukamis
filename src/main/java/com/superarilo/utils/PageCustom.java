package com.superarilo.utils;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class PageCustom<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 4804053559968742915L;
    private long total;
    private List<T> list;
    private long pages;
    private long current;
    private long size;

    public PageCustom(long total, List<T> list, long pages, long current, long size){
        this.total = total;
        this.list = list;
        this.pages = pages;
        this.current = current;
        this.size = size;
    }
}
