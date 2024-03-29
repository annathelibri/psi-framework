package net.notjustanna.psi

import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder
import org.kodein.di.Kodein
import net.notjustanna.utils.Colors
import java.awt.Color

/**
 * A Bot Definitions file.
 *
 * Use [PsiApplication] with a instance of this interface to start a bot.
 */
interface BotDef {
    /**
     * Bot name. Can be anything.
     *
     * Used internally in webhook messages.
     *
     * **Example**: `"MyBot"`
     */
    val botName: String

    /**
     * Bot version. Can be anything.
     *
     * Used internally in webhook messages.
     *
     * **Example**: `"1.0"`
     */
    val version: String

    /**
     * Base package of your commands and executables.
     *
     * Used to narrow down the classes to scan for.
     *
     * **Example**: `"com.coderwebsite.mybot"`
     */
    val basePackage: String

    /**
     * Prefixes that your bot answers to.
     *
     * **Example**: `listOf("!", "-")`
     */
    val prefixes: List<String>

    /**
     * Splashes to be randomly shown as the bot's presence.
     *
     * Can be a empty list.
     *
     * **Example**: `listOf("New commands!", "Plays music!")`
     */
    val splashes: List<String> get() = emptyList()

    /**
     * The main command of the bot, meant to be shown at the splash.
     *
     * Can be null.
     *
     * **Example**: `"about"`
     */
    val mainCommandName: String? get() = "help"

    /**
     * Main color of the bot.
     *
     * Used to generate the [net.notjustanna.psi.commands.help.HelpEmbed] dialogs.
     *
     * **Example**: [Colors.blurple][net.notjustanna.utils.Colors.blurple]
     */
    val mainColor: Color get() = Colors.blurple

    /**
     * Options used to create the [net.dv8tion.jda.api.sharding.ShardManager] instance.
     *
     * You're supposed to at least create a [DefaultShardManagerBuilder] with your bot token.
     *
     * **Example**: `new CatnipOptions("YOUR_TOKEN_HERE")`
     */
    val builder: DefaultShardManagerBuilder

    /**
     * Custom [Kodein] module used for dependency injection.
     *
     * Can be `null`.
     *
     * This is your entry point to override
     * [the default command processor][net.notjustanna.psi.commands.manager.CommandProcessor],
     * [add a custom error handling][net.notjustanna.psi.bootstrap.ErrorHandler],
     * [override the task executor][net.notjustanna.psi.executor.service.TaskExecutorService],
     * as well as adding extra objects which can be injected on commands.
     */
    val kodeinModule: Kodein.Module? get() = null
}