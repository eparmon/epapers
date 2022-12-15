package com.iapps.epapers.service;

import com.iapps.epapers.persistence.domain.EpaperRequest;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public interface EpaperService {

    EpaperRequest parseAndSave(File epaperRequestXml) throws ParserConfigurationException, IOException, SAXException;

}
