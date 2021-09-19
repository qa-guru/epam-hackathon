# Test Automation Haсkaton (QA.GURU Team)

* Запуск на JENKINS!! gradle run
* после этого отчет будет в build.directory/report.pdf

* Для локального запуска тестов необходимо вызвать метод main из CucumberTestRunner.class

* Тесты выполнены с использованием библиотеки Cucumber

* По результатм прохождения тестов в корне проекта формируется файл "report.pdf", за формирование которого отвечает
  CucumberReporter.class

![Report_example](src/main/resources/examples/report_example.png)

*  Настройки кофигурации находсятся в классе Config.class

* Обратить внимание, что для наибольшей стабильности тестов следуется выставить настройку 

    `public boolean headless = false;`
