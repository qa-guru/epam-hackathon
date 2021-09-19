# Test Automation Hackathon (QA.GURU Team) / Team 23


___

## Technologies used:

| Java | Gradle | Junit5 | Cucumber | Selenium | GitHub | Jenkins | IntelliJ IDEA |
|:------:|:----:|:----:|:------:|:------:|:--------:|:--------:|:--------:|
| <img src="https://starchenkov.pro/qa-guru/img/skills/Java.svg" width="40" height="40"> | <img src="https://starchenkov.pro/qa-guru/img/skills/Gradle.svg" width="40" height="40"> | <img src="https://starchenkov.pro/qa-guru/img/skills/JUnit5.svg" width="40" height="40"> | <img src="https://i.ibb.co/VMsd1Dn/image.png" width="40" height="40"> | <img src="https://starchenkov.pro/qa-guru/img/skills/Selenium.svg" width="40" height="40"> | <img src="https://starchenkov.pro/qa-guru/img/skills/Github.svg" width="40" height="40"> |<img src="https://starchenkov.pro/qa-guru/img/skills/Jenkins.svg" width="40" height="40"> |<img src="https://starchenkov.pro/qa-guru/img/skills/Intelij_IDEA.svg" width="40" height="40"> |

* Сборка в JENKINS http://178.250.156.47:8081/job/Project_23

    Запуск
` gradle run `

* Для локального запуска тестов необходимо вызвать метод main из CucumberTestRunner.class

* После прохождения тестов отчет будет в build.directory/report.pdf, за формирование которого отвечает CucumberReporter.class

![Report_example](src/main/resources/examples/report_example.png)

* Настройки кофигурации находсятся в классе Config.class

* Обратить внимание, что для наибольшей стабильности тестов следует выставить настройку 

    `public boolean headless = false;`
