/**
 * 
 */
package com.tally.chain.core;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;

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
	private IPayload Data;
	public Block(long timestamp, String lastHash, String hash, int nonce, int difficulty, IPayload data) {
		super();
		this.timestamp = timestamp;
		this.lastHash = lastHash;
		this.hash = hash;
		this.nonce = nonce;
		this.difficulty = difficulty;
		Data = data;
	}
	@Override
	public String toString() {
		return "Block - \n"
				+ "    Timestamp  = " + timestamp + "\n"
			    + "    Last Hash  = " + lastHash + "\n"
			    + "    Hash       = " + hash + "\n"
			    + "    Nonce      = " + nonce + "\n"
				+ "    Difficulty = " + difficulty + "\n"
			    + "    Data       = " + Data;
	}
	
	public static Block genesis() throws NoSuchAlgorithmException {
		return new Block(Instant.now().toEpochMilli(),"", ChainUtils.hash("TallyChain-Genesis".getBytes()),0, ChainConfigs.DIFFICULTY, IPayload.genesisPayload());
	}
	

}
