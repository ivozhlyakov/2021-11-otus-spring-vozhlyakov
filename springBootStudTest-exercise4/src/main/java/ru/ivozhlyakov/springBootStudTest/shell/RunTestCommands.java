package ru.ivozhlyakov.springBootStudTest.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.ivozhlyakov.springBootStudTest.service.QuestionService;

@ShellComponent
public class RunTestCommands {

    @Autowired
    private QuestionService questionService;

    private String locale;

    @ShellMethod(value = "Run test", key = {"r", "run"})
    @ShellMethodAvailability(value = "isLocaleAvailable")
    public void runTest() {
        questionService.setLocale(locale);
        questionService.test();
    }

    @ShellMethod(value = "Set locale ('ru' or 'en')", key = {"l", "locale"})
    public void setLocale(@ShellOption() String locale) {
        this.locale = locale;
    }

    private Availability isLocaleAvailable() {
        Availability availability;
        if (locale == null) {
            availability = Availability.unavailable("Set the locale first");
        } else {
            availability = Availability.available();
        }
        return availability;
    }
}
