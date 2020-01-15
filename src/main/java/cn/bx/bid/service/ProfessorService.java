package cn.bx.bid.service;

import cn.bx.bid.dao.ProfessorDao;
import cn.bx.bid.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProfessorService {
    @Resource
    private ProfessorDao professorDao;

    /**
     * 模糊分页查询
     *
     * @param professorName
     * @param mobliePhone
     * @param identityNumber
     * @param pageNo
     * @param limit
     * @return
     */
    public Page<Professor> paging(String professorName, String mobliePhone, String identityNumber,int pageNo, int limit) {
        Page<Professor> page = new Page<Professor>();
        List<Professor> list = professorDao.search(professorName, mobliePhone, identityNumber, pageNo, limit);
        int sum = professorDao.searchTotal(professorName, mobliePhone, identityNumber);
        page.init(sum,limit,pageNo);
        page.setData(list);
        return page;
    }

    /**
     * 根据id获得专家对象
     *
     * @param id
     * @return
     */
    public Professor get(long id) {
        return professorDao.get(id);
    }

    /**
     * 添加专家
     *
     * @param professor
     * @return
     */
    public int addProfessor(Professor professor) {
        return professorDao.addProfessor(professor);
    }

    /**
     * 修改专家
     *
     * @param professor
     * @return
     */
    public int updateProfessor(Professor professor) {
        return professorDao.updateProfessor(professor);
    }

    /**
     * 删除专家
     *
     * @param id
     * @return
     */
    public int delProfessor(long id){
        return professorDao.delProfessor(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public int batchDel(long[] ids) {
        return professorDao.batchDel(ids);
    }

    /**
     * 删除指定专家的工作记录，不单独设置historyDao
     *
     * @param id  专家id
     * @param hisid  历史记录id
     * @return
     */
    public int delHis(long id, long hisid) {
        int num = professorDao.delHis(id, hisid);
        return num;
    }

    /**
     * 添加工作历史
     *
     * @param w
     * @return
     */
    public int addHistory(WorkHistory w) {
        int num = professorDao.addHistory(w);
        return num;
    }

    /**
     * 根据工作记录id获得指定工作记录
     *
     * @param id
     * @return
     */
    public WorkHistory getWorkHistory(long id){
        WorkHistory workHistory = professorDao.getWorkHistory(id);
        return workHistory;
    }

    /**
     * 修改工作记录
     *
     * @param workHistory
     * @return
     */
    public int updateHistory(WorkHistory workHistory){
        int num = professorDao.updateHistory(workHistory);
        return num;
    }

    /**
     * 根据专家号获得项目信息
     *
     * @param profid
     * @return
     */
    public List<Project> findProjs(long profid) {
        List<Project> list = professorDao.findProjs(profid);
        return list;
    }
}
