/*
 * The MIT License
 *
 * Copyright 2025 Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.guisso.jankenpon;

import java.awt.RenderingHints;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.gvt.renderer.ImageRenderer;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.*;

/**
 * Utility methods
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.2
 * @since 0.1, Jul 23, 2025
 */
public class Util {

    public static Icon loadImage(
            String imagePath,
            float heightSize,
            int consecutiveVictories) {

        if (imagePath == null || heightSize == 0) {
            return null;
        }

        byte[] imageData = null;

        try {
            InputStream svgStream
                    = Util.class.getResourceAsStream(imagePath);

            SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(
                    XMLResourceDescriptor.getXMLParserClassName());

            Document svgDocument = factory.createDocument(null, svgStream);

            // Hide stars
            switch (consecutiveVictories) {
                case 0:
                    Element aboveStar = svgDocument.getElementById("three_victories");
                    aboveStar.setAttribute("visibility", "hidden");
                case 1:
                    Element bottomStar = svgDocument.getElementById("two_victories");
                    bottomStar.setAttribute("visibility", "hidden");
            }

//            PNGTranscoder transcoder = new PNGTranscoder();
            AntialiasedPNGTranscoder transcoder = new AntialiasedPNGTranscoder();

            transcoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, heightSize);

            TranscoderInput input = new TranscoderInput(svgDocument);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            transcoder.transcode(input, new TranscoderOutput(output));

            imageData = output.toByteArray();

        } catch (IOException | TranscoderException ex) {
            System.getLogger(Result.class.getName())
                    .log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return new ImageIcon(imageData);
    }

    /**
     * Antialiased PNG Transcoder
     */
    private static class AntialiasedPNGTranscoder
            extends PNGTranscoder {

        @Override
        protected ImageRenderer createRenderer() {
            ImageRenderer renderer = super.createRenderer();

            RenderingHints myHints = renderer.getRenderingHints();
            myHints.put(
                    RenderingHints.KEY_RESOLUTION_VARIANT,
                    RenderingHints.VALUE_RESOLUTION_VARIANT_DPI_FIT);
            myHints.put(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            renderer.setRenderingHints(myHints);

            return renderer;
        }
    }
}
