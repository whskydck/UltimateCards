package com.github.norbo11.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.norbo11.commands.blackjack.BlackjackBet;
import com.github.norbo11.commands.blackjack.BlackjackDouble;
import com.github.norbo11.commands.blackjack.BlackjackHit;
import com.github.norbo11.commands.blackjack.BlackjackSplit;
import com.github.norbo11.commands.blackjack.BlackjackStand;
import com.github.norbo11.commands.cards.CardsDetails;
import com.github.norbo11.commands.cards.CardsInvite;
import com.github.norbo11.commands.cards.CardsLeave;
import com.github.norbo11.commands.cards.CardsMoney;
import com.github.norbo11.commands.cards.CardsPlayers;
import com.github.norbo11.commands.cards.CardsRebuy;
import com.github.norbo11.commands.cards.CardsReload;
import com.github.norbo11.commands.cards.CardsSit;
import com.github.norbo11.commands.cards.CardsTables;
import com.github.norbo11.commands.cards.CardsTeleport;
import com.github.norbo11.commands.cards.CardsWithdraw;
import com.github.norbo11.commands.poker.PokerAllin;
import com.github.norbo11.commands.poker.PokerBet;
import com.github.norbo11.commands.poker.PokerBoard;
import com.github.norbo11.commands.poker.PokerCall;
import com.github.norbo11.commands.poker.PokerCheck;
import com.github.norbo11.commands.poker.PokerFold;
import com.github.norbo11.commands.poker.PokerHand;
import com.github.norbo11.commands.poker.PokerPay;
import com.github.norbo11.commands.poker.PokerPot;
import com.github.norbo11.commands.poker.PokerReveal;
import com.github.norbo11.commands.table.TableBan;
import com.github.norbo11.commands.table.TableClose;
import com.github.norbo11.commands.table.TableCreate;
import com.github.norbo11.commands.table.TableDelete;
import com.github.norbo11.commands.table.TableKick;
import com.github.norbo11.commands.table.TableListSettings;
import com.github.norbo11.commands.table.TableOpen;
import com.github.norbo11.commands.table.TableSet;
import com.github.norbo11.commands.table.TableStart;
import com.github.norbo11.commands.table.TableUnban;
import com.github.norbo11.util.ErrorMessages;
import com.github.norbo11.util.ExceptionCatcher;
import com.github.norbo11.util.Log;
import com.github.norbo11.util.Messages;

public class PluginExecutor implements CommandExecutor
{

    public static CardsDetails cardsDetails = new CardsDetails();
    public static CardsInvite cardsInvite = new CardsInvite();
    public static CardsLeave cardsLeave = new CardsLeave();
    public static CardsMoney cardsMoney = new CardsMoney();
    public static CardsPlayers cardsPlayers = new CardsPlayers();
    public static CardsRebuy cardsRebuy = new CardsRebuy();
    public static CardsSit cardsSit = new CardsSit();
    public static CardsTables cardsTables = new CardsTables();
    public static CardsTeleport cardsTeleport = new CardsTeleport();
    public static CardsWithdraw cardsWithdraw = new CardsWithdraw();
    public static CardsReload cardsReload = new CardsReload();

    public static PokerHand pokerHand = new PokerHand();
    public static PokerAllin pokerAllin = new PokerAllin();
    public static PokerBet pokerBet = new PokerBet();
    public static PokerBoard pokerBoard = new PokerBoard();
    public static PokerCall pokerCall = new PokerCall();
    public static PokerCheck pokerCheck = new PokerCheck();
    public static PokerFold pokerFold = new PokerFold();
    public static PokerPay pokerPay = new PokerPay();
    public static PokerPot pokerPot = new PokerPot();
    public static PokerReveal pokerReveal = new PokerReveal();

    public static TableBan tableBan = new TableBan();
    public static TableClose tableClose = new TableClose();
    public static TableCreate tableCreate = new TableCreate();
    public static TableDelete tableDelete = new TableDelete();
    public static TableKick tableKick = new TableKick();
    public static TableListSettings tableListSettings = new TableListSettings();
    public static TableOpen tableOpen = new TableOpen();
    public static TableSet tableSet = new TableSet();
    public static TableStart tableStart = new TableStart();
    public static TableUnban tableUnban = new TableUnban();

    public static BlackjackHit blackjackHit = new BlackjackHit();
    public static BlackjackStand blackjackStand = new BlackjackStand();
    public static BlackjackBet blackjackBet = new BlackjackBet();
    public static BlackjackSplit blackjackSplit = new BlackjackSplit();
    public static BlackjackDouble blackjackDouble = new BlackjackDouble();

