package ai.konduit.demo.scdf.webcamimageloggerrabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class WebcamImageLogger {

    private static final Logger logger = LoggerFactory.getLogger(WebcamImageLoggerApplication.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @StreamListener(Sink.INPUT)
    public void process(ImageEntity imageEntity) throws JsonProcessingException {
        logger.info(mapper.writeValueAsString(imageEntity).substring(0, 100) + "...");
    }
}
