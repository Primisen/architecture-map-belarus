package com.architecture_map.belarus.exception.handler;

import com.architecture_map.belarus.exception.ArchitecturalStyleException;
import com.architecture_map.belarus.exception.ConstructionException;
import com.architecture_map.belarus.exception.PhotoException;
import com.architecture_map.belarus.exception.PhotoVisualTypeException;
import com.architecture_map.belarus.exception.SourceException;
import com.architecture_map.belarus.exception.handler.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {//мягко говоря, много повторяющегося кода

    @ExceptionHandler({PhotoException.class})
    public ResponseEntity<Response> photoExceptionHandle(PhotoException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstructionException.class})
    public ResponseEntity<Response> constructionExceptionHandle(ConstructionException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SourceException.class})
    public ResponseEntity<Response> sourceExceptionHandle(SourceException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ArchitecturalStyleException.class})
    public ResponseEntity<Response> architecturalStyleExceptionHandle(ArchitecturalStyleException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PhotoVisualTypeException.class})
    public ResponseEntity<Response> photoVisualTypeExceptionHandle(PhotoVisualTypeException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
