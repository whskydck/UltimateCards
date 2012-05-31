/* ==================================================================================================
 * UltimatePoker v1.1 - By Norbo11
 * Copyright (C) 2012
 * You may NOT modify this file in any way, or use any of it's code for personal projects. 
 * You may, however, read and learn from it if you like. All rights blah blah and shit. 
 * Basically just respect my hard work, please :)
 * 
 * File notes: ListenerCommandExecutor.java
 * -Listens for commands typed by the user and handles them accordingly
 * -Every command is surrounded with a try/catch statement which spits out the error log
 * to the console and logs it to the log.txt file. It also displays the simplified error to the user.
 * ===================================================================================================
 */

package com.github.norbo11;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.norbo11.classes.PokerPlayer;

public class ListenerCommandExecutor implements CommandExecutor
{

    ListenerCommandExecutor(UltimatePoker p)
    {
        this.p = p;
    }

    UltimatePoker p;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (command.getName().equalsIgnoreCase("tables"))
        {
            p.methodsMisc.logCommand(sender, command, args);
            try
            {
                if (sender instanceof Player)
                {
                    Player player = (Player) sender;
                    if (player.hasPermission("upoker.play"))
                    {
                        p.methodsPoker.displayTables(player);
                    } else p.methodsError.noPermission(player);
                } else p.methodsError.notAPlayer(sender);
                return true;
            } catch (Exception e)
            {
                p.methodsMisc.catchException(e, command, sender, args);
            }
        }
        if (command.getName().equalsIgnoreCase("hand"))
        {
            p.methodsMisc.logCommand(sender, command, args);
            try
            {
                if (sender instanceof Player)
                {
                    Player player = (Player) sender;
                    if (player.hasPermission("upoker.play"))
                    {
                        if (args.length > 0)
                        {
                            String action = args[0];
                            if (action.equalsIgnoreCase("help") || action.equalsIgnoreCase("h"))
                            {
                                if (args.length == 1) p.methodsError.displayHelp(player, "hand");
                                else if (args.length != 0) p.methodsError.usage(player, "hand", args[1]);
                                return true;
                            }
                            if (action.equalsIgnoreCase("bet") || action.equalsIgnoreCase("b"))
                            {
                                if (args.length == 2) p.methodsHand.bet(player, args[1]);
                                else p.methodsError.usage(player, "hand", "bet");
                                return true;
                            }
                            if (action.equalsIgnoreCase("fold") || action.equalsIgnoreCase("f") || action.equalsIgnoreCase("muck"))
                            {
                                if (args.length == 1) p.methodsHand.fold(player);
                                else p.methodsError.usage(player, "hand", "fold");
                                return true;
                            }
                            if (action.equalsIgnoreCase("call") || action.equalsIgnoreCase("ca") || action.equalsIgnoreCase("match"))
                            {
                                if (args.length == 1) p.methodsHand.call(player);
                                else p.methodsError.usage(player, "hand", "call");
                                return true;
                            }
                            if (action.equalsIgnoreCase("check") || action.equalsIgnoreCase("ch"))
                            {
                                if (args.length == 1) p.methodsHand.check(player);
                                else p.methodsError.usage(player, "hand", "check");
                                return true;
                            }
                            if (action.equalsIgnoreCase("board") || action.equalsIgnoreCase("community"))
                            {
                                if (args.length == 1) p.methodsHand.displayBoard(player);
                                else p.methodsError.usage(player, "hand", "board");
                                return true;
                            }
                            if (action.equalsIgnoreCase("money") || action.equalsIgnoreCase("m") || action.equalsIgnoreCase("balance"))
                            {
                                if (args.length == 1) p.methodsHand.displayMoney(player);
                                else p.methodsError.usage(player, "hand", "money");
                                return true;
                            }
                            if (action.equalsIgnoreCase("rebuy") || action.equalsIgnoreCase("r") || action.equalsIgnoreCase("addmoney") || action.equalsIgnoreCase("addstack"))
                            {
                                if (args.length == 2) p.methodsHand.reBuy(player, args[1]);
                                else p.methodsError.usage(player, "hand", "rebuy");
                                return true;
                            }
                            if (action.equalsIgnoreCase("pot") || action.equalsIgnoreCase("p") || action.equalsIgnoreCase("pots"))
                            {
                                if (args.length == 1) p.methodsHand.displayPot(player);
                                else p.methodsError.usage(player, "hand", "pot");
                                return true;
                            }
                            if (action.equalsIgnoreCase("allin") || action.equalsIgnoreCase("a") || action.equalsIgnoreCase("shove"))
                            {
                                if (args.length == 1) p.methodsHand.allIn(player);
                                else p.methodsError.usage(player, "hand", "allin");
                                return true;
                            }
                            if (action.equalsIgnoreCase("reveal") || action.equalsIgnoreCase("show") || action.equalsIgnoreCase("display"))
                            {
                                if (args.length == 1) p.methodsHand.reveal(player);
                                else p.methodsError.usage(player, "hand", "reveal");
                                return true;
                            }
                            if (action.equalsIgnoreCase("withdraw") || action.equalsIgnoreCase("cashin") || action.equalsIgnoreCase("w"))
                            {
                                if (args.length == 2) p.methodsHand.withdraw(player, args[1]);
                                else p.methodsError.usage(player, "hand", "withdraw");
                                return true;
                            }
                            player.sendMessage(p.PLUGIN_TAG + p.red + "No such hand command. Check help with /hand help.");
                            return true;
                        } else p.methodsHand.displayHand(player);
                    } else p.methodsError.noPermission(player);
                } else p.methodsError.notAPlayer(sender);
            } catch (Exception e)
            {
                p.methodsMisc.catchException(e, command, sender, args);
            }
        }
        if (command.getName().equalsIgnoreCase("poker") || command.getName().equalsIgnoreCase("pkr"))
        {
            p.methodsMisc.logCommand(sender, command, args);
            try
            {
                if (sender instanceof Player)
                {
                    Player player = (Player) sender;
                    if (args.length > 0)
                    {
                        if (args[0].equalsIgnoreCase("tp") || args[0].equalsIgnoreCase("teleport"))
                        {
                            if (player.hasPermission("upoker.tp"))
                            {
                                if (args.length == 2) p.methodsPoker.tpToTable(player, args[1]);
                                else p.methodsError.usage(player, "poker", "teleport");
                            } else p.methodsError.noPermission(player);
                            return true;
                        }
                        if (player.hasPermission("upoker.play"))
                        {
                            String action = args[0];
                            if (action.equalsIgnoreCase("help") || action.equalsIgnoreCase("h"))
                            {
                                if (args.length == 1) p.methodsError.displayHelp(player, "poker");
                                else if (args.length == 2) p.methodsError.usage(player, "poker", args[1]);
                                else p.methodsError.usage(player, "poker", "help");
                                return true;
                            }
                            if (action.equalsIgnoreCase("list") || action.equalsIgnoreCase("l") || action.equalsIgnoreCase("all") || action.equalsIgnoreCase("tables"))
                            {
                                if (args.length == 1) p.methodsPoker.displayTables(player);
                                else p.methodsError.usage(player, "poker", "list");
                                return true;
                            }
                            if (action.equalsIgnoreCase("sit") || action.equalsIgnoreCase("join") || action.equalsIgnoreCase("s"))
                            {
                                if (args.length == 3) p.methodsPoker.sitTable(player, args[1], args[2]);
                                else p.methodsError.usage(player, "poker", "sit");
                                return true;
                            }
                            if (action.equalsIgnoreCase("getup") || action.equalsIgnoreCase("standup") || action.equalsIgnoreCase("leave") || action.equalsIgnoreCase("stand"))
                            {
                                if (args.length == 1) p.methodsPoker.leaveTable(player);
                                else p.methodsError.usage(player, "poker", "getup");
                                return true;
                            }
                            if (action.equalsIgnoreCase("details") || action.equalsIgnoreCase("d") || action.equalsIgnoreCase("info"))
                            {
                                if (args.length == 1) p.methodsPoker.listDetails(player, null, null);
                                else if (args.length == 2) p.methodsPoker.listDetails(player, args[1], null);
                                else if (args.length == 3) p.methodsPoker.listDetails(player, args[1], args[2]);
                                else p.methodsError.usage(player, "poker", "details");
                                return true;
                            }
                            if (action.equalsIgnoreCase("listplayers") || action.equalsIgnoreCase("lp") || action.equalsIgnoreCase("players"))
                            {
                                if (args.length == 1) p.methodsPoker.displayPlayers(player, null);
                                else if (args.length == 2) p.methodsPoker.displayPlayers(player, args[1]);
                                else p.methodsError.usage(player, "poker", "listplayers");
                                return true;
                            }
                            if (action.equalsIgnoreCase("invite") || action.equalsIgnoreCase("i"))
                            {
                                if (args.length == 2) p.methodsPoker.invite(player, args[1]);
                                else p.methodsError.usage(player, "poker", "invite");
                                return true;
                            }
                            if (action.equalsIgnoreCase("checkplayer") || action.equalsIgnoreCase("check"))
                            {
                                if (args.length == 2) p.methodsPoker.check(player, args[1]);
                                else p.methodsError.usage(player, "poker", "checkplayer");
                                return true;
                            }
                            if (action.equalsIgnoreCase("servetea"))
                            {
                                if (args.length == 1)
                                {
                                    PokerPlayer pokerPlayer = p.methodsCheck.isAPokerPlayer(player);
                                    if (pokerPlayer != null) p.methodsMisc.sendToAllWithinRange(pokerPlayer.table.location, p.PLUGIN_TAG + p.gold + pokerPlayer.name + p.white + " has served everyone a cup of tea! Also easter eggs.");
                                    else player.sendMessage(p.PLUGIN_TAG + p.red + "No such poker command. Check help with /poker help.");
                                }
                                return true;
                            }
                            if (action.equalsIgnoreCase("stats"))
                            {
                                if (args.length == 1) p.methodsStats.check(player, null);
                                else if (args.length == 2) p.methodsError.usage(player, "stats", args[1]);
                                else if (args.length == 3)
                                {
                                    if (args[1].equalsIgnoreCase("check")) p.methodsStats.check(player, args[2]);
                                    else if (args[1].equalsIgnoreCase("top")) p.methodsStats.top(player, args[2]);
                                    else if (args[1].equalsIgnoreCase("rank")) p.methodsStats.rank(player, args[2], player.getName());
                                    else p.methodsError.usage(player, "stats", args[1]);
                                } else if (args.length == 4)
                                {
                                    if (args[1].equalsIgnoreCase("rank")) p.methodsStats.rank(player, args[2], args[3]);
                                    else p.methodsError.usage(player, "stats", args[1]);
                                } else p.methodsError.usage(player, "stats", args[1]);
                                return true;
                            }
                            player.sendMessage(p.PLUGIN_TAG + p.red + "No such poker command. Check help with /poker help.");
                            return true;
                        } else p.methodsError.noPermission(player);
                    } else p.methodsError.displayHelp(player, "poker");
                } else p.methodsError.notAPlayer(sender);
            } catch (Exception e)
            {
                p.methodsMisc.catchException(e, command, sender, args);
            }
        }
        if (command.getName().equalsIgnoreCase("table") || command.getName().equalsIgnoreCase("tbl"))
        {
            p.methodsMisc.logCommand(sender, command, args);
            try
            {
                if (sender instanceof Player)
                {
                    Player player = (Player) sender;
                    if (player.hasPermission("upoker.table"))
                    {
                        if (args.length > 0)
                        {
                            String action = args[0];
                            if (action.equalsIgnoreCase("help") || action.equalsIgnoreCase("h"))
                            {
                                if (args.length == 1) p.methodsError.displayHelp(player, "table");
                                else if (args.length == 2) p.methodsError.usage(player, "table", args[1]);
                                else p.methodsError.usage(player, "table", "help");
                                return true;
                            }
                            if (action.equalsIgnoreCase("create") || action.equalsIgnoreCase("new") || action.equalsIgnoreCase("cr"))
                            {
                                if (args.length == 3) p.methodsTable.createTable(player, args[1], args[2]);
                                else p.methodsError.usage(player, "table", "create");
                                return true;
                            }
                            if (action.equalsIgnoreCase("delete") || action.equalsIgnoreCase("del"))
                            {
                                if (args.length == 1) p.methodsTable.deleteTable(player);
                                else p.methodsError.usage(player, "table", "delete");
                                return true;
                            }
                            if (action.equalsIgnoreCase("open") || action.equalsIgnoreCase("o") || action.equalsIgnoreCase("unlock"))
                            {
                                if (args.length == 1) p.methodsTable.openTable(player);
                                else p.methodsError.usage(player, "table", "open");
                                return true;
                            }
                            if (action.equalsIgnoreCase("close") || action.equalsIgnoreCase("cl") || action.equalsIgnoreCase("lock"))
                            {
                                if (args.length == 1) p.methodsTable.closeTable(player);
                                else p.methodsError.usage(player, "table", "close");
                                return true;
                            }
                            if (action.equalsIgnoreCase("set"))
                            {
                                if (args.length == 3) p.methodsTable.setSetting(player, args[1], args[2]);
                                else p.methodsError.usage(player, "table", "set");
                                return true;
                            }
                            if (action.equalsIgnoreCase("listsettings") || action.equalsIgnoreCase("ls"))
                            {
                                if (args.length == 1) p.methodsTable.availableSettings(player);
                                else p.methodsError.usage(player, "table", "listsettings");
                                return true;
                            }
                            if (action.equalsIgnoreCase("start") || action.equalsIgnoreCase("s") || action.equalsIgnoreCase("go"))
                            {
                                if (args.length == 1) p.methodsTable.startTable(player);
                                else p.methodsError.usage(player, "table", "start");
                                return true;
                            }
                            if (action.equalsIgnoreCase("pay") || action.equalsIgnoreCase("p"))
                            {
                                if (args.length == 3) // Pot ID and player
                                p.methodsTable.payPot(player, args[1], args[2]);
                                else if (args.length == 2) // Just player
                                p.methodsTable.payPot(player, null, args[1]);
                                else p.methodsError.usage(player, "table", "pay");
                                return true;
                            }
                            if (action.equalsIgnoreCase("kick") || action.equalsIgnoreCase("k") || action.equalsIgnoreCase("boot"))
                            {
                                if (args.length == 2) p.methodsTable.kick(player, args[1]);
                                else p.methodsError.usage(player, "table", "kick");
                                return true;
                            }
                            if (action.equalsIgnoreCase("ban") || action.equalsIgnoreCase("b"))
                            {
                                if (args.length == 2) p.methodsTable.ban(player, args[1]);
                                else p.methodsError.usage(player, "table", "ban");
                                return true;
                            }
                            if (action.equalsIgnoreCase("unban") || action.equalsIgnoreCase("u") || action.equalsIgnoreCase("pardon") || action.equalsIgnoreCase("forgive"))
                            {
                                if (args.length == 2) p.methodsTable.unBan(player, args[1]);
                                else p.methodsError.usage(player, "table", "unban");
                                return true;
                            }
                            if (action.equalsIgnoreCase("continue") || action.equalsIgnoreCase("cont") || action.equalsIgnoreCase("next"))
                            {
                                if (args.length == 1) p.methodsTable.continueHand(player);
                                else p.methodsError.usage(player, "table", "continue");
                                return true;
                            }
                            player.sendMessage(p.PLUGIN_TAG + p.red + "No such table command. Check help with /table help.");
                            return true;
                        } else p.methodsError.displayHelp(player, "table");
                    } else p.methodsError.noPermission(player);
                } else p.methodsError.notAPlayer(sender);
            } catch (Exception e)
            {
                p.methodsMisc.catchException(e, command, sender, args);
            }
        }
        return true;
    }
}
