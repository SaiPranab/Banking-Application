package com.jtbank.backend.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.StringJoiner;

@RestControllerAdvice
public class HandleException {
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map<String, String> handler(RuntimeException e) {
//        var map = new HashMap<String, String>();
//        map.put("title", "not found");
//        map.put("detail", e.getMessage());
//        map.put("timestamp", LocalDateTime.now().toString());
//
//        return map;
//    }

//    @ExceptionHandler(StudentNotFoundException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map<String, String> handler(StudentNotFoundException e) {
//        var map = new HashMap<String, String>();
//        map.put("title", "not found");
//        map.put("detail", e.getMessage());
//        map.put("timestamp", LocalDateTime.now().toString());
//
//        return map;
//    }

//    @ExceptionHandler(StudentNotFoundException.class)
//    public ProblemDetail handler(StudentNotFoundException e) {
//        var problemDetail = ProblemDetail.forStatus(400);
//        problemDetail.setTitle("Not Found");
//        problemDetail.setDetail(e.getMessage());
////        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), e.getMessage());
//        return problemDetail;
//    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ProblemDetail handler(NoResourceFoundException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handler(Exception e){
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handler(MethodArgumentNotValidException e) {
        var details = new StringJoiner(", ");
        e.getAllErrors().forEach(error -> {
            var message = error.getDefaultMessage();
            var fieldName = ((FieldError)error).getField();
            details.add(fieldName + ": " + message);
        });

        var problemDetail = ProblemDetail.forStatus(422);
        problemDetail.setTitle("Invalid data");
        problemDetail.setDetail(details.toString());
//        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(422), e.getMessage());
        return problemDetail;
    }
}
