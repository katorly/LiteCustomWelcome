package com.gmail.zhushijie.litecustomwelcome;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Objects;

public class OnJoinQuit implements Listener {
    @EventHandler
    public void OnLogin (PlayerJoinEvent event) {
        FileConfiguration config = LiteCustomWelcome.INSTANCE.getConfig();
        Player player = event.getPlayer();
        event.setJoinMessage(config.getString("player-join").replace("&","§").replace("<player>",player.getName()));
        if (!Objects.equals(config.getString("welcome-title"), "none") && !Objects.equals(config.getString("welcome-subtitle"), "none")) { //Title文字
            player.sendTitle(config.getString("welcome-title").replace("&","§").replace("<player>",player.getName()), config.getString("welcome-subtitle").replace("&","§").replace("<player>",player.getName()), 10, config.getInt("title-time") * 20, 20);
        }
        Location location = player.getLocation();
        //弃用 fireworkMeta.addEffect(builder.withFlicker().withTrail().withColor(Collections.singleton(config.getString("firework-color"))).withFade(Collections.singleton(config.getString("firework-fade"))).with(FireworkEffect.Type.valueOf(config.getString("firework-effect"))).build());
        if (Objects.equals(config.getString("firework"), "RED")) { //放烟花
            Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
            FireworkEffect.Builder builder = FireworkEffect.builder();
            FireworkMeta fireworkMeta = firework.getFireworkMeta();
            fireworkMeta.addEffect(builder.withFlicker().withTrail().withColor(Color.RED).withFade(Color.MAROON).with(FireworkEffect.Type.BURST).build());
            firework.setFireworkMeta(fireworkMeta);
        } else if (Objects.equals(config.getString("firework"), "YELLOW")) {
            Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
            FireworkEffect.Builder builder = FireworkEffect.builder();
            FireworkMeta fireworkMeta = firework.getFireworkMeta();
            fireworkMeta.addEffect(builder.withFlicker().withTrail().withColor(Color.YELLOW).withFade(Color.ORANGE).with(FireworkEffect.Type.BURST).build());
            firework.setFireworkMeta(fireworkMeta);
        } else if (Objects.equals(config.getString("firework"), "BLUE")) {
            Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
            FireworkEffect.Builder builder = FireworkEffect.builder();
            FireworkMeta fireworkMeta = firework.getFireworkMeta();
            fireworkMeta.addEffect(builder.withFlicker().withTrail().withColor(Color.AQUA).withFade(Color.BLUE).with(FireworkEffect.Type.BURST).build());
            firework.setFireworkMeta(fireworkMeta);
        } else if (Objects.equals(config.getString("firework"), "GREEN")) {
            Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
            FireworkEffect.Builder builder = FireworkEffect.builder();
            FireworkMeta fireworkMeta = firework.getFireworkMeta();
            fireworkMeta.addEffect(builder.withFlicker().withTrail().withColor(Color.LIME).withFade(Color.GREEN).with(FireworkEffect.Type.BURST).build());
            firework.setFireworkMeta(fireworkMeta);
        }
        List<String> potions = config.getStringList("potions");
        if (!Objects.equals(potions.get(0),"none")) {
            for (String potion : potions) {
                String[] potionsplit = potion.split(",");
                String potiontype = potionsplit[0];
                int potiontime = Integer.parseInt(potionsplit[1]) * 20;
                int potionlevel = Integer.parseInt(potionsplit[2]);
                player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(potiontype), potiontime, potionlevel));
            }
        }
        List<String> playerchat = config.getStringList("player-chat");
        if (playerchat.size() != 0) { //以玩家名义执行指令
            for (String s : playerchat) {
                player.chat(s);
            }
        }
    }

    @EventHandler
    public void OnLogout (PlayerQuitEvent event) {
        FileConfiguration config = LiteCustomWelcome.INSTANCE.getConfig();
        Player player = event.getPlayer();
        event.setQuitMessage(config.getString("player-quit").replace("&","§").replace("<player>",player.getName()));
    }
}
