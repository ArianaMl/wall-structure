import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    private List blocks;

    public Wall(List blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional findBlockByColor(String color) {

        List blocksHelper = new ArrayList<>(blocks);

        for (Object block : blocksHelper) {

            if (!isInstanceOfCompositeBlock(block)) {
                Block singleBlock = (Block) block;
                if (singleBlock.getColor().equals(color)) {
                    return Optional.of(singleBlock);
                }
            } else {
                blocksHelper.addAll(((CompositeBlock) block).getBlocks());
            }
        }
        return Optional.empty();
    }

    @Override
    public List findBlocksByMaterial(String material) {

        List<Block> blocksByMaterial = new ArrayList<>();
        List<Object> blocksHelper = new ArrayList<>(blocks);


        for (Object block : blocksHelper) {

            if (!isInstanceOfCompositeBlock(block)) {
                Block singleBlock = (Block) block;
                if (singleBlock.getMaterial().equals(material)) {
                    blocksByMaterial.add(singleBlock);
                }
            }
            else {
                blocksHelper.addAll(((CompositeBlock) block).getBlocks());
            }
        }
        return blocksByMaterial;
    }

    @Override
    public int count() {
        List<Object> blocksHelper = new ArrayList<>(blocks);
        int i = 0;
        for (Object block : blocksHelper) {
            if (!isInstanceOfCompositeBlock(block)) {
                i++;
            } else {
                blocksHelper.addAll(((CompositeBlock) block).getBlocks());
            }
        }
        return i;
    }

    private boolean isInstanceOfCompositeBlock(Object o) {
        return o instanceof CompositeBlock;
    }
}
