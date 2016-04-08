package com.datao.util;

import com.datao.exception.DataAccessException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Created by 海涛 on 2016/4/8.
 * 发送邮件
 */
public class EmailUtil {
    public static void sendHtmlEmail(String title, String msg, String email) {

        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName(ConfigProp.get("mail.smtp"));
        htmlEmail.setAuthentication(ConfigProp.get("mail.username"), ConfigProp.get("mail.password"));
        htmlEmail.setCharset(ConfigProp.get("mail.code"));
        htmlEmail.setStartTLSEnabled(true);

        try {
            htmlEmail.setFrom(ConfigProp.get("mail.from"));

            htmlEmail.setSubject(title);
            htmlEmail.setHtmlMsg(msg);
            htmlEmail.addTo(email);

            System.out.println("已发送的邮箱标题:" + title);
            System.out.println("已发送的邮箱内容:" + msg);
            System.out.println("已发送的邮箱接收:" + email);

            htmlEmail.send();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("发送邮件异常!");
        }


    }
}
