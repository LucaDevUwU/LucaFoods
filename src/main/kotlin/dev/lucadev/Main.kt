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

class Main: JavaPlugin(), Listener {
    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(EatListener(), this)
        getCommand("uranium")!!.setExecutor(this)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            val item = ItemStack(Material.DRIED_KELP)
            val meta = item.itemMeta
            meta!!.setDisplayName("Uranium")
            meta!!.lore = listOf("Uranium", "good luck")
            item.itemMeta = meta
            item.itemMeta!!.persistentDataContainer.set(NamespacedKey.minecraft("eaten"), PersistentDataType.INTEGER, 1)
            sender.inventory.addItem(item)
        }
        return true
    }

}