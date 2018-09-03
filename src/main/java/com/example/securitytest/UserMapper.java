package com.example.securitytest;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into user (username, password, mail_address, mail_address_verified, enabled) values (#(username), #(password), #(mail_address), #(mail_address_verified), #(enabled));")
    @Options(useGeneratedKeys = true)
    void save(User user);

    @Select("select * from user where username = ${name}")
    public User findByUsername(@Param("name") String name);

    @Select("select * from user where mail_address = ${mail}")
    public User findByMailAddress(@Param("mail_address") String mail_address);

}
