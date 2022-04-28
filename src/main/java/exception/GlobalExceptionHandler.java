package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

//Обрабатывает исключения от всех контроллеров, которые прокидываются туда из сервиса
@ControllerAdvice
public class GlobalExceptionHandler {
    //Обработка NoSuchElementException и возврат стандартного сообщения об ошибке, но с кодом 404(not found), такого вида
    /*
                "timestamp": "2022-04-28T08:47:26.117+00:00",
                "status": 404,
                "error": "Not Found",
                "path": "/response/62e0822f-9a26-4d72-89b5-c9e71463f6cb"
     */
    //@ResponseStatus(HttpStatus.NOT_FOUND) - можно было сделать и так, но тогда будет просто 404 без подробностей
    @ExceptionHandler(NoSuchElementException.class)
    public void noSuchElementExceptionHandler(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());//Или можно сконструировать кастомный ResponseEntity<?>
    }

    //Если список ответов в БД пуст то вернём 204(no content), используется NullPointerException,
    // но можно использовать свои классы исключений
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NullPointerException.class)
    public void nullPointerExceptionHandler() {}
}
