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