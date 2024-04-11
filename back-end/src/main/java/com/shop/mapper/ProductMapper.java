package com.shop.mapper;

import com.shop.pojo.Order;
import com.shop.pojo.Product;
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

    @Select("select IFNULL(MAX(id), 0) from orders")
    Integer getMaxOrderId();

    @Insert("insert into product (pid, catid, name, price, url) " +
            "values (#{pid}, #{catid}, #{name}, #{price}, #{url})")
    void adminInsert(Product product);

    @Delete("delete from product where pid = #{pid}")
    void adminDelete(Integer pid);

    @Update("update product set catid = #{catid}, name = #{name}, price = #{price}, url = #{url} " +
            "where pid = #{pid}")
    void adminUpdate(Product product);

    @Select("select * from product limit 8")
    List<Product> touristQuery();

    @Select("select * from product where pid = #{pid}")
    Product touristGetByPid(Integer pid);

    @Select("select * from product where catid = #{catid}")
    List<Product> touristGetByCatid(Integer catid);

    @Insert("insert into orders (id, pid, catid, name, price, quantity) " +
            "values (#{id}, #{pid}, #{catid}, #{name}, #{price}, #{quantity})")
    void userPayment(Order order);

    @Select("select * from orders")
    List<Order> getOrder();
}
