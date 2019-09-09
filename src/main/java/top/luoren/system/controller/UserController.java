package top.luoren.system.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;
import top.luoren.common.api.vo.Result;
import top.luoren.common.util.PageUtils;
import top.luoren.system.entity.User;
import top.luoren.system.service.UserService;

import java.util.Arrays;
import java.util.Map;

/**
 * @author luoren
 * @date 2019/9/4 15:10
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("system:user:list")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = userService.queryPage(params);
        return Result.ok().data(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("system:user:info")
    public Result info(@PathVariable("id") String id) {
        User user = userService.getById(id);

        return Result.ok().data(user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("system:user:save")
    public Result save(@RequestBody User user) {
        userService.save(user);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("system:user:update")
    public Result update(@RequestBody User user) {
        userService.updateById(user);

        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("system:user:delete")
    public Result delete(@RequestBody String[] ids) {
        userService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

}

