package top.luoren.common.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author luoren
 * @date 2019/9/6 11:21
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
