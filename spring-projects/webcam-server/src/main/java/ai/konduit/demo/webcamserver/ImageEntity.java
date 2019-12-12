package ai.konduit.demo.webcamserver;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Setter
@Getter
@AllArgsConstructor
public class ImageEntity {
    @JsonProperty("payload")
    private String payload;
}
