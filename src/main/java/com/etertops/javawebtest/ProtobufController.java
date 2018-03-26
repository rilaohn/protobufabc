package com.etertops.javawebtest;

import com.etertops.protos.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Base64;

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

        for(byte b : person.toByteArray()){
            System.out.print(b);
        }

        return Base64.getEncoder().encode(person.toByteArray());
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void post(HttpServletRequest request, byte[] proto){
        try {
            InputStream is = request.getInputStream();
            Person.PersonMessage pm = Person.PersonMessage.parseFrom(proto);
            System.out.println("\n地址是： " + pm.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
