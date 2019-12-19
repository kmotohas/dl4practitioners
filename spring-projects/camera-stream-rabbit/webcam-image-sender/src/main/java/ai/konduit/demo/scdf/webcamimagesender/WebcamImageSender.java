package ai.konduit.demo.scdf.webcamimagesender;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@EnableScheduling
@EnableBinding(Source.class)
@EnableConfigurationProperties({ WebcamImageSenderProperties.class })
public class WebcamImageSender {

    private static final Log logger = LogFactory.getLog(WebcamImageSenderApplication.class);

    @Autowired
    private Source source;

    @Autowired
    private WebcamImageSenderProperties properties;

    private OpenCVFrameGrabber grabber;

    @PostConstruct
    public void init() throws IOException {
        grabber = new OpenCVFrameGrabber(properties.getDeviceIndex());
        grabber.setImageWidth(properties.getWidth());
        grabber.setImageHeight(properties.getHeight());
        grabber.start();
        logger.info("Frame Grabber Started: " + grabber.toString());
    }

    @Scheduled(initialDelay = 1000, fixedRate = 1000)
    public void webcam() throws IOException {
        Frame frame = grabber.grab();
        BufferedImage image = Java2DFrameUtils.toBufferedImage(frame);
        String payload = new String( Base64.encodeBase64(imageToBytes(
                        resize(image, properties.getWidth(), properties.getHeight()))), StandardCharsets.US_ASCII);
        logger.debug(payload.substring(0, 80) + "...");
        this.source.output().send(MessageBuilder.withPayload(payload).build());
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
