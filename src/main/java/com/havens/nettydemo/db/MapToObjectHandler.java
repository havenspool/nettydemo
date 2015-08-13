package com.havens.nettydemo.db;

/**
 * Created by havens on 15-8-13.
 */
public interface MapToObjectHandler<T> {
    boolean handler(T o);
}
