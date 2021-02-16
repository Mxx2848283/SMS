package com.sms.dao;

import com.sms.bean.Message;
import com.sms.bean.Pagnation;

public interface MessageDao {

	Pagnation<Message> findMessageByReceiveId(Pagnation<Message> pagnation, int receiveUserId);
	
	Message findMessageById(int id);

	int deleteMsgById(int id);

	int save(Message message);

	int updateMessageStatus(Message message);
	
}
