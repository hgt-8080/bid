package cn.bx.bid.entity;

import java.io.Serializable;
import java.util.*;

public class Page<T extends Serializable> {
    private int code = 0;
    private Integer pageNum;
    private Integer limit;
    private Integer count;
    private Integer totalPage;
    private List<T> data = new ArrayList<>();
    private Integer rowNum;

    /**
     * 初始化page工具类
     *
     * @param count
     * @param limit
     * @param pageNum
     */
    public void init(Integer count, Integer limit, Integer pageNum) {
        setCount(count);
        setLimit(limit);
        setTotalPage();
        setPageNum(pageNum);
        setRowNum();
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum() {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        this.rowNum = (pageNum - 1) * limit;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (totalPage != null && pageNum >= totalPage) {
            pageNum = totalPage;
        }
        this.pageNum = pageNum;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        if (limit == null) {
            throw new NullPointerException("页面条数不能为null");
        }
        if (limit < 0) {
            throw new RuntimeException("页面条数不能为负数");
        }
        this.limit = limit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        if (count == null) {
            throw new NullPointerException("总条数不能为null");
        }
        if (count < 0) {
            throw new RuntimeException("总条数不能为负数");
        }
        this.count = count;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage() {
        if (count == 0) {
            this.totalPage = 0;
            return;
        }
        if (count % limit != 0) {
            this.totalPage = count / limit + 1;
        } else {
            this.totalPage = count / limit;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "code=" + code +
                ", pageNum=" + pageNum +
                ", limit=" + limit +
                ", count=" + count +
                ", totalPage=" + totalPage +
                ", rowNum=" + rowNum +
                ", data=" + data +
                '}';
    }
}