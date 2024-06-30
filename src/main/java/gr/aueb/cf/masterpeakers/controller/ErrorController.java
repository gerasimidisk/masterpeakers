package gr.aueb.cf.masterpeakers.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = { RuntimeException.class })
    public String handleBadRequest(Model model, RuntimeException ex) {
        model.addAttribute("err", "An unexpected error occurred: " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e) {
        model.addAttribute("err", "An error occurred: " + e.getMessage());
        return "error";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(Model model, Exception e) {
        model.addAttribute("err", "Page not found: " + e.getMessage());
        return "error";
    }
}
