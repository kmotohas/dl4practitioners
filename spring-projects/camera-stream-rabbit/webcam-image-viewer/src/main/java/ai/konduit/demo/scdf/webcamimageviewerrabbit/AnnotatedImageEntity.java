package ai.konduit.demo.scdf.webcamimageviewerrabbit;

import java.util.List;

public class AnnotatedImageEntity {
    private String image;
    private List<BoundingBox> bboxes;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<BoundingBox> getBboxes() {
        return bboxes;
    }

    public void setBboxes(List<BoundingBox> bboxes) {
        this.bboxes = bboxes;
    }
}
