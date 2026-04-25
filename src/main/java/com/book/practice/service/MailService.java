package com.book.practice.service;

import com.book.practice.dto.EmailAuthDto;
import com.book.practice.dto.ResponseDto;
import com.book.practice.entity.EmailAuth;
import com.book.practice.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSender mailSender;

    private final EmailRepository repository;

    @Transactional
    public ResponseDto<Map<String,Object>> send(String to) {

        try {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setFrom("sinkijun890@naver.com");
        msg.setSubject("인증 메일");
        String randomAuthNum = (int)((Math.random() * 900000) + 100000)+"";
        msg.setText("회원가입 인증코드: " +randomAuthNum);

        mailSender.send(msg);

        EmailAuth emailAuth = new EmailAuth();
        emailAuth.setAuthCode(randomAuthNum);
        emailAuth.setEmail(to);

        EmailAuth saveEmailAuth = repository.save(emailAuth);



        Map<String,Object> resultMap = new HashMap<>();


        resultMap.put("result", "Y");
        resultMap.put("emailNo", saveEmailAuth.getEmailNo() );
        return ResponseDto.success(resultMap);
        } catch(Exception e) {
            return ResponseDto.fail("이메일 발송에 실패하였습니다.");
        }

    }

    public ResponseDto<Map<String,Object>> confirmAuthCode(EmailAuthDto dto) {
        Map<String,Object> resultMap = new HashMap<>();

        try {
            EmailAuth emailAuth = repository.findById(dto.getEmailNo()).orElseThrow();

            LocalDateTime now = LocalDateTime.now();

            LocalDateTime sendTime = emailAuth.getSendTime().plusMinutes(10);

            if(sendTime.isBefore(now)) {
                return ResponseDto.fail("유효한 시간이 지났습니다.");
            }

            if(!dto.getAuthCode().equals(emailAuth.getAuthCode())) {
                return ResponseDto.fail("유효하지 않은 인증코드입니다.");
            }


            resultMap.put("result", "Y");
            return ResponseDto.success(resultMap);
        } catch(Exception e) {
            return ResponseDto.fail("검증에 실패하였습니다.");
        }

    }
}
