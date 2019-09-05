package top.luoren.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.luoren.system.entity.Captcha;

/**
 * 系统验证码
 * 
 * @author luoren
 * @email luoren96@gmail.com
 * @date 2019-09-05 11:37:43
 */
@Mapper
public interface CaptchaMapper extends BaseMapper<Captcha> {
	
}
