package codegym.c10.assignment.controller;

import codegym.c10.assignment.model.Computer;
import codegym.c10.assignment.model.Type;
import codegym.c10.assignment.service.IComputerService;
import codegym.c10.assignment.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping ("/computers")
public class ComputerController {
    @Autowired
    private IComputerService computerService;

    @Autowired
    private ITypeService typeService;

    @ModelAttribute("types")
    public Iterable<Type> listTypes() {
        return typeService.findAll();
    }

    @GetMapping
    public ModelAndView listComputer(Pageable pageable) {
        Page<Computer> computers = computerService.findAll(pageable);
        return new ModelAndView("/computer/index", "computers", computers);
    }

    @GetMapping("/search")
    public ModelAndView listCustomersSearch(@RequestParam("search") Optional<String> search, Pageable pageable){
        Page<Computer> computers;
        if(search.isPresent()){
            computers = computerService.findAllByNameContaining(pageable, search.get());
        } else {
            computers = computerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/computer/index");
        modelAndView.addObject("computers", computers);
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/computer/create");
        modelAndView.addObject("computer", new Computer());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("computer") Computer computer,
                         RedirectAttributes redirectAttributes) {
        computerService.save(computer);
        redirectAttributes.addFlashAttribute("message", "Create new computer successfully");
        return "redirect:/computers";
    }
}
