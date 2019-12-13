package ai.konduit.demo.scdf.webcamimageloggerrabbit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageEntity {
    @JsonProperty("payload")
    private String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "ImageEntity{" +
                "payload='" + payload + '\'' +
                '}';
    }
}
