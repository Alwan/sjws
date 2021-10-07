package app.sjws;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        IndexPage indexPage = new IndexPage();
        indexPage.setGreetings("Hi jte");

        model.addAttribute("page", indexPage);

        return "index";
    }

}
