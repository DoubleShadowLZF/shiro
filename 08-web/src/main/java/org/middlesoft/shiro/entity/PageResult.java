package org.middlesoft.shiro.entity;

import lombok.Data;

import java.util.List;

@Data
public class PageResult extends UiResult{

    private InfoBean info;

    private static PageResult uiResult = new PageResult();

    @Data
    public static class InfoBean {
        /**
         * totalPage : 1
         * list : []
         * totalCount : 5
         */

        private int totalPage;
        private int totalCount;
        private List<?> list;
    }



    public static UiResult ok(int totalPage,int totalCount,List list){

        InfoBean info = new InfoBean();
        info.setTotalPage(totalPage);
        info.setTotalCount(totalCount);
        info.setList(list);
        uiResult.setInfo(info);
        return uiResult;
    }
}
