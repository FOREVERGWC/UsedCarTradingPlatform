package org.example.springboot.system.common.exception;

import org.example.springboot.system.common.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException {
    // TODO 改名ServiceException
    @Serial
    private static final long serialVersionUID = 4733950199287552052L;
    /**
     * 消息响应码
     */
    private ResultCode resultCodeEnum;
}