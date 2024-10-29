package org.example.springboot.system.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.springboot.common.common.enums.ResultCode;
import org.example.springboot.common.domain.Result;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

/**
 * 全局处理异常
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 通用异常
     *
     * @param e 通用异常
     * @return 结果
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("异常：Exception，信息：{}，堆栈：{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
        return Result.error(e.getMessage());
    }

    /**
     * 业务异常
     *
     * @param e 业务异常
     * @return 结果
     */
    @ExceptionHandler(ServiceException.class)
    public Result<Void> handleServiceException(ServiceException e) {
        log.error("异常：ServiceException，信息：{}，堆栈：{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
        return Result.error(e.getResultCodeEnum());
    }

    /**
     * 方法参数校验异常
     *
     * @param e 方法参数校验异常
     * @return 结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("异常：MethodArgumentNotValidException，信息：{}，堆栈：{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
        BindingResult bindingResult = e.getBindingResult();
        ObjectError error = bindingResult.getAllErrors().getFirst();
        return Result.error(error.getDefaultMessage());
    }

    /**
     * 空指针异常
     *
     * @param e 空指针异常
     * @return 结果
     */
    @ExceptionHandler(value = NullPointerException.class)
    public Result<Void> handleNullPointerException(NullPointerException e) {
        log.error("异常：NullPointerException，信息：{}，堆栈：{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
        return Result.error(ResultCode.SYSTEM_ERROR);
    }

    /**
     * 数组下标越界异常
     *
     * @param e 数组下标越界异常
     * @return 结果
     */
    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    public Result<Void> handleArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException e) {
        log.error("异常：ArrayIndexOutOfBoundsException，信息：{}，堆栈：{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
        return Result.error(ResultCode.SYSTEM_ERROR);
    }

    /**
     * 算术异常
     *
     * @param e 算术异常
     * @return 结果
     */
    @ExceptionHandler(value = ArithmeticException.class)
    public Result<Void> handleArithmeticException(ArithmeticException e) {
        log.error("异常：ArithmeticException，信息：{}，堆栈：{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
        return Result.error(ResultCode.SYSTEM_ERROR);
    }

    /**
     * 类型转换异常
     *
     * @param e 类型转换异常
     * @return 结果
     */
    @ExceptionHandler(value = ClassCastException.class)
    public Result<Void> handleClassCastException(ClassCastException e) {
        log.error("异常：ClassCastException，信息：{}，堆栈：{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
        return Result.error(ResultCode.SYSTEM_ERROR);
    }

    /**
     * 非法参数异常
     *
     * @param e 非法参数异常
     * @return 结果
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("异常：IllegalArgumentException，信息：{}，堆栈：{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
        return Result.error(ResultCode.SYSTEM_ERROR);
    }

    /**
     * MyBatis系统异常
     *
     * @param e MyBatis系统异常
     * @return 结果
     */
    @ExceptionHandler(value = MyBatisSystemException.class)
    public Result<Void> handleMyBatisSystemException(MyBatisSystemException e) {
        log.error("异常：MyBatisSystemException，信息：{}，堆栈：{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
        return Result.error(ResultCode.SYSTEM_ERROR);
    }

    /**
     * SQL语法错误异常
     *
     * @param e SQL语法错误异常
     * @return 结果
     */
    @ExceptionHandler(value = BadSqlGrammarException.class)
    public Result<Void> handleBadSqlGrammarException(BadSqlGrammarException e) {
        log.error("异常：BadSqlGrammarException，信息：{}，堆栈：{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
        return Result.error(ResultCode.SYSTEM_ERROR);
    }
}
