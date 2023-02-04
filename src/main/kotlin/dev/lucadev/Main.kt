package dev.lucadev

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Main: JavaPlugin(), Listener {
    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(EatListener(), this)
        getCommand("uranium")!!.setExecutor(this)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { sender.sendMessage("You need to be a player to use this command") }
        if (sender is Player && sender.hasPermission("uranium.use")) {
            val item = ItemStack(Material.DRIED_KELP)
            val meta = item.itemMeta
            meta!!.setDisplayName("Uranium")
            meta!!.lore = listOf("Uranium", "good luck")
            item.itemMeta = meta
            item.itemMeta!!.persistentDataContainer.set(NamespacedKey.minecraft("eaten"), PersistentDataType.INTEGER, 1)
            sender.inventory.addItem(item)

            val effect = PotionEffect(PotionEffectType.POISON, 20 * 15, 0.5.toInt())
            sender.addPotionEffect(effect)
        } else {
            sender.sendMessage("You dont have perms to use this command")
        }
        return true
    }

}