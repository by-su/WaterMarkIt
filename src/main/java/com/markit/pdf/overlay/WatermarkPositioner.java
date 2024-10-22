package com.markit.pdf.overlay;

import com.markit.api.WatermarkPosition;
import com.markit.api.WatermarkPositionCoordinates;

import java.util.List;

/**
 * @author Oleg Cheban
 * @since 1.0
 */
public class WatermarkPositioner {
    public List<WatermarkPositionCoordinates.Coordinates> defineXY(
            WatermarkPosition position,
            int imageWidth,
            int imageHeight,
            int watermarkWidth,
            int watermarkHeight) {
        return new OverlayMethodPositionCoordinates(
                imageWidth,
                imageHeight,
                watermarkWidth,
                watermarkHeight).getCoordinatesForPosition(position);
    }
}