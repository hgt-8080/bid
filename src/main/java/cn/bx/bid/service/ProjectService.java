package cn.bx.bid.service;

import cn.bx.bid.dao.ProjectDao;
import cn.bx.bid.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ProjectService {
    @Resource
    private ProjectDao projectDao;

    public Page<Project> paging(int pageNo, int limit, String name, String category) {
        Page<Project> page = new Page<Project>();
        List<Project> list = projectDao.search(name, category,pageNo, limit);
        int sum = projectDao.searchTotal(name, category);
        page.init(sum,limit,pageNo);
        page.setData(list);
        return page;
    }

    public Page<ProfessorEnroll> searchProfessorInProjs(Long pid) {
        Page<ProfessorEnroll> page = new Page<ProfessorEnroll>();
        List<ProfessorEnroll> list = projectDao.searchProfessorInProjs(pid);
        page.setData(list);
        return page;
    }

    public Project get(Long id) {
        return projectDao.get(id);
    }

    public int getPrentProfessors(long id) {
        return projectDao.getPrentProfessors(id);
    }

    //根据类型和查找专家，距离不能在这里做限制,同时排除掉已经选择的专家
    public List<Professor> getProfessorsForChoose(long prjid, String category) {
        return projectDao.getProfessorsForChoose(prjid, category);
    }

    //保存选择记录，并发短信
    public int saveChooseResult(Project p, long[] ids) {
        for (long id : ids) {
            RandomName r = new RandomName();
            r.setProjectid(p.getId());
            r.setProfessorid(id);
            r.setBePresent("不确定");
            r.setCreatedate(new Date());
            r.setIsNotice("是");
            r.setMsg("邀请您参加于" + p.getOpenDate() + " ，在" + p.getAddress() + "举行的" + p.getProjectName() + " 招标评标会议，请准时参加，不能参加请及时告知，联系人：" + p.getLinkMan() + ",电话" + p.getMobliePhone());
            r.setStatus("否");
            projectDao.saveChoose(r);
            //Client.sendMsm("p.getMobilePhone",r.getMsg());
        }

        return ids.length;
    }
    //都加上判断是否要修改状态的判断

    /**
     * 确定能来
     *
     * @param profid    专家号
     * @param projectid 项目号
     * @return
     */
    public int profRoleProject(long profid, long projectid) {
        int r = projectDao.profRoleProject(profid, projectid);
        Project proj = projectDao.get(projectid);
        //找到能来的人数
        int cancome = projectDao.getComingProfessors(projectid);
        if (cancome >= proj.getProfessorNum()) {
            //人到齐了，修改项目状态为4，抽签完成
            projectDao.updateProjectState(projectid, "4");
        } else {
            //补抽中
            projectDao.updateProjectState(projectid, "3");
        }
        return r;
    }

    /**
     * 缺席操作
     *
     * @param pfid
     * @param projectid
     * @param reason
     * @return
     */
    @Transactional(readOnly = false)
    public int profnotPresentProject(long pfid, long projectid, String reason) {
        int r = projectDao.profnotPresentProject(pfid, projectid, reason);
        //记录专家未到次数
        projectDao.increaseNotPresentNum(pfid);
        Project proj = projectDao.get(projectid);
        //找到能来的人数
        int cancome = projectDao.getComingProfessors(projectid);
        if (cancome >= proj.getProfessorNum()) {
            //人到齐了，修改项目状态为4，抽签完成
            projectDao.updateProjectState(projectid, "4");
        } else {
            //补抽中
            projectDao.updateProjectState(projectid, "3");
        }
        return r;
    }

    //设置中标企业信息，并修改项目状态为5,
    public int settleProject(long projid, String dealCompany, double settlePrice) {
        int r = projectDao.settleProject(projid, dealCompany, settlePrice);
        return r;
    }

    /**
     * 添加项目
     *
     * @param project
     * @return
     */
   public int addProject(Project project){
        return projectDao.addProject(project);
   }

    /**
     * 删除项目
     *
     * @param id
     * @return
     */
   public int delProject(long id){
       return projectDao.delProject(id);
   }
}
