package com.wavewave.recyclerviewspan.bean;

/**
 * @author wavewave
 * @CreateDate: 2020-03-24 11:11
 * @Description:
 * @Version: 1.0
 */
public class ContentBean {
    /**
     * 类型 1-> 标题 2->单选 3->多选
     */
    public int type;
    public String content;
    /**
     * 用于区分是哪一组的
     */
    public String isTitle;
    public boolean isSelect;

    public ContentBean(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public ContentBean(int type, String content, String isTitle) {
        this.type = type;
        this.content = content;
        this.isTitle = isTitle;
    }
}
