# ContactsApp
## Примечания
1. Отображаются контакты, хранящиеся на устройстве, даже те, у которых нет main phone number.
2. Для каждого контакта отображается его изображение, first name и last name, а также main phone number или информация об отсутствии main phone number.
3. Если у контакта отсутствует main phone number, то при нажатии на соответствующий элемент списка контактов звонок не происходит. Можно было исключить контакты, у которых нет main phone number, с помощью [selectionArgs](https://developer.android.com/reference/android/content/ContentResolver#query(android.net.Uri,%20java.lang.String[],%20java.lang.String,%20java.lang.String[],%20java.lang.String,%20android.os.CancellationSignal)) или добавить настройки приложения (например, с помощью [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)), предоставив пользователю возможность выбрать, отображать контакты без main phone number или не отображать.
4. Звонок осуществляется по номеру, указанному в пункте списка.
