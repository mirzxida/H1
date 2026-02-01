package service;

import exceptions.InvalidSlotException;
import exceptions.TimeCollisionException;
import model.Slot;
import model.Band;
import repository.SlotRepository;
import java.util.List;
import java.util.ArrayList;

public class FestivalService {
    private final SlotRepository repo;

    public FestivalService(SlotRepository slotRepo) {
        this.repo = slotRepo;
    }

    public void scheduleSlot(Slot slot)
            throws Exception {

        if (!slot.getBand().isValid())
            throw new InvalidSlotException("Invalid band");

        if (repo.hasTimeCollision(
                slot.getStage().getId(),
                slot.getStart(),
                slot.getEnd())) {

            throw new TimeCollisionException(
                    "Stage time collision");
        }

        System.out.println("Slot scheduled successfully");
    }

    public String evaluateStageRisk(int capacity, int slotCount) {
        if (capacity >= 5000 && slotCount > 3)
            return "HIGH RISK";
        return "NORMAL";
    }

    public List<Band> getAllBands() {
        return repo.findAllBands(); // предполагается, что такой метод есть в репозитории
    }
}
