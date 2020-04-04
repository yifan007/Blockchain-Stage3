package blockchain;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

public class MinerCallable implements Callable {
    private int zeroNumber;
    private String miner;
    MinerCallable(String miner, int zeroNumber) {
        this.zeroNumber = zeroNumber;
        this.miner = miner;
    }
    public CreateHash call() throws Exception {
        Instant start = Instant.now();
        Thread.sleep(1000);
            CreateHash createHash = new CreateHash();
            createHash.GenerateHash(zeroNumber);
            createHash.setMiner(miner);
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toSeconds();
        createHash.setCostTime((int) timeElapsed);
        return createHash;
    }



}
