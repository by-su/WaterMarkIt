package com.markit.api;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.awt.*;
import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * Watermark Service for applying watermarks to PDF files
 *
 * @author Oleg Cheban
 * @since 1.3.0
 */
public interface WatermarkPDFService {

    /**
     * Sets the text to be used as the watermark
     *
     * @param text The text for the watermark
     */
    TextBasedPDFWatermarkBuilder withText(String text);

    /**
     * Sets the image to be used as the watermark
     */
    WatermarkPDFBuilder withImage(byte[] image);

    /**
     * Text-based watermarks builder
     */
    interface TextBasedPDFWatermarkBuilder {

        /**
         * Sets the color of the text
         *
         * @param color The color for the watermark text
         * @see Color
         */
        TextBasedPDFWatermarkBuilder color(Color color);

        /**
         * Adds a trademark symbol to the text
         */
        TextBasedPDFWatermarkBuilder addTrademark();

        /**
         * Getting watermarks builder
         */
        WatermarkPDFBuilder watermark();
    }

    /**
     * The general pdf watermarks builder
     */
    interface WatermarkPDFBuilder {

        /**
         * Defines the method for adding a watermark (default is DRAW)
         *
         * @param watermarkingMethod The method to use for watermarking
         * @see WatermarkingMethod
         */
        WatermarkPDFBuilder method(WatermarkingMethod watermarkingMethod);

        /**
         * Sets the size of the watermark
         */
        WatermarkPDFBuilder size(int size);

        /**
         * Sets the opacity of the watermark
         */
        WatermarkPDFBuilder opacity(float opacity);

        /**
         * Sets the rotation of the watermark
         */
        WatermarkPDFBuilder rotation(int degree);

        /**
         * Defines the position of the watermark on the file
         *
         * @param watermarkPosition The position to place the watermark (e.g., CENTER, CORNER).
         * @see WatermarkPosition
         */
        WatermarkPositionStepPDFBuilder position(WatermarkPosition watermarkPosition);

        /**
         * Sets the dpi of the watermark
         */
        WatermarkPDFBuilder dpi(int dpi);

        /**
         * Enables or disables the watermark based on a specific condition
         *
         * @param condition: A boolean value that determines whether the watermark is enabled (true) or disabled (false)
         */
        WatermarkPDFBuilder when(boolean condition);

        /**
         * Adds a condition to filter the document when applying the watermark
         * Only documents that meet the condition will have the watermark applied
         *
         * @param predicate: A condition that takes a PDDocument as input and returns true/false
         * @see Predicate
         */
        WatermarkPDFBuilder documentFilter(Predicate<PDDocument> predicate);

        /**
         * Adds a condition to filter the page when applying the watermark
         * Only pages that meet the condition will have the watermark applied
         *
         * @param predicate A condition that takes a page number (Integer) as input and returns true/false. The page index starts from 0
         * @see Predicate
         */
        WatermarkPDFBuilder pageFilter(Predicate<Integer> predicate);

        /**
         * Adds another watermark configuration to the file
         *
         * @return A new instance of Watermark for configuring another watermark
         */
        WatermarkPDFService and();

        /**
         * Applies the watermark to the file and returns the result as a byte array
         *
         * @return A byte array representing the watermarked file
         */
        byte[] apply();

        /**
         * Applies a watermark to the file and returns the result as a file path
         * The method generates a watermarked file saved in the specified directory
         * and file name, and provides the file's path for further processing
         *
         * @param directoryPath The directory path where the watermarked file will be saved
         * @param fileName      The name of the watermarked file to be created
         * @return The {@link Path} representing the location of the saved watermarked file
         */
        Path apply(String directoryPath, String fileName);
    }

    /**
     * Interface for adjusting the position of watermarks
     */
    interface WatermarkPositionStepPDFBuilder extends WatermarkPDFBuilder {

        /**
         * Adjusts the position of the watermark relative to its default location
         *
         * @param x The horizontal offset in pixels
         * @param y The vertical offset in pixels
         */
        WatermarkPDFBuilder adjust(int x, int y);
    }
}