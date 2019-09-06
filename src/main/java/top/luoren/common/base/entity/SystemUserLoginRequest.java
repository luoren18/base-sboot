package top.luoren.common.base.entity;

import lombok.Data;

/**
 * @author luoren
 * @date 2019/9/6 14:51
 */
@Data
public class SystemUserLoginRequest {
    private String username;
    private String password;
    private String uuid;
    private String code;
}
