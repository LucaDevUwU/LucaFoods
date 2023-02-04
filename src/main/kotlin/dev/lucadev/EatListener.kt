package dev.lucadev

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class EatListener : Listener {
    @EventHandler
    fun onItemConsume(event: PlayerItemConsumeEvent) {
        val item = event.item
        if (item.itemMeta!!.displayName == "Uranium") {
            val effect = PotionEffect(PotionEffectType.WITHER, 20 * 15, 1) // 15 seconds of Wither, level 1
            event.player.addPotionEffect(effect)
        }
    }
}
