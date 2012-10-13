package com.github.norbo11.util;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.norbo11.UltimateCards;

public class PluginConfig
{
    private UltimateCards p;

    // Options --------------------------------------------------
    private FileConfiguration config;

    // Update
    private boolean autoUpdate;

    // Log
    private boolean enableLog;
    private String dateFormat;

    // Chat
    private boolean displayTag;
    private String colorTag, colorNormalMessage, colorErrorMessage, colorHighlight;

    // General
    private boolean displayTurnsPublicly, allowRebuys;

    // Poker
    private int chatRange, dynamicFrequency;
    private double maxBuy, sb, bb, ante, minRaise, fixRake, minBuy, rake;
    private boolean rakeToStack, minRaiseAlwaysBB;

    // Blackjack
    private boolean allowDoubleDown, serverDealer, serverNeverDealer;
    private double minBet;
    private int amountOfDecks;

    public PluginConfig(UltimateCards p)
    {
        this.p = p;
    }

    public int getAmountOfDecks()
    {
        return amountOfDecks;
    }

    public double getAnte()
    {
        return ante;
    }

    public double getBb()
    {
        return bb;
    }

    public int getChatRange()
    {
        return chatRange;
    }

    public String getColorErrorMessage()
    {
        return colorErrorMessage;
    }

    public String getColorHighlight()
    {
        return colorHighlight;
    }

    public String getColorNormalMessage()
    {
        return colorNormalMessage;
    }

    public String getColorTag()
    {
        return colorTag;
    }

    public FileConfiguration getConfig()
    {
        return config;
    }

    public String getDateFormat()
    {
        return dateFormat;
    }

    public int getDynamicFrequency()
    {
        return dynamicFrequency;
    }

    public double getFixRake()
    {
        return fixRake;
    }

    public double getMaxBuy()
    {
        return maxBuy;
    }

    public double getMinBet()
    {
        return minBet;
    }

    public double getMinBuy()
    {
        return minBuy;
    }

    public double getMinRaise()
    {
        return minRaise;
    }

    public double getRake()
    {
        return rake;
    }

    public double getSb()
    {
        return sb;
    }

    public boolean isAllowDoubleDown()
    {
        return allowDoubleDown;
    }

    public boolean isAllowRebuys()
    {
        return allowRebuys;
    }

    public boolean isAutoUpdate()
    {
        return autoUpdate;
    }

    public boolean isDisplayTag()
    {
        return displayTag;
    }

    public boolean isDisplayTurnsPublicly()
    {
        return displayTurnsPublicly;
    }

    public boolean isEnableLog()
    {
        return enableLog;
    }

    public boolean isMinRaiseAlwaysBB()
    {
        return minRaiseAlwaysBB;
    }

    public boolean isRakeToStack()
    {
        return rakeToStack;
    }

    public boolean isServerDealer()
    {
        return serverDealer;
    }

    public boolean isServerNeverDealer()
    {
        return serverNeverDealer;
    }

    public void load()
    {
        config = p.getConfig();
        try
        {
            config.load(UltimateCards.getFilePluginConfig());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        setValues();
    }

    public void setServerDealer(boolean serverDealer)
    {
        this.serverDealer = serverDealer;
    }

    private void setValues()
    {
        // Update
        autoUpdate = config.getBoolean("options.general.autoUpdate", config.getDefaults().getBoolean("options.general.autoUpdate"));

        // Log
        enableLog = config.getBoolean("options.log.enableLog", config.getDefaults().getBoolean("options.log.enableLog"));
        dateFormat = config.getString("options.log.dateFormat", config.getDefaults().getString("options.log.dataFormat"));

        // Chat
        displayTag = config.getBoolean("options.chat.displayTag", config.getDefaults().getBoolean("options.chat.displayTag"));
        colorTag = config.getString("options.chat.colorTag", config.getDefaults().getString("options.chat.colorTag"));
        colorNormalMessage = config.getString("options.chat.colorNormalMessage", config.getDefaults().getString("options.chat.colorNormalMessage"));
        colorErrorMessage = config.getString("options.chat.colorErrorMessage", config.getDefaults().getString("options.chat.colorErrorMessage"));
        colorHighlight = config.getString("options.chat.colorHighlight", config.getDefaults().getString("options.chat.colorHighlight"));
        chatRange = config.getInt("options.chat.chatRange", config.getDefaults().getInt("options.poker.chatRange"));

        // General
        allowRebuys = config.getBoolean("options.generalDefaults.allowRebuys", config.getDefaults().getBoolean("options.generalDefaults.allowRebuys"));
        displayTurnsPublicly = config.getBoolean("options.generalDefaults.displayTurnsPublicly", config.getDefaults().getBoolean("options.generalDefaults.displayTurnsPublicly"));

        // Poker
        rakeToStack = config.getBoolean("options.poker.rakeToStack", config.getDefaults().getBoolean("options.poker.rakeToStack"));
        fixRake = config.getDouble("options.poker.fixRake", config.getDefaults().getDouble("options.poker.fixRake"));

        minRaiseAlwaysBB = config.getBoolean("options.poker.defaults.minRaiseIsAlwaysbb", config.getDefaults().getBoolean("options.poker.defaults.minRaiseIsAlwaysbb"));
        minBuy = config.getDouble("options.poker.defaults.minBuy", config.getDefaults().getDouble("options.poker.defaults.minBuy"));
        maxBuy = config.getDouble("options.poker.defaults.maxBuy", config.getDefaults().getDouble("options.poker.defaults.maxBuy"));
        sb = config.getDouble("options.poker.defaults.sb", config.getDefaults().getDouble("options.poker.defaults.sb"));
        bb = config.getDouble("options.poker.defaults.bb", config.getDefaults().getDouble("options.poker.defaults.bb"));
        ante = config.getDouble("options.poker.defaults.ante", config.getDefaults().getDouble("options.poker.defaults.ante"));
        dynamicFrequency = config.getInt("options.poker.defaults.dynamicFrequency", config.getDefaults().getInt("options.poker.defaults.dynamicFrequency"));
        rake = config.getDouble("options.poker.defaults.rake", config.getDefaults().getDouble("options.poker.defaults.rake"));
        minRaise = config.getDouble("options.poker.defaults.minRaise", config.getDefaults().getDouble("options.poker.defaults.minRaise"));

        // Blackjack
        allowDoubleDown = config.getBoolean("options.blackjack.defaults.allowDoubleDown", config.getDefaults().getBoolean("options.blackjack.defaults.allowDoubleDown"));
        serverDealer = config.getBoolean("options.blackjack.defaults.serverDealer", config.getDefaults().getBoolean("options.blackjack.defaults.serverDealer"));
        minBet = config.getDouble("options.blackjack.defaults.minBet", config.getDefaults().getDouble("options.blackjack.defaults.minBet"));
        amountOfDecks = config.getInt("options.blackjack.defaults.amountOfDecks", config.getDefaults().getInt("options.blackjack.defaults.amountOfDecks"));
        serverNeverDealer = config.getBoolean("options.blackjack.serverNeverDealer", config.getDefaults().getBoolean("options.blackjack.serverNeverDealer"));
    }
}
