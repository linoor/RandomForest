package RandomForestHOG.HOG;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid("b30f2d3b-3dd3-4928-8c2e-210cde6bac4c")
public class HOGParam {

    private HOGParam.BlockType blockType;
    private int binNumber;
    private int cellWidth;
    private int cellHeight;
    private int blockWidth;
    private int blockHeight;
    private int maskType;
    private int width;
    private int height;

    @objid("d2e76ede-7957-415b-a548-bb88abaab29a")
    public HOGParam(final BlockType blockType, final int binNumber, final int cellWidth, final int cellHeight, final int blockWidth, final int blockHeight, final int maskType,
                    int width,
                    int height) {
        this.blockType = blockType;
        this.binNumber = binNumber;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
        this.maskType = maskType;
        this.width = width;
        this.height = height;
    }

    @objid("b88524b1-694a-4bc3-898b-a62d0300bb85")
    public BlockType getBlockType() {
        return blockType;
    }

    @objid("b7c62ec1-a509-4fc4-be44-666546be5c62")
    public int getBinNumber() {
        return binNumber;
    }

    @objid("681f3204-c6bd-47af-8682-b4ad2bd1b0e6")
    public int getCellWidth() {
        return cellWidth;
    }

    @objid("1a3830ea-46ea-4e57-ace4-07b3adb5ca1e")
    public int getCellHeight() {
        return cellHeight;
    }

    @objid("5bce7051-7d4e-4695-aee9-e0af3a111261")
    public int getBlockWidth() {
        return blockWidth;
    }

    @objid("454dbf0a-1c3a-4f4d-b874-366c4c0aff83")
    public int getBlockHeight() {
        return blockHeight;
    }

    @objid("694ef3cb-17fa-4a08-8ab6-0222c2e41029")
    public int getMaskType() {
        return maskType;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public enum BlockType {
        RADIAL, RECTANGULAR
    }
}
