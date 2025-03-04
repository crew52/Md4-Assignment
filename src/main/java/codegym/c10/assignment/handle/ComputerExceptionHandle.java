package codegym.c10.assignment.handle;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ComputerExceptionHandle {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception ex, HttpServletRequest request) {
        System.out.println(ex.getMessage());
        ModelAndView modelAndView;
        modelAndView = new ModelAndView("error");
        return modelAndView;
    }
}