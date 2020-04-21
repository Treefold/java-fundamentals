package University;

public class Timetable {
    // timetable[x][y] := what hour is starting at y (hour) on x (day)
    // where x = day (0:4 -> Mon:Fri) and y = starting hour (0:5 -> {8, 10, 12, 14, 16, 18})
    private Hour[][] timetable = new Hour[5][6];

    public void addHour(Hour hour) {
        if (hour == null) {return;}
        clearHour(hour.getDay(), hour.getBeginsAt());
        timetable[hour.getDay()][hour.getBeginsAt()] = hour; // accept hour
    }

    public void clearHour(int day, int beginsAt) {
        if (day > 4 || beginsAt > 5) {return;}
        if (timetable[day][beginsAt] == null) {return;} // already cleared
        Hour previousHour = timetable[day][beginsAt];
        timetable[day][beginsAt] = null; // clear hour for student
        Hour.deleteHour(previousHour);   // also delete for the teacher
    }

    @Override
    public String toString() {
        String toPrint = "Timetable: ";
        boolean hasHours = false;
        for (int weekdayCnt = 0; weekdayCnt < 5; ++weekdayCnt) {
            for (int beginsAtCnt = 0; beginsAtCnt < 6; ++beginsAtCnt) {
                if(timetable[weekdayCnt][beginsAtCnt] != null) {
                    if (!hasHours) {
                        toPrint += "[";
                        hasHours = true;
                    }
                    toPrint += "\n\t" + timetable[weekdayCnt][beginsAtCnt];
                }
            }
        }
        if (hasHours) {toPrint += "\n]\n";}
        else          {toPrint += "No hours\n";}

        return toPrint;
    }
}
