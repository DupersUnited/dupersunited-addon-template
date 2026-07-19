package com.example;

import wtf.dupers.dupersunited.api.module.Category;
import wtf.dupers.dupersunited.api.module.Module;
import wtf.dupers.dupersunited.api.module.settings.StringSetting;
import wtf.dupers.dupersunited.commands.MainCommand;

// An example module using the DupersUnited addon API.
public class ExampleModule extends Module {
    // Setting registration is done using the 'register' function
    private final StringSetting exampleSetting = register(new StringSetting("Name", System.getProperty("user.name")));

    public ExampleModule() {
        super("ExampleModule", "This is an example.", Category.misc);
    }

    @Override
    protected void onEnable() {
        // This function is invoked every time the module is enabled.
        // In this example, we send a message in chat.
        MainCommand.sendMessage("Hello, " + exampleSetting.getValue(), true);
    }

    @Override
    protected void onDisable() {
        // This function is invoked every time the module is disabled.
        ExampleAddon.LOGGER.info("Example module disabled!");
    }

    @Override
    public void onTick() {
        // This function is invoked every tick while the module is enabled.
        ExampleAddon.LOGGER.info("Example module ticked!");

        // In this example, we use it to disable the module immediately when turned on to act as a button.
        this.toggle();
    }
}
