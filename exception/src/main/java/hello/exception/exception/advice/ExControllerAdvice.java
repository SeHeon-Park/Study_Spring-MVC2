package hello.exception.exception.advice;

import hello.exception.api.ApiExceptionV2Controller;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice                                       // 대상을 지정하지 않으면 모든 컨트롤러에 적용
//@RestControllerAdvice(annotations = RestController.class) // 어노테이션 대상 지정
//@RestControllerAdvice("hello.exception.api")              // 패키지 대상 지정
//@RestControllerAdvice(assignableTypes = {ApiExceptionV2Controller.class})  // 직접 대상 지정

public class ExControllerAdvice {

    // 예외의 자식 클래스까지 모두 잡을 수 있음, 무조건 자세한게 운선순위 굿
    // @ExceptionHandler -> 1순위
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e){
        log.info("[exceptionHandler] ex", e);
        return new ErrorResult("bad", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<UserException> userExHandler(UserException e) {
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("user-ex", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    // 위에 @ExceptionHandler 처리 못한 오류들을 여기서 해결
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e){
        log.info("[exceptionHandler] ex", e);
        return new ErrorResult("EX", " 내부 오류");
    }
}
