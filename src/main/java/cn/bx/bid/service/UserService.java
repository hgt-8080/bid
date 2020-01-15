package cn.bx.bid.service;

import cn.bx.bid.dao.UserDao;
import cn.bx.bid.entity.Page;
import cn.bx.bid.entity.User;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserService {
    @Resource
    private UserDao userDao;

    public int add(User user) {
        return userDao.add(user);
    }

    public int update(User user) {
        return userDao.update(user);
    }

    public int del(long id){
        return userDao.del(id);
    }

    public int batchDel(long[] ids) {
        return userDao.batchDel(ids);
    }

    public Page<User> paging(String name, Date start, Date end,int pageNo, int limit) {
        Page<User> page = new Page<User>();
        List<User> list = userDao.search(name, start, end,pageNo, limit);
        int sum = userDao.searchTotal(name, start, end);
        page.init(sum, limit, pageNo);
        page.setData(list);
        return page;
    }

    public User get(long id) {
        return userDao.get(id);
    }

    public boolean checkUserNameRepeat(Long id, String name) {
        return userDao.checkUserNameRepeat(id, name) > 0;
    }

    public User login(String username, String password) {
        return userDao.login(username, password);
    }
}
