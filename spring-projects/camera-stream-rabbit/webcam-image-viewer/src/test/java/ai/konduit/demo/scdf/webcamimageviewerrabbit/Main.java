package ai.konduit.demo.scdf.webcamimageviewerrabbit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.math.geometry.shape.Rectangle;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class Main {

    private static final Log logger = LogFactory.getLog(Main.class);

    public static void main(String[] args) throws IOException {

        JFrame jFrame;
        DisplayUtilities.ImageComponent imageComponent = new DisplayUtilities.ImageComponent();
        jFrame = DisplayUtilities.makeFrame("test frame");
        jFrame.add(imageComponent);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        node = mapper.readTree(new File(Resources.getResource("test.json").getPath()));
        String imageBase64 = node.get("image").asText();
        byte[] imageBytes = Base64.decodeBase64(imageBase64);
        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        MBFImage image = ImageUtilities.readMBF(bais);
        //image.drawShape(new Ellipse(10, 100, 100, 100, 10) ,RGBColour.RED);
        //image.drawShape(new Rectangle(10, 10, 100, 100), RGBColour.RED);
        JsonNode bboxesNode = node.get("bboxes");
        for (int i = 0; i < bboxesNode.size(); i++) {
            int xmin = bboxesNode.get(i).get("xmin").asInt();
            int ymin = bboxesNode.get(i).get("ymin").asInt();
            int width = bboxesNode.get(i).get("xmax").asInt() - xmin;
            int height = bboxesNode.get(i).get("ymax").asInt() - ymin;
            image.drawShape(new Rectangle(xmin, ymin, width, height), RGBColour.RED);
        }
        BufferedImage bi = ImageUtilities.createBufferedImageForDisplay(image);
        imageComponent.setImage(bi);
        imageComponent.setOriginalImage(image);
        imageComponent.setSize(bi.getWidth(), bi.getHeight());
        imageComponent.setPreferredSize(new Dimension(imageComponent.getWidth(), imageComponent.getHeight()));

        jFrame.pack();
        jFrame.setVisible(true);
    }
}


