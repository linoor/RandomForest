package RandomForestHOG.HOG;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import fr.ensmp.caor.levis.sample.Sample;

@objid ("77b78d4d-6afc-4bc3-b438-316a59622fd8")
public class HOG extends Sample {
    private HOGParam.BlockType blockType;
    private int binNumber;
    private int cellWidth;
    private int cellHeight;
    private int blockWidth;
    private int blockHeight;
    private int maskType;

    @objid ("bbea47d8-6f43-4ae3-b718-68a8765253c7")
    float[] _histogram() {
        return null;
    }

    @objid ("df0f8cc0-0cd7-4008-8922-9072a1b1d431")
    float[] _gradient() {
        return null;
    }

    @objid ("b0d3e5ab-4f32-471f-9980-8c55eea662ae")
    float[] _featureVector() {
        return null;
    }

    @objid ("bf993b00-b60a-4247-890d-9b63a2e630c9")
    HOGParam hogParam() {
        return null;
    }

    @objid ("cdef89ff-5d7d-463b-9e97-66e1ad90a707")
    public void globalNormalization() {
    	System.out.println("globalNormalization!");
    }

    @objid ("09490b81-fd8d-4de9-83c8-633b011221dd")
    public void calGradient() {
    	System.out.println("calGradient!");
    }

    @objid ("80f5b195-3530-44da-9c12-1018076aaabc")
    public void calHistogram() {
    }

    @objid ("73bca2a7-bea4-4a42-be6b-2bd8f5bde8f2")
    public void blockNormalization() {
    }

    @objid ("1102ccb7-f224-4643-856a-10438306eebc")
    public void calFeatureVector() {
    }

    @objid ("8c39ea43-b153-44c8-a0de-ff4ed52b57c0")
    public HOG() throws IOException {
        super(null, null, 0);
    }

    @objid ("3a55e97d-ae30-433e-b3cd-9ccecda51f65")
    public HOG(final HOGParam initHogParam) throws IOException {
        super(null, null, 0);
    }

    @objid ("03255d6c-46ed-478f-b835-efa132d60bac")
    public List<Float> getHistogram() {
        // TODO Auto-generated return
        return new ArrayList<Float>();
    }

    @objid ("379e73c3-44a1-422b-a1ac-cf037cad6713")
    public List<Float> getGradient() {
        // TODO Auto-generated return
        return new ArrayList<Float>();
    }

    @objid ("edccfe92-5ff2-4f94-ab89-bcf050cd60b0")
    public List<Float> getFeatureVector() {
        // TODO Auto-generated return
        return new ArrayList<Float>();
    }

    public HOGParam.BlockType getBlockType() {
        return blockType;
    }

    public int getBinNumber() {
        return binNumber;
    }

    public int cellWidth() {
        return 0;
    }

    public int cellHeight() {
        return 0;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public int getBlockWidth() {
        return blockWidth;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public int getMaskType() {
        return maskType;
    }
}
