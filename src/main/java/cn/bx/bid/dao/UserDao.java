package cn.bx.bid.dao;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.bx.bid.entity.User;

//映射user.xml中的操作
public interface UserDao {
    /**
     * 登录功能
     *
     * @param username
     * @param password
     * @return
     */
    User login(@Param("userName") String username, @Param("pass") String password);

    /**
     * 根据id获得用户
     *
     * @param id
     * @return
     */
    User get(long id);

    /**
     * 添加功能
     *
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 修改功能
     *
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 删除功能
     *
     * @param id
     * @return
     */
    int del(long id);

    /**
     * 分页查询
     *
     * @param name
     * @param start
     * @param end
     * @param pageNo
     * @param limit
     * @return
     */
    List<User> search(
            @Param("name") String name,
            @Param("start") Date start,
            @Param("end") Date end,
            @Param("pageNo") int pageNo,
            @Param("pageSize") int limit);

    /**
     * 搜索总条数
     *
     * @param name
     * @param start
     * @param end
     * @return
     */
    int searchTotal(
            @Param("name") String name,
            @Param("start") Date start,
            @Param("end") Date end);

    /**
     *检查用户名
     *
     * @param id
     * @param userName
     * @return
     */
    int checkUserNameRepeat(@Param("id") Long id, @Param("userName") String userName);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int batchDel(@Param("ids") long[] ids);
}
