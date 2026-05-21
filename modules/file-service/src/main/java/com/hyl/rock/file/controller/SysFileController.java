package com.hyl.rock.file.controller;

import com.hyl.rock.base.Result;
import com.hyl.rock.domain.SysFile;
import com.hyl.rock.file.service.ISysFileService;
import com.hyl.rock.utils.StringUtils;
import com.hyl.rock.utils.file.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件请求处理
 * 
 * @author ruoyi
 */
@RestController
public class SysFileController
{
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    @Autowired
    private ISysFileService sysFileService;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public Result<SysFile> upload(MultipartFile file)
    {
        try
        {
            // 上传并返回访问地址
            String url = sysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return Result.success(sysFile);
        }
        catch (Exception e)
        {
            log.error("上传文件失败", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 文件删除请求
     */
    @DeleteMapping("delete")
    public Result<Boolean> delete(String fileUrl)
    {
        try
        {
            if (!FileUtils.validateFilePath(fileUrl))
            {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许删除。 ", fileUrl));
            }
            sysFileService.deleteFile(fileUrl);
            return Result.success();
        }
        catch (Exception e)
        {
            log.error("删除文件失败", e);
            return Result.fail(e.getMessage());
        }
    }
}
