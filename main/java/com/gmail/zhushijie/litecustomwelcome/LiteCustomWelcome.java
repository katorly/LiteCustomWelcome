package com.gmail.zhushijie.litecustomwelcome;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public final class LiteCustomWelcome extends JavaPlugin implements Listener {
    public static LiteCustomWelcome INSTANCE;
    public LiteCustomWelcome() {
        INSTANCE = this;
    }

    public void pluginupdater() {
        String currentversion = "1.1";
        getLogger().info("正在检查更新......");
        try {
            URL url = new URL("https://cdn.jsdelivr.net/gh/main-world/litecustom@update/LiteCustomWelcome/version.txt");
            InputStream is = url.openStream();
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);
            String version = br.readLine();
            if (version.equals(currentversion)) {
                getLogger().info("插件已是最新版本!");
            } else {
                getLogger().info("检查到插件有新版本!");
                getLogger().info("请前往相应网页下载更新!");
            }
        } catch (Throwable t) {
            getLogger().info("更新检查失败!");
        }
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        getLogger().info("LiteCustomWelcome已成功加载!");
        getLogger().info("作者:主世界");
        getLogger().info("本插件已免费发布并在Gitee上开源");
        pluginupdater();
        Bukkit.getPluginManager().registerEvents(new OnJoinQuit(),this);
        LiteCustomWelcome.INSTANCE.getCommand("LiteCustomWelcome").setExecutor(new CommandHandler());
    }

    @Override
    public void onDisable() {
        reloadConfig();
        LiteCustomWelcome.INSTANCE.saveConfig();
        HandlerList.unregisterAll((Listener) this);
        getLogger().info("LiteCustomWelcome已成功卸载!");
    }
}
