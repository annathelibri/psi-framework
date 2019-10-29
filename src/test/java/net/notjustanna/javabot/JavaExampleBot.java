package net.notjustanna.javabot;

import com.mewna.catnip.CatnipOptions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kodein.di.Kodein;
import net.notjustanna.psi.BotDef;
import net.notjustanna.psi.PsiApplication;
import net.notjustanna.utils.Colors;

import java.awt.*;
import java.util.List;

public class JavaExampleBot implements BotDef {
    public static void main(String[] args) {
        new PsiApplication(new JavaExampleBot()).init();
    }

    @NotNull
    @Override
    public String getBotName() {
        return "JavaBot";
    }

    @NotNull
    @Override
    public String getVersion() {
        return "1.0";
    }

    @NotNull
    @Override
    public String getBasePackage() {
        return "net.notjustanna.javabot";
    }

    @NotNull
    @Override
    public List<String> getPrefixes() {
        return List.of("!");
    }

    @NotNull
    @Override
    public List<String> getSplashes() {
        return List.of("Java!");
    }

    @Nullable
    @Override
    public String getMainCommandName() {
        return null;
    }

    @Nullable
    @Override
    public String getConsoleWebhook() {
        return System.getenv("webhook");
    }

    @Nullable
    @Override
    public String getServersWebhook() {
        return System.getenv("webhook");
    }

    @NotNull
    @Override
    public Color getMainColor() {
        return Colors.INSTANCE.getDiscordPurple();
    }

    @NotNull
    @Override
    public CatnipOptions getCatnipOptions() {
        return new CatnipOptions(System.getenv("token"));
    }

    @Nullable
    @Override
    public Kodein.Module getKodeinModule() {
        return null;
    }
}