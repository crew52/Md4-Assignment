package codegym.c10.assignment.controller;

import codegym.c10.assignment.exception.NotFountException;
import codegym.c10.assignment.model.Computer;
import codegym.c10.assignment.model.Type;
import codegym.c10.assignment.service.IComputerService;
import codegym.c10.assignment.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public String create(@Validated @ModelAttribute("computer") Computer computer,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
//        computerService.save(computer);
//        redirectAttributes.addFlashAttribute("message", "Create new computer successfully");
//        return "redirect:/computers";

        if (bindingResult.hasFieldErrors()) {
            return "/computer/create";
        }
        computerService.save(computer);
        redirectAttributes.addFlashAttribute("message", "Create new computer successfully");
        return "redirect:/computers";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) throws NotFountException {
        Optional<Computer> computer = computerService.findById(id);
            ModelAndView modelAndView = new ModelAndView("/computer/update");
            modelAndView.addObject("computer", computer.get());
            return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("computer") Computer computer,
                         RedirectAttributes redirect) {
        computerService.save(computer);
        redirect.addFlashAttribute("message", "Update computer successfully");
        return "redirect:/computers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirect) {
        computerService.remove(id);
        redirect.addFlashAttribute("message", "Delete computer successfully");
        return "redirect:/computers";
    }

    @ExceptionHandler(NotFountException.class)
    public String handleNotFound(Model model, NotFountException e) {
        return "/error_404";
    }
}
