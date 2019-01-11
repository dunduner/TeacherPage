package cn.yunhe.teacherPage.entity;

import java.util.List;

public class PageView {

	private List<Student> records;// 记录集合
	private Long totalrecordnumber;// 总记录数
	private Integer totalpagenumber;// 总页数
	private Integer currentpage;// 当前页
	private Integer maximum;// 每页显示多少条
	
/**
 * 
 * @param records  所有记录的集合
 * @param totalrecordnumber   总记录数
 * @param currentpage   当前页
 * @param maximum  每页可以显示多少条
 * @param pageSize   可以显示多少页
 */
	public PageView(List<Student> records, Long totalrecordnumber, int currentpage, int maximum){
		this.records = records;
		this.totalrecordnumber = totalrecordnumber;
		this.currentpage = currentpage;
		//总页数  = 总记录数  （除以）  每页可以显示的条数  ---如果正好整除结果就是总页数 --- 如果不可以整除  那结果就是+1后的结果
		this.totalpagenumber = (int) (totalrecordnumber % maximum == 0 ? totalrecordnumber / maximum : totalrecordnumber / maximum + 1);// 获得总页数
		this.maximum = maximum;
	}

	public List<Student> getRecords() {
		return records;
	}

	public Long getTotalrecordnumber() {
		return totalrecordnumber;
	}


	public Integer getTotalpagenumber() {
		return totalpagenumber;
	}

	public Integer getCurrentpage() {
		return currentpage;
	}


}