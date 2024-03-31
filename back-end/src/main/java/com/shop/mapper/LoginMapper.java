package com.shop.mapper;

import com.shop.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LoginMapper {
    @Select("select email, password from user " +
            "where email = #{email} and password = #{password}")
    User login(String email, String password);

    @Update("update user set password = #{newPassword} " +
            "where email = #{email}")
    void changePassword(String email, String newPassword);
}
