package cn.bx.bid.dao;

import cn.bx.bid.entity.Professor;
import cn.bx.bid.entity.ProfessorEnroll;
import cn.bx.bid.entity.Project;
import cn.bx.bid.entity.RandomName;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ProjectDao {
    /**
     * 模糊分页查询
     *
     * @param name
     * @param category
     * @param pageNo
     * @param limit
     * @return
     */
    List<Project> search(
            @Param("name") String name,
            @Param("category") String category,
            @Param("pageNo") int pageNo,
            @Param("pageSize") int limit);

    /**
     * 搜索查询总条数
     *
     * @param name
     * @param category
     * @return
     */
    int searchTotal(@Param("name") String name, @Param("category") String category);

    /**
     * 查找项目参与的专家
     *
     * @param pid
     * @return
     */
    List<ProfessorEnroll> searchProfessorInProjs(Long pid);

    /**
     * 项目信息
     *
     * @param id
     * @return
     */
    Project get(Long id);

    /**
     * 根据项目编号，获得能来的以及没有确定不来的专家数量
     *
     * @param id
     * @return
     */
    int getPrentProfessors(long id);

    /**
     * 根据项目编号，获得能来的专家数量
     *
     * @param id
     * @return
     */
    int getComingProfessors(long id);

    /**
     * 根据类型和查找专家，距离不能在这里做限制,同时排除掉已经选择的专家
     *
     * @param prjid
     * @param category
     * @return
     */
    List<Professor> getProfessorsForChoose(@Param("prjid") long prjid, @Param("category") String category);

    /**
     * 确定能来的专家，修改状态
     *
     * @param pfid
     * @param proid
     * @return
     */
    int profRoleProject(@Param("pfid") long pfid, @Param("proid") long proid);

    /**
     * 修改项目状态，为state
     *
     * @param projectid
     * @param state
     */
    void updateProjectState(@Param("id") long projectid, @Param("state") String state);

    int saveChoose(RandomName randomName);

    int profnotPresentProject(
            @Param("pfid") long pfid,
            @Param("projectid") long projectid,
            @Param("reason") String reason);

    int increaseNotPresentNum(long pfid);

    int settleProject(
            @Param("projid") long projid,
            @Param("dealCompany") String dealCompany,
            @Param("settlePrice") double settlePrice);

    /**
     * 添加项目
     *
     * @param project
     * @return
     */
    int addProject(Project project);

    /**
     * 删除项目
     *
     * @param id
     * @return
     */
    int delProject(long id);
}
