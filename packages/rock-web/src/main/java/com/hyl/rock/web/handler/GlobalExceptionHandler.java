package com.hyl.rock.web.handler;

import cn.hutool.extra.spring.SpringUtil;
import com.hyl.rock.api.RemoteErrLogService;
import com.hyl.rock.base.MessageEnum;
import com.hyl.rock.base.Result;
import com.hyl.rock.constant.EnvConstants;
import com.hyl.rock.constant.SecurityConstants;
import com.hyl.rock.domain.SysErrLog;
import com.hyl.rock.exception.ServiceException;
import com.hyl.rock.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private RemoteErrLogService remoteLogService;

    /**
     * @valid参数校验异常
     *
     * @param exception
     * @return Result
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result bodyValidExceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.info("参数校验异常,ex = {}", fieldErrors.get(0).getDefaultMessage());
        Result result = new Result();
        result.setCode(MessageEnum.PARAM_ERROR.getCode());
        result.setMessage((fieldErrors.get(0).getDefaultMessage()));
        return result;
    }

    /**
     * validation Exception (以form-data形式传参)
     * @param exception
     * @return R
     */
    @ExceptionHandler({ BindException.class })
    @ResponseStatus(HttpStatus.OK)
    public Result bindExceptionHandler(BindException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.info("参数绑定异常,ex = {}", fieldErrors.get(0).getDefaultMessage());
        Result result = new Result();
        result.setCode(MessageEnum.PARAM_ERROR.getCode());
        result.setMessage((fieldErrors.get(0).getDefaultMessage()));
        return result;
    }

    /**
     * 断言异常
     * @param exception
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        Result result = new Result();
        result.setCode(MessageEnum.PARAM_ERROR.getCode());
        log.error("断言异常",exception);
        result.setMessage(exception.getMessage());
        return result;
    }

    /**
     * security异常
     * @param
     * @return
     */
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Result usernameNotFoundExceptionHandler(UsernameNotFoundException exception) {
		exception.printStackTrace();
		Result result = new Result();
		result.setCode(MessageEnum.USER_NOT_FOUND.getCode());
		result.setMessage(exception.getMessage());
		return result;
	}

    /**
     * 业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public Result<String> serviceExceptionResult(Exception ex) {
        Result<String> result = new Result<>();
        ServiceException serviceException = (ServiceException)ex;
        log.info("业务异常, code:{}, message:{}", serviceException.getCode(), serviceException.getMessage());
        result.setCode(serviceException.getCode());
        result.setMessage(serviceException.getMessage());
        return result;
    }

    /**
     * 未知异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result<String> responseResult(Exception ex) {
        log.info("统一异常应答：", ex);
        // 只有生产环境预警
        if (!EnvConstants.PROD.equals(SpringUtil.getActiveProfile())) {
            //执行发送邮件或者是推送消息操作
        }

        try {
            // 记录错误日志
            SysErrLog errLog = new SysErrLog();
            errLog.setTitle(ex.getMessage());
            errLog.setContent(ExceptionUtil.getExceptionMessage(ex));
            remoteLogService.saveErrLog(errLog, SecurityConstants.INNER);
        } catch (Exception e) {
            //不处理
        }

        return Result.fail();
    }
}
