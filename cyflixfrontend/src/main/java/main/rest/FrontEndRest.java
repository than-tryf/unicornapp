package main.rest;

import org.apache.catalina.Executor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class FrontEndRest {


    private static final ExecutorService executors = Executors.newFixedThreadPool(4);
    @GetMapping(name = "home", path = "/home")
    @ResponseBody
    public String homepage(){
        for(long i=0; i<4; i++){

         executors.execute(new Task());
        }
        return "Hello World!";
    }
}

class Task implements Runnable{

    public void run() {
        for(long i=10; i<230L; i++){

            double x = Math.tan(Math.tan(Math.tan(i)));

        }

    }
}
