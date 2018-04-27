package cn.gridlife.xiaobei.bzbtoolsapp.functions.two;

/**
 * Created by BZB on 2018/4/25.
 * Project: Complex.
 * Package：cn.gridlife.xiaobei.bzbtoolsapp.functions.two.
 */

public class Type2Bean {
   private String title;
   private String content;

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
