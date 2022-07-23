package hello.exception.api;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {

//    // 예외의 자식 클래스까지 모두 잡을 수 있음, 무조건 자세한게 운선순위 굿
//    // @ExceptionHandler -> 1순위
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ErrorResult illegalExHandler(IllegalArgumentException e){
//        log.info("[exceptionHandler] ex", e);
//        return new ErrorResult("bad", e.getMessage());
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<UserException> userExHandler(UserException e) {
//        log.error("[exceptionHandler] ex", e);
//        ErrorResult errorResult = new ErrorResult("user-ex", e.getMessage());
//        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
//    }
//
//    // 위에 @ExceptionHandler 처리 못한 오류들을 여기서 해결
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler
//    public ErrorResult exHandler(Exception e){
//        log.info("[exceptionHandler] ex", e);
//        return new ErrorResult("EX", " 내부 오류");
//    }

    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id){
        if (id.equals("ex")){
            throw new RuntimeException("잘못된 사용자");
        }

        if (id.equals("bad")){
            throw new IllegalArgumentException("잘못된 입력 값");
        }

        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String memberId;
        private String name;
    }
}
