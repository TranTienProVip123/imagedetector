package org.sakaiproject.tool.imagedetector.controller;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

import org.sakaiproject.tool.imagedetector.controller.model.SearchMosModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@Slf4j
public class SearchMosController extends BaseController {

    @Value("${detection.api}")
    String detectionApiUrl;

    @GetMapping(value = "/searchmos/upload")
    public ModelAndView uploadImageRefresh() {
        return new ModelAndView("search-result");
    }

    @PostMapping(value = "/searchmos/upload")
    public ModelAndView uploadImage(@ModelAttribute("model") SearchMosModel model, BindingResult bindingResult) throws IOException {
        ModelAndView mav = new ModelAndView("home");

        MultipartFile file = model.getAttachment();
        String imageBase64 = Base64Utils.encodeToString(file.getBytes());
        mav.addObject("image", imageBase64);
        
        String responseJson = null;  

        log.debug(String.format("Connecting to %s ...", detectionApiUrl));

        try {
            MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
            bodyBuilder.part("file", file.getResource());

            WebClient webClient = WebClient.builder()
                    .baseUrl(detectionApiUrl)
                    .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024)) // 2MB
                    .build();


            mav.addObject("response", responseJson);

        } catch (Exception e) {
            log.error("Error while uploading image: ", e);
            mav.addObject("error", "An error occurred while processing the image.");
        }

        return mav;
    }
}
