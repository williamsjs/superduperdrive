package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

public interface UserJoinMapper<T> {

    T getById(int id);

    List<T> getByUserId(int userId);

    int insert(T t);

    void update(T t);

    void delete(int id);
}
