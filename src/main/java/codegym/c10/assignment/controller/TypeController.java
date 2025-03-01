package codegym.c10.assignment.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/types")
public class TypeController {
    @GetMapping
    public ModelAndView listType(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/type/index");
        return modelAndView;
    }
}
