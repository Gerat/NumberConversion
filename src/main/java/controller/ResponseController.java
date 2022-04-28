package controller;

import entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.RequestResponseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/")
public class ResponseController {
    private final RequestResponseService requestResponseService;

    @Autowired
    public ResponseController(RequestResponseService requestResponseService) {
        this.requestResponseService = requestResponseService;
    }

    //Если ничего нет, вернёт 204(no content)
    @GetMapping(path = "response", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Response> getAllResponses() {
        return requestResponseService.findAllResponses();
    }

    //А вот здесь может не найтись элемент, тогда сервис бросит NoSuchElementException, который будет пойман
    // GlobalExceptionHandler'ом и клиенту вернётся 404
    @GetMapping(path = "response/{response_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Response getResponse(@PathVariable(name = "response_id") UUID responseId) {
        return requestResponseService.findResponseById(responseId);
    }
}
