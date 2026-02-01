import model.*;
import repository.SlotRepository;
import service.FestivalService;
import utils.SortingUtils;
import java.util.List;
import java.util.ArrayList;


import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        SlotRepository repo = new SlotRepository();
        FestivalService service = new FestivalService(repo);

        Band band = new Band(1, "Echo Pulse", "USA", "Rock");
        Stage stage = new Stage(1, "Main Stage", 5000);

        Slot slot = new Slot(
                band,
                stage,
                LocalDateTime.of(2026,7,1,18,0),
                LocalDateTime.of(2026,7,1,19,0)
        );

        try {
            repo.bandOnStage();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


        try {
            // Получаем все бэнды, которые есть в базе через сервис
            List<Band> bands = service.getAllBands(); // метод, который ты должен реализовать

            if (bands.isEmpty()) {
                System.out.println("No bands found in database!");
                return;
            }

            // Сортируем бэнды по имени
            bands = SortingUtils.sort(
                    bands,
                    (b1, b2) -> b1.getName().compareTo(b2.getName())
            );

            System.out.println("Sorted bands:");
            for (Band b : bands) {
                System.out.println(b.getSummary());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
