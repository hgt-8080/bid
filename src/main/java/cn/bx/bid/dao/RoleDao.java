package cn.bx.bid.dao;


import cn.bx.bid.entity.Userrole;
import java.util.List;

public interface RoleDao {
	/**
	 * 获得所有
	 *
	 * @return
	 */
	List<Userrole> findALL();
}
