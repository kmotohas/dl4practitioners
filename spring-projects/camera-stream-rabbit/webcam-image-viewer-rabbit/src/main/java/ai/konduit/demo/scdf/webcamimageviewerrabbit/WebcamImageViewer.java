package ai.konduit.demo.scdf.webcamimageviewerrabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class WebcamImageViewer {

    private static final Logger logger = LoggerFactory.getLogger(WebcamImageViewerApplication.class);

    @StreamListener(Sink.INPUT)
    public void process(AnnotatedImageEntity annotatedImageEntity) {

    }
}
