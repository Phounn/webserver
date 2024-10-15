package gov.la.webserver.user.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web-server")
public class WelcomeController {
    @GetMapping("/")
    public String welcome_controller() {
        return "<h1 style=\"color:blue;text-align:center;\">Welcome Controller</h1>";
    }

    @GetMapping("/web-server")
    public String web_server() {
        return "<h1>Hello web-server</h1>";
    }

    @GetMapping("/hello")
    public String hello() {
        return "<h2>hello</h2>";
    }

    @GetMapping("/world")
    public String world() {
        return " <h2>world</h2>";
    }

    @GetMapping("/hello-world")
    public String hello_world() {
        return " <h2>hello world</h2>";
    }
}
