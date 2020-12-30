package com.grid.salary.verify.domain.req;/**
 * @description: 描述
 * @author: zhangyaofang
 * @date: 2019/12/18 15:06
 * @version: 1.0
 */

import java.io.Serializable;

/**
 *@description: 描述
 *@author: zhangyaofang
 *@date: 2019/12/18 15:06
 *@version: 1.0
 */
public class BaseReq implements Serializable {

    private int pageNum;//第几页
    private int pageSize;//每页条数

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
