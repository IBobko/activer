package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.todo100.activer.payeer.service.PayeerService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/payeer")
public class PayeerController {
    private PayeerService payeerService;

    private PayeerService getPayeerService() {
        return payeerService;
    }

    @Autowired
    public void setPayeerService(PayeerService payeerService) {
        this.payeerService = payeerService;
    }

    @ResponseBody
    @RequestMapping("/success")
    public String successPage() {
        return "1";
    }


    @ResponseBody
    @RequestMapping("/fail")
    public String failedPage() {
        return "0";
    }

    /**
     * Обработчик платежей Payeer.
     *
     * @param params post parameters.
     * @return null
     */
    @ResponseBody
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public String handlerPage(@RequestParam final Map<String, String> params, final HttpServletRequest request) {
        final List<String> allowedIp = new ArrayList<>();
        allowedIp.add("185.71.65.92");
        allowedIp.add("185.71.65.189");
        allowedIp.add("149.202.17.210");

        if (!allowedIp.contains(request.getRemoteAddr())) {
            return null;
        }

        if (params.containsKey("m_operation_id") && params.containsKey("m_sign")) {
            final String key = getPayeerService().getKey();
            final List<String> arHash = new ArrayList<>();
            arHash.add(params.get("m_operation_id"));
            arHash.add(params.get("m_operation_ps"));
            arHash.add(params.get("m_operation_date"));
            arHash.add(params.get("m_operation_pay_date"));
            arHash.add(params.get("m_shop"));
            arHash.add(params.get("m_orderid"));
            arHash.add(params.get("m_amount"));
            arHash.add(params.get("m_curr"));
            arHash.add(params.get("m_desc"));
            arHash.add(params.get("m_status"));

            if (params.containsKey("m_params")) {
                arHash.add(params.get("m_params"));
            }
            arHash.add(key);
            final String hash = getPayeerService().getHash(arHash);
            if (params.get("m_sign").equals(hash) && params.get("m_status").equals("success")) {
                // Здесь нужно запустить процесс зачисления денег на счет.
                return null;
            }
        }
        return null;
    }
}
