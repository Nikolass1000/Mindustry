package io.anuke.mindustry;

import io.anuke.mindustry.command.CommandSystem;
import io.anuke.mindustry.core.*;
import io.anuke.mindustry.io.BlockLoader;
import io.anuke.mindustry.io.BundleLoader;
import io.anuke.ucore.modules.ModuleCore;
import io.anuke.ucore.util.Log;

import static io.anuke.mindustry.Vars.*;

public class Mindustry extends ModuleCore {

	@Override
	public void init(){
		debug = Platform.instance.isDebug();

		Log.setUseColors(false);
		BundleLoader.load();
		BlockLoader.load();

		module(logic = new Logic());
		module(global = new Global());

		world = new World[dimensionIds];
		for (int i=0;i<dimensionIds;i++){
			module(world[i] = new World(i));
		}

		module(control = new Control());
		module(renderer = new Renderer());
		module(ui = new UI());
		module(netServer = new NetServer());
		module(netClient = new NetClient());
		module(netCommon = new NetCommon());
		module(commandSystem = new CommandSystem());
	}

	@Override
	public void render(){
		super.render();
		threads.handleRender();
	}

}
