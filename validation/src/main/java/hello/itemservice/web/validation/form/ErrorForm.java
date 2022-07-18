package hello.itemservice.web.validation.form;

import lombok.Data;
import org.springframework.http.HttpEntity;

@Data
public class ErrorForm {

    private String defaultMessage;
}
