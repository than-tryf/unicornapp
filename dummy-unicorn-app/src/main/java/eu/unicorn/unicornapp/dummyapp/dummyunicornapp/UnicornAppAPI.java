package eu.unicorn.unicornapp.dummyapp.dummyunicornapp;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class UnicornAppAPI {

    @GetMapping(name = "whoami", path = "/whoami")
    @ResponseBody
    public Map<String, String> whoAmI(){

        Map<String, String> containerId = new HashMap<String, String>();
        Map<String, String> contEnv = System.getenv();
        containerId.putAll(contEnv);

        //This is the container ID as well
        String hostname = System.getenv("HOSTNAME");

        List<String> list = new ArrayList<>();

        try {


            /*

            Sample /etc/hosts file:

                127.0.0.1	localhost
                ::1	localhost ip6-localhost ip6-loopback
                fe00::0	ip6-localnet
                ff00::0	ip6-mcastprefix
                ff02::1	ip6-allnodes
                ff02::2	ip6-allrouters
                172.18.0.2	77ea63ae7466   <----- We are interested about this line that contains the private IP of the container on the specific network.

             */
            Stream<String> stream = Files.lines(Paths.get("/etc/hosts"));
            list = stream
                    .filter(line -> line.contains(hostname))
                    .map(line -> {
                        String s = line.split("\\t")[0];
                        return s;
                    })
                    .collect(Collectors.toList());
            if (!list.isEmpty()){
                //We assume that there is a unique line within /etc/hosts file that contains the hostname
                //and from this line we can extract the container private IPv4.
                containerId.put("CONTAINER_IP", list.get(0));
            }
        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            return containerId;
        }

    }
}
