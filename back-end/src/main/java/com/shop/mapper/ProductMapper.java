package com.shop.mapper;

import com.shop.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("select * from product limit 8")
    List<Product> userQuery();

    @Select("select * from product where pid = #{pid}")
    Product getByPid(Integer pid);

    @Select("select * from product where catid = #{catid}")
    List<Product> getByCatid(Integer catid);

    @Select("select * from product")
    List<Product> adminQuery();

    @Select("select max(pid) from product")
    Integer getMaxId();

    @Insert("insert into product (pid, catid, name, price, url)" +
            "values (#{pid}, #{catid}, #{name}, #{price}, #{url})")
    void adminInsert(Product product);

    @Delete("delete from product where pid = #{pid}")
    void adminDelete(Integer pid);

    @Update("update product set catid = #{catid}, name = #{name}, price = #{price}, url = #{url}" +
            "where pid = #{pid}")
    void adminUpdate(Product product);
}
