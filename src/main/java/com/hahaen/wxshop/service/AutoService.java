package com.hahaen.wxshop.service;

import com.hahaen.wxshop.generate.User;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoService {
    private final UserService userService;
    private final VerificationCodeCheckService verificationCodeCheckService;
    private final SmsCodeService smsCodeService;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    @Autowired
    public AutoService(UserService userService,
                       VerificationCodeCheckService verificationCodeCheckService,
                       SmsCodeService smsCodeService) {
        this.userService = userService;
        this.verificationCodeCheckService = verificationCodeCheckService;
        this.smsCodeService = smsCodeService;
    }

    @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE")
    public void sendVerificationCode(String tel) {
        User user = userService.createUserIfNotExist(tel);
        String correctCode = smsCodeService.sendSmsCode(tel);
        verificationCodeCheckService.addCode(tel, correctCode);
    }
}
