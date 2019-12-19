package ai.konduit.demo.scdf.webcamimageviewerrabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.math.geometry.shape.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@EnableBinding(Sink.class)
public class WebcamImageViewer {

    private static final Logger logger = LoggerFactory.getLogger(WebcamImageViewerApplication.class);

    @Autowired
    private JFrame jFrame;

    @Autowired
    private DisplayUtilities.ImageComponent imageComponent;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Bean
    public JFrame frame(DisplayUtilities.ImageComponent imageComponent) {
        JFrame frame = DisplayUtilities.makeFrame("");
        frame.add(imageComponent);
        return frame;
    }

    @Bean
    public DisplayUtilities.ImageComponent imageComponent() {
        return new DisplayUtilities.ImageComponent();
    }

    //@PostConstruct
    //public void init() {
    //    //jFrame = frame(imageComponent);
    //    //jFrame.add(imageComponent);
    //    jFrame = DisplayUtilities.makeFrame("");
    //    jFrame.add(imageComponent);
    //}

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void handle(Message<?>message) throws JsonProcessingException, IOException {
        String script = (String) message.getPayload();
        logger.info(script.substring(0, 100) + "...");
        Root root = mapper.readValue(script, Root.class);
        AnnotatedImageEntity annotatedImageEntity = root.getRoot();
        String imageBase64 = annotatedImageEntity.getImage();
        List<BoundingBox> bboxes = annotatedImageEntity.getBboxes();
        byte[] imageBytes = Base64.decodeBase64(imageBase64);
        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        MBFImage image = ImageUtilities.readMBF(bais);
        for (BoundingBox bbox : bboxes) {
            int xmin = (int) bbox.getXmin();
            int ymin = (int) bbox.getYmin();
            int width = (int) bbox.getXmax() - xmin;
            int height = (int) bbox.getYmax() - ymin;
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
