package com.shf.dcs.dao;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shf.dcs.model.DebtUploadHdr;

public interface DebtUploadHdrDAO extends JpaRepository<DebtUploadHdr, BigDecimal>{

	@Query(nativeQuery=true,value="select * from DEBT_UPLOAD_HDR where trunc(sys_run_date) > trunc(sysdate) -1 and FILE_TYPE =:fileType order by id desc")
	public List<DebtUploadHdr> getListFileByType(@Param("fileType")String fileType);
}