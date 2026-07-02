package com.travel.letsgospringboot.user.controller;

import com.travel.letsgospringboot.user.service.UserService;
import com.travel.letsgospringboot.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("loginView")
    public String loginView(@RequestParam(value = "error", required = false) String error, Model model){
        if (error != null) {
            String errorMessage = "아이디/비밀번호를 다시 입력하세요.";
            if ("disabled".equals(error)) {
                errorMessage = "정지된 계정입니다.";
            }
            model.addAttribute("errorMessage", errorMessage);
        }
        return "login";
    }

    @GetMapping("signUpView")
    public String signUpView() {
        return "signup";
    }

    @GetMapping("getIdView")
    public String getIdView() {
        return "getid";
    }

    @GetMapping("updatePwView")
    public String updatePwView() {
        return "updatepw";
    }

    @GetMapping("addUser")
    public String addUser(OAuth2AuthenticationToken token) {
        OAuth2User oAuth2User = token.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        userService.signUp(UserVO.builder()
                .userID("google_" + email)
                .email(email)
                .name(name)
                .password(email + "!")
                .build());

        return "redirect:/";
    }
}
