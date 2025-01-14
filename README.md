## Отчет по лабораторной работе № 3

#### № группы: `ПМ-2401`

#### Выполнил: `Голубев Павел Сергеевич`

#### Вариант: `7`

### Cодержание:

- [Постановка задачи](#1-постановка-задачи)
-  [Выбор структуры данных](#2-выбор-структуры-данных)
- [Описание классов](#3-описание-классов)
- [Программа](#4-программа)
- [Анализ правильности решения](#5-анализ-правильности-решения)

  ### 1. Постановка задачи

>Разработать программу для работы с «букварем», представляющим буквы алфавита и
их количество. Реализовать функционал для добавления букв, подсчёта их количества,
проверки возможности составления слов и выполнения операций с буквами

1. Создание объекта с именем и количеством букв
Создание объекта, который хранит имя пользователя и массив букв с их количеством. Вывод данных может быть в одном из форматов:
   - «aaaaa c kkk» (без пробелов между буквами);
   - «a-5; c-1; k-3» (в формате количества). Реализована возможность выбора формата вывода.

2. Добавление буквы
Увеличение количества определённой буквы в массиве.

3. Подсчёт количества определённой буквы
Возвращает количество указанной буквы в массиве.

4. Подсчёт общего количества букв
Вычисляет общее количество всех букв в массиве.

5. Сборка слова из букв
Позволяет собрать слово, удаляя использованные буквы из массива. Выводит составленное слово, используя доступные буквы. Пример: при наличии букв «с»,
«л», «н» и попытке составить слово «слон», результатом будет «слн».

6. Проверка возможности составления слова
Определяет, возможно ли составить заданное слово из доступных букв, не изменяя
массив.

7. Сборка слова без изменения массива
Выводит результат сборки слова, используя доступные буквы, но не удаляет их
из массива.

8. Прочитать слово, если возможно
Удаляет буквы из массива и возвращает результат только в случае, если слово
можно полностью составить.

9. Подсчёт количества возможных слов
Вычисляет, сколько раз можно составить указанное слово из доступных букв.

10. Добавление буквы и контроль изменений
Чёткое указание, какие функции изменяют массив (например, удаляют буквы), а
какие работают с копиями данных, не влияя на исходный массив.

### 2. Выбор структуры данных

На вход программа получает имя пользовтеля `userName`, создается объект `name` класса `Primer`.
Объект  `name` хранит имя пользователя и массив букв с их количеством.

### 3. Описание классов

Класс `Primer`  обеспечивает удобный интерфейс для работы с буквами, связанными с именем пользователя.
Инициализированы 2 поля:

```java
    public String userName; //имя пользователя
    public char [][] letterCount; // массив букв с их количеством
```

Осущтвлены методы для работы с букварем: 
   
   1. Вывод в формате ааа сс `outputFormat1`
   
   2. Вывод в формате количества а-3; с-2 `outputFormat2`
   
   3. Добавлеие буквы `addLetter`
   
   4. Подсчет количества буквы `countLetter`
   
   5. Подсчет общего количество буквы `countLetters`
  
   6. Сборка слова из букв, меняя массив `tryCollectWord`
   
   7. Проверка возможности составления слова `checkCollectWord`
   
   8. Сборка слова без изменения массива `tryCollectWordNoRemoving`
   
   9. Прочитать слово, если возможно `collectWord`
   
   10. Подсчёт количества возможных слов `countCollectWord`
   
### 4. Программа

```java
public class Primer {
    public String userName; //имя пользователя
    public char [][] letterCount; // массив букв с их количеством

    // Наполняем массив символами
    private char [][] getLetterCount(String name) {
        char [][] res = new char [26][2]; // создаем двумерный массив
        for (int i = 0; i < 26; i ++) {
            res[i][0] = (char) (i + 'a'); // в первой ячейке храним букву алфавита
            res [i][1] = '0';             // во второй кол-во раз которое она встречается
        }
        for (int i = 0; i < name.length(); i++) {
            res [(int) name.charAt(i) - 'a'][1]++; // заполняем вторую ячейку
        }
        return res;
    }

    //конструктор
    public Primer (String userName) {
        this.userName = userName;
        letterCount = getLetterCount(userName);
    }

    // вывод в формате ааа сс
    public String outputFormat1() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (letterCount[i][1] > '0') // добавляем к строке нужное кол-во раз букву (если она есть)
                res.append(String.valueOf(letterCount[i][0]).repeat(letterCount[i][1]-'0')).append(' ');
        }
        return res.toString().trim(); // удаляем последний символ (пробел)
    }

    // вывод в формате а-3; с-2
    public String outputFormat2() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (letterCount[i][1] > '0') // добавляем к строке букву и пишем сколько раз она встречается
                res.append(String.format("%c-%d; ",letterCount[i][0],(int)(letterCount[i][1]) - '0'));
        }
        return res.toString().substring(0,res.length()-2); // удаляем последние 2 символа
    }

    // добавляем букву
    public void addLetter (char letter) {
        letterCount[letter-'a'][1]++;
    }

    // считаем кол-во определенной буквы
    public int countLetter (char letter) {
        return letterCount[letter-'a'][1] - '0';
    }

    // считаем общее кол-во букв
    public int countLetters() {
        int count = 0;
        for (int i = 0; i < letterCount.length; i++)
            count+=letterCount[i][1] - '0';
        return count;
    }

    // собираем слово изменяя букварь
    public String tryCollectWord (String word) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (letterCount[word.charAt(i)-'a'][1] > '0') { // если буква есть
                res.append(word.charAt(i)); // то добавляем в строку
                letterCount[word.charAt(i)-'a'][1]--; // и уменьшаем кол-во буквы
            }
        }
        return res.toString();
    }

    // проверяем можно ли собрать слово из букваря
    public String checkCollectWord (String word) {
        String res = "Можно собрать";
        int [] letterCount = new int [26]; // массив будет хранить кол-во букв слова
        for (int i = 0; i < word.length(); i++) {
            // если в слове буква встречается меньше чем есть в букваре
            if (letterCount[word.charAt(i)-'a'] < this.letterCount[word.charAt(i)-'a'][1] - '0') {
                letterCount[word.charAt(i)-'a']++; // запоминаем что буква встречалась
            }
            // если в букваре не хватает букв, значит слово собрать нельзя
            else {
                res = "Нельзя собрать";
                break;
            }
        }
        return res;
    }

    // сотавляем слово не изменяя букварь
    public String tryCollectWordNoRemoving (String word) {
        StringBuilder res = new StringBuilder();
        int [] letterCount = new int [26]; // массив будет хранить кол-во букв слова
        for (int i = 0; i < word.length(); i++) {
            // если в слове буква встречается меньше чем есть в букваре
            if (letterCount[word.charAt(i)-'a'] < this.letterCount[word.charAt(i)-'a'][1] - '0') {
                res.append(word.charAt(i)); // то добваляем букву в строку
                letterCount[word.charAt(i)-'a']++; // запоминаем что буква встречалась
            }
        }
        return res.toString();
    }

    //Собираем слово изменяя массив, только если его можно собрать полностью
    public String collectWord(String word) {
        String res = ""; // выводим если слово собрать нельзя
        if (checkCollectWord(word).equals("Можно собрать")) // проверяем можно ли собрать слово
            res = tryCollectWord(word); // собираем слово изменяя массив
        return res;
    }

    //Выводим сколько раз можно собрать слово
    public int countCollectWord(String word) {
        int res = 0;
        boolean check = true;
        int [] letterCount = new int [26]; // массив будет хранить кол-во букв слова
        while (true) {
            for (int i = 0; i < word.length(); i++) {
            // если в слове буква встречается меньше чем есть в букваре
                if (letterCount[word.charAt(i)-'a'] < this.letterCount[word.charAt(i)-'a'][1] - '0')
                    letterCount[word.charAt(i)-'a']++; // запоминаем что буква встречалась
                else
                    check = false;
            }
            if (!check)
                break;
            res++;
        }
        return res;
    }
}
```

### 5. Анализ правильности решения

Для анализа правильности решения написан тестовый класс:

```java
public class test {
    public static void main (String[] args) {
        String userName = "aaaaabbbmynameww";
        Primer name = new Primer(userName); // создаем объект name, с именем и количеством букв

        System.out.print("Вывод в 1 формате: " + name.outputFormat1()); // вывод в формате ааа сс

        System.out.print("\nВывод во 2 формате: " + name.outputFormat2()); // вывод в формате а-3; с-2

        name.addLetter('k'); // увеличиваем кол-во буквы
        System.out.print("\nПосле добавления буквы: " + name.outputFormat2()); // смотрим на изменения массива

        System.out.print("\nКоличество буквы: " + name.countLetter('m')); // выводим кол-во буквы в массива
        System.out.print("\nКоличество всех букв: " + name.countLetters()); // выводим кол-во всех букв в массиве

        // собираем слово, изменяя массив
        System.out.print("\nСобираем слово изменяя букварь: " + name.tryCollectWord("baba"));
        // следим за изменением массива
        System.out.print("\nСледим за изменениями: " + name.outputFormat2());

        // собираем слово не изменяя массив
        System.out.print("\nСобираем слово не изменяя букварь: " + name.tryCollectWordNoRemoving("laba"));
        // смотрим чтобы массив не изменился
        System.out.print("\nСледим за изменениями: " + name.outputFormat2());

        // проверяем можно ли собрать слово
        System.out.print("\n" + name.checkCollectWord("manka"));

        // собираем слово, изменяя массив, при условии, что слово можно собрать полностью
        System.out.print("\nСобираем слово, если можно: " + name.collectWord("manka"));
        // следим за изменениями массива
        System.out.print("\nСледим за изменениями: " + name.outputFormat2());

        // Проверяем сколько раз можно собрать слово
        System.out.print("\nКол-во сколько можно собрать слово: " + name.countCollectWord("wa"));
        // следим за изменениями массива
        System.out.print("\nСледим за изменениями: " + name.outputFormat2());
    }
}
```
