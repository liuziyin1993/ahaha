package entity;

import java.util.List;

/**
 * Created by 简单· on 2019/4/18.
 * 分页结果类
 */
public class PageResult <T>{
    private  Long  total;
    private List<T> rows;
    public  PageResult(Long  total,  List<T>  rows)  {
        super();
        this.total  =  total;
        this.rows  =  rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
