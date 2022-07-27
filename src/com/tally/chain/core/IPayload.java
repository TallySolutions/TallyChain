/**
 * 
 */
package com.tally.chain.core;

/**
 * @author tuhin.sengupta
 *
 */
public interface IPayload {
	
	public String serialize();
	
	public void deserialize(String data);

	public static IPayload genesisPayload() {
		
		return new IPayload() {
			
			@Override
			public String serialize() {
				return "";
			}
			
			@Override
			public void deserialize(String data) {
			}
		};
	}

}
