package com.iapps.epapers.web.controller;

import com.iapps.epapers.persistence.predicate.EpaperRequestPredicateBuilder;
import com.iapps.epapers.service.EpaperService;
import com.iapps.epapers.utils.FileUtils;
import com.iapps.epapers.web.converter.EpaperRequestConverter;
import com.iapps.epapers.web.dto.EpaperRequestDto;
import com.iapps.epapers.web.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public PageDto<EpaperRequestDto> getRequests(@RequestParam(name = "newspaper_name", required = false) String newspaperName,
                                                 @RequestParam(name = "screen_width", required = false) Integer screenWidth,
                                                 @RequestParam(name = "screen_height", required = false) Integer screenHeight,
                                                 @RequestParam(name = "screen_dpi", required = false) Integer screenDpi,
                                                 @RequestParam(required = false) Integer page,
                                                 @RequestParam(required = false) Integer size,
                                                 @RequestParam(name = "sort_column", required = false) String sortColumn,
                                                 @RequestParam(name = "sort_direction", required = false) Sort.Direction sortDirection) {
        Sort sort = getSort(sortColumn, sortDirection, "createdAt", Sort.Direction.DESC);
        return new PageDto<>(epaperService.getRequests(new EpaperRequestPredicateBuilder()
                                .withNewspaperName(newspaperName)
                                .withScreenWidth(screenWidth)
                                .withScreenHeight(screenHeight)
                                .withScreenDpi(screenDpi),
                        PageRequest.of(page == null ? 0 : page, size == null ? PageDto.DEFAULT_PAGE_SIZE : size, sort))
                .map(EpaperRequestConverter::toDto));
    }

    public static Sort getSort(String sortColumn,
                               Sort.Direction sortDirection,
                               String defaultSortColumn,
                               Sort.Direction defaultSortDirection) {
        if (sortColumn != null) {
            if (sortDirection != null) {
                return Sort.by(sortDirection, sortColumn);
            } else {
                return Sort.by(defaultSortDirection, sortColumn);
            }
        } else {
            return Sort.by(defaultSortDirection, defaultSortColumn);
        }
    }

}
