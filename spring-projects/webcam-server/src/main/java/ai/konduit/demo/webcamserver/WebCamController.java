package ai.konduit.demo.webcamserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/webcam")
@ResponseBody
@EnableConfigurationProperties({ WebCamProperties.class })
public class WebCamController {

    private static final Log logger = LogFactory.getLog(WebCamController.class);
    @Autowired
    private WebCamProperties properties;

    private OpenCVFrameGrabber grabber;

    @PostConstruct
    public void frameGrabber() throws IOException {
        grabber = new OpenCVFrameGrabber(properties.getDeviceIndex());
        grabber.setImageWidth(properties.getWidth());
        grabber.setImageHeight(properties.getHeight());
        grabber.start();
        logger.info("Frame Grabber Started: " + grabber.toString());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ImageEntity> webcam() throws IOException {
        Frame frame = grabber.grab();
        BufferedImage image = Java2DFrameUtils.toBufferedImage(frame);
        String payload = new String(Base64.encodeBase64(imageToBytes(resize(image, properties.getWidth(), properties.getHeight()))), "ASCII");
        ImageEntity imageEntity = new ImageEntity(payload);
        logger.info(payload.substring(0, 80) + "...");
        return ResponseEntity.ok(imageEntity);
    }

    private byte[] imageToBytes(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        baos.flush();
        byte[] imageInBytes = baos.toByteArray();
        baos.close();
        return imageInBytes;
    }

    private BufferedImage resize(BufferedImage originalImage, int newWidth, int newHeight) {
        Image tmpImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, properties.getOutputImageType().typeId);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(tmpImage, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }
}
