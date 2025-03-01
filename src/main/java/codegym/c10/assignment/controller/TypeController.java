package codegym.c10.assignment.controller;

import codegym.c10.assignment.model.Type;
import codegym.c10.assignment.service.IComputerService;
import codegym.c10.assignment.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private IComputerService computerService;

    @Autowired
    private ITypeService typeService;

    @GetMapping
    public ModelAndView listProvince() {
        ModelAndView modelAndView = new ModelAndView("/type/index");
        Iterable<Type> types = typeService.findAll();
        modelAndView.addObject("types", types);
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("type") Type type,
                         RedirectAttributes redirectAttributes) {
        typeService.save(type);
        redirectAttributes.addFlashAttribute("message", "Create new type successfully");
        return "redirect:/types";
    }
}
