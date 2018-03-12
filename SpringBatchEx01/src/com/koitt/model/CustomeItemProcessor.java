package com.koitt.model;

import org.springframework.batch.item.ItemProcessor;

public class CustomeItemProcessor implements ItemProcessor<Board, Board>{

	@Override
	public Board process(Board item) throws Exception {
		System.out.println("처리 중이에요!! : " + item);
		return item;
	}

	

}
