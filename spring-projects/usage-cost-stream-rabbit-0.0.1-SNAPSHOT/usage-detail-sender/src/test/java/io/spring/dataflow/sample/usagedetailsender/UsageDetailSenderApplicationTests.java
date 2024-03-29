package io.spring.dataflow.sample.usagedetailsender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.cloud.stream.messaging.Source;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsageDetailSenderApplicationTests {

	@Autowired
	private MessageCollector messageCollector;

	@Autowired
	private Source source;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testUsageDetailsSender() throws Exception {
		Message message = this.messageCollector.forChannel(this.source.output()).poll(1, TimeUnit.SECONDS);
		String usageDetailJSON = message.getPayload().toString();
		System.out.println(usageDetailJSON);
		assertTrue(usageDetailJSON.contains("userId"));
		assertTrue(usageDetailJSON.contains("duration"));
		assertTrue(usageDetailJSON.contains("data"));
	}

}
