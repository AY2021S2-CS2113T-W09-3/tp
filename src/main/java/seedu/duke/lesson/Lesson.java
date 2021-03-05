package seedu.duke.lesson;

public class Lesson {

    private String time;
    private final LessonType lessonType;
    private TeachingStaff teachingStaff;
    private String onlineLink;

    public Lesson(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public String getTime() {
        return time;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public TeachingStaff getTeachingStaff() {
        return teachingStaff;
    }

    public String getOnlineLink() {
        return onlineLink;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTeachingStaff(TeachingStaff teachingStaff) {
        this.teachingStaff = teachingStaff;
    }

    public void setOnlineLink(String onlineLink) {
        this.onlineLink = onlineLink;
    }
}