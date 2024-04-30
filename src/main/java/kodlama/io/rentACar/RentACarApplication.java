package kodlama.io.rentACar;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kodlama.io.rentACar.core.utilities.ValidationProblemDetails;
import kodlama.io.rentACar.core.utilities.exceptions.BusinessException;
import kodlama.io.rentACar.core.utilities.exceptions.ProblemDetails;

@SpringBootApplication
@RestControllerAdvice
public class RentACarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACarApplication.class, args);
	}
    
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(BusinessException businessException) {
		ProblemDetails problemDetails = new ProblemDetails();
		ProblemDetails.setMessage(businessException.getMessage());
		

		return problemDetails;
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
		ValidationProblemDetails validationproblemDetails = new ValidationProblemDetails();
		validationproblemDetails.setMessage("VALIDATION.EXCEPTION");
		
		ValidationProblemDetails validationProblemDetails;
		validationProblemDetails.setValidationErrors(new HashMap<String, String>());
		
		
		for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldError()) {
			validationProblemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return validationProblemDetails;
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
				}
}
