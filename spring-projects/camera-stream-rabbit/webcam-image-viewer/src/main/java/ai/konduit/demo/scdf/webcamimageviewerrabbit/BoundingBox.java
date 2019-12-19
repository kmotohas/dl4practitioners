package ai.konduit.demo.scdf.webcamimageviewerrabbit;

/**
 * input json example
 * {
 *   "root": {
 *     "image": "\<base64 encoded image\>",
 *     "bboxes":[
 *       {
 *         "xmin": 0,
 *         "xmax": 98,
 *         "ymin": 10,
 *         "ymax": 120,
 *         "label": "person",
 *         "conf": 0.9,
 *       },
 *       {
 *           ...
 *       }
 *     ]
 *   }
 * }
 */
public class BoundingBox {
    private int xmin;
    private int xmax;
    private int ymin;
    private int ymax;
    private String label;
    private Float conf;

    public int getXmin() {
        return xmin;
    }

    public void setXmin(int xmin) {
        this.xmin = xmin;
    }

    public int getXmax() {
        return xmax;
    }

    public void setXmax(int xmax) {
        this.xmax = xmax;
    }

    public int getYmin() {
        return ymin;
    }

    public void setYmin(int ymin) {
        this.ymin = ymin;
    }

    public int getYmax() {
        return ymax;
    }

    public void setYmax(int ymax) {
        this.ymax = ymax;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Float getConf() {
        return conf;
    }

    public void setConf(Float conf) {
        this.conf = conf;
    }
}
