package com.initsrc.common.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.initsrc.common.base.Result;
import com.initsrc.common.plugin.oss.OssServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * <p>
 *  公共接口-验证码模块
 * </p>
 *
 * @author INITSRC
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/common/captcha")
@Api(tags = "公共接口-验证码模块")
public class CaptchaController {
    @Resource
    private Producer captchaProducer;
    @Resource
    private OssServiceImpl ossService;

    /**
     * 公共接口-验证码模块
     *
     * @param
     * @return
     */
    @GetMapping("/getVerifyCode")
    @ApiOperation(value = "公共接口-验证码模块", notes = "获取验证码")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //用字节数组存储
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        ServletOutputStream responseOutputStream =
                response.getOutputStream();
        final HttpSession httpSession = request.getSession();
        try {
            //生产验证码字符串并保存到session中
            String createText = captchaProducer.createText();
            httpSession.setAttribute(Constants.KAPTCHA_SESSION_KEY, createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = captchaProducer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
            captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
        } catch (IllegalArgumentException | IOException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } finally {
            responseOutputStream.close();
        }
    }

    /**
     * 授权前端OSS TOKEN
     *
     * @param
     * @return Result
     */
    @PostMapping("/getOssToken")
    @ApiOperation(value = "授权前端OSS TOKEN", notes = "授权前端OSS TOKEN")
    public Result saveData(){
        return Result.success(ossService.getStsAuthorization());
    }
}
