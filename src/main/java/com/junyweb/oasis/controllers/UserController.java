package com.junyweb.oasis.controllers;

import com.junyweb.oasis.entities.UserEntity;
import com.junyweb.oasis.enums.user.LoginResult;
import com.junyweb.oasis.services.UserService;
import com.junyweb.oasis.vos.user.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE) // 로그인 GET
    public String loginGet(@SessionAttribute(value = "userEntity", required = false) UserEntity userEntity) {
        if (userEntity != null) {
            return "redirect:/";
        }
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE) // 로그인 POST
    public String loginPost(@SessionAttribute(value = "userEntity", required = false) UserEntity userEntity,
                            LoginVo loginVo,
                            Model model,
                            HttpSession session) {
        if (userEntity != null) {
            return "redirect:/";
        }
        this.userService.login(loginVo); // 서비스단에서 로그인 메서드 호출
        if (loginVo.getLoginResult() == LoginResult.SUCCESS) { //  로그인 성공했을시
            session.setAttribute("userEntity", loginVo.getUserEntity()); // 세션에 회원정보담고
            return "redirect:/"; // 홈으로
        }
        model.addAttribute("loginResult", loginVo.getLoginResult()); // 모델에 로그인 결과담기
        model.addAttribute("loginVo", loginVo); // 모델에 로그인 객체 담기
        return "user/login";
    }

    @RequestMapping(value = "/logout") // 로그아웃
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 끊기위한 함수 호출
        return "redirect:/";
    }

    @RequestMapping(value = "/register-agree", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE) // 회원가입 전 약관동의 GET
    public String agreeGet() {
        return "user/register-agree";
    }

    @RequestMapping(value = "/register-agree", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE) // 회원가입 전 약관동의 POST
    public String agreePost() {
        return "redirect:/user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE) // 회원가입 GET
    public String registerGet(@SessionAttribute(value = "userEntity", required = false) UserEntity userEntity) {
        if (userEntity != null) {
            return "redirect:/";
        }
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE) // 회원가입 POST
    public String registerPost(RegisterVo registerVo,
                               Model model) throws MessagingException {
        this.userService.register(registerVo); // service 단에서 register 메서드 호출
        model.addAttribute("registerResult", registerVo.getRegisterResult());
        return "user/register";
    }

    @ResponseBody // ajax 를 이용하기위한 ResponseBody
    @RequestMapping(value = "/check-email",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkEmailPost(@RequestBody EmailCheckVo emailCheckVo) {  // 회원가입시 ajax를 이용한 이메일 체크
        this.userService.checkEmail(emailCheckVo);
        JSONObject responseJson = new JSONObject(); // Json 객체 만들기
        responseJson.put("result", emailCheckVo.getEmailCheckResult().name().toLowerCase()); // Json 객체에 이메일체크 결과 담기 -> register.js
        return responseJson.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/check-nickname",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkNicknamePost(
            @RequestBody NicknameCheckVo nicknameCheckVo) {
        this.userService.checkNickname(nicknameCheckVo);
        JSONObject responseJson = new JSONObject();
        responseJson.put("result", nicknameCheckVo.getNicknameCheckResult().name().toLowerCase());
        return responseJson.toString();
    }

    @RequestMapping(value = "/email-verify",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public String verifyEmailGet(EmailVerificationVo emailVerificationVo, Model model) {    // 이메일 인증 GET
        this.userService.verifyEmail(emailVerificationVo);
        model.addAttribute("emailVerificationResult", emailVerificationVo.getEmailVerificationResult());
        return "user/verifyEmail";
    }

}
