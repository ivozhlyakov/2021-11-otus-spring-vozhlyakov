package ru.ivozhlyakov.springBootStudTest.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ru.ivozhlyakov.springBootStudTest.service.QuestionService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Класс shell ")
@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
class RunTestCommandsTest {

    @MockBean
    private QuestionService questionService;

    @Autowired
    private Shell shell;

    private static final String COMMAND_LOCALE = "locale";
    private static final String COMMAND_LOCALE_SHORT = "l";
    private static final String COMMAND_RUN = "run";
    private static final String COMMAND_RUN_SHORT = "r";

    public static final String TEST_PASSED = "Тест Проведен";
    public static final String SELECTED_LOCALE_DEFAULT ="Выбранная локаль: ru";
    public static final String SELECTED_LOCALE_EN ="Выбранная локаль: en";

    @DisplayName("Должен запуститься тест")
    @Test
    void testPassed() {
        shell.evaluate(() -> COMMAND_LOCALE);
        String res = (String) shell.evaluate(() -> COMMAND_RUN);
        assertThat(res).isEqualTo(TEST_PASSED);

        shell.evaluate(() -> COMMAND_LOCALE);
        res = (String) shell.evaluate(() -> COMMAND_RUN_SHORT);
        assertThat(res).isEqualTo(TEST_PASSED);
    }

    @DisplayName("Должен добавиться дефолтная локаль")
    @Test
    void setLocaleDefault() {
        String res = (String) shell.evaluate(() -> COMMAND_LOCALE);
        assertThat(res).isEqualTo(SELECTED_LOCALE_DEFAULT);
    }

    @DisplayName("Должен добавиться выбранная локаль")
    @Test
    void setLocaleEn() {
        String res = (String) shell.evaluate(() -> COMMAND_LOCALE_SHORT+" en");
        assertThat(res).isEqualTo(SELECTED_LOCALE_EN);
    }
}