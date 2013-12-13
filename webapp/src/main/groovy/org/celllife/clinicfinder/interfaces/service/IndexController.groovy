package org.celllife.clinicfinder.interfaces.service

import org.celllife.clinicfinder.application.framework.restclient.RESTClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class IndexController {

    @Value('${external.base.url}')
    def String externalBaseUrl
	
	@Autowired
	RESTClient restClient

    @RequestMapping("/")
    def index(Model model) {
        getReports(model)
    }

    @RequestMapping(value="index", method = RequestMethod.GET)
    def getReports(Model model) {

        def reports = restClient.get("${externalBaseUrl}/service/reports")
        model.put("reports", reports)
        return "index";

    }

}
