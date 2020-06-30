package com.abc.demo.controller;

import com.abc.demo.controller.dto.res.DemoResponse;
import com.abc.demo.controller.dto.res.ResState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class DemoController {

    @GetMapping("/message/{languageTag}")
    public DemoResponse message(HttpSession httpSession, @PathVariable String languageTag) {
        httpSession.setAttribute("languageTag", languageTag);

        return DemoResponse.state(ResState.SUCCESS);

    }
}
