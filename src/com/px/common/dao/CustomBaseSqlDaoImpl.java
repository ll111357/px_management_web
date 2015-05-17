package com.px.common.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import com.px.common.entity.PageModel;

public class CustomBaseSqlDaoImpl {


	@Autowired
	protected EntityManagerFactory emf;
	
	public List<Map> querySqlObjects(String sql,Integer currentPage,Integer rowsInPage){
		EntityManager em = this.emf.createEntityManager();
		Query qry = em.createNativeQuery(sql);
		SQLQuery s = qry.unwrap(SQLQuery.class);
		if (currentPage!=null&&rowsInPage!=null) {//判断是否有分页
			// 起始对象位置
			qry.setFirstResult(rowsInPage * (currentPage - 1));
			// 查询对象个数
			qry.setMaxResults(rowsInPage);
		}
		s.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map> resultList=new ArrayList<Map>();
		try {
			resultList=s.list();
		} catch (Exception e) {
		}finally{
			em.close();
		}
		return resultList;
	}
	
	public List<Map> querySqlObjects(String sql){
		return this.querySqlObjects(sql, null, null);
	}
	
	public int getCount(String sql){
		String sqlCount="select count(0) count_num from ("+sql+") as total";
		int countNum=((BigInteger) this.querySqlObjects(sqlCount).get(0).get("count_num")).intValue();
		return countNum;
	}
	
	/**
	 * 处理sql语句
	 * 
	 * @param _strSql
	 * @return
	 */
	public String toSql(String _strSql) {
		String strNewSql = _strSql;

		if (strNewSql != null) {
			strNewSql = regReplace("'", "''", strNewSql);
		} else {
			strNewSql = "";
		}

		return strNewSql;
	}

	private String regReplace(String strFind, String strReplacement, String strOld) {
		String strNew = strOld;
		Pattern p = null;
		Matcher m = null;
		try {
			p = Pattern.compile(strFind);
			m = p.matcher(strOld);
			strNew = m.replaceAll(strReplacement);
		} catch (Exception e) {
		}

		return strNew;
	}
	
	/**
	 * 根据hql语句查询数据
	 * @param hql
	 * @return
	 */
	public List queryForList(String hql){
		EntityManager em = this.emf.createEntityManager();
		Query query = em.createQuery(hql);
		return query.getResultList();
	}
	
	/**
	 * 根据hql语句和分页条件查找分页数据
	 * @param hql
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageModel queryForPage(String hql,int currentPage,int pageSize){
		PageModel page = new PageModel();
		List list = null;
		Integer totalCount = 0;
		Integer totalPage = 0; //总页数
		
		int firstResult = (currentPage-1)*pageSize;
		EntityManager em = this.emf.createEntityManager();
		Query query = em.createQuery(hql);
		query.setMaxResults(pageSize);
		query.setFirstResult(firstResult);
		list = query.getResultList();
		
		Query query2 = em.createQuery(hql);
		totalCount = (query2.getResultList() == null) ? 0 : query2.getResultList().size();
		
		if(totalCount % pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize + 1;
		}
		
		page.setCurrentPage(currentPage);
		page.setList(list);
		page.setPageSize(pageSize);
		page.setTotalCount(totalCount);
		
		return page;
	}
	
}
