package top.luoren.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.luoren.common.api.vo.Result;

/**
 * @author luoren
 * @date 2019/9/3 17:16
 */
@Slf4j
@RestControllerAdvice
public class SbootExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(SbootException.class)
    public Result handleSbootException(SbootException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }
}
