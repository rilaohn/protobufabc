package com.etertops.javawebtest;

import com.etertops.protos.Person;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("proto")
public class ProtobufController {

    @RequestMapping("/get")
    public byte[] send(){
        Person.PersonMessage.Builder personBuilder = Person.PersonMessage.newBuilder();
        personBuilder.setId("test001");
        personBuilder.setName("user test");
        personBuilder.setSex("male");
        personBuilder.setAddress("湖南省长沙市岳麓区银盆岭岳麓大道绿地中央广场");
        personBuilder.setAge(18);
        personBuilder.setPhone("18816881688");
        Person.PersonMessage person = personBuilder.build();
        return Base64.getEncoder().encode(person.toByteArray());
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void post(String proto){
        try {
            Person.PersonMessage pm = Person.PersonMessage.parseFrom(Base64.getDecoder().decode(proto));
            System.out.println("id： " + pm.getId());
            System.out.println("name： " + pm.getName());
            System.out.println("sex： " + pm.getSex());
            System.out.println("address： " + pm.getAddress());
            System.out.println("age： " + pm.getAge());
            System.out.println("phone： " + pm.getPhone());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
