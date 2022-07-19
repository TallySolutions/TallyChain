/**
 * 
 */
package com.tally.chain.core;

/**
 * @author tuhin.sengupta
 *
 */
public interface IPayload {
	
	public byte[] serialize();
	
	public void deserialize(byte[] data);

	public static IPayload genesisPayload() {
		
		return new IPayload() {
			
			@Override
			public byte[] serialize() {
				return new byte[0];
			}
			
			@Override
			public void deserialize(byte[] data) {
			}
		};
	}

}
