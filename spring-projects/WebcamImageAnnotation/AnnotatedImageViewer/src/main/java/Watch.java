import sun.management.FileSystem;
import sun.misc.BASE64Decoder;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.Watchable;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Watch {

    private static final Log logger = LogFactory.getLog(Watch.class);

    public static void main(String[] args) throws IOException {
        final Path path = FileSystems.getDefault().getPath("/tmp/annotated_images");
        try {
            final WatchService watchService = FileSystems.getDefault().newWatchService();
            WatchKey watchKey = path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY);

            while (true) {
                final WatchKey wk = watchService.take();
                //final WatchKey wk = watchService.poll(100, TimeUnit.MILLISECONDS);
                for (WatchEvent<?> event : wk.pollEvents()) {
                    final Path changed = (Path) event.context();
                    if (changed.endsWith("annotated.txt")) {
                        File file = new File("/tmp/annotated_images/annotated.txt");
                        FileReader fileReader = new FileReader(file);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String data = bufferedReader.readLine();

                        BufferedImage image = null;
                        byte[] imageByte;

                        BASE64Decoder decoder = new BASE64Decoder();
                        imageByte = decoder.decodeBuffer(data);
                        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                        image = ImageIO.read(bis);
                        bis.close();

                        File outputfile = new File("/tmp/annotated_images/annotated.jpg");
                        ImageIO.write(image, "jpg", outputfile);
                        logger.info("annotated.jpg generated");
                    }
                }
                boolean valid = wk.reset();
                if (!valid) {
                    System.out.println("Key has been unregistered");
                }
            }
        } catch (Throwable e) {
            System.out.println(e);
        }
    }
}
