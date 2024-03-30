package cn.jackson.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * PageController
 *
 * @author Jackson.Ni
 * @since 2024/3/30
 */
@Controller
public class PageController {

    @GetMapping("/qrupload")
    public String uploadPage() {
        return "qrupload";
    }
}
