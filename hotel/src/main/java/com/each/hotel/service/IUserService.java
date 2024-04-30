package com.each.hotel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.each.hotel.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author each
 * @since 2023-04-27
 */
public interface IUserService extends IService<User> {

    /**
     *
     * @param user 前端传入数据
     * @param pageNo 当前页
     * @param pageSize 每页显示条数
     * @return
     */
    Page<User> getUserPageList(User user, int pageNo, int pageSize);


    List<User> getUserList();


    /**
     * 添加用户数据
     * @param user 前端传入数据
     * @return 是否添加成功
     */
    boolean addUser(User user);


    /**
     *  修改用户数据
     * @param user 前端传入用户数据
     * @return
     */
    boolean editUser(User user);


    /**
     *  删除用户数据
     * @param id 前端传入用户id
     * @return
     */
    boolean delUserById(String id);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return 是否登录成功
     */
    String login(String username, String password);


}
