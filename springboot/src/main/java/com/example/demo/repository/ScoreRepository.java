package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Long>{
	
	
		// 根據 name 來取得 Score
		Score getByName(String name);
	    
	    //chinese分數在Ｘ～Ｙ之間
	    // Where chinese >= ? And chinese <= ?
	    List<Score> getByChineseGreaterThanEqualAndChineseLessThanEqual(Integer chineseBegin, Integer chineseEnd);
	    
	    // Where birth between ?(含) and ?(含)
	    List<Score> getByChineseBetween(Integer chineseBegin, Integer chineseEnd);
	    
	    // 查詢 id 值最大的 Score
	    @Query("SELECT s FROM Score s WHERE s.id = (SELECT MAX(s2.id) FROM Score s2)")
	    Score getMaxIdUser();
	   
	    //查詢筆數
	    @Query(value = "SELECT count(id) FROM Score", nativeQuery = true)
	    long getTotalCount();
	    
		List<Score> findByOrderByChineseAsc();
		List<Score> findByOrderByChineseDesc();
		List<Score> findByOrderByEnglishAsc();
		List<Score> findByOrderByEnglishDesc();
		List<Score> findByOrderByMathAsc();
		List<Score> findByOrderByMathDesc();
		List<Score> findByOrderBySocietyAsc();
		List<Score> findByOrderBySocietyDesc();
		List<Score> findByOrderByScienceAsc();
		List<Score> findByOrderByScienceDesc();
		
		
	

}
