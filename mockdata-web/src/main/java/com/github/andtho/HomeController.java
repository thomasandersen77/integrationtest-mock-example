package com.github.andtho;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class HomeController {

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/editor")
    String editor() {
        return "editor";
    }

    @RequestMapping("properties")
    @ResponseBody
    java.util.Properties properties() {
        return System.getProperties();
    }
}