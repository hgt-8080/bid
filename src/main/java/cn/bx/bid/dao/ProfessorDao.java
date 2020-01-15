package cn.bx.bid.dao;

import cn.bx.bid.entity.Professor;
import cn.bx.bid.entity.Project;
import cn.bx.bid.entity.WorkHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProfessorDao {
    /**
     * 分页查询
     *
     * @param professorName
     * @param mobliePhone
     * @param identityNumber
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Professor> search(
            @Param("professorName") String professorName,
            @Param("mobliePhone") String mobliePhone,
            @Param("identityNumber") String identityNumber,
            @Param("pageNo") int pageNo,
            @Param("pageSize") int pageSize);

    /**
     * 搜索总条数
     *
     * @param professorName
     * @param mobliePhone
     * @param identityNumber
     * @return
     */
    int searchTotal(
            @Param("professorName") String professorName,
            @Param("mobliePhone") String mobliePhone,
            @Param("identityNumber") String identityNumber);

    /**
     * 根据id获得专家
     *
     * @param id
     * @return
     */
    Professor get(long id);

    /**
     * 添加专家
     *
     * @param professor
     * @return
     */
    int addProfessor(Professor professor);

    /**
     * 修改专家
     *
     * @param professor
     * @return
     */
    int updateProfessor(Professor professor);

    /**
     * 删除专家
     *
     * @param id
     * @return
     */
    int delProfessor(long id);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int batchDel(@Param("ids") long[] ids);

    /**
     * 删除指定专家的工作记录，不单独设置historyDao
     *
     * @param id    专家号
     * @param hisid 历史记录号
     * @return
     */
    int delHis(@Param("pid") long id, @Param("hisid") long hisid);

    /**
     * 添加工作历史
     *
     * @param workHistory
     * @return
     */
    int addHistory(WorkHistory workHistory);

    /**
     * 根据id获得指定工作记录
     *
     * @param id
     * @return
     */
    WorkHistory getWorkHistory(long id);

    /**
     * 修改工作记录
     *
     * @param workHistory
     * @return
     */
    int updateHistory(WorkHistory workHistory);

    /**
     * 根据专家号获得项目信息
     *
     * @param id
     * @return
     */
    List<Project> findProjs(long id);
}