    public static ArrayList<PluginCommand> commandsCards = new ArrayList<PluginCommand>();
    public static ArrayList<PluginCommand> commandsTable = new ArrayList<PluginCommand>();
    public static ArrayList<PluginCommand> commandsPoker = new ArrayList<PluginCommand>();
    public static ArrayList<PluginCommand> commandsBlackjack = new ArrayList<PluginCommand>();
    public static ArrayList<ArrayList<PluginCommand>> commands = new ArrayList<ArrayList<PluginCommand>>();

    static
    {
        commandsCards.add(cardsDetails);
        commandsCards.add(cardsInvite);
        commandsCards.add(cardsLeave);
        commandsCards.add(cardsMoney);
        commandsCards.add(cardsPlayers);
        commandsCards.add(cardsRebuy);
        commandsCards.add(cardsSit);
        commandsCards.add(cardsTables);
        commandsCards.add(cardsTeleport);
        commandsCards.add(cardsWithdraw);
        commandsCards.add(cardsReload);

        commandsTable.add(tableBan);
        commandsTable.add(tableClose);
        commandsTable.add(tableCreate);
        commandsTable.add(tableDelete);
        commandsTable.add(tableKick);
        commandsTable.add(tableListSettings);
        commandsTable.add(tableOpen);
        commandsTable.add(tableSet);
        commandsTable.add(tableStart);
        commandsTable.add(tableUnban);

        commandsPoker.add(pokerHand);
        commandsPoker.add(pokerReveal);
        commandsPoker.add(pokerAllin);
        commandsPoker.add(pokerBet);
        commandsPoker.add(pokerBoard);
        commandsPoker.add(pokerCall);
        commandsPoker.add(pokerCheck);
        commandsPoker.add(pokerFold);
        commandsPoker.add(pokerPay);
        commandsPoker.add(pokerPot);

        commandsBlackjack.add(blackjackHit);
        commandsBlackjack.add(blackjackStand);
        commandsBlackjack.add(blackjackBet);
        commandsBlackjack.add(blackjackSplit);
        commandsBlackjack.add(blackjackDouble);

        commands.add(commandsCards);
        commands.add(commandsTable);
        commands.add(commandsPoker);
        commands.add(commandsBlackjack);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        try
        {
            if (sender instanceof Player)
            {
                Player player = (Player) sender;

                if (args.length >= 1)
                {
                    String action = args[0];
                    Log.logCommand(sender, command, args);

                    if (action.equalsIgnoreCase("help"))
                    {
                        String commandToHelpWith = args.length == 2 ? args[1] : command.getName();
                        ErrorMessages.displayHelp(player, commandToHelpWith);
                        return true;
                    }

                    if (command.getName().equalsIgnoreCase("cards"))// || command.getName().equalsIgnoreCase("c"))
                    {

                        for (PluginCommand cmd : commandsCards)
                            if (performChecks(cmd, args, player, action)) return true;
                        Messages.sendMessage(player, "&cNo such cards command. Check help with &6/cards help.");
                    }

                    if (command.getName().equalsIgnoreCase("table"))// || command.getName().equalsIgnoreCase("t"))
                    {
                        for (PluginCommand cmd : commandsTable)
                            if (performChecks(cmd, args, player, action)) return true;
                        Messages.sendMessage(player, "&cNo such table command. Check help with &6/table help.");
                    }

                    if (command.getName().equalsIgnoreCase("poker"))// || command.getName().equalsIgnoreCase("p"))
                    {
                        for (PluginCommand cmd : commandsPoker)
                            if (performChecks(cmd, args, player, action)) return true;
                        Messages.sendMessage(player, "&cNo such poker command. Check help with &6/poker help.");
                    }

                    if (command.getName().equalsIgnoreCase("blackjack") || command.getName().equalsIgnoreCase("bj"))
                    {
                        for (PluginCommand cmd : commandsBlackjack)
                            if (performChecks(cmd, args, player, action)) return true;
                        Messages.sendMessage(player, "&cNo such blackjack command. Check help with &6/blackjack help.");
                    }
                } else
                {
                    ErrorMessages.displayHelp(player, command.getName());
                }
            } else
            {
                ErrorMessages.notHumanPlayer(sender);
            }
        } catch (Exception e)
        {
            ExceptionCatcher.catchException(e, command, sender, args);
        }
        return true;
    }

    private boolean performChecks(PluginCommand cmd, String[] args, Player player, String action) throws Exception
    {
        if (cmd.containsAlias(action)) 
        {
            if (cmd.hasPermission(player) || player.hasPermission(PluginCommand.PERMISSIONS_BASE_NODE + "*"))
            {
                cmd.setArgs(args);
                cmd.setPlayer(player);
                if (cmd.conditions())
                {
                    cmd.perform();
                }
                return true;
            } else
            {
                ErrorMessages.noPermission(player);
            }
        }
        return false;
    }
}
