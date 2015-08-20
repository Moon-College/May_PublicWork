package com.tz.asy.bean.result;


import com.tz.asy.http.common.CommentPagination;

/**
 * 业务员的所有评论
 * Created by Administrator on 2015/5/19.
 */
public class ResultCourierComments extends HttpBaseResult{

    private float totalAvgScore;
    private CommentPagination pagination;

    public float getTotalAvgScore() {
        return totalAvgScore;
    }

    public void setTotalAvgScore(float totalAvgScore) {
        this.totalAvgScore = totalAvgScore;
    }

    public CommentPagination getPagination() {
        return pagination;
    }

    public void setPagination(CommentPagination pagination) {
        this.pagination = pagination;
    }
}
