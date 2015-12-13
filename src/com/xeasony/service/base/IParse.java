package com.xeasony.service.base;

@FunctionalInterface
public interface IParse<T> {
    T parseFromLine(String line);
}
