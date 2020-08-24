package com.gmail.zhushijie.litecustomwelcome;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration mconfig = LiteCustomWelcome.Messagesconfig.getConfig();
        if (command.getName().equalsIgnoreCase("litecustomwelcome")) {
            if (args.length < 1) { //若只输入了个/lcwc则弹出插件帮助
                if (!(sender instanceof Player)) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),"litecustomwelcome help");
                } else {
                    Player player = (Player) sender;
                    player.performCommand("litecustomwelcome help");
                }
            } else if (Objects.equals(args[0], "reload")) { //重载插件配置的指令 lcwc reload
                LiteCustomWelcome.INSTANCE.reloadConfig();
                LiteCustomWelcome.INSTANCE.saveConfig();
                sender.sendMessage(mconfig.getString("plugin-prefix").replace("&","§") + mconfig.getString("reload-success").replace("&","§"));
            } else if (Objects.equals(args[0], "help")) { //查看插件帮助的指令 lcwc help
                List<String> helpmessage = mconfig.getStringList("help-message");
                int i = 0;
                for (int length = helpmessage.size(); i < length; i++) {
                    sender.sendMessage(helpmessage.get(i).replace("&","§"));
                }
            } else { //若指令输入错误
                if (!(sender instanceof Player)) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),"litecustomwelcome help");
                } else {
                    Player player = (Player) sender;
                    player.performCommand("litecustomwelcome help");
                }
            }
        }
        return true;
    }
}
