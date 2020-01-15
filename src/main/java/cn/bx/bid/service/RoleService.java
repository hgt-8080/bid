package cn.bx.bid.service;

import cn.bx.bid.dao.RoleDao;
import cn.bx.bid.dao.UserDao;
import cn.bx.bid.entity.Page;
import cn.bx.bid.entity.User;
import cn.bx.bid.entity.Userrole;
import cn.bx.bid.util.MyBatisUtil;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleService {
    @Resource
    private RoleDao roleDao;

    public List<Userrole> findALL() {
        return roleDao.findALL();
    }
}
