package com.hyl.rock.file.service.impl;

import com.hyl.rock.file.service.ISysFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LocalSysFileServiceImpl implements ISysFileService {

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        return "";
    }

    @Override
    public void deleteFile(String fileUrl) throws Exception {

    }
}
