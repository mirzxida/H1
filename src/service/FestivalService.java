package service;

import exceptions.InvalidSlotException;
import exceptions.TimeCollisionException;
import model.Slot;
import repository.SlotRepository;

public class FestivalService {
    private SlotRepository slotRepo = new SlotRepository();

    public void scheduleSlot(Slot slot)
            throws Exception {

        if (!slot.getBand().isValid())
            throw new InvalidSlotException("Invalid band");

        if (slotRepo.hasTimeCollision(
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
}
