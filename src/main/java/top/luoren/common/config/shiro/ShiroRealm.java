package top.luoren.common.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.luoren.common.util.JwtUtil;
import top.luoren.system.entity.User;
import top.luoren.system.service.UserService;

import java.util.Set;

/**
 * @author luoren
 * @date 2019/9/6 11:27
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = JwtUtil.getUsernameFromToken(principalCollection.getPrimaryPrincipal().toString());
        Set<String> userRolesSet = userService.getUserRolesSet(username);
        Set<String> userPermissionsSet = userService.getUserPermissionsSet(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(userRolesSet);
        simpleAuthorizationInfo.setStringPermissions(userPermissionsSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 获取身份验证信息
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String username = JwtUtil.getUsernameFromToken(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        User user = userService.getUser(username);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }
        boolean flag = JwtUtil.validateToken(token, username);
        if (!flag) {
            throw new AuthenticationException("");
        }
        return new SimpleAuthenticationInfo(token, token, "ShiroRealm");
    }
}
