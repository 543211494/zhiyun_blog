package org.nwpu.blog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.regex.Pattern;

/**
 * @author lzy
 * @date 2022/8/17
 * 邮件操作工具类
 */
@Component
public class EmailSender {

    @Value("${spring.mail.from}")
    private String from;

    private final static String TEGEX="^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$";

    /**
     * 邮件内容模板
     */
    private static final String HEADCONTENT = "<head><base target=\"_blank\" /><style type=\"text/css\">::-webkit-scrollbar {display: none;}</style><style id=\"cloudAttachStyle\" type=\"text/css\">#divNeteaseBigAttach,#divNeteaseBigAttach_bak {display: none;}</style><style id=\"blockquoteStyle\" type=\"text/css\">blockquote {display: none;}</style><style type=\"text/css\">body {font-size: 14px;font-family: arial, verdana, sans-serif;line-height: 1.666;padding: 0;margin: 0;overflow: auto;white-space: normal;word-wrap: break-wor d;min-height: 100px}td,input,button,select,body {font-family: Helvetica, 'Microsoft Yahei', verdana}pre {white-space: pre-wrap;white-space: -moz-pre-wrap;white-space: -pre-wrap;white-space: -o-pre-wrap;word-wrap: break-word;width: 95%}th,td {font-family: arial, verdana, sans-serif;line-height: 1.666}img {border: 0}header,footer,section,aside,article,nav,hgroup,figure,figcaption {display: block}blockquote {margin-right: 0px}</style></head><body tabindex=\"0\" role=\"listitem\"><table width=\"700\" border=\"0\" align=\"center\" cellspacing=\"0\" style=\"width:700px;\"><tbody><tr><td><div style=\"width:700px;margin:0 auto;border-bottom:1px solid #ccc;margin-bottom:30px;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"700\" height=\"39\" style=\"font:12px Tahoma, Arial, 宋体;\"><tbody><tr><td width=\"210\"></td></tr></tbody></table></div><div style=\"width:680px;padding:0 10px;margin:0 auto;\"><div style=\"line-height:1.5;font-size:14px;margin-bottom:25px;color:#4d4d4d;\"><strong style=\"display:block;margin-bottom:15px;\">尊敬的用户：<span style=\"color:#f60;font-size: 16px;\"></span>您好！</strong><strong style=\"display:block;margin-bottom:15px;\">您正在进行<span style=\"color: red\">";

    private static final String MEDIUMCONTENT = "</span>操作，请在验证码输入框中输入：<span style=\"color:#f60;font-size: 24px\">";

    private static final String ENDCONTENT = "</span>，以完成操作。</strong></div><div style=\"margin-bottom:30px;\"><small style=\"display:block;margin-bottom:20px;font-size:12px;\"><p style=\"color:#747474;\">注意: 此操作可能会修改您的密码、登陆邮箱。如非本人操作，请及时登录并修改密码以保证帐户安全<br>（⼯作⼈员不会向你索取此验证码，请勿泄漏！)</p></small></div></div><div style=\"width:700px;margin:0 auto;\"><div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\"><p>此为系统邮件，请勿回复<br> 请保管好您的邮箱，避免账号被他⼈盗⽤</p><p>2022知云博客团队</p></div></div></td></tr></tbody></table></body>";

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送邮件
     * @param to 收件人邮箱地址(比如:abc@qq.com)
     * @param code 验证码内容
     * @param operationName 操作名字(比如"注册账号", "修改密码"等)
     */
    public void send(String to, String code, String operationName){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("\"知云博客\"平台验证码");
            helper.setText(EmailSender.HEADCONTENT+operationName+EmailSender.MEDIUMCONTENT+code+EmailSender.ENDCONTENT, true);
            javaMailSender.send(mimeMessage);
        }catch (MessagingException messagingException){
            messagingException.printStackTrace();
        }
    }

    /**
     * 检验邮箱格式是否正确
     * @param email 邮箱地址
     * @return 格式正确与否
     */
    public static boolean isEmail(String email) {
        if ((email != null) && (!email.isEmpty())) {
            return Pattern.matches(EmailSender.TEGEX, email);
        }
        return false;
    }
}
