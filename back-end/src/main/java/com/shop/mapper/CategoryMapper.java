package com.shop.mapper;


import com.shop.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select * from category")
    List<Category> getAll();

    @Select("select max(catid) from category")
    Integer getMaxId();
    @Insert("insert into category (catid, name)" +
            "values (#{catid}, #{name})")
    void insert(Integer catid, String name);

    @Delete("delete from category " +
            "where catid = #{catid}")
    void delete(Integer catid);

    @Update("update category set name = #{name}" +
            "where catid = #{catid}")
    void update(Category category);
}
