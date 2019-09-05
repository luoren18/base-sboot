package top.luoren.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户Token
 *
 * @author luoren
 * @date 2019-09-05 11:37:43
 */
@Data
@TableName("user_token")
public class UserToken implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId
    private Long userId;
    /**
     * token
     */
    private String token;
    /**
     * 过期时间
     */
    private Date expireTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
