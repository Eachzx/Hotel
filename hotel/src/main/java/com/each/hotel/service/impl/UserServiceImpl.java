package com.each.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.each.hotel.entity.User;
import com.each.hotel.mapper.UserMapper;
import com.each.hotel.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author each
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public Page<User> getUserPageList(User user, int pageNo, int pageSize) {
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNo, pageSize), new QueryWrapper<>());
        return userPage;
    }

    @Override
    public List<User> getUserList() {
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        return users;
    }

    @Override
    public boolean addUser(User user) {
        int insert = userMapper.insert(user);
        if (insert == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean editUser(User user) {
        int update = userMapper.update(user, new LambdaUpdateWrapper<User>().eq(User::getId, user.getId()));
        if (update == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delUserById(String id) {
        int delete = userMapper.deleteById(id);
        if (delete == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String login(String account, String password) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getAccount, account));
        if (user==null){
            return "没有此用户!";
        }else {
            if (user.getPassword().equals(password)){
                return "登录成功!";
            }else {
                return "密码错误!";
            }
        }
    }
}
