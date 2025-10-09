import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<EBook> ebookList = new ArrayList<>();

        EBook test = new EBook("Война и мир", "Лев Толстой", "Классика", 12500);

        ebookList.add(test);
        ebookList.add(new EBook("1984", "Джордж Оруэлл", "Антиутопия", 8900));
        ebookList.add(new EBook("Преступление и наказание", "Фёдор Достоевский", "Классика", 7600));
        ebookList.add(new EBook("Мастер и Маргарита", "Михаил Булгаков", "Фэнтези", 15400));
        ebookList.add(new EBook("Гарри Поттер и философский камень", "Джоан Роулинг", "Фэнтези", 28700));
        ebookList.add(new EBook("Три товарища", "Эрих Мария Ремарк", "Роман", 5400));
        ebookList.add(new EBook("Маленький принц", "Антуан де Сент-Экзюпери", "Философия", 21300));
        ebookList.add(new EBook("Алхимик", "Пауло Коэльо", "Притча", 18200));
        ebookList.add(new EBook("Шерлок Холмс", "Артур Конан Дойл", "Детектив", 9800));
        ebookList.add(new EBook("Властелин колец", "Джон Р. Р. Толкин", "Фэнтези", 19600));
        ebookList.add(new EBook("Гордость и предубеждение", "Джейн Остин", "Роман", 6700));
        ebookList.add(new EBook("Убить пересмешника", "Харпер Ли", "Драма", 4300));
        ebookList.add(new EBook("Тёмные начала", "Филип Пулман", "Фэнтези", 5200));
        ebookList.add(new EBook("Код да Винчи", "Дэн Браун", "Триллер", 24100));
        ebookList.add(new EBook("Портрет Дориана Грея", "Оскар Уайльд", "Классика", 7100));

        printEBook(ebookList);

        ebookList.remove(test);
        ebookList.remove(4);
        ebookList.removeLast();

        printEBook(ebookList);

        ebookList.sort(Comparator.comparing(EBook::getDownloads));

        printEBook(ebookList);

        List<EBook> fantasyBooks = ebookList.stream()
                .filter(eBook -> eBook.getGenre().equals("Фэнтези"))
                .collect(Collectors.toList());

        printEBook(fantasyBooks);

        List<EBook> pauloBooks = ebookList.stream()
                .filter(eBook -> eBook.getAuthor().equals("Пауло Коэльо"))
                .collect(Collectors.toList());

        printEBook(pauloBooks);

        ebookList.sort(Comparator.comparing(EBook::getDownloads));
        List<EBook> topThree = ebookList.subList(Math.max(0, ebookList.size() - 3), ebookList.size());

        printEBook(topThree);
    }

    private static void printEBook(List<EBook> list) {
        for (EBook eBook : list) {
            System.out.println(eBook + "\n");
        }

        System.out.println("--------------------\n");
    }
}