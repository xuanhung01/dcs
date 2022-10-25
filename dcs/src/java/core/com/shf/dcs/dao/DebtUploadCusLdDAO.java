package com.shf.dcs.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shf.dcs.model.DebtUploadCusLd;
import com.shf.dcs.model.DebtUploadDuThu;
import com.shf.dcs.model.DebtUploadSoThu;

public interface DebtUploadCusLdDAO extends JpaRepository<DebtUploadCusLd, BigDecimal>{

	@Query(nativeQuery=true,value="select * from DEBT_UPLOAD_CUS_LD where UPLOAD_HDR_ID =:uploadHdrId AND ERROR_CODE IN (99)")
	List<DebtUploadCusLd> getByUploadCusLdFailById(@Param("uploadHdrId") BigDecimal uploadHdrId);
	
	@Query(nativeQuery=true,value="select * from DEBT_UPLOAD_DU_THU where UPLOAD_HDR_ID =:uploadHdrId AND ERROR_CODE IN (99)")
	List<DebtUploadDuThu> getByUploadDuThuFailById(@Param("uploadHdrId") BigDecimal uploadHdrId);
	
	@Query(nativeQuery=true,value="select * from DEBT_UPLOAD_SO_THU where UPLOAD_HDR_ID =:uploadHdrId AND ERROR_CODE IN (99)")
	List<DebtUploadSoThu> getByUploadSoThuFailById(@Param("uploadHdrId") BigDecimal uploadHdrId);
}
