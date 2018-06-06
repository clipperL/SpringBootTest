package com.clipperl.springboottest.controller;

import com.clipperl.springboottest.dao.DeveloperDAO;
import com.clipperl.springboottest.model.CommonModel;
import com.clipperl.springboottest.model.DeveloperModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/developer")
public class DeveloperController {
    private DeveloperDAO developerDAO;

    @Autowired
    DeveloperController(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
    }

    @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/api/developers", method = RequestMethod.GET)
    @ResponseBody//通过@ResponseBody返回json数据，通过@ResquestBody解析json
    public CommonModel getAllDevelopers() {
        List<DeveloperModel> developerList = developerDAO.getAllDevelopers();
        CommonModel commonModel = new CommonModel();
        if (developerList.size() > 0) {
            commonModel.setSuccess();
            commonModel.setData(developerList);
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    @RequestMapping(value = "/api/developer", method = RequestMethod.GET)
    @ResponseBody
    public CommonModel getDeveloper(String id) {
        //自动匹配参数
        System.out.println("deveoperId = " + id);

        DeveloperModel developerModel = developerDAO.getDeveloper(id);
        CommonModel commonModel = new CommonModel();
        if (developerModel != null) {
            commonModel.setSuccess();
            commonModel.setData(developerModel);
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    @RequestMapping(value = "/api/developer/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonModel addDeveloper(String name, String site, String avatar) {
        //自动匹配参数
        System.out.println("name = " + name);
        DeveloperModel developerModel = new DeveloperModel();
        developerModel.setName(name);
        developerModel.setSite(site);
        developerModel.setAvatar(avatar);
        CommonModel commonModel = new CommonModel();
        if (developerDAO.addDeveloper(developerModel)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    @RequestMapping(value = "/api/developer/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonModel updateDeveloper(String id, String name) {
        //自动匹配参数
        System.out.println("name = " + name);
        CommonModel commonModel = new CommonModel();
        if (developerDAO.updateDeveloper(id, name)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    //Spring MVC 不支持 put, delete 方法传参，这里用到了 PathVariable
    @RequestMapping(value = "/api/developer/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonModel deleteDeveloper(@PathVariable("id") String id) {
        //id 对应 {id}
        System.out.println("developerId = " + id);
        CommonModel commonModel = new CommonModel();

        if (developerDAO.deleteDeveloper(id)) {
            commonModel.setSuccess();
        }else{
            commonModel.setFail();
        }
        return commonModel;
    }
}
