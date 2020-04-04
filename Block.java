package blockchain;

import java.util.Date;

public class Block {
    private int id;
    private long timeStamp;
    private long magicNumber;
    private String preHash;
    private String hash;
    private String msg;
    private int costTime;
    private String miner;

    public Block(String miner, int id, String preHash, String hash, String msg, long magicNumber, int costTime) {
        this.miner = miner;
        this.id = id;
        this.preHash = preHash;
        this.hash = hash;
        this.msg = msg;
        this.timeStamp = new Date().getTime();
        this.magicNumber = magicNumber;
        this.costTime = costTime;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getPreHash() {
        return preHash;
    }

    public int getId() {
        return id;
    }

    public long getMagicNumber() {
        return magicNumber;
    }

    public String getMsg() {
        return msg;
    }

    public String getHash() {
        return hash;
    }

    public int getCostTime() {
        return costTime;
    }
    public String toString() {
        String str = "Block:\n"
                + "Created by miner # " + miner + "\n"
                +"Id: "+ id + "\n"
                +"Timestamp: "+timeStamp+"\n"
                +"Magic number: "+magicNumber+"\n"
                +"Hash of the previous block:\n"+preHash+"\n"
                +"Hash of the block:\n"+hash+"\n"
                +"Block was generating for "+costTime+" seconds\n";
        return str;
    }
}
