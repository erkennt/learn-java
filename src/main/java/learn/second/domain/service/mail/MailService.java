package learn.second.domain.service.mail;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;


    @Async // 非同期
    public void doSendUserAuthenticationEmail(String nickName, String mailAddress, String url) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // メール本文の生成
        Map<String,Object> model = new HashMap<String,Object>();
        StringWriter writer = new StringWriter();
        model.put("user", nickName);
        model.put("url", url);
       VelocityContext ctx = new VelocityContext	(model);
        velocityEngine.mergeTemplate("UserAuthentication.vm", "UTF-8", ctx, writer);

        // メール本文のセット
        helper.setText(writer.toString());

        // 送信先を設定
        helper.setTo(mailAddress);

        // 件名
        helper.setSubject("[CardGame]登録確認");
        // 送信
        mailSender.send(message);
    }

    @Async // 非同期
    public void doSendUserIdRemaindEmail(String userId, String mailAddress) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // メール本文の生成
        Map<String,Object> model = new HashMap<String,Object>();
        StringWriter writer = new StringWriter();
        model.put("userId", userId);
       VelocityContext ctx = new VelocityContext	(model);
        velocityEngine.mergeTemplate("UserIdRemind.vm", "UTF-8", ctx, writer);

        // メール本文のセット
        helper.setText(writer.toString());

        // 送信先を設定
        helper.setTo(mailAddress);

        // 件名
        helper.setSubject("[CardGame]ユーザーIDの確認");
        // 送信
        mailSender.send(message);
    }

    @Async // 非同期
    public void doSendPasswordRemaindEmail(String userId, String mailAddress, String url) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // メール本文の生成
        Map<String,Object> model = new HashMap<String,Object>();
        StringWriter writer = new StringWriter();
        model.put("userId", userId);
        model.put("url", url);
       VelocityContext ctx = new VelocityContext	(model);
        velocityEngine.mergeTemplate("PasswordRemind.vm", "UTF-8", ctx, writer);

        // メール本文のセット
        helper.setText(writer.toString());

        // 送信先を設定
        helper.setTo(mailAddress);

        // 件名
        helper.setSubject("[CardGame]パスワードの変更");
        // 送信
        mailSender.send(message);
    }
}