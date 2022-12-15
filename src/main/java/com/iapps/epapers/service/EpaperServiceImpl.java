package com.iapps.epapers.service;

import com.iapps.epapers.persistence.domain.EpaperRequest;
import com.iapps.epapers.persistence.repository.EpaperRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EpaperServiceImpl implements EpaperService {

    private final EpaperRequestRepository epaperRequestRepository;
    private final DocumentBuilderFactory documentBuilderFactory;

    private static final String NEWSPAPER_NAME = "newspaperName";
    private static final String SCREEN_INFO = "screenInfo";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DPI = "dpi";

    public EpaperRequest parseAndSave(File epaperRequestXml)
            throws ParserConfigurationException, IOException, SAXException {
        return epaperRequestRepository.save(parse(epaperRequestXml));
    }

    private EpaperRequest parse(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        document.getDocumentElement().normalize();
        return EpaperRequest.builder()
                .newspaperName(document.getElementsByTagName(NEWSPAPER_NAME).item(0).getTextContent())
                .screenWidth(Integer.parseInt(document.getElementsByTagName(SCREEN_INFO).item(0)
                        .getAttributes().getNamedItem(WIDTH).getNodeValue()))
                .screenHeight(Integer.parseInt(document.getElementsByTagName(SCREEN_INFO).item(0)
                        .getAttributes().getNamedItem(HEIGHT).getNodeValue()))
                .screenDpi(Integer.parseInt(document.getElementsByTagName(SCREEN_INFO).item(0)
                        .getAttributes().getNamedItem(DPI).getNodeValue()))
                .build();
    }

}
