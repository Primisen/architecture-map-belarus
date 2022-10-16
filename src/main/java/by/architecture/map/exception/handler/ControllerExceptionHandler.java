package by.architecture.map.exception.handler;

import by.architecture.map.exception.ConstructionException;
import by.architecture.map.exception.PhotoException;
import by.architecture.map.exception.SourceException;
import by.architecture.map.exception.handler.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({PhotoException.class})
    public ResponseEntity<Response> photoExceptionHandle(PhotoException e){
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstructionException.class})
    public ResponseEntity<Response> constructionExceptionHandle(ConstructionException e){
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SourceException.class})
    public ResponseEntity<Response> constructionExceptionHandle(SourceException e){
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
