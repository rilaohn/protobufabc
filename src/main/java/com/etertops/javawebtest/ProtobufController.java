package com.etertops.javawebtest;

import com.etertops.protos.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        System.out.println();
        return Base64.getEncoder().encode(person.toByteArray());
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void post(HttpServletRequest request, byte[] proto){
        try {
            InputStream is = request.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            StringBuffer buffer = new StringBuffer();
            String msg = null;
            while((msg=in.readLine()) != null){
                buffer.append(msg);
            }
            System.out.println("buffer" + buffer);
            StringBuffer sb = new StringBuffer();
            for (int i =0; i < proto.length; i++){
                System.out.print(proto[i]);
                sb.append(proto[i]);
            }
            System.out.println();
            System.out.println(sb.toString());
            Person.PersonMessage pm = Person.PersonMessage.parseFrom(proto);
            System.out.println("地址是： " + pm.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
