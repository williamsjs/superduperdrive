package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserJoinMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.DefaultModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class DefaultService<T extends DefaultModel> {

    private UserJoinMapper mapper;

    public DefaultService(UserJoinMapper mapper) {
        this.mapper = mapper;
    }

    public String save(T t, Integer userId) {

        if (t.getId() == 0 || ((Integer)t.getId()).equals(null)) {
            t.setUserId(userId);
            add(t);
        } else {
            update(t);
        }

        return null;
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

    public T getById(int id) {
        return (T) mapper.getById(id);
    }

}
