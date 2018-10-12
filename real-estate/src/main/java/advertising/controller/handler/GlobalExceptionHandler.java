package advertising.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import advertising.exception.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception e) {
		ModelAndView mav = new ModelAndView("errors/404error");
		mav.addObject("exception", e);
		return mav;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handleNumberFormat(Exception e) {
		ModelAndView mav = new ModelAndView("errors/400error");
		mav.addObject("msg", e.getCause().getMessage());
		mav.addObject("exception", e);
		return mav;
	}
	
}
