package com.fu.demo.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fu.demo.mbg.dto.OssUploadDto;
import com.fu.demo.service.FileService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

@Service
public class FileServiceImp implements FileService{
	@Value("${tenentcos.endpoint}")
	private String OSS_ENDPOINT;
	@Value("${tenentcos.SecretId}")
	private String OSS_SECRET_ID;
	@Value("${tenentcos.SecretKey}")
	private String OSS_SECRET_KEY;
	@Value("${tenentcos.bucketName}")
	private String OSS_BUCKET_NAME;

	@Override
	public OssUploadDto uploadImage(MultipartFile file) {
		try {
			COSCredentials credentials = new BasicCOSCredentials(OSS_SECRET_ID, OSS_SECRET_KEY);
			ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));

			COSClient cosClient = new COSClient(credentials, clientConfig);

			String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            
            Random r = new Random();
            int randResult = r.nextInt(9999);
            
            String objectName = sdf.format(new Date()) + "_" + randResult + filename;

			File localFile = File.createTempFile(objectName, null);
			file.transferTo(localFile);

			PutObjectRequest putObjectRequest = new PutObjectRequest(OSS_BUCKET_NAME, objectName, localFile);
			PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

//			LOGGER.info("文件上传成功!");
			OssUploadDto minioUploadDto = new OssUploadDto();
			minioUploadDto.setName(filename);
			minioUploadDto.setUrl(OSS_ENDPOINT + putObjectRequest.getKey());

			return minioUploadDto;
		} catch (Exception e) {
//			LOGGER.info("上传发生错误: {}！", e.getMessage());
		}
		
		return null;
	}

}
