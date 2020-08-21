package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.Element;
import cn.blatter.network.service.ElementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

@Slf4j
@RestController
public class ElementController {

    static String path = "src/main/resources/static/Elements/";

    @Autowired
    private ElementService elementService;

    @RequestMapping(value = "/getElements", method = RequestMethod.GET)
    public ServiceResponse list() {
        List<Element> elementList = elementService.findAll();
        return ServiceResponse.createBySuccess(elementList);
    }

    @PostMapping(value = "/setElement")
    public ServiceResponse setElement(@RequestBody Element element) {
        elementService.setElement(element.getId(),element.getName());
        return ServiceResponse.createBySuccess();
    }

    @PostMapping(value = "/deleteElement")
    public ServiceResponse deleteElement(@RequestBody Element element) {
        elementService.deleteElement(element.getId());
        return ServiceResponse.createBySuccess();
    }

    @PostMapping(value = "/addElement")
    public ServiceResponse addElement(@RequestBody Element element){
        try{
            File file = new File(path + element.getName() + ".svg");
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8");
            BufferedWriter br = new BufferedWriter(out);
            String str = element.getPath();
            br.write(str);
            br.flush();
            br.close();
            element.setPath("/Elements/" + element.getName() + ".svg");
        }catch (Exception e) {
            System.out.println(e);
        }
        elementService.addElement(element.getId(),element.getName(),element.getPath());
        return ServiceResponse.createBySuccess();
    }
}
