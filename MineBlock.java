package blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MineBlock {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;
    private int zeroNumber = 0;

    MineBlock(int zeroNumber) {
        this.zeroNumber = zeroNumber;
    }

    public Block getNextBlock(Block preBlock) throws ExecutionException, InterruptedException {
        ArrayList<Block> blocks = new ArrayList<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        List<Future<CreateHash>> futureList = new ArrayList<>();
        var callableList = new ArrayList<Callable<CreateHash>>();
        for (int i = 0; i < 10; i++) callableList.add(new MinerCallable("" + i, zeroNumber));
        CreateHash result = executor.invokeAny(callableList);
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        int id = preBlock == null? 0 : preBlock.getId()+1;
        String preHash = preBlock == null? "0" : preBlock.getHash();
        Block block = new Block(result.getMiner()
                ,id
                , preHash
                , result.getHash()
                , ""
                , result.getMagicNumber()
                , result.getCostTime());
        return block;
    }

    public void setZeroNumber(int zeroNumber) {
        this.zeroNumber = zeroNumber;
    }

    public int getZeroNumber() {
        return zeroNumber;
    }

    public void increase() {
        this.zeroNumber++;
    }
    public void decrease() {
        this.zeroNumber--;
        zeroNumber = zeroNumber < 0? 0 : zeroNumber;
    }
}
