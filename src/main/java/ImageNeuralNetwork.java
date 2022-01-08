import org.encog.EncogError;
import org.encog.neural.networks.BasicNetwork;
import org.encog.platformspecific.j2se.data.image.ImageMLDataSet;
import org.encog.util.downsample.Downsample;
import org.encog.util.downsample.RGBDownsample;
import org.encog.util.downsample.SimpleIntensityDownsample;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageNeuralNetwork {

    private final List<ImagePair> imageList = new ArrayList();
    private final Map<String, String> args = new HashMap();
    private final Map<String, Integer> identity2neuron = new HashMap();
    private final Map<Integer, String> neuron2identity = new HashMap();
    private ImageMLDataSet training;
    private String line;
    private int outputCount;
    private int downsampleWidth;
    private int downsampleHeight;
    private BasicNetwork network;
    private Downsample downsample;

    /**
     * This consists of input-output pairs. The input is an image file and the output is the number
     * that the image represents and that the network is supposed to recognize.
     * For example, ("img1.jpg", 9) is an input-output pair that carries this
     * information: "img1 is an image of the number 9".
     */
    class ImagePair {
        private final File file;
        private final int identity;

        public ImagePair(final File file, final int identity) {
            super();
            this.file = file;
            this.identity = identity;
        }

        public File getFile() {
            return this.file;
        }

        public int getIdentity() {
            return this.identity;
        }
    }

    /**
     * An empty constructor
     */
    public ImageNeuralNetwork() {
    }

    /**
     * The application takes three arguments from the command line: width, height, and type (colorless, RBG)
     * @param name
     * @return
     */
    private String getArg(String name) {
        String result = (String)this.args.get(name);
        if (result == null) {
            throw new EncogError("Missing argument " + name + " on line: " + this.line);
        } else {
            return result;
        }
    }

    /**
     * Creates a training set from the downsampled images already present in the class
     */
    private void processCreateTraining() {
        String strWidth = this.getArg("width");
        String strHeight = this.getArg("height");
        String strType = this.getArg("type");
        this.downsampleHeight = Integer.parseInt(strHeight);
        this.downsampleWidth = Integer.parseInt(strWidth);
        if (strType.equals("RGB")) {
            this.downsample = new RGBDownsample();
        } else {
            this.downsample = new SimpleIntensityDownsample();
        }

        this.training = new ImageMLDataSet(this.downsample, false, 1.0D, -1.0D);
        System.out.println("Training set created");
    }

}
