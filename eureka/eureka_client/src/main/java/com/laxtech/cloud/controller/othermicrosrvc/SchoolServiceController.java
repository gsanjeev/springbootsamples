package com.laxtech.cloud.controller.othermicrosrvc;

import com.laxtech.cloud.controller.Student;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class SchoolServiceController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/other_microservice/getSchoolDetails/{schoolname}", method = RequestMethod.GET)
    public String getStudents(@PathVariable String schoolname)
    {
        System.out.println("Getting School details for " + schoolname);
//here we are using name of service "student-service"
        String response = restTemplate.exchange("http://student-service/getStudentDetailsForSchool/{schoolname}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, schoolname).getBody();

        System.out.println("Response Received as " + response);

        return "School Name -  " + schoolname + " \n Student Details " + response;
    }

    @Autowired
    private EurekaClient discoveryClient;

    //following method is not working need more research
    @RequestMapping(value = "/other_microservice/retrieveSchoolDetails/{schoolname}", method = RequestMethod.GET)
    public String retrieveStudents(@PathVariable String schoolname)
    {
        System.out.println("Retrieving School details for " + schoolname);

        //InstanceInfo studentService = discoveryClient.getNextServerFromEureka("student-service", false);
        Application studentService = discoveryClient.getApplication("student-service");

        InstanceInfo instanceInfo = studentService.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" +
                "/student-service/getStudentDetailsForSchool/" + schoolname;
/*        String response = restTemplate.exchange("http://" + instanceInfo.getIPAddr()  + ":" + instanceInfo.getPort()
                        +  "/student-service/getStudentDetailsForSchool/{schoolname}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, schoolname).getBody();*/
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>(){});
        List<Student> students = response.getBody();
        System.out.println("Response Received as " + students);

        return "School Name -  " + schoolname + " \n Student Details " + students;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}