package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserJoinMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class DefaultService<T> {

    private UserJoinMapper mapper;

    public DefaultService(UserJoinMapper mapper) {
        this.mapper = mapper;
    }

    public void add(T t) {
        mapper.insert(t);
    }

    public void delete(int id) {
        mapper.delete(id);
    }

    public void update(T t) {
        mapper.update(t);
    }

    public List<T> getByUserId(int userId) {
        return mapper.getByUserId(userId);
    }
}
