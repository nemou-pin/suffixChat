package club.nyandere.suffixchat;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public final class SuffixChat extends JavaPlugin implements Listener {

    private String gobi = "";

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("setgobi").setExecutor(this);
        getCommand("resetgobi").setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setgobi")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§4このコマンドはプレイヤーのみ使用できます");
                return true;
            }
            if (!(sender.hasPermission("admin"))) {
                sender.sendMessage("§4あなたはこのコマンドを実行する権限がありません");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage("§4コマンドの引数が指定されていません§r\n例:/setgobi \"にゃ(ΦωΦ)\"");
                return true;
            }
            String newGobi = String.join(" ",args).replaceAll("\"","");
            gobi = newGobi;
            sender.sendMessage("§a語尾を「§r"+gobi+"§a」に設定しました!!");
            return true;
        } else if (command.getName().equalsIgnoreCase("resetgobi")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§4このコマンドはプレイヤーのみ使用できます");
                return true;
            }
            if (!(sender.hasPermission("admin"))) {
                sender.sendMessage("§4あなたはこのコマンドを実行する権限がありません");
                return true;
            }
            gobi = "";
            sender.sendMessage("§a語尾をリセットしました!!");
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        String finalmessage = message + gobi;
        event.setMessage(finalmessage);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
