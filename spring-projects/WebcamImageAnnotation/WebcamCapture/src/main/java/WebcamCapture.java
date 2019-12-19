import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.opencv_core.Mat;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static org.bytedeco.opencv.global.opencv_imgcodecs.*;

enum ImageType {
    RGB(1),
    ARGB(2),
    ARGB_PRE(3),
    BRG(4),
    BRG_3BYTE(5),
    BRG_4BYTE(6),
    BRG_PRE_4BYTE(7),
    USHORT_565_RGB(8),
    USHORT_555_RGB(9),
    BYTE_GRAY(10),
    USHORT_GRAY(11),
    BYTE_BINARY(12),
    BYTE_INDEXED(13);

    public final int typeId;

    ImageType(int typeId) {
        this.typeId = typeId;
    }
}

public class WebcamCapture {
    private static final Log logger = LogFactory.getLog(WebcamCapture.class);

    final private static int WEBCAM_DEVICE_INDEX = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        final int captureWidth = 640;
        final int captureHeight = 360;
        final int INTERVAL = 3000;

        // The available FrameGrabber classes include OpenCVFrameGrabber (opencv_videoio),
        // DC1394FrameGrabber, FlyCapture2FrameGrabber, OpenKinectFrameGrabber,
        // PS3EyeFrameGrabber, VideoInputFrameGrabber, and FFmpegFrameGrabber.
        final OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(WEBCAM_DEVICE_INDEX);
        grabber.setImageWidth(captureWidth);
        grabber.setImageHeight(captureHeight);
        grabber.start();
        logger.info("Frame Grabber Started: " + grabber.toString());

        Frame capturedFrame = null;

        OpenCVFrameConverter.ToMat toMat = new OpenCVFrameConverter.ToMat();

        while ((capturedFrame = grabber.grab()) != null) {
            Thread.sleep(INTERVAL);
            Mat capturedMat = toMat.convert(capturedFrame);
            //imwrite(String.format("/tmp/raw_images/image_%d.jpg", System.currentTimeMillis()), capturedMat);
            imwrite(String.format("/tmp/raw_images/image.jpg", System.currentTimeMillis()), capturedMat);
            //imwrite(String.format("/tmp/annotated_images/annotated.jpg", System.currentTimeMillis()), capturedMat);
        }
        grabber.stop();
    }
}
