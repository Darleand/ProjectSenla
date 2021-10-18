package by.senla.weblocatiom.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Incorrect to input value")
public class Exceptions extends RuntimeException {
}
