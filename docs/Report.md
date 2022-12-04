# Отчет о проведенном тестировании

Всего в проекте было разработано 17 автотестов (1 позитивный и 16 негативных) для проверки работы сервиса по покупке тура с помощью банковской карты.
План тестирования можно найти в файле Plan.md через путь ./docs/Plan.md
По результатам проведения тестирования процент успешных и неуспешных тестов составил: успешных тестов - 58.82%, неуспешных - 41.18%.
Было выявлено 7 багов:
1. При покупке тура с карты 5555 6666 7777 8888 операция успешно одобряется банком и проводится
   - Ссылка на [Issue на GitHub](https://github.com/Nightfox87/QAProject/issues/1)
2. При введении 000 в поле CVC код успешно проводится покупка тура 
   - Ссылка на [Issue на GitHub](https://github.com/Nightfox87/QAProject/issues/3)
3. При введение русских букв в поле Владелец покупка успешно одобряется банком
   - Ссылка на [Issue на GitHub](https://github.com/Nightfox87/QAProject/issues/4)
4. При введение символов в поле Владелец покупка успешно одобряется банком
   - Ссылка на [Issue на GitHub](https://github.com/Nightfox87/QAProject/issues/5)
5. При введение цифр в поле Владелец покупка успешно одобряется банком
   - Ссылка на [Issue на GitHub](https://github.com/Nightfox87/QAProject/issues/6)
6. При введении имени без фамилии в поле Владелец покупка успешно одобряется банком
   - Ссылка на [Issue на GitHub](https://github.com/Nightfox87/QAProject/issues/7)
7. При введении одной буквы в поле Владелец покупка успешно одобряется банком
   - Ссылка на [Issue на GitHub](https://github.com/Nightfox87/QAProject/issues/8)

## Отчет Allure

<img width="1130" alt="Снимок экрана 2022-12-03 в 19 54 58" src="https://user-images.githubusercontent.com/98237389/205484663-f4af124e-28f0-44a0-8e34-0c56538aee23.png">
<img width="1242" alt="Снимок экрана 2022-12-03 в 19 56 46" src="https://user-images.githubusercontent.com/98237389/205484669-ffa13b32-dade-4508-b949-1de4f6c40c80.png">
<img width="1242" alt="Снимок экрана 2022-12-03 в 19 57 05" src="https://user-images.githubusercontent.com/98237389/205484679-78660fe6-a2d4-4858-9a35-5d2b1f6af683.png">
<img width="1242" alt="Снимок экрана 2022-12-03 в 19 57 19" src="https://user-images.githubusercontent.com/98237389/205484681-bc83be48-3633-4ac9-aa70-bbc618d42d81.png">
<img width="1242" alt="Снимок экрана 2022-12-03 в 19 57 43" src="https://user-images.githubusercontent.com/98237389/205484686-f4eb4e46-d0bf-4d3d-a425-d8c7554eefdf.png">
<img width="1242" alt="Снимок экрана 2022-12-03 в 19 57 59" src="https://user-images.githubusercontent.com/98237389/205484688-2a3cfcda-a043-4b28-904c-b79a236d93b3.png">
<img width="1242" alt="Снимок экрана 2022-12-03 в 19 58 10" src="https://user-images.githubusercontent.com/98237389/205484692-771a75d5-a3d4-4eb9-b96e-cccf0fe99f55.png">
<img width="1242" alt="Снимок экрана 2022-12-03 в 19 58 19" src="https://user-images.githubusercontent.com/98237389/205484695-df8fcb56-e377-4320-9e7f-7866b5f6f475.png">
