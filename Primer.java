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