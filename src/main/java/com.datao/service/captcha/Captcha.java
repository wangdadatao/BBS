package com.datao.service.captcha;

import com.datao.web.BaseServlet;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 海涛 on 2016/4/8.
 * 产生验证码
 */
@WebServlet("/newcaptcha.png")
public class Captcha extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConfigurableCaptchaService service = new ConfigurableCaptchaService();
        service.setColorFactory(new SingleColorFactory(new Color(26, 69, 226)));
        service.setFilterFactory(new CurvesRippleFilterFactory(service.getColorFactory()));

        RandomWordFactory wordFactory = new RandomWordFactory();
        wordFactory.setMinLength(4);
        wordFactory.setMaxLength(4);
        service.setWordFactory(wordFactory);

        response.setContentType("image/png");

        OutputStream outputStream = response.getOutputStream();
        String captcha = EncoderHelper.getChallangeAndWriteImage(service, "png", outputStream);

        HttpSession session = request.getSession();
        session.setAttribute("captcha", captcha);
        System.out.println("产生的验证码为:" + captcha);

        outputStream.flush();
        outputStream.close();
    }
}
