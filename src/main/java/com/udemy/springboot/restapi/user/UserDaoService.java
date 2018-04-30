package com.udemy.springboot.restapi.user;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users= new ArrayList<>();

    static {
        users.add(new User(1, "user_1", new Date()));
        users.add(new User(2, "user_2", new Date()));
        users.add(new User(3, "user_3", new Date()));
    }


    public List<User> findAll()
    {
        return users;
    }


    public User findOne( int id)
    {
        for (User user: users) {
            if(user.getId()==id){
                return user;
            }
        }

        return null;
    }

    public User addUser(User user)
    {
        if(user.getId() == null)
        {
            int id = users.size() + 1;
            user.setId(id);
        }
        users.add(user);
        return user;
    }

    public User removeOne(int id)
    {
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext())
        {
            User user=iterator.next();
            if(user.getId()==id) {
                iterator.remove();
            }
        }

        return null;
    }

    public static void main(String[] s){
        UserDaoService obj = new UserDaoService();

        /*User user = obj.findOne(1);
        System.out.println(user);*/

        List<User> list= obj.findAll();
        System.out.println(list);
    }
}
