package com.rumaruka.simplegrinder.Proxy;

import com.rumaruka.simplegrinder.Init.BlocksCore;
import com.rumaruka.simplegrinder.Init.ItemsCore;

public class ClientProxy extends CommonProxy{

	
	@Override
	public void Renders() {
		ItemsCore.Render();
		BlocksCore.Render();
	}
}
