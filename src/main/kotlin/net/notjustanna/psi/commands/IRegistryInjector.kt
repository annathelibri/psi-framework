package net.notjustanna.psi.commands

import net.notjustanna.psi.commands.manager.CommandRegistry

interface IRegistryInjector {
    fun inject(r: CommandRegistry)
}
