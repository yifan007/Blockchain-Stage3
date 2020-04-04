package blockchain;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateHash {
    private String hash;
    private int costTime;
    private long magicNumber;
    private String miner;

    private boolean isHash(int zeroNumber, String hash) {
        if (zeroNumber <0 && "".equals(hash) )
            return false;
        String regx = String.format("\\b0{%d,}", zeroNumber);
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(hash);
        return matcher.find();
    }

    public void GenerateHash(int zeroNumber) {
        long startTime = System.currentTimeMillis(); //begin time
        boolean loopFlg = true;
        long magicNumber = 0;
        String hash = "";
        Random random = new Random();
        while (loopFlg) {
            magicNumber = Math.abs( random.nextLong() );
            hash = StringUtil.applySha256(Long.toString(magicNumber) );
            loopFlg = ! isHash(zeroNumber, hash);
        }
        long endTime = System.currentTimeMillis(); //end time
        this.hash = hash;
        this.costTime =(int) (endTime - startTime)/1000;
        this.magicNumber = magicNumber;
    }


    public String getHash() {
        return hash;
    }

    public long getMagicNumber() {
        return magicNumber;
    }

    public int getCostTime() {
        return costTime;
    }

    public void setMiner(String miner) {
        this.miner = miner;
    }

    public void setCostTime(int costTime) {
        this.costTime = costTime;
    }

    public String getMiner() {
        return miner;
    }
}
