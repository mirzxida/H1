import model.*;
import service.FestivalService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        FestivalService service = new FestivalService();

        Band band = new Band(1, "Echo Pulse", "USA", "Rock");
        Stage stage = new Stage(1, "Main Stage", 5000);

        Slot slot = new Slot(
                band,
                stage,
                LocalDateTime.of(2026,7,1,18,0),
                LocalDateTime.of(2026,7,1,19,0)
        );

        try {
            service.scheduleSlot(slot);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
