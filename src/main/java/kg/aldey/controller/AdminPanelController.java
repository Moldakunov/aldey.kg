package kg.aldey.controller;

import kg.aldey.model.admin.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPanelController {
    @RequestMapping("/adminPage")
    public String index() {
        return "adminPage";
    }

    @RequestMapping("/fishkAdmin")
    public String loginAdmin(Model model) {
        model.addAttribute("title", "ADMIN МЫШКА");
        model.addAttribute("adminForm", new Admin());
        return "loginAdmin";
    }

    @RequestMapping("/fishkAdmin-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "loginAdmin";
    }
}
