backend
[前端地址](https://github.com/LittleTheFu/demo_app_front_end)

![](https://img.shields.io/badge/mybatis-green.svg)
![](https://img.shields.io/badge/spring_boot-green.svg)
![](https://img.shields.io/badge/swagger3-green.svg)
![](https://img.shields.io/badge/tencent_cos-green.svg)

首先，你需要配置`application-eamil.yml`里面的值，这个是用来配置你的发送邮件的邮箱的。
接下来，你要开启一行注释:

```java
	emailService.send(emailDto.getEmail(), "reset link", link);
```

这行注释在这里AccountCoutroller.java:

```java
	public CommonResult<String> wantResetPassword(@RequestBody EmailDto emailDto) 	{
		String link = accountService.getResetLink(emailDto.getEmail());
		//emailService.send(emailDto.getEmail(), "reset link", link);

		return CommonResult.success(link);
	}
```

当你忘记密码的时候，系统会发送一封邮件给你，这个邮件包含了重置密码的地址链接。

然后，你需要配置`applicaiton-osskey.yml`里面的值，因为所有的图片都是存储在的腾讯oss里面的，所以你需要填写对应的key之类的值。