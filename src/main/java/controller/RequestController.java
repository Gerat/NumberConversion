package controller;

import entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.RequestResponseService;

@RestController
@RequestMapping(path = "/")
public class RequestController {
    private final RequestResponseService requestResponseService;

    @Autowired
    public RequestController(RequestResponseService requestResponseService) {
        this.requestResponseService = requestResponseService;
    }

    //Если параметр number не будет передан, или некорректен сервис вернёт 400(bad request), не требуется какая то доп.
    // обработка ошибок
    @GetMapping(path = "request", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response acceptRequest(@RequestParam(name = "number") int number) {
        return requestResponseService.processRequest(number);
    }
}
