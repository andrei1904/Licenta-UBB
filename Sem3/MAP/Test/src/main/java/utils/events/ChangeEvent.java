package utils.events;


import domain.Entity;

public class ChangeEvent<X> implements Event {
    private ChangeEventType type;
    Entity<X> data, oldData;

    public ChangeEvent(ChangeEventType type, Entity<X> data) {
        this.type = type;
        this.data = data;
    }

    public ChangeEvent(ChangeEventType type, Entity<X> data, Entity<X> oldData) {
        this.type = type;
        this.data = data;
        this.oldData = oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Entity<X> getData() {
        return data;
    }

    public Entity<X> getOldData() {
        return oldData;
    }
}