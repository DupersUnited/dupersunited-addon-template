package com.example;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.CommandBuildContext;
import wtf.dupers.dupersunited.api.command.Command;
import wtf.dupers.dupersunited.commands.MainCommand;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommands.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommands.literal;

// An example command using the DupersUnited addon API.
public class ExampleCommand extends Command {
    public ExampleCommand() {
        // This example command would be invoked using '/du example'
        super("example", "This is an example command.");
    }

    @Override
    public void build(LiteralArgumentBuilder<FabricClientCommandSource> builder, CommandBuildContext registryAccess) {
        // Example command execution
        builder.executes(c -> {
            MainCommand.sendMessage("Hello, world!", true);
            return 1;
        });

        // Example command execution with subcommand, '/du example ping'
        builder.then(literal("ping").executes(c -> {
            MainCommand.sendMessage("Pong!", true);
            return 1;
        }));


        // Example command execution with argument, '/du example {name}'
        builder.then(argument("name", StringArgumentType.word()).executes(c -> {
            // Retrieve the name argument from the command context
            String name = StringArgumentType.getString(c, "name");

            MainCommand.sendMessage("Hello, " + name + "!", true);
            return 1;
        }));
    }
}
