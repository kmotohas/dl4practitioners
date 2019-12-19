package ai.konduit.demo.scdf.webcamimagesender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebcamImageSenderApplicationTests {

	@Autowired
	private MessageCollector messageCollector;

	@Autowired
	private Source source;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testWebcamImageSender() throws Exception {
		Message message = this.messageCollector.forChannel(this.source.output()).poll(2, TimeUnit.SECONDS);
		String imageEntityJSON = message.getPayload().toString();
		System.out.println(imageEntityJSON);
		//assertTrue(imageEntityJSON.contains("payload"));
	}

}
