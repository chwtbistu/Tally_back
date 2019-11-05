package Tally.Dao;

public class BillDao {
	/**
	 * 
	 * @param classify 类别
	 * @param type 收入=1 支出=2
	 * @param amount 金额
	 * @param remark 备注
	 * @return 添加成功 true 添加失败 false
	 */
	public boolean addBill(String classify, String type, float amount, String remark) {
		//TODO 调用insert into添加至数据库
		return true;
	}

}
