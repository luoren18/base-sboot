package top.luoren.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 角色与菜单对应关系
 * 
 * @author luoren
 * @email luoren96@gmail.com
 * @date 2019-09-05 11:37:43
 */
@Data
@TableName("sys_role_menu")
public class RoleMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 菜单ID
	 */
	private Long menuId;

}
