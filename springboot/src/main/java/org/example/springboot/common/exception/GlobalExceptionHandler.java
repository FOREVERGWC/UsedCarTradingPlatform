package org.example.springboot.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.springboot.domain.Result;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局处理异常
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result<Void> Exception(Exception e) {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<Void> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError error = bindingResult.getAllErrors().get(0);
        return Result.error(error.getDefaultMessage());
    }

    @ExceptionHandler(CustomException.class)
    public Result<Void> CustomException(CustomException e) {
        return Result.error(e.getResultCodeEnum());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public Result<Void> RuntimeException(RuntimeException e) {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = NullPointerException.class)
    public Result<Void> NullPointerException(NullPointerException e) {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = MyBatisSystemException.class)
    public Result<Void> MyBatisSystemException(MyBatisSystemException e) {
        log.error(e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result<Void> IllegalArgumentException(IllegalArgumentException e) {
        return Result.error(e.getMessage());
    }
}
