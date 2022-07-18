package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ErrorForm;
import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemAPiController {

    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult){
        log.info("API 컨트롤러 호출");

        if (bindingResult.hasErrors()){
            log.info("검증 오류 발생={}", bindingResult);
            log.info("{}", bindingResult.getFieldErrors().get(0).getDefaultMessage());
            ErrorForm errorForm = new ErrorForm();
            errorForm.setDefaultMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return errorForm;
        }

        log.info("성공 로직 실행");
        return form;
    }
}
