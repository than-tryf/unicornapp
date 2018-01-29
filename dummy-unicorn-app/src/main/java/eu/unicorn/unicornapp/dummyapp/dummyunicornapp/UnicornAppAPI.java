package eu.unicorn.unicornapp.dummyapp.dummyunicornapp;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UnicornAppAPI {

    @GetMapping(name = "whoami", path = "/whoami")
    @ResponseBody
    public Map<String, String> whoAmI(){
//        String containerId = null;
        Map<String, String> envVars =System.getenv();


//        containerId = System.getenv("CATASCOPIA_APP_ID");

        return envVars;
    }
}
