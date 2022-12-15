package com.iapps.epapers.web.controller;

import com.iapps.epapers.service.EpaperService;
import com.iapps.epapers.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/epapers")
@RequiredArgsConstructor
public class EpaperController {

    private final EpaperService epaperService;
    private final Validator epaperRequestValidator;

    @PostMapping(consumes = MediaType.ALL_VALUE)
    public void saveRequest(@RequestParam("file") MultipartFile multipartFile)
            throws IOException, SAXException, ParserConfigurationException {
        File file = FileUtils.convertToFileAndSave(multipartFile);
        epaperRequestValidator.validate(new StreamSource(file));
        epaperService.parseAndSave(file);
    }

}
