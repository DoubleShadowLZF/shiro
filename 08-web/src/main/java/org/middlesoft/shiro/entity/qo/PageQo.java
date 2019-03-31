package org.middlesoft.shiro.entity.qo;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OrderBy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 分頁Qo
 */
@Data
@Accessors(chain = true)
public class PageQo {

    /**
     * 當前頁碼
     */
    private Integer pageNum = 1;

    /**
     * 每頁條數
     */
    private Integer pageRow =10;

    private String name;

    /**
     * 排序方向
     */
    private String direction;

    /**
     * 分页信息
     */
    private Pageable pageable;


    /**
     * 获取分页信息
     * @return
     */
    public Pageable getPageable(){
        pageable = new PageRequest(pageNum,pageRow);
        return pageable;
    }

    /**
     * 添加排序信息
     * <p>OrderSpecifier 需要添加 querydsl-jpa 依赖</p>
     * @param direction
     * @param filesList
     * @return
     */
    public Pageable sort(Sort.Direction direction, List<String> filesList){
        filesList = filesList == null || filesList.size() == 0 ? new ArrayList<>() : filesList;
        String [] files = new String[]{};
        filesList.toArray(files);
        if(direction != null){
            pageable = new PageRequest(pageNum,pageRow, direction,files);
        }else{
            pageable = new PageRequest(pageNum,pageRow);
        }
        return this.pageable;
    }

    public Pageable sort(){
        return sort(null,null);
    }

}
