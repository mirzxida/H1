package controller;

import model.Slot;
import service.FestivalService;

public class SlotController {
    private final FestivalService service;

    public SlotController(FestivalService service) {
        this.service = service;
    }

    public void addSlot(Slot slot) throws Exception {
        service.scheduleSlot(slot);
    }
}

