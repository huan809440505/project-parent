package com.hyl.rock.api.factory;

import com.hyl.rock.api.RemoteFileService;
import com.hyl.rock.base.Result;
import com.hyl.rock.domain.SysFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务降级处理
 * 
 */
@Component
public class RemoteFileFallbackFactory implements FallbackFactory<RemoteFileService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteFileFallbackFactory.class);

    @Override
    public RemoteFileService create(Throwable throwable)
    {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new RemoteFileService()
        {
            @Override
            public Result<SysFile> upload(MultipartFile file)
            {
                return Result.fail("上传文件失败:" + throwable.getMessage());
            }

            @Override
            public Result<Boolean> delete(String fileUrl)
            {
                return Result.fail("删除文件失败:" + throwable.getMessage());
            }
        };
    }
}
