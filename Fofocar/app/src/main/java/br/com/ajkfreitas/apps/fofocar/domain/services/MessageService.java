package br.com.ajkfreitas.apps.fofocar.domain.services;

import java.util.List;

import br.com.ajkfreitas.apps.fofocar.domain.model.Message;
import br.com.ajkfreitas.apps.fofocar.util.RestClient;

/**
 * Created by Angelo Freitas on 14/12/2017.
 */

public class MessageService {
    private  static final String SERVICE_API = "https://service-api.herokuapp.com/statuses";

    private  static final String QUERY_API = SERVICE_API+"?1=1";

    private static final String ORDER_BY_CREATION_DESC = "&_sort=created_at&_order=DESC";

    private final RestClient rest = new RestClient();

    public List<Message> getLastStatuses() {
        return rest.getAll(QUERY_API + ORDER_BY_CREATION_DESC, Message[].class);
    }

    public Message publish(Message message) {
        return rest.post(SERVICE_API, message, Message.class);
    }
}
