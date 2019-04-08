package com.hardtask.nytimesmostpopular.dataModels;

/**
 * Created by it_ah on 07/04/2019.
 */

public class DataModelResult
{
    public String titleData ;

    public String abstractData ;

    public String calendarData ;

    public String uRLImage ;

    public DataModelResult(String titleData, String abstractData, String calendarData, String uRLImage) {
        this.titleData = titleData;
        this.abstractData = abstractData;
        this.calendarData = calendarData;
        this.uRLImage = uRLImage;
    }

    public String getTitleData() {
        return titleData;
    }

    public void setTitleData(String titleData) {
        this.titleData = titleData;
    }

    public String getAbstractData() {
        return abstractData;
    }

    public void setAbstractData(String abstractData) {
        this.abstractData = abstractData;
    }

    public String getCalendarData() {
        return calendarData;
    }

    public void setCalendarData(String calendarData) {
        this.calendarData = calendarData;
    }

    public String getuRLImage() {
        return uRLImage;
    }

    public void setuRLImage(String uRLImage) {
        this.uRLImage = uRLImage;
    }
}
