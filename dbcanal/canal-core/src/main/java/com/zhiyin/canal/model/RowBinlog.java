package com.zhiyin.canal.model;

import com.zhiyin.canal.processor.RowOperation;
import lombok.Data;

import java.util.Map;

@Data
public class RowBinlog extends RowOperation {

	private Long rowId;

	private  Map<String,Object> oldColDataMap; // 旧的行值
	private  Map<String,Object> newColDataMap; // 新的行值
}
