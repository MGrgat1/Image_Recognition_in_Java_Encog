import org.encog.Encog;
import org.encog.platformspecific.j2se.data.image.ImageMLData;
import org.encog.platformspecific.j2se.data.image.ImageMLDataSet;
import org.encog.util.downsample.SimpleIntensityDownsample;

import javax.imageio.ImageIO;
import java.awt.*;

public class Main {

    public static void main(final String[] args) {
        if (args.length < 1) {
            System.out
                    .println("Must specify command file.  See source for format.");
        } else {
            try {
                final ImageNeuralNetwork program = new ImageNeuralNetwork();
                program.execute(args[0]);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        Encog.getInstance().shutdown();
    }


}
