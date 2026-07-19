package com.example;

import com.mojang.logging.LogUtils;

import org.slf4j.Logger;
import wtf.dupers.dupersunited.api.DupersUnitedAddon;
import wtf.dupers.dupersunited.api.DupersUnitedRegistry;

public class ExampleAddon implements DupersUnitedAddon {
	// This logger is used to write text to the console and the log file.
	public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void initialize(DupersUnitedRegistry registry) {
        // Modules & commands are added to the DupersUnited mod through the DupersUnitedRegistry object.
        registry.registerModule(new ExampleModule());
        registry.registerCommand(new ExampleCommand());
    }
}
