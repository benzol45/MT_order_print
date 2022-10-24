package com.example.mt_orderandprintworkspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MtOrderAndPrintWorkspaceApplication {

    public static void main(String[] args) {
        //TODO механизм скрытия заказов и пачек кодов. Поле булево hide. На страницах кнопка показа всех/только активных

        //TODO https://forum.mista.ru/topic.php?id=863195 как получать с криптохвостом, последнее сообщение

        //TODO кодирование пачек и нанесение этого как штрихкода на первой этикетке

        //TODO новые, ни разу не напечатанные пачки помечать в таблице зелёным т.е. хранить признак "printed"

        //TODO !!!!!! как передавать данные о полученных пачках ? получать будут разные сервисы. Кафка ? База и иногда проверки ?

        SpringApplication.run(MtOrderAndPrintWorkspaceApplication.class, args);
    }

}
