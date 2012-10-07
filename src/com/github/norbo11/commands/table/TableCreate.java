package com.github.norbo11.commands.table;

import com.github.norbo11.UltimateCards;
import com.github.norbo11.commands.PluginCommand;
import com.github.norbo11.game.blackjack.BlackjackTable;
import com.github.norbo11.game.cards.CardsPlayer;
import com.github.norbo11.game.cards.CardsTable;
import com.github.norbo11.game.poker.PokerTable;
import com.github.norbo11.util.DateMethods;
import com.github.norbo11.util.ErrorMessages;
import com.github.norbo11.util.Formatter;
import com.github.norbo11.util.Log;
import com.github.norbo11.util.Messages;
import com.github.norbo11.util.NumberMethods;

public class TableCreate extends PluginCommand
{
    public static boolean isGameType(String gameType)
    {
        return gameType.equalsIgnoreCase("poker") || gameType.equalsIgnoreCase("blackjack") || gameType.equalsIgnoreCase("bj");
    }

    double buyin;
    String gameType;

    public TableCreate()
    {
        getAlises().add("create");
        getAlises().add("new");
        getAlises().add("cr");

        setDescription("Creates a table and sits at it with the specified buy-in. Game types: &6poker, blackjack|bj");

        setArgumentString("[table name] [buyin] [game type]");

        getPermissionNodes().add(PERMISSIONS_BASE_NODE + "table");
        getPermissionNodes().add(PERMISSIONS_BASE_NODE + "table." + getAlises().get(0));
    }

    // table create name buyin poker|blackjack
    @Override
    public boolean conditions()
    {
        if (getArgs().length == 4)
        {
            if (CardsPlayer.getCardsPlayer(getPlayer().getName()) == null)
            {
                gameType = getArgs()[3];
                if (isGameType(gameType))
                {
                    buyin = NumberMethods.getDouble(getArgs()[2]);
                    if (buyin != -99999)
                    {
                        if (UltimateCards.getEconomy().has(getPlayer().getName(), buyin)) return true;
                        else
                        {
                            ErrorMessages.notEnoughMoney(getPlayer(), buyin, UltimateCards.getEconomy().getBalance(getPlayer().getName()) - buyin);
                        }
                    } else
                    {
                        ErrorMessages.invalidNumber(getPlayer(), getArgs()[2]);
                    }
                } else
                {
                    ErrorMessages.notGameType(getPlayer());
                }
            } else
            {
                ErrorMessages.playerSittingAtTable(getPlayer());
            }
        } else
        {
            showUsage();
        }
        return false;
    }


    @Override
    public void perform() throws Exception
    {
        CardsTable newTable = null;
        if (gameType.equalsIgnoreCase("poker"))
        {
            newTable = new PokerTable(getPlayer(), getArgs()[1], CardsTable.getFreeTableID(), getPlayer().getLocation(), buyin);
        } else if (gameType.equalsIgnoreCase("blackjack") || gameType.equalsIgnoreCase("bj"))
        {
            newTable = new BlackjackTable(getPlayer(), getArgs()[1], CardsTable.getFreeTableID(), getPlayer().getLocation(), buyin);
        }
        CardsTable.getTables().add(newTable);

        // Send messages
        Messages.sendToAllWithinRange(newTable.getLocation(), "&6" + getPlayer().getName() + " &fhas created a &6" + gameType + "&f table named " + "&6'" + newTable.getName() + "'&f, ID " + "&6" + Integer.toString(newTable.getID()));
        Messages.sendToAllWithinRange(newTable.getLocation(), "Bought in for " + "&6" + Formatter.formatMoney(buyin));
        Messages.sendMessage(getPlayer(), "Edit the rules of your table with " + "&6'/table set' &f, and open it with " + "&6'/table open' &fwhen ready!");

        // Take money
        UltimateCards.getEconomy().withdrawPlayer(getPlayer().getName(), buyin);
        Log.addToLog(DateMethods.getDate() + " [ECONOMY] Withdrawing " + buyin + " from " + getPlayer().getName());
    }
}