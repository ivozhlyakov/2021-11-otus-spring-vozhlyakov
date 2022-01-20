package ru.ivozhlyakov.library.dao;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = {"ru.ivozhlyakov.library.dao", "ru.ivozhlyakov.library.mapper"})
public class TestDaoConfiguration {
}
