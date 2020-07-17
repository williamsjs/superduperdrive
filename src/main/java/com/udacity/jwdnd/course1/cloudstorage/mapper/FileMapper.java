package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {

    String getById = "SELECT * FROM FILES WHERE fileid = #{id}";

    String insert = "INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{filename}, #{contentType}, #{fileSize}, #{userId}, #{fileData})";

    String delete = "DELETE FROM FILES WHERE fileid = #{id}";

    @Select(getById)
    File getById(int id);

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty="id")
    int insert(File file);

    @Delete(delete)
    void delete(int id);

}
