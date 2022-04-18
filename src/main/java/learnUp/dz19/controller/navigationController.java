package learnUp.dz19.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopBooks")
public class navigationController {

    @GetMapping("/")
    public String startPage(Model model){
        return "navigation";
    }

}
