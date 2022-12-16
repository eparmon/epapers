package com.iapps.epapers.web.converter;

import com.iapps.epapers.persistence.domain.EpaperRequest;
import com.iapps.epapers.web.dto.EpaperRequestDto;

public class EpaperRequestConverter {

    private EpaperRequestConverter() {
    }

    public static EpaperRequestDto toDto(EpaperRequest epaperRequest) {
        return new EpaperRequestDto(epaperRequest.getNewspaperName(), epaperRequest.getScreenWidth(),
                epaperRequest.getScreenHeight(), epaperRequest.getScreenDpi());
    }

}
