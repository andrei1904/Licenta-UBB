package contest.domain;

import java.io.Serializable;

public class Entry extends Entity<Integer> implements Serializable {
    int raceId;
    int participantId;

    public Entry(int raceId, int participantId) {
        this.raceId = raceId;
        this.participantId = participantId;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }
}
