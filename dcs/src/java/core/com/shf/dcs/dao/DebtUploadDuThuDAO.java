package com.shf.dcs.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shf.dcs.model.DebtUploadCusLd;
import com.shf.dcs.model.DebtUploadDuThu;

public interface DebtUploadDuThuDAO extends JpaRepository<DebtUploadDuThu, BigDecimal>{

	@Query(nativeQuery=true,value="select * from DEBT_UPLOAD_DU_THU where UPLOAD_HDR_ID =:uploadHdrId AND ERROR_CODE IN (99)")
	List<DebtUploadCusLd> getByUploadHdrIdFail(@Param("uploadHdrId") BigDecimal uploadHdrId);
}
