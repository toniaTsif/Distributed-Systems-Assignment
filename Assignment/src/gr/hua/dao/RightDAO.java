package gr.hua.dao;

import java.util.ArrayList;
import java.util.List;

import gr.hua.entity.Right;
import gr.hua.entity.RightPk;

public interface RightDAO {
	public void saveRight(Right right);
	public ArrayList<Right> getRights();
	public Right getServiceByRole(RightPk id);
	public void deleteRight(Right right);
	public void updateRight(Right right);
	public List<Right> getRightsByRole(String role);
}
