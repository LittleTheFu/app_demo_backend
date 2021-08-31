package com.fu.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fu.demo.common.api.CommonResult;
import com.fu.demo.mbg.dto.FollowResponseDto;
import com.fu.demo.mbg.dto.MinioUploadDto;
import com.fu.demo.mbg.dto.UpdateArticleDto;
import com.fu.demo.mbg.dto.UpdateUserNameDto;
import com.fu.demo.mbg.dto.UserDto;
import com.fu.demo.service.UserService;

import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "UserController", description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;
	
	@ApiOperation("获取所有用户")
	@GetMapping("/all")
	public List<UserDto> allUser() {
		List<UserDto> users = userService.listAllUserWithCurrentUser();
		
		return users;
	}
	
	@ApiOperation("根据id查询用户")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<UserDto> getById(@PathVariable("id") int id) {
		UserDto user = userService.getUserByIdWithCurrentUser(id);
		
		return CommonResult.success(user);
	}
	
	@ApiOperation("查询当前用户")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<UserDto> getCurrentUserProfile() {
		UserDto user = userService.getCurrentUser();
		
		return CommonResult.success(user);
	}
	
	@ApiOperation("查询用户的关注")
	@RequestMapping(value = "/followings/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<UserDto>> getFollowings(@PathVariable("id") int id) {
		List<UserDto> followings = userService.getFollowingsWithCurrentUser(id);
		
		return CommonResult.success(followings);
	}
	
	@ApiOperation("查询用户的粉丝")
	@RequestMapping(value = "/followers/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<UserDto>> getFollowers(@PathVariable("id") int id) {
		List<UserDto> followers = userService.getFollowerWithCurrentUser(id);
		
		return CommonResult.success(followers);
	}
	
	@ApiOperation("更换用户名")
	@RequestMapping(value = "/change_name", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult updateName(@RequestBody UpdateUserNameDto updateUserNameDto) {
		userService.setCurrentUserName(updateUserNameDto.getName());
		
		return CommonResult.success(null);
	}	
	
	@ApiOperation("更换头像")
	@RequestMapping(value = "/change_icon", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<MinioUploadDto> uploadIcon(@RequestParam("file") MultipartFile file) {
		try {
            //创建一个MinIO的Java客户端
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            boolean isExist = minioClient.bucketExists(BUCKET_NAME);
            if (isExist) {
                LOGGER.info("存储桶已经存在！");
            } else {
                //创建存储桶并设置只读权限
                minioClient.makeBucket(BUCKET_NAME);
                minioClient.setBucketPolicy(BUCKET_NAME, "*.*", PolicyType.READ_ONLY);
            }
            String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 设置存储对象名称
            String objectName = sdf.format(new Date()) + "/" + filename;
            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(BUCKET_NAME, objectName, file.getInputStream(), file.getContentType());
            
            LOGGER.info("文件上传成功!");
            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName(filename);
            minioUploadDto.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);
            
            userService.setCurrentUserIcon(minioUploadDto.getUrl());
            
            return CommonResult.success(minioUploadDto);
        } catch (Exception e) {
            LOGGER.info("上传发生错误: {}！", e.getMessage());
        }
        return CommonResult.failed();
	}
	
	@ApiOperation("follow用户")
	@RequestMapping(value = "/follow/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult<FollowResponseDto> follow(@PathVariable("id") Long id) {
		userService.currentUserFollow(id);
		
		FollowResponseDto responseDto = new FollowResponseDto();
		responseDto.setFollowed(true);
		
		return CommonResult.success(responseDto);
	}
	
	@ApiOperation("unfollow用户")
	@RequestMapping(value = "/unfollow/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommonResult<FollowResponseDto> unfollow(@PathVariable("id") Long id) {
		userService.currentUserUnfollow(id);
		
		FollowResponseDto responseDto = new FollowResponseDto();
		responseDto.setFollowed(false);
		
		return CommonResult.success(responseDto);
	}
}