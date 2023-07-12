package lop01.coordinatebook.coordinatebook.playerposition;

import java.util.Objects;

// If it was unclear then this finds the next available slot, if there are none it does nothing (false)
public class availableslot {
    public static Integer getavailableslot(String field5, String field6, String field7, String field8) {
        if        (Objects.equals(field5, "")) {
            return 1;
        } else if (Objects.equals(field6, "")) {
            return 2;
        } else if (Objects.equals(field7, "")) {
            return 3;
        } else if (Objects.equals(field8, "")) {
            return 4;
        }

        return 0;
    }
}
