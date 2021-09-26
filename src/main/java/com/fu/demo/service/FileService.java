package com.fu.demo.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fu.demo.mbg.dto.OssUploadDto;

public interface FileService {
	OssUploadDto uploadImage(@RequestParam("file") MultipartFile file);
}
