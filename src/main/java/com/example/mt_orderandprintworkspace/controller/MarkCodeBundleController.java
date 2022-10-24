package com.example.mt_orderandprintworkspace.controller;

import com.example.mt_orderandprintworkspace.DTO.LabelDTO;
import com.example.mt_orderandprintworkspace.DTO.MarkCodeBundleDTO;
import com.example.mt_orderandprintworkspace.entity.MarkCodeBundle;
import com.example.mt_orderandprintworkspace.service.LabelPDFGenerator;
import com.example.mt_orderandprintworkspace.service.MarkCodeBundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/markCode/Print")
public class MarkCodeBundleController {
    private final MarkCodeBundleService markCodeBundleService;

    @Autowired
    public MarkCodeBundleController(MarkCodeBundleService markCodeBundleService) {
        this.markCodeBundleService = markCodeBundleService;
    }

    @GetMapping
    public String getAllBundlePage(Model model) {
        model.addAttribute("bundleDTOs", MarkCodeBundleDTO.convertFromBundleList(markCodeBundleService.getAllActive()));
        return "bundles";
    }

    @GetMapping(value =  "/print{id}.pdf", produces = "application/octet-stream")
    @ResponseBody
    public byte[] printBundle(@PathVariable long id) {
        // Отдача файла в ответ на запрос https://baeldung.com/spring-controller-return-image-file
        //https://stackoverflow.com/questions/5673260/downloading-a-file-from-spring-controllers
        //Вы можете добавить products = MediaType.APPLICATION_OCTET_STREAM_VALUE в @RequestMapping для принудительной загрузки–
        //Дэвид Каго 25 сент. 2013 г., 17:33

        return markCodeBundleService.getMarkCodeBundleAsPDFFile(id);
    }

//    @GetMapping("/hide/{id}")
//    public String hideBundle(@PathVariable String id) {
//        return "redirect:/markCode/Print";
//    }

}
