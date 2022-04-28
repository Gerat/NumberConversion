package service;

import com.ibm.icu.text.RuleBasedNumberFormat;
import entity.Request;
import entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RequestRepository;
import repository.ResponseRepository;

import javax.transaction.Transactional;
import java.util.*;


@Service
public class RequestResponseService {
    private final RequestRepository requestRepository;
    private final ResponseRepository responseRepository;

    @Autowired
    public RequestResponseService(RequestRepository requestRepository, ResponseRepository responseRepository) {
        this.requestRepository = requestRepository;
        this.responseRepository = responseRepository;
    }

    @Transactional
    public Response processRequest(int number) {
        /*UUID - 128 битное целое число, и генерация 2-х одинаковых почти невозможна, однако лучше проверить новый id
        на уникальность, и если не уникальный сгенерировать новый, может быть даже не один раз, поэтому в цикле*/
        UUID requestId = UUID.randomUUID();
        while(requestRepository.existsById(requestId)) {
            requestId = UUID.randomUUID();
        }
        Request request = new Request();
        request.setId(requestId);
        request.setNumber(number);

        UUID responseId = UUID.randomUUID();
        while(requestRepository.existsById(responseId)) {
            responseId = UUID.randomUUID();
        }
        Response response = new Response();
        response.setId(responseId);
        String stringPresentation = intToStringPresentation(number);
        response.setStringPresentation(stringPresentation);
        response.setNumber(number);

        request.setResponse(response);
        response.setRequest(request);

        requestRepository.save(request);
        responseRepository.save(response);

        return response;
    }

    @Transactional//если возникнет исключение, транзакция откатится
    public List<Response> findAllResponses() {
        List<Response> res = responseRepository.findAll();
        if(!res.isEmpty()) {
            return res;
        }
        throw new NullPointerException();
    }

    @Transactional//если возникнет исключение, транзакция откатится
    public Response findResponseById(UUID id) {
        Optional<Response> res = responseRepository.findById(id);
        if(res.isPresent()) {
            return res.get();
        }
        throw new NoSuchElementException();
    }

    private String intToStringPresentation(int number) {
        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);
        return nf.format(number);
    }
}
