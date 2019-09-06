package top.luoren.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.luoren.common.api.vo.Result;
import top.luoren.common.base.entity.SystemUserLoginRequest;
import top.luoren.common.util.JwtUtil;
import top.luoren.system.entity.User;
import top.luoren.system.service.UserService;

/**
 * @author luoren
 * @date 2019/9/6 14:49
 */
@RestController
@RequestMapping("/")
public class UserLoginController {
    private
    UserService userService;

    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public Result login(@RequestBody SystemUserLoginRequest userLoginRequest) {
        User user = userService.getUser(userLoginRequest.getUsername());
        if (userLoginRequest.getPassword().equals(user.getPassword())) {
            return Result.ok().data(JwtUtil.sign(userLoginRequest.getUsername(), userLoginRequest.getPassword()));
        }
        return Result.error("", "用户名密码错误");
    }

}
