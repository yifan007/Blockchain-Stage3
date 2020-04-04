package blockchain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ArrayList<Block> blocks = new ArrayList<>();
        MineBlock mineBlock = new MineBlock(0);
        for (int i = 0; i < 5; i++) {
            Block proBlock = i == 0 ? null : blocks.get(i - 1);
            Block block = mineBlock.getNextBlock(proBlock);
            String str = block.toString();
            if (block.getCostTime() < 10) {
                mineBlock.increase();
                str = str + "N was increased to " + mineBlock.getZeroNumber() + "\n";
            } else if (block.getCostTime() < 60) {
                str = str + "N stays the same\n";
            } else if (block.getCostTime() >= 60) {
                mineBlock.decrease();
                str = str + "N was decreased to " + mineBlock.getZeroNumber() + "\n";
            }
            blocks.add(block);
            System.out.println(str);

    }
    }
}
