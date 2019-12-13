package ai.konduit.demo.scdf.webcamimagesender;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ImageEntity {
    @JsonProperty("payload")
    private String payload;
}
