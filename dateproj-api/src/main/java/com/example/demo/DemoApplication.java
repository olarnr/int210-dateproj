package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@RestController
public class DemoApplication {
    public static void main(String[] args) {
      SpringApplication.run(DemoApplication.class, args);
    }
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
      return String.format("Hello %s!", name);
    }

    
    @GetMapping("/api/date")
    @ResponseBody
    public String date() {
	    return  new DateResponse().toString();
    }


    private class DateResponse {
        private String humanReadable;
        private String timeStamp;

        public DateResponse() {
            Date now = new Date();
            this.humanReadable = now.toString();
            this.timeStamp = Long.toString(now.getTime());
        }

        public String toString() {
            return String.format("{\"humanReadable\": \"%s\", \"timeStamp\": \"%s\"}", humanReadable, timeStamp);
        }
    }

}
