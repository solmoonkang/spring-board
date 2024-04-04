package kr.co.noticeboard.controller;

import kr.co.noticeboard.infra.response.ResponseFormat;
import kr.co.noticeboard.infra.response.ResponseStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/api/user")
    public ResponseFormat<Void> userHome() {

        return ResponseFormat.successMessage(
                ResponseStatus.SUCCESS_EXECUTE
        );
    }

    @GetMapping("/api/admin")
    public ResponseFormat<Void> adminHome() {

        return ResponseFormat.successMessage(
                ResponseStatus.SUCCESS_EXECUTE
        );
    }

    @GetMapping("/user")
    public ResponseFormat<String> userDetail(@AuthenticationPrincipal UserDetails userDetails) {

        return ResponseFormat.successMessageWithData(
                ResponseStatus.SUCCESS_EXECUTE,
                userDetails.getUsername()
        );
    }
}
