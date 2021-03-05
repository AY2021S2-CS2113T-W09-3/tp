package seedu.duke.lesson;

import seedu.duke.ui.UI;

import java.util.ArrayList;

public class Lesson {

    public static final String DELIMITER = ";;";
    public static final int NUMBER_OF_ARGUMENTS = 5;
    public static final String TUTORIAL = "TUTORIAL";
    public static final String LECTURE = "LECTURE";
    public static final String LAB = "LAB";
    public static final int LESSON_TYPE_INDEX = 0;
    public static final int TIME_INDEX = 1;
    public static final int ONLINE_LINK_INDEX = 2;
    public static final int TEACHING_STAFF_NAME_INDEX = 3;
    public static final int TEACHING_STAFF_EMAIL_INDEX = 4;
    private String time;
    private final LessonType lessonType;
    private TeachingStaff teachingStaff;
    private String onlineLink;


    public Lesson(String[] arguments) {
        this.lessonType = initialiseLessonType((arguments[LESSON_TYPE_INDEX].toUpperCase()).trim());
        this.time = arguments[TIME_INDEX];
        this.onlineLink = arguments[ONLINE_LINK_INDEX];
        TeachingStaff tutor = new TeachingStaff();
        tutor.name = arguments[TEACHING_STAFF_NAME_INDEX];
        tutor.email = arguments[TEACHING_STAFF_EMAIL_INDEX];
        this.teachingStaff = tutor;
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

    public LessonType initialiseLessonType(String lessonType) {
        switch (lessonType) {
        case TUTORIAL:
            return LessonType.TUTORIAL;

        case LECTURE:
            return LessonType.LECTURE;

        case LAB:
            return LessonType.LAB;

        default:
            //throw required exception if parser didn't handle
            System.out.println("Wrong name");
            return LessonType.LECTURE; //temporary
        }
    }

    /**
     * User input: "add tutorial ;; Wednesday 9 am - 10am ;; https://nus-sg.zoom.us/j/abc"
     * Parser input: "tutorial ;; Wednesday 9 am - 10am ;; https://nus-sg.zoom.us/j/abc ;;  ;; "
     * Parser will input " " and delimiters in parser input equal to five i,e
     * Tentatively discussed with Ivan(REMOVE THIS COMMENT AFTER IMPLEMENTATION)
     * This method add a new lesson to the list of lessons of a module
     *
     * @param parserInput is the input obtained from parser assumed to be in the correct format
     * @param lessonList  is the list of lessons for one module
     */
    public static void addNewLesson(String parserInput, ArrayList<Lesson> lessonList) {
        String[] arguments = getArguments(parserInput);
        Lesson newLesson = new Lesson(arguments);
        lessonList.add(newLesson);
        UI.printAddNewLesson(newLesson.lessonType);
    }

    private static String[] getArguments(String parserInput) {
        return parserInput.split(DELIMITER, NUMBER_OF_ARGUMENTS);
    }
}
