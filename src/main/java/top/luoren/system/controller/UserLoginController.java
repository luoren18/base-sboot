package top.luoren.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.luoren.common.api.vo.Result;
import top.luoren.common.base.entity.SystemUserLoginRequest;
import top.luoren.common.util.JwtUtil;
import top.luoren.common.util.PasswordUtil;
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
        if (!ObjectUtils.isEmpty(user)) {
            String passwordTemp = PasswordUtil.encrypt(userLoginRequest.getUsername(), userLoginRequest.getPassword());
            if (user.getPassword().equals(passwordTemp)) {
                return Result.ok().data(JwtUtil.generateToken(user.getUsername()));
            }
        }
        return Result.error("", "用户名密码错误");
    }

}
