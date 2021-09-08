package com.fu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fu.demo.common.api.CommonResult;
import com.fu.demo.mbg.dto.MinioUploadDto;
import com.fu.demo.service.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "FileController", description = "文件上传管理")
@RestController
@RequestMapping("/file")
public class FileController {
	@Autowired
	FileService fileService;

	@ApiOperation("上传文件")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<MinioUploadDto> upload(@RequestParam("file") MultipartFile file) {
		MinioUploadDto minioUploadDto = fileService.uploadImage(file);
		
		if(minioUploadDto == null) {
			return CommonResult.failed();
		}
		
		return CommonResult.success(minioUploadDto);
	}
}
