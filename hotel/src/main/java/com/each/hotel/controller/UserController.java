package com.each.hotel.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.each.hotel.entity.User;
import com.each.hotel.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 分页查询用户列表
     * @param user
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/getUserPageList")
    public Page<User>  getUserPageList(User user, int pageNo, int pageSize) {
        Page<User> userPageList = userService.getUserPageList(user, pageNo, pageSize);
        return userPageList;
    }


    @GetMapping("/getUserList")
    public List<User> getUserList() {
        List<User> userList = userService.getUserList();
        return userList;
    }


    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public boolean addUser(@RequestBody User user) {
        boolean b = userService.addUser(user);
        return b;
    }

    /**
     * 根据用户id删除
     * @param id
     * @return
     */
    @DeleteMapping("/delUserById")
    public boolean delUserById(String id) {
        boolean delete = userService.delUserById(id);
        return delete;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PutMapping("/editUser")
    public boolean editUser(@RequestBody User user) {
        boolean edit = userService.editUser(user);
        return edit;
    }

    /**
     * 用户登录
     *
     * @param user 用户名
     */
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String login = userService.login(user.getAccount(), user.getPassword());
        return login;
    }
}
