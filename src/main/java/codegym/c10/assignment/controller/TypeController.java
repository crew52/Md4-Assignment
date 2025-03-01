package codegym.c10.assignment.controller;

import codegym.c10.assignment.model.Computer;
import codegym.c10.assignment.model.Type;
import codegym.c10.assignment.service.IComputerService;
import codegym.c10.assignment.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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

    @GetMapping("/view-type/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Optional<Type> typeOptional = typeService.findById(id);
        if(!typeOptional.isPresent()){
            return new ModelAndView("/error_404");
        }

        Iterable<Computer> computers = computerService.findAllByType(typeOptional.get());

        ModelAndView modelAndView = new ModelAndView("/computer/index");
        modelAndView.addObject("computers", computers);
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Type> typeOptional = typeService.findById(id);
        if (typeOptional.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/type/update");
            modelAndView.addObject("type", typeOptional.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("type") Type type,
                         RedirectAttributes redirect) {
        typeService.save(type);
        redirect.addFlashAttribute("message", "Update type successfully");
        return "redirect:/types";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        Optional<Type> typeOptional = typeService.findById(id);
        if (typeOptional.isPresent()) {
            typeService.remove(id);
            return new ModelAndView("redirect:/types");
        }
        return new ModelAndView("/error_404");
    }
}
