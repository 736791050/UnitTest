package com.example.unit.app.net;

import java.util.List;

/**
 * @author lisen
 * @since 09-05-2018
 */

public class ArticleResponse {
    public int errorCode;
    public String errorMsg;
    public Data data;

    public class Data{
        public int curPage;
        public List<Article> datas;
        public int offset;
        public boolean over;
        public int pageCount;
        public int size;
        public int total;
    }
}
