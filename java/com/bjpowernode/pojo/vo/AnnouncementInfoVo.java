package com.bjpowernode.pojo.vo;

import java.util.Date;

public class AnnouncementInfoVo {
    //公告内容
    private String neirong;
    //公告发布时间
    private Date datetime;

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "AnnouncementInfoVo{" +
                "neirong='" + neirong + '\'' +
                ", datetime=" + datetime +
                '}';
    }

    public AnnouncementInfoVo() {
    }
}
