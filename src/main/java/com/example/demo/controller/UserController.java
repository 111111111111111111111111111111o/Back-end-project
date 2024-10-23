package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        try {
            userService.save(user);
        } catch (Exception e) {
                return Result.error("error");
            }
        return Result.success();
    }


    @PutMapping("/update/{id}")
    public Result update(@RequestBody User user) {
        try {
            userService.updateById(user);
        } catch (Exception e) {
            return Result.error("error");
        }
        return Result.success();
    }


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        try {
            userService.removeById(id);
        } catch (Exception e) {
            return Result.error("error");
        }
        return Result.success();
    }

    @PostMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {
        try {
            userService.removeBatchByIds(ids);
        } catch (Exception e) {
            return Result.error("error");
        }
        return Result.success();
    }


    @GetMapping("/selectAll")
    public Result selectAll() {
        try {
            List<User> userList = userService.list(new QueryWrapper<User>().orderByAsc("id"));
            return Result.success(userList);
        } catch (Exception e) {
            return Result.error("error");
        }
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        try {
            User user = userService.getById(id);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error("error");
        }
    }
}
