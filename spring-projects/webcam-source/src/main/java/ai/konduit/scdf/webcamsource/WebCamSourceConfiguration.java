package ai.konduit.scdf.webcamsource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.bytedeco.javacv.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
//import org.springframework.cloud.stream.reactive.StreamEmitter;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@EnableBinding(Source.class)
@EnableConfigurationProperties({ WebCamSourceProperties.class })
public class WebCamSourceConfiguration {

    private static final Log logger = LogFactory.getLog(WebCamSourceConfiguration.class);

    @Autowired
    private WebCamSourceProperties properties;

    @Autowired
    private FrameGrabber frameGrabber;

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "${webcam.captureInterval:1000}", maxMessagesPerPoll = "1"))
    public MessageSource<byte[]> myMessageSource() {
        return () -> {
            try {
                Frame frame = frameGrabber.grab();
                BufferedImage image = Java2DFrameUtils.toBufferedImage(frame);
                return MessageBuilder.withPayload(imageToBytes(resize(image, properties.getWidth(), properties.getHeight()))).build();
            } catch (IOException e) {
                logger.error("Failed to grab the frame or to convert the image", e);
            }
            return null;
        };
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

    @Bean
    public FrameGrabber frameGrabber(WebCamSourceProperties properties) throws IOException {
        FrameGrabber grabber;
        if (properties.getInputVideoUri() != null) {
            grabber = new FFmpegFrameGrabber(properties.getInputVideoUri().getInputStream());
        } else {
            //grabber = OpenCVFrameGrabber.createDefault(properties.getDeviceIndex());
            grabber = new OpenCVFrameGrabber(properties.getDeviceIndex());
        }
        grabber.setImageWidth(properties.getWidth());
        grabber.setImageHeight(properties.getHeight());
        grabber.start();
        logger.info("Frame Grabber Started: " + grabber.toString());
        return grabber;
    }

}
