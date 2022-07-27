package test;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.tally.chain.core.*;

class UTBlock {

	private IPayload data;
	private Block lastBlock;
	private Block block;
	
	public UTBlock() throws NoSuchAlgorithmException {
	  data = new IPayload() {

		private String data = "bar";
		@Override
		public String serialize() {
			return data;
		}
		
		@Override
		public void deserialize(String data) {
			this.data = data; 
		}
	  };	
	  lastBlock = Block.genesis();
	  block = Block.mineBlock(lastBlock,data);
	}
	@Test
	void testData() {
		assertTrue(block.getData().serialize().equals(data.serialize()));
	}
	
	@Test
	void testLastHash() {
		assertTrue(block.getLastHash().equals(lastBlock.getHash()));		
	}
	
	@Test
	void testHash() {
		assertTrue(block.getHash().substring(0,block.getDifficulty()).equals(String.join("", Collections.nCopies(block.getDifficulty(), "0"))));		
	}

	@Test
	void testAdjustingDifficultyForSlowerRates() {
		assertTrue(Block.adjustDifficulty(block, block.getTimestamp()+360000) == block.getDifficulty()-1);
	}
	
	@Test
	void testAdjustingDifficultyForFasterRates() {
		assertTrue(Block.adjustDifficulty(block, block.getTimestamp()+1) == block.getDifficulty()+1);
	}
}
