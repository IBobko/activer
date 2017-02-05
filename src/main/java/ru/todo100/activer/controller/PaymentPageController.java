package ru.todo100.activer.controller;

import com.yandex.money.api.net.clients.ApiClient;
import com.yandex.money.api.net.clients.DefaultApiClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/payment")
public class PaymentPageController {

    @RequestMapping("")
    public String index() {
        ApiClient client = new DefaultApiClient.Builder()
                .setClientId("your_client_id_here")
                .create();

        return client.getClientId();
    }
}
