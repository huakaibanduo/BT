package com.asm.dao;
import com.asm.hibernate.domain.User;

public interface UserDao {
public void saveUser(User user);
public User queryById(int id);
public User quertByName(String name);
public void update(User user);
public void delete(User user);
}
