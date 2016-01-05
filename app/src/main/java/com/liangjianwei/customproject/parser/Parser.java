package com.liangjianwei.customproject.Parser;

public interface Parser<T> {
    T parser(String content, Class<T> tClass);
}