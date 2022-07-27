/**
 * 
 */
package com.tally.chain.core;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Collections;

import com.tally.chain.utils.*;

/**
 * @author tuhin.sengupta
 *
 */
public class Block {
	
	private long timestamp;
	private String lastHash;
	private String hash;
	private long nonce;
	private int difficulty; 
	private IPayload data;
	
	public Block(long timestamp, String lastHash, String hash, long nonce, int difficulty, IPayload data) {
		super();
		this.timestamp = timestamp;
		this.lastHash = lastHash;
		this.hash = hash;
		this.nonce = nonce;
		this.difficulty = difficulty;
		this.data = data;
	}
	@Override
	public String toString() {
		return "Block - \n"
				+ "    Timestamp  = " + timestamp + "\n"
			    + "    Last Hash  = " + lastHash + "\n"
			    + "    Hash       = " + hash + "\n"
			    + "    Nonce      = " + nonce + "\n"
				+ "    Difficulty = " + difficulty + "\n"
			    + "    Data       = " + data;
	}
	
	public static Block genesis() throws NoSuchAlgorithmException {
		return new Block(Instant.now().toEpochMilli(),"", ChainUtils.hash("TallyChain-Genesis".getBytes()),0, ChainConfigs.DIFFICULTY, IPayload.genesisPayload());
	}
	
	public static Block mineBlock(Block lastBlock, IPayload data) throws NoSuchAlgorithmException
	{
		String lastHash = lastBlock.hash;
		long nonce = 0;
		int difficulty = lastBlock.difficulty;
		String hash = "";
		long timestamp;
		
		do {
			timestamp = Instant.now().toEpochMilli();
			difficulty = Block.adjustDifficulty(lastBlock, timestamp);
			hash = Block.hash(timestamp, lastHash, data, nonce, difficulty);
			nonce++;
		}while(!hash.substring(0,difficulty).equals(String.join("", Collections.nCopies(difficulty, "0"))));
		
		return new Block(timestamp, lastHash, hash, nonce, difficulty, data);
	}
	
	
	public static int adjustDifficulty(Block lastBlock, long  currentTime) {
		
		return lastBlock.timestamp+ChainConfigs.MINE_RATE > currentTime ? lastBlock.difficulty+1:lastBlock.difficulty-1;
	}
	
	private static String hash(long timestamp, String lastHash, IPayload data, long nonce, int difficulty) throws NoSuchAlgorithmException 
	{
		return ChainUtils.hash(String.join("|", Long.toString(timestamp), lastHash, data.serialize(), Long.toString(nonce), Integer.toString(difficulty)).getBytes());
	}
	public IPayload getData() {
		return data;
	}
	/**
	 * @return the lastHash
	 */
	public String getLastHash() {
		return lastHash;
	}
	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}
	/**
	 * @return the difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}
	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

}
