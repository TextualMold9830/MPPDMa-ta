package textualmold9830.mata;

import textualmold9830.plugins.Event;
import textualmold9830.plugins.Plugin;
import textualmold9830.plugins.events.HeroInitEvent;

public class MataPlugin implements Plugin {
    @Override
    public String getName() {
        return "Mă-ta";
    }

    @Override
    public void initialize() {

    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof HeroInitEvent) {
            new Mata().collect(((HeroInitEvent) event).hero);
        }
    }
}
